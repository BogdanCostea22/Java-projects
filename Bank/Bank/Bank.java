package Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import javax.swing.JTable;

import Account.Account;
import Account.SavingAccount;
import Account.SpendingAccount;
import Person.Person;
/**
 * 
 * @author Bogdan
 *@invariant isWellFormed()
 */
public class Bank  implements BankProc,java.io.Serializable {
	
	//Declaratii
	private HashMap<Person,List<Account>> hashMap=new HashMap<Person,List<Account>>();
	
	//Gasire persoana dupa CNP
	public Person takePerson()
	{
		for(Person person:hashMap.keySet())
			System.out.println(person.personToString());
		
		return null;
	}
	
	//Add new Person
	public boolean addPerson(Person p)
	{
		 	assert p!=null:"Nu exista persoana";
		 	assert isWellFormed():"Exista probleme la creeare";
	    
		 boolean check=false;
	     
	     int length=hashMap.keySet().size();
	     //Adaugam persoana si cream o noua lista oentru ea
		 if(hashMap.put(p,new ArrayList<Account>())!=null)check=true;
		 
		 	assert length==(hashMap.keySet().size()-1):"Contul nu s-a adugat";
		 	assert isWellFormed():"Exista probleme la creeare";
		 
		 return check;
	}
	
	//Delete person
	public boolean deletePerson(Person person)
		{
				assert person!=null:"Persoana nu exista!";
				assert isWellFormed():"Exista probleme la creeare";
			
			//Verificam daca s-a sters persoana
			boolean check=false;
			int length=hashMap.keySet().size();
			if(hashMap.remove(person)!=null)check=true;
			
				assert (length-1)==hashMap.keySet().size():"Stergerea nu a avut loc!";
				assert isWellFormed():"Exista probleme la creeare!";
			
			return check;
		}
	
	//Adaugarea unui cont la un detinator
	public	void addholderAccount(Account a, Person p) {
			assert a!=null&&p!=null:"Trebuie introduse contul si parola!";
			assert isWellFormed():"Exista probleme la creeare!";
		
		//Aflam lista si adaugam in ea
		List<Account> list=hashMap.get(p);
		
		//Aflam lungimea listei
		int length=list.size();
		
		//Aduagam in lista
		list.add(a);
			
			assert (length+1)==list.size():"S-a adaugat un nou cont!";
			assert isWellFormed():"Exista probleme la creeare!";
	}
	
	//Stergerea unui cont de la detinator
	public boolean removeHolderAccount(Account account, Person person) {
			assert account!=null&&person!=null:"Trebuie introduse un contul si persoana!";
			assert isWellFormed():"Exista probleme la creeare!";
			
			//Aflam lungimea listei corespunzatoare persoanei
			int length=hashMap.get(person).size();
			
		boolean check=hashMap.get(person).remove(account);
			
			assert length==(hashMap.get(person).size()+1):"Nu s-a sters contul!";
			assert isWellFormed():"Exista probleme la creeare!";
		return check;
	}
	
	//Update account
	public void updateAccount(int nrAccount,Person p,int amount)
			{
				int nr=1;
					for(Account account:hashMap.get(p))
						if(nr==nrAccount)
						{
							//System.out.println("Numarul contului"+);
							account.setAmount(amount);
							return;
						}
						else nr++;
				
			}

	//Update person
	public void updatePerson(Person newPerson)
	{
				for(Person p:hashMap.keySet())
					if(p.getCNP().equals(newPerson.getCNP()))
					{
						p.setFirstName(newPerson.getFirstName());
						p.setLastName(newPerson.getLastName());
						p.setPhoneNumber(newPerson.getPhoneNumber());
						return;
					}
	}
			
	//Add money to account
	public void addMoney(Person person,int nr,int money)
			{
					assert person!=null&&nr>0&&money>0:"Datele introduse nu sunt bune!";
					assert isWellFormed():"Exista probleme la creeare!";
					
				int n=1;
				
				for(Account account:hashMap.get(person))
					if(n!=nr)n++;
					else
						{
							account.addMoney(money);
							return;
						}
					
					assert isWellFormed():"Exista probleme la creeare!";
			}
			
	//WithDrawMonney
	public boolean withdrawMoney(Person person,int nr,int money)
			{
					assert person!=null&&nr>0&&money>0:"Datele introduse nu sunt bune!";
					assert isWellFormed():"Exista probleme la creeare!";
					
				int n=1;
				boolean check =false;
				for(Account account:hashMap.get(person))
					if(nr!=n)n++;
					else{ 
							if(account instanceof SpendingAccount) 
								{	
									account.withdrawMoney(money);
									check=true;
									break;
								}
							else{
								if(money==account.getAmount())
									{	account.withdrawMoney(money);
										removeHolderAccount(account,person);
										check=true;
										break;
									}
								else break;
							}	
						}
					
					assert isWellFormed():"Exista probleme la creeare!";
				return check;
			}
		
	//Find Account by person and id
	public Account findByPerson(Person person,int id)
		{
			List<Account> list=hashMap.get(person);
			int nr=1;
			Account account=null;
			for(Account acc:list)
			if(nr==id)account=acc;
			else nr++;
			return account;
		}

	//Afiseaza conturile unei persoane
	public void printAccount(Person p)
	{
		List<Account> list= hashMap.get(p);
		p.personToString();
		int nr=0;
		if(list==null)return;
		for(Account account:list)
		{
			nr++;
			System.out.println("Account with number = "+nr+" and amount ="+account.getAmount());
		}
	}
	
	//Create Person Table
	public JTable createPersonTable()
	{	
		//Create Person List
		List<Person> list=new ArrayList<Person>();
		for(Person person:hashMap.keySet())
			list.add(person);
		
		return Person.makeNewTable(list);
	}
	
	//Metoda pentru creearea tabelei de conturi
	public JTable createAccountTable()
	{	
		Object []name=new Object[6];
		name[0]="Id/Person";
		name[1]="Saving/Spending";
		name[2]="Amount";
		name[3]="First Name";
		name[4]="Last Name";
		name[5]="CNP";
		
		//Determinarea numarului de randuri din cadrul tabelei de conturi
		int nr=0;
		for(Person person:hashMap.keySet())
			nr+=hashMap.get(person).size();
		int i=0;
		//Creearea tabelei
		Object [][]data=new Object[nr][6];
		
		for(Person person:hashMap.keySet())
		{
			nr=0;
			List<Account> listAccount=hashMap.get(person);
			for(Account account:listAccount)
			{	data[i][0]=nr+1;
				data[i][1]=account.getAmount();
				data[i][2]=account instanceof SavingAccount?"Da":"Nu";
				data[i][3]=person.getFirstName();
				data[i][4]=person.getLastName();
				data[i][5]=person.getCNP();
				nr++;
				i++;
			}
		}
		return new JTable(data,name);
	}
	
	//Get Person by CNP
	public Person getByCNP(String CNP)
	{
		for(Person person:hashMap.keySet())
			if(person.getCNP().equals(CNP))return person;
		return null;
	}
	
	//Aduagarea observatorilor
	public void addObservers()
	{
		for(Person person:hashMap.keySet())
			for(Account account:hashMap.get(person))
				account.addObserver(person);
	}

	//Metoda is WellFormed
	public boolean isWellFormed()
	{
			for(Person person:hashMap.keySet())
			{if(person.getCNP().length()<13)return false;
			int n=0;
			for(Account account:hashMap.get(person))
				n++;
			if(n!=hashMap.get(person).size())return false;
			}
			return true;
	}
	
	//Get account list by person
	public List<Account> getList(Person person)
	{
		return hashMap.get(person);
	}
}

