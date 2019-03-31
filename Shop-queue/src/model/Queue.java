package model;

import java.util.*;
import view.View;

public class Queue extends Thread{
	// Declararea listei de clienti si al timpului de asteptare
	private ArrayList<Client> clients;
	private int waitingTime;
	private int numberOFSecond;
	private int id;
	private int clientId;
	private View view;
	private int numberOFClients;
	private int totalWaitingTime;
	// Declararea constructorilor
	public Queue(int id,View view)// String name)
	{
		this.clients = new ArrayList<Client>();
		this.waitingTime = 0;
		this.id = id;
		this.view=view;
		this.numberOFClients=0;
		this.totalWaitingTime=0;
		this.numberOFSecond=0;
		
	}

	// Getterul pentru waiting time avem nevoie de el pentru determinare cozi in
	// care trebuie sa adaugam un nou client
	public synchronized int getWaitingTime() {
		return this.waitingTime;
	}

	// Metoda run specfica interfetei Runnable
	public void run() {
		try {
			
			while(true)
			{
				//Luam informatiile legate de primul client din lista de clienti
				this.takeInformation();
				sleep(1000 * numberOFSecond);
				
				//Afisam un mesaj in LogEvent cum ca primul client a fost procesat
				this.view.addNewMessage("Clientul " + clientId + "a fost servit la casa " + this.id);
				this.waitingTime=this.waitingTime-this.numberOFSecond;
				
				//Stergem elementul si reactualizam simulator de cozi
				stergeClient();
				view.refreshSimulation(this.id, convertClientsListToString());
			}
		} catch (InterruptedException e) {
			System.out.println("Intrerupere");
			System.out.println(e.toString());
		}
	}

	public synchronized void takeInformation() throws InterruptedException
	{
		// Daca nu avem clienti la coada punem threadul sa astepte
		while(clients.size()==0)
			wait();
		//Preluam informatiile din primul element din coada
		Client client=clients.get(0);
		numberOFSecond = client.getProcessTime();
		clientId=client.getId();
		
				notifyAll();		
		
	}
	//Eliminam primul client din lista de clienti,care a fost deja servit la coada
	public synchronized void stergeClient(){
		// Eliminam primul element si aflam
		clients.remove(0);	
	}

	//Aduagam un nou client in lista de clienti
	public synchronized void adauga(Client client) throws InterruptedException {
		clients.add(client);
		// Trebuie anuntat intr-un text field area ca a fot adugat un nou element la
		// aceasta coada
		this.view.addNewMessage("Clientul" + client.getId() + " a fost aduagat la coada " + this.id+"Timpul de asteptare"+client.getProcessTime());
		this.waitingTime = this.waitingTime + client.getProcessTime();
		this.numberOFClients++;
		this.totalWaitingTime+=client.getProcessTime();
		view.refreshSimulation(this.id,convertClientsListToString());
		notifyAll();
	}
	
	// Functie care trimite o lista de clienti
	public synchronized String convertClientsListToString()
	{
		String listClients="";
		
		for(Client client:clients)
		{
			System.out.print(client.getId()+ "  ");
			listClients=listClients+" "+client.getId();
		}
		System.out.println();
		return listClients;
	}
	
	//Returneaza numarul de clienti din lista
	public synchronized int clientsNumber()
	{
		return this.clients.size();
	}
	
	//Returneaza AverageTime pentru fiecare coada
	public synchronized String showAverageTime(int i)
	{
		double partialResult=(double)this.totalWaitingTime/this.numberOFClients;
		Double result=new Double(Math.round(partialResult*100)/100.0);
		return result.toString();
	}
}