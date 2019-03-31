package Main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Account.Account;
import Account.SavingAccount;
import Account.SpendingAccount;
import Bank.Bank;
import Person.Person;
import TEST_BANK.App;

import javax.swing.JCheckBox;

public class View {
	//Declarari
	private JFrame frame;
	private JTabbedPane tabbedPane;
	private JTable tablePerson;
	private JTable tableAccount;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfCNP;
	private JTextField tfPhoneNumber;
	private JPanel personTab;
	private JPanel accountTab;
	private JCheckBox cbSavingAccount;

	//Persoana si countul asupra careia se executa anumite operatii
	private Person person;
	private Account account;
	private int nrAccount;
	//Declararea unei bancii
	Bank bank=new Bank();
	private JTextField tfAmount;
	private JTextField tfAmountEdit;
	private JTextField tfEditFirstName;
	private JTextField tfEditLastName;
	private JTextField tfEditPhoneNumber;
	private JTextField tfMoney;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, -49, 434, 310);
		frame.getContentPane().add(tabbedPane);
		
		//Primul tab
		JPanel mainPanel=new JPanel();
		tabbedPane.add("Main",mainPanel);
		mainPanel.setLayout(null);
		
		JButton buttonPerson = new JButton("Person");
		buttonPerson.setBounds(149, 38, 106, 23);
		buttonPerson.addActionListener(new pushPersonButton());
		mainPanel.add(buttonPerson);
		
		JButton buttonAccount = new JButton("Account");
		buttonAccount.setBounds(149, 110, 106, 23);
		buttonAccount.addActionListener(new pushAccountButton());
		mainPanel.add(buttonAccount);
		
		JButton btnAddPerson = new JButton("Add Person");
		btnAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTab(3);
			}
		});
		btnAddPerson.setBounds(149, 72, 106, 23);
		mainPanel.add(btnAddPerson);
		
		//Tabul pentru Person
		personTab=new JPanel();
		tabbedPane.add("PersonTab", personTab);
		personTab.setLayout(null);
		
		//Dimensiunea JTableului;
		
		//personTab.add(tablePerson);
		
		JButton btnDeletePerson = new JButton("Delete");
		//Listener pentru stergerea unei persoane
		btnDeletePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(bank.deletePerson(person)==true) 
				{	//Afisam mesajul si schimbam tabul
					showError("Persoana a fost stearsa cu succes!");
					setTab(0);
					//Serializam noinele modificari aduse la banca
					serializare(bank);
				}
				else
				{
					showError("Persoana nu a putut fi stearsa!");
					setTab(0);
				}
			}
		});
		btnDeletePerson.setBounds(315, 41, 104, 23);
		personTab.add(btnDeletePerson);
		
		JButton btnEditPerson = new JButton("Edit");
		btnEditPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(5);
			}
		});
		btnEditPerson.setBounds(315, 75, 104, 23);
		personTab.add(btnEditPerson);
		
		//Butonul pentru adaugarea unui nou cont
		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(4);
			}
		});
		btnAddAccount.setBounds(315, 109, 104, 23);
		personTab.add(btnAddAccount);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setTab(0);
			}
		});
		btnNewButton_1.setBounds(315, 143, 104, 23);
		personTab.add(btnNewButton_1);
		
		//Tabul pentru Account
		accountTab=new JPanel();
		tabbedPane.add("AccountTab", accountTab);
		accountTab.setLayout(null);
		
		JButton btnDeleteAccount = new JButton("Delete");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(bank.removeHolderAccount(account, person))
				{
					showError("Account was succesfully deleted!");
					setTab(0);
					serializare(bank);
				}
				else
				{
					showError("Account wasn't deleted!");
					setTab(0);
				}
			}
		});
		btnDeleteAccount.setBounds(314, 11, 89, 23);
		accountTab.add(btnDeleteAccount);
		
		JButton btnEditAccount = new JButton("Edit");
		btnEditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(6);
			}
		});
		btnEditAccount.setBounds(314, 105, 89, 23);
		accountTab.add(btnEditAccount);
		
		JButton btnBack_2 = new JButton("Back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(0);
			}
		});
		btnBack_2.setBounds(314, 133, 89, 23);
		accountTab.add(btnBack_2);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new pushWithdrawMoney());
		btnWithdraw.setBounds(314, 37, 89, 23);
		accountTab.add(btnWithdraw);
		
		JButton btnAddMoney = new JButton("Add money");
		btnAddMoney.addActionListener(new pushAddMoney());
		btnAddMoney.setBounds(314, 71, 89, 23);
		accountTab.add(btnAddMoney);
		
		tfMoney = new JTextField();
		tfMoney.setBounds(314, 167, 86, 20);
		accountTab.add(tfMoney);
		tfMoney.setColumns(10);
		
		//Tabul pentru adaugarea unui nou client
		JPanel addPerson=new JPanel();
		tabbedPane.add("Add New Person",addPerson);
		addPerson.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 11, 67, 14);
		addPerson.add(lblFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setBounds(10, 36, 86, 20);
		addPerson.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(10, 59, 86, 14);
		addPerson.add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setBounds(10, 83, 86, 20);
		addPerson.add(tfLastName);
		tfLastName.setColumns(10);
		
		JLabel lblCnp = new JLabel("CNP:");
		lblCnp.setBounds(10, 113, 46, 14);
		addPerson.add(lblCnp);
		
		tfCNP = new JTextField();
		tfCNP.setBounds(10, 134, 86, 20);
		addPerson.add(tfCNP);
		tfCNP.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(10, 159, 86, 14);
		addPerson.add(lblPhoneNumber);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setBounds(10, 176, 86, 20);
		addPerson.add(tfPhoneNumber);
		tfPhoneNumber.setColumns(10);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new pushCreatePerson());
		btnCreateAccount.setBounds(127, 199, 115, 23);
		addPerson.add(btnCreateAccount);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(0);
			}
		});
		btnBack_1.setBounds(252, 199, 115, 23);
		addPerson.add(btnBack_1);
		
		//Tabul oentru adaugarea unui nou cont
		JPanel btnaddAccount=new JPanel();
		tabbedPane.add("Add New Account", btnaddAccount);
		btnaddAccount.setLayout(null);
		
		cbSavingAccount = new JCheckBox("Saving Account");
		cbSavingAccount.setBounds(258, 70, 107, 23);
		btnaddAccount.add(cbSavingAccount);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(63, 71, 107, 20);
		btnaddAccount.add(tfAmount);
		tfAmount.setColumns(10);
		
		JLabel lblAmountOfMoney = new JLabel("Amount Of Money :");
		lblAmountOfMoney.setBounds(63, 46, 107, 14);
		btnaddAccount.add(lblAmountOfMoney);
		
		JButton btnNewButton = new JButton("Add Account");
		btnNewButton.addActionListener(new pushCreateAccount());
		btnNewButton.setBounds(149, 128, 112, 23);
		btnaddAccount.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(0);
			}
		});
		btnBack.setBounds(149, 162, 112, 23);
		btnaddAccount.add(btnBack);
		
		//Taburile pentru editare
		
		//Person
		JPanel personEditTab=new JPanel();
		tabbedPane.add("Edit Person", personEditTab);
		personEditTab.setLayout(null);
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.addActionListener(new pushEditClient());
		btnUpdate_1.setBounds(314, 80, 89, 23);
		personEditTab.add(btnUpdate_1);
		
		JButton btnBack_3 = new JButton("Back");
		btnBack_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(0);
			}
		});
		btnBack_3.setBounds(314, 114, 89, 23);
		personEditTab.add(btnBack_3);
		
		JLabel lblFirstName_1 = new JLabel("First Name:");
		lblFirstName_1.setBounds(42, 30, 76, 14);
		personEditTab.add(lblFirstName_1);
		
		JLabel lblLastName_1 = new JLabel("Last Name:");
		lblLastName_1.setBounds(42, 84, 76, 14);
		personEditTab.add(lblLastName_1);
		
		JLabel lblPhoneNumber_1 = new JLabel("Phone Number:");
		lblPhoneNumber_1.setBounds(39, 136, 89, 14);
		personEditTab.add(lblPhoneNumber_1);
		
		tfEditFirstName = new JTextField();
		tfEditFirstName.setBounds(42, 55, 86, 20);
		personEditTab.add(tfEditFirstName);
		tfEditFirstName.setColumns(10);
		
		tfEditLastName = new JTextField();
		tfEditLastName.setBounds(42, 100, 86, 20);
		personEditTab.add(tfEditLastName);
		tfEditLastName.setColumns(10);
		
		tfEditPhoneNumber = new JTextField();
		tfEditPhoneNumber.setBounds(42, 155, 86, 20);
		personEditTab.add(tfEditPhoneNumber);
		tfEditPhoneNumber.setColumns(10);
		
		//Account
		JPanel accountEditTab=new JPanel();
		tabbedPane.add("Edit Account", accountEditTab);
		accountEditTab.setLayout(null);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new pushEditAccount());
		btnUpdate.setBounds(182, 117, 89, 23);
		accountEditTab.add(btnUpdate);
		
		tfAmountEdit = new JTextField();
		tfAmountEdit.setBounds(51, 68, 86, 20);
		accountEditTab.add(tfAmountEdit);
		tfAmountEdit.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(59, 43, 78, 14);
		accountEditTab.add(lblAmount);
		
		//Deserializam banka 
		bank=deSerialized();
		//App.main(bank);
		//serializare(bank);
		//Adaugam listener pentru tabela de Persoane si Conturi
		
	}
	
	//Listener pentru create new Person
	public class pushCreatePerson implements ActionListener{
		
		public void actionPerformed(ActionEvent e)
		{
			//Preluam datele introduse in view;
			String firstName=tfFirstName.getText().toString();
			String lastName=tfLastName.getText().toString();
			String CNP=tfCNP.getText().toString();
			String phoneNumber=tfPhoneNumber.getText().toString();
		
			//Verificam daca datele care au fost introduse sunt corecte 
			if(firstName.length()>0&&lastName.length()>0&&CNP.length()>0&&phoneNumber.length()>0)
			{
			//Verificam daca CNPul are exact 12 elemente
			if(CNP.length()!=13)showError("CNP doesn't have enough caracters!");
			else {
				//Cream o noua persoana
				try {
				//Verificam daca numarul intrdus este format doar din cifre
					int nr=Integer.parseInt(phoneNumber);
				
				//Adaugam clientul in hash map
				Person p=new Person(firstName,lastName,CNP,phoneNumber);
				bank.addPerson(p);
				serializare(bank);
				bank.takePerson();
				setTab(0);
				}catch(NumberFormatException ex){
					ex.printStackTrace();
					showError("Phone number contains caracters that are restricted!");
				}
			}
		}
			else showError("Please complete all the fields!");
		}
	}
	
	//Listener pentru adaugarea unui nou cont
	public class pushCreateAccount implements ActionListener{
		
		public void actionPerformed(ActionEvent e)
		{
			if(tfAmount.getText().length()>0)
			{
			if(cbSavingAccount.isSelected())
			{	
				SavingAccount sv=new SavingAccount(Integer.parseInt(tfAmount.getText().toString()));
				bank.addholderAccount(sv, person);
			}
			else
			{
				SpendingAccount sa=new SpendingAccount(Integer.parseInt(tfAmount.getText().toString()));
				bank.addholderAccount(sa, person);
			}
				showError("Contul a fost creat!");
				serializare(bank);
				setTab(0);
			}
			else showError("Trebuie sa introduci canititatea de bani!");
		}
	}	
	
	//Listener pentru editare
	//Person
	public class pushEditClient implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			if(person==null)showError("Please choose a person!");
			else{
					Person newPerson=new Person();
					newPerson.setCNP(person.getCNP());
					newPerson.setFirstName(person.getFirstName());
					newPerson.setLastName(person.getLastName());
					newPerson.setPhoneNumber(person.getPhoneNumber());
					String fn=tfEditFirstName.getText().toString();
					String ln=tfEditLastName.getText().toString();
					String ph=tfEditPhoneNumber.getText().toString();
					if(fn.length()>0)newPerson.setFirstName(fn);
					if(ln.length()>0)newPerson.setLastName(ln);
					if(ph.length()>0)newPerson.setPhoneNumber(ph);
					System.out.println(newPerson.personToString());
					try{
						int nr=Integer.parseInt(newPerson.getPhoneNumber());
						bank.updatePerson(newPerson);
						serializare(bank);
					}catch(NumberFormatException ex)
					{
						showError("Phone number is not correct!");
						ex.printStackTrace();
					}
				}
		setTab(0);
		}
	}
	//Acount
	public class pushEditAccount implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			if(account==null) {
				showError("Need to select an account before edit one!");
				setTab(0);
			}
			else
			{
				if(tfAmountEdit.getText().length()==0)showError("Need to insert new amount!");
						else{
							try {
									int nr=Integer.parseInt(tfAmountEdit.getText().toString());
									bank.updateAccount(nrAccount,person,nr);
									showError("Account updated!");
									serializare(bank);
							}
							catch(NumberFormatException ex)
							{
								showError("This amount is not a number!");
								ex.printStackTrace();
							}
							finally{
								setTab(0);
							}
			}
			}
		}
	}
	
	//Listener pentru add/withdraw money
	//Add money
	public class pushAddMoney implements ActionListener{
	
		public void actionPerformed(ActionEvent e)
		{
			if(account==null)showError("Need to set an account!");
			else{
				String money=tfMoney.getText().toString();
					if(money.length()==0)showError("Please insert amount of money!");
					else{
						try{
							int nr=Integer.parseInt(money);
							bank.addMoney(person, nrAccount, nr);
							serializare(bank);
							showError("Money was add to selected account!");
							setTab(0);
						}catch(NumberFormatException ex)
						{
							showError("Please insert a number!");
							ex.printStackTrace();
						}
					}
						
			}
		}
	}
	//Withdraw money
	public class pushWithdrawMoney implements ActionListener{
		
		public void actionPerformed(ActionEvent e)
		{

			if(account==null)showError("Need to set an account!");
			else{
				String money=tfMoney.getText().toString();
				/*if(account instanceof SavingAccount)showError("Amount is incorect!");
				else {	*/
				if(money.length()==0)showError("Please insert amount of money!");
					else{
						try{
							int nr=Integer.parseInt(money);
							if(bank.withdrawMoney(person, nrAccount, nr))
							{serializare(bank);
							showError("Amount was withdraw from selected account!");
							setTab(0);}
							else showError("This operation cannot be done!");
						
						}catch(NumberFormatException ex)
						{
							showError("Please insert a number!");
							ex.printStackTrace();
						}
					}
		
				
			}
		}
	}
	//ActionListener pentru buttonul de Person si Account
	public class pushPersonButton implements ActionListener{
		
	public void actionPerformed(ActionEvent e)
	{
		person=null;
		
		if(tablePerson!=null)personTab.remove(tablePerson);
		tablePerson=bank.createPersonTable();
		tablePerson.setBounds(10, 11, 273, 211);
		personTab.add(tablePerson);
		tablePerson.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e)
			{
				int row=tablePerson.getSelectedRow();
				int column=2;
				String valueCNP=tablePerson.getModel().getValueAt(row, column).toString();
				System.out.println(valueCNP);
				person=bank.getByCNP(valueCNP);
				
			}
			});
		setTab(1);
	}
	}
	public class pushAccountButton implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			account=null;
			bank.addObservers();
			if(tableAccount!=null)accountTab.remove(tableAccount);
			tableAccount=bank.createAccountTable();
			tableAccount.setBounds(10, 11, 262, 222);
			accountTab.add(tableAccount);
			tableAccount.addMouseListener(new java.awt.event.MouseAdapter(){
				
				public void mouseClicked(java.awt.event.MouseEvent e)
				{
					int row=tableAccount.getSelectedRow();
					int column=0;
					String valueId=tableAccount.getModel().getValueAt(row, column).toString();
					String valueCNP=tableAccount.getModel().getValueAt(row, column+5).toString();
					System.out.println("S-a selectat contul "+valueId+" titularul are CNP-ul "+valueCNP);
					person=bank.getByCNP(valueCNP);
					account=bank.findByPerson(person, Integer.parseInt(valueId));
					nrAccount=Integer.parseInt(valueId);
					System.out.println(person.personToString());
					System.out.println(account.getAmount());
				}
			});
			setTab(2);
		}
	}

	//Functie de schimbare a unui tab
	private void setTab(int tabNumber)
	{
		tabbedPane.setSelectedIndex(tabNumber);
	}
	
	//Metoda pentru crearea junui mesaj de erroare
	private void showError(String error)
	{
		JOptionPane.showMessageDialog(frame, error);
	}
	
	//Functia de serializare
	public void serializare(Bank bank)
	{
		 try {
	         FileOutputStream fileOut =new FileOutputStream("bank.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(bank);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved inbank.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}

	//Fucntia de deserializare
	public Bank deSerialized()
	{ Bank bank=null;
		try {
			 FileInputStream fileIn=new FileInputStream("bank.ser");
			 ObjectInputStream in=new ObjectInputStream(fileIn);
			 bank=(Bank)in.readObject();
			 in.close();
			 fileIn.close();
			 System.out.println("Deserializarea a avut loc!");
			 return bank;
			}catch(IOException i)
			{
				i.printStackTrace();
			}
			catch(ClassNotFoundException c)
			{
				c.printStackTrace();
			}
		return bank;
	}
}
