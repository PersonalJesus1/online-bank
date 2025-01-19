package onlinebank.dao;

import onlinebank.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MortgageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Mortgage> getAllMortgages() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Mortgage", Mortgage.class).getResultList();
    }

    public Mortgage show(int passportNumber, double mortgageSumm) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Mortgage m WHERE m.passportNumber = :passportNumber AND m.mortgageSumm = :mortgageSumm";
        return session.createQuery(hql, Mortgage.class)
                .setParameter("passportNumber", passportNumber)
                .setParameter("mortgageSumm", mortgageSumm)
                .uniqueResultOptional().orElse(null);
    }

    public void save(Mortgage mortgage) {
        Session session = sessionFactory.getCurrentSession();
        session.save(mortgage);
    }

    public void update(int passportNumber, double mortgageSumm, Mortgage updatedMortgage) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Mortgage m WHERE m.passportNumber = :passportNumber AND m.mortgageSumm = :mortgageSumm";
        Query<Mortgage> query = session.createQuery(hql, Mortgage.class);
        query.setParameter("passportNumber", passportNumber);
        query.setParameter("mortgageSumm", mortgageSumm);
        Mortgage existingMortgage = query.uniqueResultOptional().orElse(null);
        if (existingMortgage != null) {
            existingMortgage.setMortgageSumm(updatedMortgage.getMortgageSumm());
            existingMortgage.setCurrentMortgageSumm(updatedMortgage.getCurrentMortgageSumm());
            existingMortgage.setMortgageTerm(updatedMortgage.getMortgageTerm());
            session.update(existingMortgage);
        }
    }

    public void delete(int passportNumber, double mortgageSumm) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Mortgage m WHERE m.passportNumber = :passportNumber AND m.mortgageSumm = :mortgageSumm";
        Query<Mortgage> query = session.createQuery(hql, Mortgage.class);
        query.setParameter("passportNumber", passportNumber);
        query.setParameter("mortgageSumm", mortgageSumm);
        Mortgage existingMortgage = query.uniqueResultOptional().orElse(null);
        if (existingMortgage != null) {
            session.delete(existingMortgage);
        }
    }

    public Mortgage findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Mortgage m WHERE m.id = :id";
        return session.createQuery(hql, Mortgage.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    public void update(Long id, Mortgage mortgage) {
        Session session = sessionFactory.getCurrentSession();
        Mortgage existingMortgage = session.get(Mortgage.class, id);
        if (existingMortgage != null) {
            existingMortgage.setCurrentMortgageSumm(mortgage.getCurrentMortgageSumm());
            session.merge(existingMortgage);
        }
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Mortgage mortgage = session.get(Mortgage.class, id);
        if (mortgage != null) {
            session.delete(mortgage);
        }
    }
    public boolean existsByPassportNumber(int passportNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT COUNT(*) > 0 FROM User u WHERE u.passportNumber = :passportNumber";
        return session.createQuery(hql, Boolean.class)
                .setParameter("passportNumber", passportNumber)
                .uniqueResult();
    }
}