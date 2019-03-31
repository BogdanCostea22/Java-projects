package Dao;

import Model.Client;
public class ClientDAO extends AbstractDAO<Client>{
public int checkUsernamePassword(String username,String password) {
	for(Client client:super.getALL())
	if(client.getUsername().equals(username))
		{
			if(client.getPassword().equals(password))return client.getId_client();
			else return -1;
		}
return -1;
}
}
