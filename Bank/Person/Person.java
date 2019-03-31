package Person;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTable;

public class Person implements java.io.Serializable,Observer{
	//Declaratii de  variabile
	private String firstName;
	private String lastName;
	private String CNP;
	private String phoneNumber;
	//Constructori
	public Person()
	{
	}
	public Person(String firstName,String lastName,String CNP,String phoneNumber)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.CNP=CNP;
		this.phoneNumber=phoneNumber;
	}
	
	//Gettere si Settere
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCNP() {
		return CNP;
	}
	public void setCNP(String cNP) {
		CNP = cNP;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	//Methoda de printare
	public String personToString()
	{
		return ""+this.firstName+" "+this.lastName+" "+this.CNP+" "+this.phoneNumber;
	}
	
	//Metoda equals
	public boolean equals(Object obj)
	{
		if(obj==this)return true;
		if(!(obj instanceof Person))return false;
		else
			{
			Person p=(Person) obj;
			if(p.getFirstName().equals(firstName)&&p.getLastName().equals(lastName)&&p.getPhoneNumber().equals(phoneNumber))return true;
			return false;	
			}
	}
	
	//Metoda de hashcode
	public int hashCode()
	{	
		
		//System.out.println(" Numar:"+Integer.parseInt(CNP.substring(5, 12)));
		return Integer.parseInt(CNP.substring(5,12));
	}
	
	//Metoda pentru creearea unui nou tabel
	public static JTable makeNewTable(List<Person> list)
	{
		System.out.println("DADADA");
		
		Object [][]data=new Object[list.size()][4];
		int i=0;
		for(Person person:list)
		{
			data[i][0]=person.getFirstName();
			data[i][1]=person.getLastName();
			data[i][2]=person.getCNP();
			data[i][3]=person.getPhoneNumber();
			i++;
		}
		Object []name=new Object[4];
		name[0]="Fisrt Name";
		name[1]="Last Name";
		name[2]="CNP";
		name[3]="Phone Number";
		for(int j=0;j<i;j++)
		{for(int k=0;k<4;k++)
			System.out.print(data[j][k].toString()+"     ");
		System.out.println();
		}
		for(i=0;i<4;i++)
			System.out.print(name[i]);
		JTable table=new JTable(data,name);
		return table;
	}
	
	//Functia update pentru observable
	public void update(Observable obs,Object obj)
	{
		if(obj instanceof Integer)
		{
			int amount=((Integer) obj).intValue();
			System.out.println("S-a adaugat suma"+amount);
		}
		else{
			String amount=obj.toString();
			System.out.println("S-a retras suma "+amount);
		}
	}
}
