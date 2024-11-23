package onlinebank.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import onlinebank.models.AutoLoan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoLoanDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<AutoLoan> getAllAutoloans() {
        String hql = "FROM AutoLoan";
        TypedQuery<AutoLoan> query = entityManager.createQuery(hql, AutoLoan.class);
        return query.getResultList();
    }

    public AutoLoan show(int passportNumber, double mortgageSumm) {
        String hql = "FROM AutoLoan a WHERE a.passportNumber = :passportNumber AND a.mortgageSumm = :mortgageSumm";
        TypedQuery<AutoLoan> query = entityManager.createQuery(hql, AutoLoan.class);
        query.setParameter("passportNumber", passportNumber);
        query.setParameter("mortgageSumm", mortgageSumm);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Transactional
    public void save(AutoLoan autoloan) {
        entityManager.persist(autoloan);
    }

    @Transactional
    public void update(int passportNumber, double mortgageSumm, AutoLoan updatedAutoLoan) {
        // Поиск записи по номеру паспорта и сумме кредита
        String hql = "FROM AutoLoan a WHERE a.passportNumber = :passportNumber AND a.mortgageSumm = :mortgageSumm";
        TypedQuery<AutoLoan> query = entityManager.createQuery(hql, AutoLoan.class);
        query.setParameter("passportNumber", passportNumber);
        query.setParameter("mortgageSumm", mortgageSumm);

        AutoLoan existingAutoLoan = query.getResultStream().findFirst().orElse(null);

        // Если запись найдена, обновить её
        if (existingAutoLoan != null) {
            existingAutoLoan.setMortgageSumm(updatedAutoLoan.getMortgageSumm());
            existingAutoLoan.setMortgageMonthsTerm(updatedAutoLoan.getMortgageMonthsTerm());
            entityManager.merge(existingAutoLoan);
        }
    }
    @Transactional
    public void delete(int passportNumber, double mortgageSumm) {
        String hql = "FROM AutoLoan a WHERE a.passportNumber = :passportNumber AND a.mortgageSumm = :mortgageSumm";
        TypedQuery<AutoLoan> query = entityManager.createQuery(hql, AutoLoan.class);
        AutoLoan existingAutoLoan = query.getResultStream().findFirst().orElse(null);
        if (existingAutoLoan != null) {
            entityManager.remove(existingAutoLoan);
        }
    }
}