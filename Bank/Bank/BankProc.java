package Bank;

import Account.Account;
import Person.Person;

public interface BankProc {
	/**
	 * Metoda care adauga o noua persoana
	 * @pre p!=null
	 * @post lengthHashMap()==lengthHashMap()@pre+1
	 * @param person
	 * @return
	 */
	public boolean addPerson(Person person);
	/**
	 * Stergerea unei persoane din hashMap
	 * @pre person!=null
	 * @post lengthHashMap()==lengthHashMap()@pre-1
	 * @param person
	 * @return 
	 */
	public boolean deletePerson(Person person);
	/**
	 * Adaugarea unui nou cont
	 * @pre a!=null && p!=null
	 * @post listSize()==listSize()@pre+1
	 * @param a
	 * @param p
	 */
	public void addholderAccount(Account a,Person p);
	/**
	 * Stergem un cont din hashMap
	 * @pre person!=null && a!=null
	 * @post listSize()==listSize()@pre-1
	 * @param a
	 * @param p
	 * @return
	 */
	public boolean removeHolderAccount(Account a,Person p);
	/**
	 * Adauga bani intr-un anumit cont
	 * @pre person!=null && nr>0 && money>0
	 *@param person
	 *@param nr
	 *@param money
	 *@return
	 */
	public void addMoney(Person person,int nr,int money);
	/**
	 * Scoate bani dintr-un cont
	 * @pre person!=null && nr>0 && money>0
	 * @param person
	 * @param nr
	 * @param money
	 * @return
	 */
	public boolean withdrawMoney(Person person,int nr,int money);
	
}
