package onlinebank.dao;

import onlinebank.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).getResultList();
    }

    public User show(int passportNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User u WHERE u.passportNumber = :passportNumber";
        return session.createQuery(hql, User.class)
                .setParameter("passportNumber", passportNumber)
                .uniqueResult();
    }

    public List<Mortgage> showMortgages(int passportNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Mortgage m WHERE m.passportNumber = :passportNumber";
        return session.createQuery(hql, Mortgage.class)
                .setParameter("passportNumber", passportNumber)
                .getResultList();
    }

    public List<AutoLoan> showAutoLoans(int passportNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM AutoLoan a WHERE a.passportNumber = :passportNumber";
        return session.createQuery(hql, AutoLoan.class)
                .setParameter("passportNumber", passportNumber)
                .getResultList();
    }

    public boolean existsByPassportNumber(int passportNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT COUNT(*) > 0 FROM User u WHERE u.passportNumber = :passportNumber";
        return session.createQuery(hql, Boolean.class)
                .setParameter("passportNumber", passportNumber)
                .uniqueResult();
    }
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    public void update(int passportNumber, User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User u WHERE u.passportNumber = :passportNumber";
        User existingUser = session.createQuery(hql, User.class)
                .setParameter("passportNumber", passportNumber)
                .uniqueResult();
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setSurname(updatedUser.getSurname());
            existingUser.setDateOfBirth(updatedUser.getDateOfBirth());
            existingUser.setSex(updatedUser.getSex());
            session.update(existingUser);
        } else System.out.println("User not found");
    }

    public void delete(int passportNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User u WHERE u.passportNumber = :passportNumber";
        User user = session.createQuery(hql, User.class)
                .setParameter("passportNumber", passportNumber)
                .uniqueResult();
        if (user != null) {
            session.delete(user);
        }
    }
    public int countLoansAndMortgagesByPassportNumber(int passportNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = """
                SELECT (SELECT COUNT(*) FROM AutoLoan WHERE passportNumber = :passportNumber)
                     + (SELECT COUNT(*) FROM Mortgage WHERE passportNumber = :passportNumber)
                """;
        return ((Long) session.createQuery(hql)
                .setParameter("passportNumber", passportNumber)
                .uniqueResult()).intValue();
    }
}