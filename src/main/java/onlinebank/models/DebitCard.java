package onlinebank.models;

import java.time.LocalDate;

public class DebitCard {
    private String cardNumber;
    private LocalDate issueCardDate;
    private LocalDate cardExpirationDate; // 3 years
    private int cvvCode;
    private double cardBalance;
    private int passportNumber;

    public DebitCard(String cardNumber, LocalDate issueCardDate, LocalDate cardExpirationDate, int cvvCode, double cardBalance, int passportNumber) {
        this.cardNumber = cardNumber;
        this.issueCardDate = issueCardDate;
        this.cardExpirationDate = cardExpirationDate;
        this.cvvCode = cvvCode;
        this.cardBalance = cardBalance;
        this.passportNumber = passportNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getIssueCardDate() {
        return issueCardDate;
    }

    public void setIssueCardDate(LocalDate issueCardDate) {
        this.issueCardDate = issueCardDate;
    }

    public LocalDate getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(LocalDate cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public int getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(int cvvCode) {
        this.cvvCode = cvvCode;
    }

    public double getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(double cardBalance) {
        this.cardBalance = cardBalance;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }
}
