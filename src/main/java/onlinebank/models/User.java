package onlinebank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "bankuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Size(max = 64, message = "Name cannot exceed 64 characters")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @Column(name = "sex")
    private String sex;

    @Column(name = "passportnumber")
    private int passportNumber;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Mortgage> mortgageList;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<AutoLoan> autoLoanList;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<DebitCard> debitcardList;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(String name, String surname, LocalDate dateOfBirth, String sex, int passportNumber,
                List<Mortgage> mortgageList, List<AutoLoan> autoLoanList, List<DebitCard> debitcardList) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.passportNumber = passportNumber;
        this.mortgageList = mortgageList;
        this.autoLoanList = autoLoanList;
        this.debitcardList = debitcardList;
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

    public List<Mortgage> getMortgageList() {
        return mortgageList;
    }

    public void setMortgageList(List<Mortgage> mortgageList) {
        this.mortgageList = mortgageList;
    }

    public List<AutoLoan> getAutoLoanList() {
        return autoLoanList;
    }

    public void setAutoLoanList(List<AutoLoan> autoLoanList) {
        this.autoLoanList = autoLoanList;
    }

    public List<DebitCard> getDebitcardList() {
        return debitcardList;
    }

    public void setDebitcardList(List<DebitCard> debitcardList) {
        this.debitcardList = debitcardList;
    }
}