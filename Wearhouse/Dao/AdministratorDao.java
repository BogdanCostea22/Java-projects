package Dao;

import Model.Administrator;
public class AdministratorDao extends AbstractDAO<Administrator> {
/**
 * 
 * Pentru a verifica daca contul administratorului si parola sunt corecte am implemnatat o metoda care verifica acest lucru.Aceasta metoda preia lista
 * de administratori returnata de clasa AbstractDAO o parcurge si verifica daca usernameul se gaseste printe conturile deja creeate, in cazul in care usernameul se gaseste
 * se va verifica daca parola introdusa corespunde acetui username.Rezultatul returnat va fi un tip boolean care atesta daca aceste date apartin tabelei administrator oferind 
 * oportunitatea de a intra in aplicatie si a face anumite operatii.Verificare pe care o implementeaza aceasta problema este doar o restrictionare a utilizatorilor.
 * Aceeasi implementare se va face si in cazul ClientDAO. 
 * @param username
 * @param password
 * @return
 */
public int checkUsernamePassword(String username,String password)
{	
	for(Administrator admin:super.getALL())
	{
		System.out.println(admin.printAdmin());
		if(username.equals(admin.getUsername()))
			{
				if(password.equals(admin.getPassword()))
								return admin.getId_administrator();
				else return -1;
					
			}
		}
	return -1;
}

}
