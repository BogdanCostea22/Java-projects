package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import model.Scheduler;
import view.View;
public class Controller {
private Scheduler scheduler;
private String minA,maxA,minS,maxS,sim,nrQ,nrC;
private View view;

//Constructori
public Controller(View view)
{
	this.view=view;
	view.addListenerSimulation(new pushSimulation());
}

//Stergem continul din campurile completate in procesul anterior
public void clearAll()
{
	//Stergem continutul din LogEvent
	view.clearLogEvent();
}

public class pushSimulation implements ActionListener{
	
	public void actionPerformed(ActionEvent a)
	{
		//Stergem continutul din campuri
		clearAll();
		
		//Preluam datele din TextFielduri
		minA=view.getMinArrive();
		maxA=view.getMaxArrive();
		minS=view.getMinService();
		maxS=view.getMaxService();
		sim=view.getSimulationTime();
		nrQ=view.getNumberQueue();
		nrC=view.getNumberOFClients();
		//Verificam daca datele introduse sunt corecte
		if(minA.length()>0&&minA.length()>0&&minS.length()>0&&maxS.length()>0&&sim.length()>0&&nrQ.length()>0)
		{
			try{
				int minArrive=Integer.parseInt(minA);
				int maxArrive=Integer.parseInt(maxA);
				int minService=Integer.parseInt(minS);
				int maxService=Integer.parseInt(maxS);
				int simulation=Integer.parseInt(sim);
				int numberQueue=Integer.parseInt(nrQ);
				int numberClients=Integer.parseInt(nrC);
				
				
				//Verificam daca sunt respectate regulile
				boolean check=true;
				if(minArrive>0&&maxArrive>0&&minService>0&&maxService>0&&simulation>0&&numberQueue>0&&numberClients>0)
				{
				if(minArrive>maxArrive) 
					{
					check=false;
					view.showError("Campurile introduse nu sunt corecte!");
					}
				if(minService>maxService)
					{
					check=false;
					view.showError("Campurile introduse nu sunt corecte!");
					}
				if(check)
					{
					view.showSimulation(numberQueue);
					scheduler=new Scheduler(simulation,numberQueue,minArrive,maxArrive,minService,maxService,numberClients,view);
					Thread threadSimulation=new Thread(scheduler);
					threadSimulation.start();
					}
				}
				else view.showError("Campurile introduse contin numere negative sau egale cu 0");
			}catch(NumberFormatException e){
				//Daca datele introduse nu sunt corecte vom afisa un mesaj
				view.showError("Campurile complatate nu sunt numere\n"+e.toString());
			}
		}
			else view.showError("Nu sunt completate toate campurile");
	}
}
/*public static void main(String []args)
{
	View view=new View();
/*Scanner in=new Scanner(System.in);
System.out.println("Minimumm arriving time");
int minArrive=in.nextInt();
System.out.println("Maximum arrive time");
int maxArrive=in.nextInt();
System.out.println("Minimum service time");
int minService=in.nextInt();
System.out.println("Maximum service time");
int maxService=in.nextInt();
System.out.println("Simulation time");
int simulationTime=in.nextInt();
System.out.println("Number of Queue");
int nrQueue=in.nextInt();
in.close();
Scheduler scheduler=new Scheduler(simulationTime,nrQueue,minArrive,maxArrive,minService,maxService);
Thread thread=new Thread(scheduler);
thread.start();

}
*/
}