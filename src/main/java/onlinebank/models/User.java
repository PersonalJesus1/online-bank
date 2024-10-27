package onlinebank.models;

import java.time.LocalDate;
import java.util.ArrayList;


public class User {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String sex;
    private int passportNumber;

    private ArrayList<Mortgage> mortgageList;
    private ArrayList<AutoLoan> autoLoanList;

    public User() {
    }

    public User(String name, String surname, LocalDate dateOfBirth, String sex, int passportNumber, ArrayList<Mortgage> mortgageList, ArrayList<AutoLoan> autoLoanList) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.passportNumber = passportNumber;
        this.mortgageList = mortgageList;
        this.autoLoanList = autoLoanList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public ArrayList<Mortgage> getMortgageList() {
        return mortgageList;
    }

    public void setMortgageList(ArrayList<Mortgage> mortgageList) {
        this.mortgageList = mortgageList;
    }

    public ArrayList<AutoLoan> getAutoLoanList() {
        return autoLoanList;
    }

    public void setAutoLoanList(ArrayList<AutoLoan> autoLoanList) {
        this.autoLoanList = autoLoanList;
    }
}
