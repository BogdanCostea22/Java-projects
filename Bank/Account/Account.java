package Account;

import java.util.Observable;

public class Account extends Observable implements java.io.Serializable{

private int amount;

//Constructor
public Account(int money)
{
	this.amount=money;
}
public Account()
{
	this.amount=0;
}

//Getter
public int getAmount()
{
	return amount;
}

//Setter
public void setAmount(int amount)
{
	this.amount=amount;
}

//Method for add money
public void addMoney(int newAmount)
{
	this.amount+=newAmount;
	setChanged();
	notifyObservers(new Integer(newAmount));
}

//Method for withdraw money
public boolean withdrawMoney(int cantity)
{
	if(cantity>amount)return false;
	this.amount-=cantity;
	setChanged();
	notifyObservers(new String(Integer.toString(cantity)));
	return true;
}

}
