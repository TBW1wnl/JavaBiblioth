package fr.cci.desktop.controllers;

import fr.cci.desktop.configurations.ApiConstants;
import fr.cci.desktop.models.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.*;

public class BookController {
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField isbnField;

    private final ApiConstants apiConstants = ApiConstants.getInstance();

    @FXML
    public void handleAddBook() {
        Book newBook = new Book();
        newBook.setTitle(titleField.getText());
        newBook.setAuthor(authorField.getText());
        newBook.setIsbn(isbnField.getText());

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(newBook);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiConstants.getAPI_URL("book")))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpClient.newHttpClient().sendAsync(request, HttpResponse.BodyHandlers.discarding())
                    .thenRun(() -> {
                        javafx.application.Platform.runLater(() -> {
                            Stage stage = (Stage) titleField.getScene().getWindow();
                            stage.close();
                        });
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
