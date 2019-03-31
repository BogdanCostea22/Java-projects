package model;
import java.util.*;

import view.View;

public class Scheduler implements Runnable{
private Queue []queues;
private int simulationTime;
private int numberQueue;
private int numberOFClients;
private int minimumArrive;
private int maximumArrive;
private int minimumService;
private int maximumService;
private View view;
private int maxNumberOFClients;
private int peakTime;
//Constructori
public Scheduler(int simulationTime,int numberQueue,int minimumArrive,int maximumArrive,int minimumService,int maximumService,int numberOFClients,View view)
{
	this.queues=new Queue[numberQueue];
	this.simulationTime=simulationTime;
	this.numberQueue=numberQueue;
	this.numberOFClients=numberOFClients;
	this.minimumArrive=minimumArrive;
	this.maximumArrive=maximumArrive;
	this.minimumService=minimumService;
	this.maximumService=maximumService;
	this.view=view;
	this.maxNumberOFClients=0;
	this.peakTime=0;
	
	//Cream listele si pornim treadurile pentru fiecare coada
	for(int i=0;i<this.numberQueue;i++)
		{
			this.queues[i]=new Queue(i+1,view);
			queues[i].start();
		}
}
//Gasim Coada care are waitingTime cel mai mic si retunam indexul ei
int bestQueue()
{
	int i=0;
	
	int min=queues[0].getWaitingTime();
	System.out.println("Minimul"+min+"Locat la pozitia"+i);
	//Parcurgem toate cozile pentru a determina coada cu waiting time-ul cel mai mic si vom retunat indecele ei
	for(int j=1;j<numberQueue;j++)
		{int min1=queues[j].getWaitingTime();
		if(min1<min&&min!=min1)
			{
			System.out.println("Minimul"+min1+"Indexul "+j);
			min=min1;
			i=j;
			}
		}
	return i;
}

//Metoda care adauga un nou client in coada
public void addClient(int clientID,int timeCounter)
{
	Random rand=new Random();
	//Generam timpul de servire
	int newClientServiceTime=rand.nextInt(this.maximumService-this.minimumService+1)+this.minimumService;
	Client client=new Client(clientID,timeCounter,newClientServiceTime);
	
	//Adauagam clientul creat in coada cea mai scurta
	int indexMinimumQueue=bestQueue();
	
	try
	{
		queues[indexMinimumQueue].adauga(client);
	}
	catch (InterruptedException e) 
	{
	System.out.println(e.toString());	
	}
	
	//Aflam un numarul de clienti de la momentul actual 
	int newClientsNumber=this.totalNumberOfClients();
	if(newClientsNumber>this.maxNumberOFClients)
		{
		this.maxNumberOFClients=newClientsNumber;
		this.peakTime=timeCounter;
		}
}

//Metoda Run implementata de Runnable
public void run()
{
	int timeCounter=1;
	int clientID=1;
	while(timeCounter<this.simulationTime)
	{
		//Timpul de asteptare intre clienti
		int waitingTimeBetweenCustomers;
		
		//Creeam un nou client
		//Genram un numar de clienti care v-or intra la momentul actual
		Random rand=new Random();
		int randNumber=0;
		if(numberOFClients>0)randNumber=rand.nextInt(this.numberOFClients);
		
		//Creeam mai multi clienti sau numai unul altfel
		if(randNumber!=0&&this.numberOFClients>randNumber)
		{
			//Scadem numarul de clienti pe care ii vom creea din numarul pe care trebuie sa-l creeam
		this.numberOFClients=this.numberOFClients-randNumber;
			while(randNumber!=0)
				{
				this.addClient(clientID, timeCounter);		
				//Marim id clientului
				clientID++;
				randNumber--;
				}
		
		//Setam un nou numar random de asteptare
		waitingTimeBetweenCustomers=rand.nextInt(this.maximumArrive-this.minimumArrive)+this.minimumArrive;
		}
		//Daca am creat deja numarul de clienti vom mari doar timpul de simulare
		else 
			{
				if(this.numberOFClients==0)
				waitingTimeBetweenCustomers=1;
				//Adaugam numai un client
				else
				{
					this.addClient(clientID, timeCounter);
					//Marim id clientului
					clientID++;
					waitingTimeBetweenCustomers=rand.nextInt(this.maximumArrive-this.minimumArrive)+this.minimumArrive;
					this.numberOFClients--;
				}
				
			}
		try 
		{
			Thread.sleep(waitingTimeBetweenCustomers*1000);
		} catch (InterruptedException e) 
		{	
			System.out.println(e.toString());
		}
		System.out.println("Timpul inainte sa astepte"+waitingTimeBetweenCustomers+" timpul "+timeCounter);
		//Marim timpul de simulare
		timeCounter=timeCounter+waitingTimeBetweenCustomers;
		System.out.println("Timpul dupa asteptare"+timeCounter);
	}
	
	showResults();
	
}

//Afisam rezultatele obtinute
public void showResults()
{
	//Afisam in SimulationWinfow
	//Afisam peak time
	view.resultShow(this.numberQueue,"Peak Time= "+this.peakTime+" a avut un numar de ="+this.maxNumberOFClients);
	
	for(int i=0;i<this.numberQueue;i++)
		view.resultShow(i,"AveraTime for queue"+(i+1)+" is:"+queues[i].showAverageTime(i));
}

//Functie care returneaza numarul de clienti din toate listele pentru a calcula mai usor peakTime
public int totalNumberOfClients()
{
	int nr=0;
	for(int i=0;i<this.numberQueue;i++)
		nr=nr+queues[i].clientsNumber();
	return nr;
	
}

}
