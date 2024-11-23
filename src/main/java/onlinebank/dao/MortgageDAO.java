package onlinebank.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import onlinebank.models.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MortgageDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Mortgage> getAllMortgages() {
        String hql = "FROM Mortgage";
        TypedQuery<Mortgage> query = entityManager.createQuery(hql, Mortgage.class);
        return query.getResultList();
    }

    public Mortgage show(int passportNumber, double mortgageSumm) {
        String hql = "FROM Mortgage m WHERE m.passportNumber = :passportNumber AND m.mortgageSumm = :mortgageSumm";
        TypedQuery<Mortgage> query = entityManager.createQuery(hql, Mortgage.class);
        query.setParameter("passportNumber", passportNumber);
        query.setParameter("mortgageSumm", mortgageSumm);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Transactional
    public void save(Mortgage mortgage) {
        entityManager.persist(mortgage);
    }

    @Transactional
    public void update(int passportNumber, double mortgageSumm, Mortgage updatedMortgage) {
        // Поиск записи по номеру паспорта и сумме кредита
        String hql = "FROM Mortgage m WHERE m.passportNumber = :passportNumber AND m.mortgageSumm = :mortgageSumm";
        TypedQuery<Mortgage> query = entityManager.createQuery(hql, Mortgage.class);
        query.setParameter("passportNumber", passportNumber);
        query.setParameter("mortgageSumm", mortgageSumm);

        Mortgage existingMortgage = query.getResultStream().findFirst().orElse(null);

        // Если запись найдена, обновить её
        if (existingMortgage != null) {
            existingMortgage.setMortgageSumm(updatedMortgage.getMortgageSumm());
            existingMortgage.setMortgageTerm(updatedMortgage.getMortgageTerm());
            entityManager.merge(existingMortgage);
        }
    }

    @Transactional
    public void delete(int passportNumber, double mortgageSumm) {
        String hql = "FROM Mortgage m WHERE m.passportNumber = :passportNumber AND m.mortgageSumm = :mortgageSumm";
        TypedQuery<Mortgage> query = entityManager.createQuery(hql, Mortgage.class);
        Mortgage existingMortgage = query.getResultStream().findFirst().orElse(null);
        if (existingMortgage != null) {
            entityManager.remove(existingMortgage);
        }
    }
}