package fr.cci.back.payloads.responses;

import java.util.ArrayList;
import java.util.List;

public class GetBookByTitleResponseDTO {
    private String title;
    private String author;
    private String isbn;
    private List<String> loans = new ArrayList<String>();

    public GetBookByTitleResponseDTO() {}

    public GetBookByTitleResponseDTO(String title, String author, String isbn, List<String> loans) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.loans = loans;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getLoans() {
        return loans;
    }

    public void setLoans(List<String> loans) {
        this.loans = loans;
    }
}
