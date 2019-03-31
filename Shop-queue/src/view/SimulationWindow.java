package view;
import java.awt.*;
import javax.swing.*;

public class SimulationWindow extends JFrame{
private JLabel label[];
private JLabel result[];
private JTextField queue[];
private JPanel panel1=new JPanel();
private JPanel panel2=new JPanel();
//Constructor
public SimulationWindow(int nrQueues)
{
	//Creeam vectori de JLabek si JTextField
	label=new JLabel[nrQueues];
	result=new JLabel[nrQueues+1];
	queue=new JTextField[nrQueues];
	
	//Setam layoutul panelului
	panel1.setLayout(new GridLayout(nrQueues,2));
	panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
	
	//Setam labelurile si JTextFieldurile pentru fiecare coada in parte, dupa care le adaugam in fereastra
	for(int i=0;i<nrQueues;i++)
	{
		label[i]=new JLabel("Queue"+(i+1));
		queue[i]=new JTextField();
		queue[i].setEditable(false);
		queue[i].setPreferredSize(new Dimension(100,30));
		panel1.add(label[i]);
		panel1.add(queue[i]);
	}
	panel2.add(panel1);
	
	//Adaugam JLabelurile pentru Rezultate
	for(int i=0;i<nrQueues;i++)
		{
			result[i]=new JLabel("Result"+(i+1));
			panel2.add(result[i]);
		}
	result[nrQueues]=new JLabel("Result Queues");
	panel2.add(result[nrQueues]);
	panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	//Seteam fereasra
	this.setContentPane(panel2);
	this.setSize(300,30*(nrQueues*2+1));
	this.setTitle("LIVE simulation");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setVisible(true);
}

//Punem sau reactualizam informatiile din cozi
public void refreshQueue(int nrQueue,String clientsList)
{
	queue[nrQueue-1].setText(clientsList);
}

//Inchidem fereastra deschisa
public void closeWindow()
{
	this.dispose();
}

//Scriem  rezultatul averageTime pentru fiecare coada si peakTime
public void showResult(int numberOFQueue,String text)
{
	result[numberOFQueue].setText(text);
}
}