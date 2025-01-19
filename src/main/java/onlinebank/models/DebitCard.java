package onlinebank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import onlinebank.ValidIssueDate;

import java.time.LocalDate;

@Entity
@Table(name = "debitcard")
public class DebitCard   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "cardNumber", unique = true, nullable = false)
    @Pattern(regexp = "\\d{16}", message = "Card number must be exactly 16 digits without spaces.")
    private String cardNumber;

    @Column(name = "issuecarddate")
    @NotNull(message = "Issue date cannot be null")
    @ValidIssueDate
    private LocalDate issueCardDate;
    @Column(name = "cardexpirationdate")
    private LocalDate cardExpirationDate; // 3 years

    @Column(name = "cvvcode")
    @NotNull(message = "CVV code cannot be null")
    @Min(value = 100, message = "CVV code must be 3 digits.")
    @Max(value = 999, message = "CVV code must be 3 digits.")
    private int cvvCode;

    @Column(name = "cardbalance")
    @DecimalMin(value = "0.0", inclusive = true, message = "Card balance must be greater than or equal to 0.")
    @DecimalMax(value = "999999.99", inclusive = true, message = "Card balance must not exceed 999,999.99.")
    private double cardBalance;

    @Column(name = "passportnumber")
    @NotNull(message = "Passport number cannot be null")
    private int passportNumber;

    public DebitCard(String cardNumber, LocalDate issueCardDate, LocalDate cardExpirationDate, int cvvCode, double cardBalance, int passportNumber) {
        this.cardNumber = cardNumber;
        this.issueCardDate = issueCardDate;
        this.cardExpirationDate = cardExpirationDate;
        this.cvvCode = cvvCode;
        this.cardBalance = cardBalance;
        this.passportNumber = passportNumber;
    }
    public DebitCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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