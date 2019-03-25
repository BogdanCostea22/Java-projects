package factory;

import dao.*;
import dao.hibernate.DocumentDao;
import dao.hibernate.HouseDao;
import dao.hibernate.RequestDao;
import dao.hibernate.UserDao;

public class HibernateDAO implements AbstractFactory {
    public DAO getUserDao() {
        return new UserDao();
    }

    public DAO getHouseDao() {
        return new HouseDao();
    }

    public DAO getDocumentDao() {
        return new DocumentDao();
    }

    public DAO getRequestDao() {
        return new RequestDao();
    }
}
