package onlinebank.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import onlinebank.models.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DebitCardDAO {


    @PersistenceContext
    private EntityManager entityManager;

    public List<DebitCard> getAllDebitcards() {
        String hql = "FROM DebitCard";
        TypedQuery<DebitCard> query = entityManager.createQuery(hql, DebitCard.class);
        return query.getResultList();
    }

    public DebitCard show(String cardNumber) {
        return entityManager.find(DebitCard.class, cardNumber);
    }

    @Transactional
    public void save(DebitCard debitCard) {
        entityManager.persist(debitCard);
    }

    @Transactional
    public void update(String cardNumber, DebitCard updatedDebitCard) {
        DebitCard existingDebitCard = entityManager.find(DebitCard.class, cardNumber);
        if (existingDebitCard != null) {
            entityManager.merge(updatedDebitCard);
        }
    }
    @Transactional
    public void delete(String cardNumber) {
        DebitCard debitCard = entityManager.find(DebitCard.class, cardNumber);
        if (debitCard != null) {
            entityManager.remove(debitCard);
        }
    }
}