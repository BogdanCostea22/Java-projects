package dao.jdbc;

import dao.DAO;
import model.User;

import java.util.List;

public class UserDAO extends AbstractDAO<User> implements DAO<User, String> {

    //Override update function
    public void update(User document){
        super.update(document, document.getId());
    }

    public User findById(String s) {
        List<User> list = super.findAll();

        for(User user : list)
            if(user.getId() == Integer.parseInt(s))
                return user;

        return null;
    }

    //Override delete function
    public  void delete(User document){
        super.deleteById(document.getId());
    }

    public void deleteAll() {

    }
}
