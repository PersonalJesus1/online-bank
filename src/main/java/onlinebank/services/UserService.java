package onlinebank.services;

import onlinebank.dao.UserDAO;
import onlinebank.models.AutoLoan;
import onlinebank.models.Mortgage;
import onlinebank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;
    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }


    public void save(User user) {
        userDAO.save(user);
    }
    public User show(int passportNumber) {
        return userDAO.show(passportNumber);
    }

    public ArrayList<Mortgage> showMortgages(int passportNumber) {
        return (ArrayList<Mortgage>) userDAO.showMortgages(passportNumber);
    }

    public ArrayList<AutoLoan> showAutoLoans(int passportNumber) {
        return (ArrayList<AutoLoan>) userDAO.showAutoLoans(passportNumber);
    }

    public void update(int passportNumber, User updatedUser) {
        userDAO.update( passportNumber, updatedUser);
    }



    public void delete(int passportNumber) {
        userDAO.delete(passportNumber);
    }
}