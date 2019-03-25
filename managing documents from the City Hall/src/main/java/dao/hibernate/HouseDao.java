package dao.hibernate;

import dao.DAO;
import model.House;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HouseDao implements DAO<House, String> {
    private Session currentSession;

    private Transaction currentTransaction;

    public HouseDao() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    private Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    private void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(House entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().save(entity);
        closeCurrentSessionwithTransaction();
    }

    public void update(House entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().update(entity);
        closeCurrentSessionwithTransaction();
    }

    public House findById(String id) {
        openCurrentSession();
        House house = (House) getCurrentSession().get(House.class, id);
        closeCurrentSession();
        return house;
    }

    public void delete(House entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().delete(entity);
        closeCurrentSessionwithTransaction();
    }

    @SuppressWarnings("unchecked")
    public List<House> findAll() {
        openCurrentSession();
        List<House> houses = (List<House>) getCurrentSession().createQuery("from House").list();
        closeCurrentSession();
        return houses;
    }

    public void deleteAll() {
        openCurrentSessionwithTransaction();
        List<House> entityList = findAll();
        for (House entity : entityList) {
            delete(entity);
        }
        closeCurrentSessionwithTransaction();
    }
}
