package Account;

import java.util.Date;

public class SavingAccount extends Account{
private	Date date=new Date();

//Constructori
public  SavingAccount(int amount)
{   super(amount+(amount*5)/100);
//TO-DO:Partea cu dobanda si data
	//this.date=date;
}
public SavingAccount()
{
	super();
}

}
