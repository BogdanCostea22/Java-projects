package TEST_BANK;

import Account.Account;
import Account.SavingAccount;
import Account.SpendingAccount;
import Bank.Bank;
import Person.Person;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(Bank bank)
    {
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
        bank.printAccount(p);
        bank.printAccount(p1);
        //bank.takePerson();
    }
}
