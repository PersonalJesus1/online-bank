package onlinebank.dao;

import onlinebank.models.AutoLoan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AutoLoanDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<AutoLoan> getAllAutoloans() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AutoLoan", AutoLoan.class).getResultList();
    }

    public AutoLoan show(int passportNumber, double mortgageSumm) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM AutoLoan a WHERE a.passportNumber = :passportNumber AND a.mortgageSumm = :mortgageSumm";
        return session.createQuery(hql, AutoLoan.class)
                .setParameter("passportNumber", passportNumber)
                .setParameter("mortgageSumm", mortgageSumm)
                .uniqueResult();
    }

    public void save(AutoLoan autoloan) {
        Session session = sessionFactory.getCurrentSession();
        session.save(autoloan);
    }

    public void update(int passportNumber, double mortgageSumm, AutoLoan updatedAutoLoan) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM AutoLoan a WHERE a.passportNumber = :passportNumber AND a.mortgageSumm = :mortgageSumm";
        AutoLoan existingAutoLoan = session.createQuery(hql, AutoLoan.class)
                .setParameter("passportNumber", passportNumber)
                .setParameter("mortgageSumm", mortgageSumm)
                .uniqueResultOptional()
                .orElse(null);
        if (existingAutoLoan != null) {
            existingAutoLoan.setMortgageSumm(updatedAutoLoan.getMortgageSumm());
            existingAutoLoan.setMortgageMonthsTerm(updatedAutoLoan.getMortgageMonthsTerm());
            session.update(existingAutoLoan);
        }
    }

    public void update(Long id, AutoLoan autoLoan) {
        Session session = sessionFactory.getCurrentSession();
        AutoLoan existingLoan = session.get(AutoLoan.class, id);
        if (existingLoan != null) {
            existingLoan.setCurrentMortgageSumm(autoLoan.getCurrentMortgageSumm());
            session.update(existingLoan);
        }
    }

    public void delete(int passportNumber, double mortgageSumm) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM AutoLoan a WHERE a.passportNumber = :passportNumber AND a.mortgageSumm = :mortgageSumm";
        AutoLoan existingAutoLoan = session.createQuery(hql, AutoLoan.class)
                .setParameter("passportNumber", passportNumber)
                .setParameter("mortgageSumm", mortgageSumm)
                .uniqueResultOptional()
                .orElse(null);
        if (existingAutoLoan != null) {
            session.delete(existingAutoLoan);
        }
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        AutoLoan autoLoan = session.get(AutoLoan.class, id);
        if (autoLoan != null) {
            session.delete(autoLoan);
        }
    }

    public AutoLoan findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM AutoLoan a WHERE a.id = :id";
        return session.createQuery(hql, AutoLoan.class)
                .setParameter("id", id)
                .uniqueResult();
    }
}