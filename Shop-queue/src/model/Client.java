package model;

public class Client {
//Declararea campurilor
	private int id,arrivalTime,processTime;
//Constructori pentru Client
public Client()
{
this.id=0;
this.arrivalTime=0;
this.processTime=0;
}
public Client(int id,int arrival,int processTime)
{
this.id=id;
this.arrivalTime=arrival;
this.processTime=processTime;
}
//Gettere si settere pentru client
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getArrivalTime() {
	return arrivalTime;
}
public void setArrivalTime(int arrivalTime) {
	this.arrivalTime = arrivalTime;
}
public int getProcessTime() {
	return processTime;
}
public void setProcessTime(int processTime) {
	this.processTime = processTime;
}

//Convert to String
public String clientToString()
{
	return "Client "+this.id+" arrival time "+this.arrivalTime+" process time "+this.processTime; 
}
}
