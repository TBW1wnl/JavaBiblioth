package fr.cci.desktop.controllers;

import fr.cci.desktop.configurations.ApiConstants;
import fr.cci.desktop.models.Book;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.application.Platform;

import java.net.http.*;
import java.net.URI;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HomeController {

    private ApiConstants apiConstants = ApiConstants.getInstance();

    @FXML
    private ListView<Book> bookList;

    @FXML
    public void initialize() {
        loadBooks();
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
}
