package BLL;

import Dao.AdministratorDao;
import Model.Administrator;

public class AdministratorBLL{
	private AdministratorDao operation=new AdministratorDao();
	//Constructori
	public AdministratorBLL()
	{
		
	}
	
	//Metodele pe care le implementam pentru Administrator
	
	public int  checkUsernamePAssword(String username,String password){
		return operation.checkUsernamePassword(username,password);
	}
	
	public Administrator takeAdminById(int id)
	{
		return operation.findById(id);	
	}
	
	public boolean deleteById(int id)
	{
		return operation.deleteById(id);
	}
	public boolean insertAdministrator(String first_name,String last_name,String username,String password)
	{
		Administrator admin=new Administrator(first_name,last_name,username,password);
			return operation.insert(admin);
	}
	public boolean updateAdministrator(int id,String first_name,String last_name,String username,String password)
	{
		Administrator admin=new Administrator(first_name,last_name,username,password);
		return operation.update(admin, id);
	}
}