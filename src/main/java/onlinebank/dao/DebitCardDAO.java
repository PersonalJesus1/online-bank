package onlinebank.dao;

import onlinebank.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DebitCardDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<DebitCard> getAllDebitcards() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM DebitCard", DebitCard.class).getResultList();
    }

    public DebitCard show(String cardNumber, int passportNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM DebitCard d WHERE d.cardNumber = :cardNumber AND d.passportNumber = :passportNumber";
        return session.createQuery(hql, DebitCard.class)
                .setParameter("cardNumber", cardNumber)
                .setParameter("passportNumber", passportNumber)
                .uniqueResult();
    }
    public boolean existsByPassportNumber(int passportNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT COUNT(*) > 0 FROM User u WHERE u.passportNumber = :passportNumber";
        return session.createQuery(hql, Boolean.class)
                .setParameter("passportNumber", passportNumber)
                .uniqueResult();
    }

    public  void save(DebitCard debitcard) {
        Session session = sessionFactory.getCurrentSession();
        session.save(debitcard);
    }

    public void update(String cardNumber, DebitCard updatedDebitCard) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM DebitCard d WHERE d.cardNumber = :cardNumber";
        Query<DebitCard> query = session.createQuery(hql, DebitCard.class);
        query.setParameter("cardNumber", cardNumber);
        DebitCard existingDebitCard = query.uniqueResultOptional().orElse(null);
        if (existingDebitCard != null) {
            existingDebitCard.setCardNumber(updatedDebitCard.getCardNumber());
            existingDebitCard.setCardExpirationDate(updatedDebitCard.getCardExpirationDate());
            existingDebitCard.setCardBalance(updatedDebitCard.getCardBalance());
            session.update(existingDebitCard);
        }
    }

    public void delete(String cardNumber, int passportNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM DebitCard d WHERE d.cardNumber = :cardNumber AND d.passportNumber = :passportNumber";
        DebitCard debitcard = session.createQuery(hql, DebitCard.class)
                .setParameter("cardNumber", cardNumber)
                .setParameter("passportNumber", passportNumber)
                .uniqueResult();
        if (debitcard != null) {
            session.delete(debitcard);
        }
    }

  public DebitCard findByCardNumber(String cardNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM DebitCard d WHERE d.cardNumber = :cardNumber";
        return session.createQuery(hql, DebitCard.class)
                .setParameter("cardNumber", cardNumber)
                .uniqueResult();
    }
    public boolean existsByCardNumber(String cardNumber) {
        String hql = "SELECT COUNT(*) > 0 FROM DebitCard d WHERE d.cardNumber = :cardNumber";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Boolean.class)
                .setParameter("cardNumber", cardNumber)
                .uniqueResult();
    }
}