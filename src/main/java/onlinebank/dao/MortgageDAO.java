package onlinebank.dao;

import jakarta.transaction.Transactional;
import onlinebank.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MortgageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Mortgage> getAllMortgages() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Mortgage", Mortgage.class).getResultList();
        }
    }

    public Mortgage show(int passportNumber, double mortgageSumm) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Mortgage m WHERE m.passportNumber = :passportNumber AND m.mortgageSumm = :mortgageSumm";
            Query<Mortgage> query = session.createQuery(hql, Mortgage.class);
            query.setParameter("passportNumber", passportNumber);
            query.setParameter("mortgageSumm", mortgageSumm);
            return query.uniqueResultOptional().orElse(null);
        }
    }

    @Transactional
    public void save(Mortgage mortgage) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(mortgage);
            transaction.commit();
        }
    }

    @Transactional
    public void update(int passportNumber, double mortgageSumm, Mortgage updatedMortgage) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Mortgage m WHERE m.passportNumber = :passportNumber AND m.mortgageSumm = :mortgageSumm";
            Query<Mortgage> query = session.createQuery(hql, Mortgage.class);
            query.setParameter("passportNumber", passportNumber);
            query.setParameter("mortgageSumm", mortgageSumm);

            Mortgage existingMortgage = query.uniqueResultOptional().orElse(null);

            if (existingMortgage != null) {
                existingMortgage.setMortgageSumm(updatedMortgage.getMortgageSumm());
                existingMortgage.setMortgageTerm(updatedMortgage.getMortgageTerm());
                session.update(existingMortgage);
            }
            transaction.commit();
        }
    }

    @Transactional
    public void delete(int passportNumber, double mortgageSumm) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Поиск записи по номеру паспорта и сумме кредита
            String hql = "FROM Mortgage m WHERE m.passportNumber = :passportNumber AND m.mortgageSumm = :mortgageSumm";
            Query<Mortgage> query = session.createQuery(hql, Mortgage.class);
            query.setParameter("passportNumber", passportNumber);
            query.setParameter("mortgageSumm", mortgageSumm);

            Mortgage existingMortgage = query.uniqueResultOptional().orElse(null);

            // Если запись найдена, удалить её
            if (existingMortgage != null) {
                session.delete(existingMortgage);
            }

            transaction.commit();
        }
    }
}