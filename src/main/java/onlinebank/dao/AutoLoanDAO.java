package onlinebank.dao;

import jakarta.transaction.Transactional;
import onlinebank.models.AutoLoan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AutoLoanDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<AutoLoan> getAllAutoloans() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from AutoLoan", AutoLoan.class).getResultList();
        }
    }

    public AutoLoan show(int passportNumber, double mortgageSumm) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM AutoLoan a WHERE a.passportNumber = :passportNumber AND a.mortgageSumm = :mortgageSumm";
            return session.createQuery(hql, AutoLoan.class)
                    .setParameter("passportNumber", passportNumber)
                    .setParameter("mortgageSumm", mortgageSumm)
                    .uniqueResultOptional()
                    .orElse(null);
        }
    }

    @Transactional
    public void save(AutoLoan autoloan) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(autoloan);
            transaction.commit();
        }
    }

    @Transactional
    public void update(int passportNumber, double mortgageSumm, AutoLoan updatedAutoLoan) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "FROM AutoLoan a WHERE a.passportNumber = :passportNumber AND a.mortgageSumm = :mortgageSumm";
            AutoLoan existingAutoLoan = session.createQuery(hql, AutoLoan.class)
                    .setParameter("passportNumber", passportNumber)
                    .setParameter("mortgageSumm", mortgageSumm)
                    .uniqueResultOptional()
                    .orElse(null);

            if (existingAutoLoan != null) {
                existingAutoLoan.setMortgageSumm(updatedAutoLoan.getMortgageSumm());
                existingAutoLoan.setMortgageMonthsTerm(updatedAutoLoan.getMortgageMonthsTerm());
                session.merge(existingAutoLoan);
            }
            transaction.commit();
        }
    }

    @Transactional
    public void delete(int passportNumber, double mortgageSumm) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "FROM AutoLoan a WHERE a.passportNumber = :passportNumber AND a.mortgageSumm = :mortgageSumm";
            AutoLoan existingAutoLoan = session.createQuery(hql, AutoLoan.class)
                    .setParameter("passportNumber", passportNumber)
                    .setParameter("mortgageSumm", mortgageSumm)
                    .uniqueResultOptional()
                    .orElse(null);

            if (existingAutoLoan != null) {
                session.delete(existingAutoLoan);
            }

            transaction.commit();
        }
    }
}
