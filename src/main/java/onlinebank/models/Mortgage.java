package onlinebank.models;

import jakarta.persistence.*;

@Entity
@Table(name = "mortgage")
public class Mortgage {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "mortgagesumm")
    private double mortgageSumm;
    @Column(name = "currentmortgagesumm")
    private double currentMortgageSumm;
    @Column(name = "mortgageterm")
    private MortgageTerm mortgageTerm;
    @Column(name = "passportnumber")
    private int passportNumber;

    public Mortgage(double mortgageSumm, double currentMortgageSumm, MortgageTerm mortgageTerm, int passportNumber) {
        this.mortgageSumm = mortgageSumm;
        this.currentMortgageSumm = currentMortgageSumm;
        this.mortgageTerm = mortgageTerm;
        this.passportNumber = passportNumber;
    }

    public Mortgage() {

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
