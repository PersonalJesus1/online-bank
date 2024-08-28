package onlinebank.models;

public class Mortgage {
    private double mortgageSum;
    private double currentMortgageSum;
    private mortgageTerm mortgageTerm;
    private int passportNumber;

    public Mortgage(double mortgageSum, double currentMortgageSum, onlinebank.models.mortgageTerm mortgageTerm, int passportNumber) {
        this.mortgageSum = mortgageSum;
        this.currentMortgageSum = currentMortgageSum;
        this.mortgageTerm = mortgageTerm;
        this.passportNumber = passportNumber;
    }

    public double getMortgageSumm() {
        return mortgageSum;
    }

    public void setMortgageSumm(double mortgageSumm) {
        this.mortgageSum = mortgageSumm;
    }

    public double getCurrentMortgageSumm() {
        return currentMortgageSum;
    }

    public void setCurrentMortgageSumm(double currentMortgageSumm) {
        this.currentMortgageSum = currentMortgageSumm;
    }

    public onlinebank.models.mortgageTerm getMortgageTerm() {
        return mortgageTerm;
    }

    public void setMortgageTerm(onlinebank.models.mortgageTerm mortgageTerm) {
        this.mortgageTerm = mortgageTerm;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }
}

