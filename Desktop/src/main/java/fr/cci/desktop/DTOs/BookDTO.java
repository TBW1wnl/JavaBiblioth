package fr.cci.desktop.DTOs;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String toString() {
        return title + " - " + author;
    }
}
