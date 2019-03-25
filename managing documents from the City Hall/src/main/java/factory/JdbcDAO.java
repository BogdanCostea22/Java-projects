package factory;

import dao.*;
import dao.jdbc.DocumentDAO;
import dao.jdbc.HouseDAO;
import dao.jdbc.RequestDAO;
import dao.jdbc.UserDAO;

public class JdbcDAO implements AbstractFactory {

    public DAO getUserDao() {
        return new UserDAO();
    }

    public DAO getHouseDao() {
        return new HouseDAO();
    }

    public DAO getDocumentDao() {
        return new DocumentDAO();
    }

    public DAO getRequestDao() {
        return new RequestDAO();
    }
}
