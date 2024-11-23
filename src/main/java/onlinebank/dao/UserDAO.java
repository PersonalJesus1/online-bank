package onlinebank.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import onlinebank.models.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        String hql = "FROM User";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        return query.getResultList();
    }

    public User show(int passportNumber) {
        return entityManager.find(User.class, passportNumber);
    }

    public List<Mortgage> showMortgages(int passportNumber) {
        String hql = "FROM Mortgage m WHERE m.passportNumber = :passportNumber";
        TypedQuery<Mortgage> query = entityManager.createQuery(hql, Mortgage.class);
        query.setParameter("passportNumber", passportNumber);
        return query.getResultList();
    }

    public List<AutoLoan> showAutoLoans(int passportNumber) {
        String hql = "FROM AutoLoan a WHERE a.passportNumber = :passportNumber";
        TypedQuery<AutoLoan> query = entityManager.createQuery(hql, AutoLoan.class);
        query.setParameter("passportNumber", passportNumber);
        return query.getResultList();
    }

    @Transactional
    public void save(User user) {
            entityManager.persist(user);
    }
    @Transactional
    public void update(int passportNumber, User updatedUser) {
        User existingUser = entityManager.find(User.class, passportNumber);
        if (existingUser != null) {
            entityManager.merge(updatedUser);
        }
    }

    @Transactional
    public void delete(int passportNumber) {
        User user = entityManager.find(User.class, passportNumber);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
