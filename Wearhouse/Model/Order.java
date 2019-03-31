package Model;


public class Order {
private int id_order;
private String id_product;
private String id_client;

//Constructori
public Order(String id_client,String id_product)
{
	this.id_product=id_product;
	this.id_client=id_client;
}
public Order()
{}
//Gettere si Settere
public int getId_order() {
	return id_order;
}
public void setId_order(int id_order) {
	this.id_order = id_order;
}
public String getId_product() {
	return id_product;
}
public void setId_product(String id_product) {
	this.id_product = id_product;
}
public String getId_client() {
	return id_client;
}
public void setId_client(String id_client) {
	this.id_client = id_client;
}




}

