package fr.cci.desktop.controllers;

import fr.cci.desktop.configurations.ApiConstants;
import fr.cci.desktop.models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.application.Platform;

import java.net.http.*;
import java.net.URI;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController {

    private ApiConstants apiConstants = ApiConstants.getInstance();

    @FXML
    private ListView<Book> bookList;

    @FXML
    private javafx.scene.control.Button addBookButton;

    @FXML
    public void initialize() {
        loadBooks();
        addBookButton.setOnAction(event -> showAddBookModal());
    }

    private void loadBooks() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiConstants.getAPI_URL("book")))
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::updateBookList)
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
    }

    private void updateBookList(String responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Book[] books = mapper.readValue(responseBody, Book[].class);

            Platform.runLater(() -> {
                bookList.getItems().clear();
                bookList.getItems().addAll(Arrays.asList(books));
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAddBookModal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddBookDialog.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Ajouter un livre");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // modal
            stage.showAndWait();

            // recharger la liste après fermeture
            loadBooks();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
