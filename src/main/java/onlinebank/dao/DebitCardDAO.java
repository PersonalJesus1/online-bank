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
public class DebitCardDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<DebitCard> getAllDebitcards() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM DebitCard", DebitCard.class).getResultList();
        }
    }

    public DebitCard show(String cardNumber) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(DebitCard.class, cardNumber);
        }
    }

    @Transactional
    public void save(DebitCard debitCard) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(debitCard);
            transaction.commit();
        }
    }

    @Transactional
    public void update(String cardNumber, DebitCard updatedDebitCard) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            DebitCard existingDebitCard = session.get(DebitCard.class, cardNumber);

            if (existingDebitCard != null) {
                existingDebitCard.setCardNumber(updatedDebitCard.getCardNumber());
                existingDebitCard.setCardExpirationDate(updatedDebitCard.getCardExpirationDate());
                existingDebitCard.setCardBalance(updatedDebitCard.getCardBalance());
                session.update(existingDebitCard);
            }
            transaction.commit();
        }
    }

    @Transactional
    public void delete(String cardNumber) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            DebitCard debitCard = session.get(DebitCard.class, cardNumber);

            if (debitCard != null) {
                session.delete(debitCard);
            }
            transaction.commit();
        }
    }
}
