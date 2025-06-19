package fr.cci.desktop.models;

public class Loan {
    private int id;
    private String loanDate;
    private String returnDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Début : " + loanDate + " | Fin : " + (returnDate != null ? returnDate : "Non rendu");
    }
}
