package view;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
public class View extends JFrame{
//Declararea textfieldurilor si textarea
private JTextField tf1=new JTextField();
private JTextField tf2=new JTextField();
private JTextArea taResult=new JTextArea();

//Declarare array de stringuri
String []s= {"","Adunare","Scadere","Inmultire","Impartire","Derivare","Integrare"};

//Declararea butoanelor
private JComboBox operation=new JComboBox(s);
private JButton calculateButton=new JButton("Calculeaza");

//Declararea labelurilor
private JLabel label1=new JLabel();
private JLabel label2=new JLabel();
//private JLabel labelResult=new JLabel();

//Declararea panelurilor
private JPanel panel1=new JPanel();
private JPanel panel2=new JPanel();
private JPanel panel3=new JPanel();

//Constructorul
public View()
{	//Formare Fereastra
	this.setTitle("Calcul polinoame!");
	this.setVisible(true);
	this.setSize(600,190);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
	//Setare design si componente
	taResult.setEditable(false);
	taResult.setPreferredSize(new Dimension(20,20));

	//Primul panou
	label1.setText("Polonomul I");
	label2.setText("Polinomul II");
	tf1.setPreferredSize(new Dimension(30,30));
	tf2.setPreferredSize(new Dimension(30,30));
	panel1.setLayout(new GridLayout(2,2));
	panel1.setPreferredSize(new Dimension(50,50));
	panel1.add(label1);
	panel1.add(label2);
	panel1.add(tf1);
	panel1.add(tf2);
	
	//Al doilea panou
	panel2.setLayout(new FlowLayout());
	panel2.add(operation);
	panel2.add(calculateButton);
	operation.setSelectedIndex(0);
	operation.setPreferredSize(new Dimension(150,30));
	calculateButton.setPreferredSize(new Dimension(150,30));
	
	//Al treila panou
	panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS));
	panel3.add(panel1);
	panel3.add(panel2);
	panel3.add(taResult);
	
	this.setContentPane(panel3);
	}

//Adaugam listener pentru butoane si comboBox
public void operationListener(ActionListener a)
{operation.addActionListener(a);
}
public void calculateButtonListener(ActionListener a)
{calculateButton.addActionListener(a);
}

//Preluarea polinoamelor
public String getTf1() {
	return tf1.getText().toString();
}
public String getTf2() {
	return tf2.getText().toString();
}
public String getJComboBox() {
	String operationS=operation.getSelectedItem().toString();
	if(operationS.equals("Derivare")||operationS.equals("Integrare"))
		this.hideTextField();
	else 
		this.showTextField();
	return operationS;
}

//Acundem al doilea TextField pentru operatiile de derivare si integrare
public void hideTextField()
{
	tf2.setVisible(false);
	label2.setVisible(false);
}

//Facem ca cel del doilea textField sa apara
public void showTextField()
{
 tf2.setVisible(true);
 label2.setVisible(true);
}

//Afisam o eroare
public void showError(String error)
{
 JOptionPane.showMessageDialog(this,error);
}

//Setam text area
public void showResult(String s)
{
	taResult.setText(s);
}
}
