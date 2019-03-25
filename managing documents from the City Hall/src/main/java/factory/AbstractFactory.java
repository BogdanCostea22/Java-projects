package factory;

import dao.DAO;

public interface AbstractFactory {
    DAO getUserDao();
    DAO getHouseDao();
    DAO getDocumentDao();
    DAO getRequestDao();
}