package controller;
import view.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import model.Polinom;
import model.WrongPolinom;

public class Controller {
private View view;
private Polinom polinom1,polinom2;
private String selectedOperation=new String("");

//Constructor
public Controller(View view)
{this.view=view;
polinom1=new Polinom();
polinom2=new Polinom();
view.calculateButtonListener(new pushCalculate());
view.operationListener(new changeList());
}

//Citirea polinoamelor
private void readPolinom()
{
	try{
		clearPolinoms();
		polinom1.convertToPolinom(view.getTf1());
		}
	catch(WrongPolinom e)
		{
		view.showError(e.toString());
		}
}
private void readPolinoms()
{
	try{
		clearPolinoms();
		polinom1.convertToPolinom(view.getTf1());
		polinom2.convertToPolinom(view.getTf2());
		}
	catch(WrongPolinom e)
		{
		view.showError(e.toString());
		}
}

//Clear polinoms
public void clearPolinoms()
{polinom1.clearList();
 polinom2.clearList();
}


/*3X^4+5X^2-6X^1-89
 * 3X^3+4X^2-123

*/
//Listenerele pentru butoane si combobox
public class changeList implements ActionListener{
	public void actionPerformed(ActionEvent a)
	{
		selectedOperation=view.getJComboBox();
	}
}
public class pushCalculate implements ActionListener{
	public void actionPerformed(ActionEvent a)
	{	switch (selectedOperation) {
	
		case "": break;
		
		//Adunarea celor doua polinoame:
		case "Adunare":
					   readPolinoms();
					   view.showResult(polinom1.addPolinom(polinom2).polinomString());
					   break;
		
		//Scaderea celor doua polinoame
		case "Scadere":
					   readPolinoms();
					   view.showResult(polinom1.subPolinom(polinom2).polinomString());
					   break;
					   
		//Inmultirea celor doua polinoame
		case "Inmultire":
						 readPolinoms();
		 				 view.showResult(polinom1.mulPolinom(polinom2).polinomString());
						 break;
		case "Impartire":
						readPolinoms();
						if(polinom2.polinomDoubleString().equals(""))view.showError("Nu este posibila impartirea la 0!");
						else view.showResult(polinom1.divisionPolinom(polinom2));
						break;
		case"Derivare":
					   readPolinom();
					   view.showResult(polinom1.polinomDerivation().polinomString());
					   break;
		case"Integrare":
						readPolinom();
						view.showResult(polinom1.polinomIntegration().polinomDoubleString());
						break;
	}
	}
}
}
