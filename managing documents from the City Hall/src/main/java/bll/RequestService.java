package bll;

import dao.DAO;
import dao.hibernate.RequestDao;
import factory.AbstractFactory;
import model.Request;

import java.util.List;

public class RequestService {
    private static DAO requestDao;

    public RequestService(AbstractFactory abstractFactory) {
        requestDao = abstractFactory.getRequestDao();
    }

    public void persist(Request entity) {
        requestDao.persist(entity);
    }

    public void update(Request entity) {
        requestDao.update(entity);
    }

    public Request findById(String id) {
        Request request = (Request)requestDao.findById(id);
        return request;
    }

    public void delete(String id) {
        Request request = (Request)requestDao.findById(id);
        requestDao.delete(request);
    }

    public List<Request> findAll() {
        List<Request> requests = requestDao.findAll();
        return requests;

    }

    public void deleteAll() {
        requestDao.deleteAll();
    }

    public DAO requestDao() {
        return requestDao;
    }

    //Return last request id
    public String getLastId()
    {
        List<Request> requests = requestDao.findAll();

        int max = 0;
        for(Request request: requests)
        {
            if(request.getId().compareTo(Integer.toString(max))>0)
                max = Integer.parseInt(request.getId());
        }
        return Integer.toString(max+1);
    }
}