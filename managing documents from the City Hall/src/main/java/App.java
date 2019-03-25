import bll.DocumentService;
import bll.HouseService;
import bll.RequestService;
import bll.UserService;
import factory.AbstractFactory;
import factory.HibernateDAO;
import factory.JdbcDAO;
import model.Document;
import model.House;
import model.Request;
import model.User;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Scanner;


public class App {

    private static User loginUser;
    private static   AbstractFactory abstractFactory;

    //Register User
    static void registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Registration process...");
        System.out.println("Username::");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        System.out.println("State:");
        String state = scanner.nextLine();

        UserService userService = new UserService(abstractFactory);
        User user = new User(Integer.parseInt(userService.getLastId()) + 1, username, password, state);

        userService.persist(user);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int command1 = -1;
        while (command1 != 0) {
            System.out.println("1.Hibernate");
            System.out.println("2.JDBC");
            System.out.println("0.Exit");

            command1 = Integer.parseInt(scanner.nextLine());


            int command = -1;

            while (command != 0) {
                System.out.println("1.Login");
                System.out.println("2.Register");
                System.out.println("0.Exit");

                command = Integer.parseInt(scanner.nextLine());

                switch (command1){
                    case 1:
                        abstractFactory = new HibernateDAO();
                        break;
                    case 2:
                        abstractFactory = new JdbcDAO();
                }

                switch (command) {
                    case 1:
                        System.out.println("Please enter username:");
                        String username = scanner.nextLine();
                        System.out.println("Please enter password:");
                        String password = scanner.nextLine();

                        UserService userService = new UserService(abstractFactory);
                        loginUser = userService.loginUser(username, password);

                        if (loginUser != null)
                            if (loginUser.getState().equals("0"))
                                normalUserOperations();
                            else
                                adminOperaions();

                        else
                            System.out.print("Wrong credentials");
                        break;
                    case 2:
                        registerUser();
                        break;
                     default:
                         break;
                }

            }
        }
    }

    private static void adminOperaions() {
        Scanner scanner = new Scanner(System.in);
        int command = -1;

        while (command != 0) {
            System.out.println("Administrator operations:");
            System.out.println("1.Show all users");
            System.out.println("2.Add Document");
            System.out.println("3.Delete Document");
            System.out.println("4.Show all requests");
            System.out.println("5.Approve request");
            System.out.println("6.Remove request");
            System.out.println("0.Exit");

            command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1: {
                    UserService userService = new UserService(abstractFactory);
                    List<User> list = userService.findAll();

                    for (User user : list)
                        System.out.println(user.toString());
                    break;
                }
                case 2: {

                    DocumentService documentService = new DocumentService(abstractFactory);
                    System.out.println("Please enter the name of document:");
                    String documentName = scanner.nextLine();

                    if (documentName.equals("")) {
                        System.out.println("Please enter a valid name....");
                        break;
                    }

                    try {

                        documentService.persist(new Document(documentService.getLastId(), documentName));
                    } catch (HibernateError e) {
                        System.out.println("Wrong data");
                    }
                    break;

                }
                case 3: {
                    System.out.println("Document id:");
                    String documentId = scanner.nextLine();
                    DocumentService documentService = new DocumentService(abstractFactory);
                    try {
                        documentService.delete(documentId);
                    } catch (HibernateError e) {
                        System.out.println("Wrong data");
                    }
                    break;

                }
                case 4: {
                    RequestService requestService = new RequestService(abstractFactory);
                    List<Request> list = requestService.findAll();
                    for (Request request : list)
                        System.out.println(request.toString());
                    break;
                }
                case 5: {
                    System.out.println("Request id:");
                    String requestId = scanner.nextLine();

                    RequestService requestService = new RequestService(abstractFactory);
                    List<Request> list = requestService.findAll();
                    for (Request request : list)
                        if (request.getId().equals(requestId)) {
                            request.setAvailable(1);
                            requestService.update(request);
                        }

                    System.out.println("Wrong request number....");
                    break;
                }
                case 6: {
                    System.out.println("Request id:");
                    String request_id = scanner.nextLine();

                    RequestService requestService = new RequestService(abstractFactory);
                    for (Request request : requestService.findAll())
                        if (request.getId().equals(request_id)) {
                            requestService.delete(request.getId());
                            break;
                        }
                    System.out.println("Request not found....");
                    break;
                }
            }

        }
    }

    private static void normalUserOperations() {
        Scanner scanner = new Scanner(System.in);
        int command = -1;

        while (command != 0) {
            System.out.println("Normal user operations:");
            System.out.println("1.ShowDocuments");
            System.out.println("2.Add House");
            System.out.println("3.Delete House");
            System.out.println("4.Show my requests");
            System.out.println("5.Add request");
            System.out.println("6.Remove request");
            System.out.println("7.Update Request");
            System.out.println("0.Exit");

            command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1: {
                    DocumentService documentService = new DocumentService(abstractFactory);
                    List<Document> list = documentService.findAll();

                    for (Document document : list)
                        System.out.println(document.toString());
                    break;
                }
                case 2: {
                    HouseService houseService = new HouseService(abstractFactory);
                    try {
                        houseService.persist(new House(houseService.getLastId(), Integer.toString(loginUser.getId())));
                    } catch (HibernateError e) {
                        System.out.println("Wrong data");
                    }
                    break;

                }
                case 3: {
                    System.out.println("House id:");
                    String houseId = scanner.nextLine();
                    HouseService houseService = new HouseService(abstractFactory);
                    try {
                        houseService.delete(houseId);
                    } catch (HibernateError e) {
                        System.out.println("Wrong data");
                    }
                    break;

                }
                case 4: {
                    RequestService requestService = new RequestService(abstractFactory);
                    List<Request> list = requestService.findAll();
                    for (Request request : list)
                        if (request.getUserId().equals(loginUser.getId()))
                            System.out.println(request.toString());
                    break;
                }
                case 5: {
                    System.out.println("Document id:");
                    String documentId = scanner.nextLine();
                    System.out.println("House id:");
                    String houseId = scanner.nextLine();
                    RequestService requestService = new RequestService(abstractFactory);
                    Request request = new Request(requestService.getLastId(), 0, documentId, Integer.toString(loginUser.getId()),houseId);
                    try {
                        requestService.persist(request);

                    } catch (HibernateError e) {
                        System.out.println("Wrong data");
                    } catch (HibernateException e) {
                        System.out.println("Wrong data");
                    }
                    break;
                }
                case 6: {
                    System.out.println("Request id:");
                    String request_id = scanner.nextLine();

                    RequestService requestService = new RequestService(abstractFactory);
                    for (Request request : requestService.findAll())
                        if (request.getId().equals(request_id)) {
                            requestService.delete(request.getId());
                            break;
                        }
                    System.out.println("Request not found....");
                    break;
                }
                case 7: {
                    System.out.println("Request id:");
                    String request_id = scanner.nextLine();

                    RequestService requestService = new RequestService(abstractFactory);
                    for (Request request : requestService.findAll())
                        if (request.getId().equals(request_id)) {
                            System.out.println("Document id:");
                            String documentId = scanner.nextLine();
                            System.out.println("House id:");
                            String houseId = scanner.nextLine();

                            request.setDocument_id(documentId);
                            request.setHouseId(houseId);
                            requestService.update(request);
                            break;
                        }

                    break;
                }
            }

        }
    }
}