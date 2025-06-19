package fr.cci.desktop.controllers;

import fr.cci.desktop.configurations.ApiConstants;
import fr.cci.desktop.DTOs.BookDTO;
import fr.cci.desktop.models.Book;
import fr.cci.desktop.models.Loan;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    private ListView<BookDTO> bookList;

    @FXML
    private javafx.scene.control.Button addBookButton;

    @FXML
    private ListView<Loan> loanList;

    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label isbnLabel;

    @FXML
    public void initialize() {
        loadBooks();
        addBookButton.setOnAction(event -> showAddBookModal());
        bookList.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                loadBookDetails(selected.getId());
            }
        });


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
            BookDTO[] bookDTOS = mapper.readValue(responseBody, BookDTO[].class);

            Platform.runLater(() -> {
                bookList.getItems().clear();
                bookList.getItems().addAll(Arrays.asList(bookDTOS));
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
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            loadBooks();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBookDetails(long bookId) {
        String url = apiConstants.getAPI_URL("book/" + bookId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpClient.newHttpClient().sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::updateBookDetails)
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
    }

    private void updateBookDetails(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Book bookDTO = mapper.readValue(json, Book.class);

            Platform.runLater(() -> {
                titleLabel.setText(bookDTO.getTitle());
                authorLabel.setText(bookDTO.getAuthor());
                isbnLabel.setText(bookDTO.getIsbn());

                if (bookDTO.getLoans() != null) {
                    loanList.getItems().setAll(bookDTO.getLoans());
                } else {
                    loanList.getItems().clear();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
