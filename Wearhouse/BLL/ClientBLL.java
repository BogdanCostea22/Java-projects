package BLL;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import Dao.ClientDAO;
import Model.Client;
import Model.Product;

public class ClientBLL{
private ClientDAO operation=new ClientDAO();
//Metoda de verificare a usernameului si a parolei
public int checkUsernamePassword(String username,String password) {
	return operation.checkUsernamePassword(username, password);
}
//Metoda de inserare a unui nou client
public boolean insertClient(String first_name,String last_name,String username,String password,String email,String adress,String country,String county,String city,String phone_number)
{
	Client client=new Client(first_name,last_name,username,password,adress,country,county,city,email,phone_number);
	return operation.insert(client);
}
//Metoda de stergere a unui client
public boolean deleteById(int id) {
	return operation.deleteById(id);
}
//Metoda de indentificare dupa un id
public Client findById(int id) {
	return operation.findById(id);
}
//Metoda de update pentru un client
public boolean updateClient(int id,String first_name,String last_name,String username,String password,String email,String adress,String country,String county,String city,String phone_number) {
	Client client=new Client(first_name,last_name,username,password,adress,country,county,city,email,phone_number);
	return operation.update(client, id);
}
public List<Object> getClient()
{
	List<Object> obj=new ArrayList<Object>();
	for(Client client:operation.getALL())
		obj.add(client);
	return obj;
}
public JTable createTable()
{
	List<Object> list=getClient();
	return operation.newTable(list);
	
}
}
