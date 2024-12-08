package onlinebank.dao;

import jakarta.transaction.Transactional;
import onlinebank.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).getResultList();
        }
    }

    public User show(int passportNumber) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, passportNumber);
        }
    }

    public List<Mortgage> showMortgages(int passportNumber) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Mortgage m WHERE m.passportNumber = :passportNumber";
            return session.createQuery(hql, Mortgage.class)
                    .setParameter("passportNumber", passportNumber)
                    .getResultList();
        }
    }

    public List<AutoLoan> showAutoLoans(int passportNumber) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM AutoLoan a WHERE a.passportNumber = :passportNumber";
            return session.createQuery(hql, AutoLoan.class)
                    .setParameter("passportNumber", passportNumber)
                    .getResultList();
        }
    }

    @Transactional
    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Transactional
    public void update(int passportNumber, User updatedUser) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User existingUser = session.get(User.class, passportNumber);
            if (existingUser != null) {
                session.merge(updatedUser);
            }
            transaction.commit();
        }
    }

    @Transactional
    public void delete(int passportNumber) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, passportNumber);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        }
    }
}