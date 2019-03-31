package view;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class View extends JFrame{
	//Declararea componentelor vizuale
	private JTextField minArrive=new JTextField();
	private JTextField maxArrive=new JTextField();
	private JTextField minService=new JTextField();
	private JTextField maxService=new JTextField();
	private JTextField simulationTime=new JTextField();
	private JTextField numberQueue=new JTextField();
	private JTextField numberClients=new JTextField();
	private JTextArea logEvent=new JTextArea();
	private JScrollPane jsp;
	private JLabel ma=new JLabel("Minimumm arriving time");
	private JLabel mA=new JLabel("Maximum arrive time");
	private JLabel ms=new JLabel("Minimum service time");
	private JLabel mS=new JLabel("Maximum service time");
	private JLabel sm=new JLabel("SimulationTime");
	private JLabel nq=new JLabel("Number of Queue");
	private JLabel nc=new JLabel("Number of Clients");
	private JButton simulate=new JButton("Simulate");
	
	//Declararea panourilor
	private JPanel panel1=new JPanel();
	private JPanel panel2=new JPanel();
	
	//Declararea ferestrei care simuleaza in direct cozile
	private SimulationWindow simulationQueue;
	
	//Constructor
	public View()
	{
		//Setam Fereastra
		this.setTitle("Queue simulation");
	
		this.setSize(500,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		//Setare design si componente
		logEvent.setEditable(false);
		logEvent.setPreferredSize(new Dimension(30,30));
		
		//Panel1
		minArrive.setPreferredSize(new Dimension(30,20));
		maxArrive.setPreferredSize(new Dimension(30,20));
		minService.setPreferredSize(new Dimension(30,20));
		maxService.setPreferredSize(new Dimension(30,20));
		simulationTime.setPreferredSize(new Dimension(30,20));
		numberQueue.setPreferredSize(new Dimension(30,20));
		panel1.setLayout(new GridLayout(7,2));
		panel1.setPreferredSize(new Dimension(170,200));
		panel1.add(ma);
		panel1.add(mA);
		panel1.add(minArrive);
		panel1.add(maxArrive);
		panel1.add(ms);
		panel1.add(mS);
		panel1.add(minService);
		panel1.add(maxService);
		panel1.add(sm);
		panel1.add(nq);
		panel1.add(simulationTime);
		panel1.add(numberQueue);
		panel1.add(nc);
		//panel1.add(simulate);
		panel1.add(numberClients);
		
		//Panel2
		simulate.setPreferredSize(new Dimension(30,20));
		logEvent.setPreferredSize(new Dimension(500,500));
		//Setam scroll barul pentru logEvent
		jsp=new JScrollPane(logEvent,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		logEvent.setLineWrap(true);
		logEvent.setWrapStyleWord(true);
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		panel2.setPreferredSize(new Dimension(530,500));
		panel2.add(panel1);
		panel2.add(simulate);
		
		//panel2.add(logEvent);
		panel2.add(jsp);
		this.setVisible(true);
		this.setContentPane(panel2);
	}
	
	//Ascultatori
	public void addListenerSimulation(ActionListener a)
	{
		simulate.addActionListener(a);
	}

	//Getere pentru textgielduri
	public String getMinArrive() {
		return minArrive.getText().toString();
	}
	public String getMaxArrive() {
		return maxArrive.getText().toString();
	}
	public String getMinService() {
		return minService.getText().toString();
	}
	public String getMaxService() {
		return maxService.getText().toString();
	}
	public String getNumberQueue() {
		return numberQueue.getText().toString();
	}
	public String getSimulationTime(){
		return simulationTime.getText().toString();
	}
	public String getNumberOFClients(){
		return numberClients.getText().toString();
	}
	
	//Adaugam un camp nou in logEvent
	public void addNewMessage(String text)
	{
		logEvent.setText(logEvent.getText().toString()+"\n"+text);
	}
	
	//Stergem mesajele afiste in JTextArea
	public void clearLogEvent()
	{
		this.logEvent.setText("");
	}
	
	//Afisam fereasta pentru simulare in directa a cozilor
	public void showSimulation(int nrQueues)
	{
		simulationQueue=new SimulationWindow(nrQueues);
	}
	
	//Reactualizam cozile cand s-a adaugat s-au s-a eleminat un element;
	public void refreshSimulation(int nrQueue,String clients)
	{
		simulationQueue.refreshQueue(nrQueue,clients);
	}
	
	//Afisam rezultatul dupa simulare
	public void resultShow(int i,String text)
	{
		this.simulationQueue.showResult(i, text);
	}

	//Inchidem fereastra de simulare actuala
	public void closeSimulationWindow()
	{
		this.simulationQueue.closeWindow();
	}
	//Afisam o eroare in caz ca utilizatorul nu introduce corect un camp
	public void showError(String error)
	{
		JOptionPane.showMessageDialog(this, error);
	}
}
