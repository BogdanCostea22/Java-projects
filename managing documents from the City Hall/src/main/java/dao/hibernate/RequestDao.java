package dao.hibernate;

import dao.DAO;
import model.Request;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RequestDao implements DAO<Request, String> {
    private Session currentSession;

    private Transaction currentTransaction;

    public RequestDao() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
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

    public void persist(Request entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().save(entity);
        closeCurrentSessionwithTransaction();
    }

    public void update(Request entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().update(entity);
        closeCurrentSessionwithTransaction();
    }

    public Request findById(String id) {
        openCurrentSession();
        Request request = (Request) getCurrentSession().get(Request.class, id);
        closeCurrentSession();
        return request;
    }

    public void delete(Request entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().delete(entity);
        closeCurrentSessionwithTransaction();
    }

    @SuppressWarnings("unchecked")
    public List<Request> findAll() {
        openCurrentSession();
        List<Request> requests = (List<Request>) getCurrentSession().createQuery("from Request").list();
        closeCurrentSession();
        return requests;
    }

    public void deleteAll() {
        openCurrentSessionwithTransaction();
        List<Request> entityList = findAll();
        for (Request entity : entityList) {
            delete(entity);
        }
        closeCurrentSessionwithTransaction();
    }
}
