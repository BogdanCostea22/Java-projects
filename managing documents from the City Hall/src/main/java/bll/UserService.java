package bll;

import dao.DAO;
import dao.hibernate.UserDao;
import factory.AbstractFactory;
import model.User;

import java.util.List;

public class UserService {
    private static DAO userDao;

    public UserService(AbstractFactory abstractFactory) {
        userDao = abstractFactory.getUserDao();
    }

    public void persist(User entity) {
       userDao.persist(entity);
    }

    public void update(User entity) {
        userDao.update(entity);
    }

    public User findById(String id) {
        User user = (User)userDao.findById(id);
        return user;
    }

    public void delete(String id) {
        User user = (User)userDao.findById(id);
        userDao.delete(user);
    }

    public List<User> findAll() {
        List<User> users = userDao.findAll();
        return users;

    }

    public void deleteAll() {
        userDao.deleteAll();
    }

    public User loginUser(String username, String password)
    {
        List<User> list = userDao.findAll();

        for(User user:list)
            if(user.getPassword().equals(password) && user.getUsername().equals(username))
                return  user;

        return null;
    }

    //Return last user id
    public String getLastId()
    {
        List<User> users = userDao.findAll();

        int max = 0;
        for(User user: users)
        {
            if(user.getId()>max)
                max = user.getId();
        }
        return Integer.toString(max+1);
    }

    public DAO userDao() {
        return userDao;
    }
}