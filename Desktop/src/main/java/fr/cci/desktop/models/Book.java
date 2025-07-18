package fr.cci.desktop.models;

import java.util.List;

public class Book {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private List<Loan> loans;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public List<Loan> getLoans() { return loans; }

    public void setLoans(List<Loan> loans) { this.loans = loans; }

    public String toString() {
        return title + " - " + author;
    }
}
