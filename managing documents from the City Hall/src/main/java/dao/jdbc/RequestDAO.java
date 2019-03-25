package dao.jdbc;

import dao.DAO;
import model.Request;

import java.util.List;

public class RequestDAO extends AbstractDAO<Request> implements DAO<Request, String> {

    //Override update function
    public void update(Request document){
        super.update(document, Integer.parseInt(document.getId()));
    }

    public Request findById(String s) {
        List<Request> list = super.findAll();

        for(Request request : list)
            if(request.getId().equals(s))
                return request;

        return null;
    }

    //Override delete function
    public  void delete(Request document){
        super.deleteById(Integer.parseInt(document.getId()));
    }

    public void deleteAll() {

    }
}
