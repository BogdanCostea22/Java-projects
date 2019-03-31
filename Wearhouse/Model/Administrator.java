package Model;


public class Administrator {
private int id_administrator;
private String first_name;
private String last_name;
private String username;
private String password;

//Constructori
public Administrator(String firstName,String lastName,String username,String password)
{
	this.first_name=firstName;
	this.last_name=lastName;
	this.username=username;
	this.password=password;
}

public Administrator() {
}

//Gettere si Settere
public int getId_administrator() {
	return id_administrator;
}
public void setId_administrator(int id_administrator) {
	this.id_administrator = id_administrator;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String firstName) {
	this.first_name = firstName;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String lastName) {
	this.last_name = lastName;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String printAdmin(){
	return first_name+" "+last_name+" "+username+" "+password;
}

}