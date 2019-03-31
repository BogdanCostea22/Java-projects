package Model;

public class Comnada {
private int id_comnada;
private String produs;
private String client;

//Constructori
public Comnada(String produs, String client) {
	this.produs = produs;
	this.client = client;
}
public Comnada()
{}

//Gettere si settere
public int getId_comnada() {
	return id_comnada;
}
public void setId_comnada(int id_comnada) {
	this.id_comnada = id_comnada;
}
public String getProdus() {
	return produs;
}
public void setProdus(String produs) {
	this.produs = produs;
}
public String getClient() {
	return client;
}
public void setClient(String client) {
	this.client = client;
}

}
