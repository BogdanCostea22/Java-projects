package Model;



public class Client {
private int id_client;
private String first_name;
private String last_name;
private String username;
private String password;
private String adress;
private String email;
private String phone_number;
private String country;
private String county;
private String city;

//Constructori
public Client(String firstName,String lastName,String username,String password,String adress,String country,String county,String city,String email,String phoneNumber)
{
	this.first_name=firstName;
	this.last_name=lastName;
	this.adress=adress;
	this.city=city;
	this.country=country;
	this.county=county;
	this.phone_number=phoneNumber;
	this.email=email;
	this.username=username;
	this.password=password;
}

public Client() {
}

/**
 * Getere si settere
 * @return
 */
public int getId_client(){
	return id_client;
}
public void setId_client(int id_client) {
	this.id_client = id_client;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
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
public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone_number() {
	return phone_number;
}
public void setPhone_number(String phone_number) {
	this.phone_number = phone_number;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getCounty() {
	return county;
}
public void setCounty(String county) {
	this.county = county;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
//Conver to String
public String printClient()
{
return first_name+" "+last_name+" "+username+" "+password;
}
}