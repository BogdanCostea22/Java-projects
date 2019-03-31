package TEST_BANK;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Account.Account;
import Account.SavingAccount;
import Account.SpendingAccount;
import Bank.Bank;
import Person.Person;

public class BankTEST{
	Bank bank;
	@Before
	public void setUp()
	{
				bank=new Bank();
		
				Person p=new Person("Peter","Ion","1942533684516","5168509846");
		        bank.addPerson(p);
		        
		        Person p1=new Person("IOAN","PETRESCU","1234788564923","123456789");
		        bank.addPerson(p1);
		        
		        Account a=new SavingAccount(1000);
		        Account a1=new SpendingAccount(2000);
		        bank.addholderAccount(a1, p1);
		        bank.addholderAccount(a, p1);
		        bank.addholderAccount(new SpendingAccount(1506), p);
		        bank.addholderAccount(new SpendingAccount(8549), p);
		        bank.addholderAccount(new SpendingAccount(6848), p1);
	}
	
	@Test
	public void testAddPerson() {
		//Adaugam un nou client
		Person p1=new Person("IO","PE","1234788545612","1587687668");
		bank.addPerson(p1);
		
		assert(p1.equals(bank.getByCNP("1234788545612")));
	}
	
	@Test
	public void testDeletePerson() {
		//Stergem o persoana
		Person p=new Person("Peter","Ion","1942533684516","5168509846");
		bank.deletePerson(p);
		assert(!p.equals(bank.getByCNP("1942533684516")));
	}

	@Test
	public void testAddholderAccount() {
		//Add account
		Person person=new Person("Peter","Ion","1942533684516","5168509846");
		Account account=new SpendingAccount(1907);
		bank.addholderAccount(account, person);
		assert(bank.getList(person).contains(account));
	}

	@Test
	public void testRemoveHolderAccount() {
		Person person=new Person("Peter","Ion","1942533684516","5168509846");
		Account account=bank.findByPerson(person,1);		
		//Stergem contul
		bank.removeHolderAccount(account, person);
		assert(!bank.getList(person).contains(account));
	}

	@Test
	public void testUpdatePerson() {
		
		Person person=bank.getByCNP("1942533684516");
		person.setLastName("NOUL NUME");
		
		bank.updatePerson(person);
		
		assert(bank.getByCNP("1942533684516").getLastName().equals("NOUL NUME"));
	}
	
	@Test
	public void testAddMoney() {
		Account account=new SpendingAccount(1506);
		account.addMoney(120);
		Person person=bank.getByCNP("1942533684516");
		//Adaugam bani
		bank.addMoney(person, 1, 120);
		assert(bank.getList(person).get(0).getAmount()==account.getAmount());
	}
	
	@Test
	public void testWithdrawMoney() {
		Person person=bank.getByCNP("1942533684516");
		//Scoatem bani
		bank.withdrawMoney(person, 1, 100);
		assert(bank.getList(person).get(0).getAmount()==1406);
	}

}
