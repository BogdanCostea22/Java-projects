
import dao.jdbc.UserDAO;
import model.User;
import org.hibernate.exception.ConstraintViolationException;

public class Main {

    public static void main(String []args)
    {
        try {
            //User user = new User("140", "newUR", "pass", "0");
            UserDAO userDAO = new UserDAO();
            //userDAO.persist(user);
        }
        catch (ConstraintViolationException e)
        {
            System.out.println("User already exist");
        }
    }

}
