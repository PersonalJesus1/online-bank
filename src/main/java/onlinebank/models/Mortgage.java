package onlinebank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "mortgage")
public class Mortgage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "mortgagesumm")
    @NotNull(message = "Mortgage Summ cannot be null")
    @Min(value = 100, message = "Summ must be at least 100")
    private double mortgageSumm;
    @Column(name = "currentmortgagesumm")
    private double currentMortgageSumm;
    @Enumerated(EnumType.STRING)
    @Column(name = "mortgageterm")
    private MortgageTerm mortgageTerm;
    @Column(name = "passportnumber")
    @NotNull(message = "Passport number cannot be null")
    private int passportNumber;

    public Mortgage(double mortgageSumm, double currentMortgageSumm, MortgageTerm mortgageTerm, int passportNumber) {
        this.mortgageSumm = mortgageSumm;
        this.currentMortgageSumm = currentMortgageSumm;
        this.mortgageTerm = mortgageTerm;
        this.passportNumber = passportNumber;
    }

    public Mortgage() {
    }

    public Mortgage(int mortgageSumm, int currentMortgageSumm, String fifteenyears, int passportNumber) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMortgageSumm() {
        return mortgageSumm;
    }

    public void setMortgageSumm(double mortgageSumm) {
        this.mortgageSumm = mortgageSumm;
    }

    public double getCurrentMortgageSumm() {
        return currentMortgageSumm;
    }

    public void setCurrentMortgageSumm(double currentMortgageSumm) {
        this.currentMortgageSumm = currentMortgageSumm;
    }

    public MortgageTerm getMortgageTerm() {
        return mortgageTerm;
    }

    public void setMortgageTerm(MortgageTerm mortgageTerm) {
        this.mortgageTerm = mortgageTerm;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }
}
