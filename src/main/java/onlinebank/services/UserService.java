package onlinebank.services;

import onlinebank.dao.UserDAO;
import onlinebank.models.AutoLoan;
import onlinebank.models.Mortgage;
import onlinebank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }


    @Transactional
    public void save(User user) {
        userDAO.save(user);
    }

    @Transactional(readOnly = true)
    public User show(int passportNumber) {
        return userDAO.show(passportNumber);
    }

    @Transactional(readOnly = true)
    public List<Mortgage> showMortgages(int passportNumber) {
        return userDAO.showMortgages(passportNumber);
    }

    @Transactional(readOnly = true)
    public List<AutoLoan> showAutoLoans(int passportNumber) {
        return userDAO.showAutoLoans(passportNumber);
    }

    @Transactional
    public void update(int passportNumber, User updatedUser) {
        userDAO.update(passportNumber, updatedUser);
    }

    @Transactional
    public void delete(int passportNumber) {
        userDAO.delete(passportNumber);
    }
}