package fr.cci.back.payloads.requests;

import fr.cci.back.entities.Loan;
import jakarta.persistence.OneToMany;

import java.util.List;

public class SaveBookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private List<Loan> loan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Loan> getLoan() {
        return loan;
    }

    public void setLoan(List<Loan> loan) {
        this.loan = loan;
    }
}
