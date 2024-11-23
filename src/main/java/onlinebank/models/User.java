package onlinebank.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "bankuser")
public class User extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;
    @Column(name = "sex")
    private String sex;
    @Column(name = "passportnumber")
    private int passportNumber;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL})
    private ArrayList<Mortgage> mortgageList;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL})
    private ArrayList<AutoLoan> autoLoanList;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL})
    private ArrayList<AutoLoan> debitcardList;


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

    public ArrayList<AutoLoan> getDebitcardList() {
        return debitcardList;
    }

    public void setDebitcardList(ArrayList<AutoLoan> debitcardList) {
        this.debitcardList = debitcardList;
    }
}
