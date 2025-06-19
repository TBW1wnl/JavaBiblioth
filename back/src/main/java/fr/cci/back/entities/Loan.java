package fr.cci.back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

@Entity
@Table(name = "LOANS")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loanDate;
    private String returnDate;

    @ManyToOne
    @JoinColumn(name = "idCustomer")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "idBook")
    @JsonIgnore
    private Book book;

    public Loan() {}

    public Loan(String loanDate, String returnDate) {
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", loanDate='" + loanDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", customer=" + customer +
                ", book=" + book +
                '}';
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
