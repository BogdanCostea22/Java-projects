package Presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import BLL.AdministratorBLL;
import BLL.ClientBLL;
import BLL.OrderBLL;
import BLL.ProductBLL;
import Model.Administrator;
import Model.Client;
import Model.Product;

import javax.swing.*;

public class View {

	//Frameul
	private JFrame frame;
	
	//Text Fielduri
	private JTextField textField;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldUsername;
	private JTextField textFieldAdministratorCode;
	
	//Butoanele
	private JButton createAccountButton;
	
	//Text Fieldurile pentru parole
	private JPasswordField textFieldConfirmPassword;
	private JPasswordField textFieldPassword;
	private JPasswordField passwordField;
	
	//Tabelul care contine taburie
	private JTabbedPane tabbedPane;
	private JTextField textFieldAdress;
	private JTextField textFieldPhoneNumber;
	private JTextField textFieldEmail;
	private JLabel lblNewLabel_4;
	private JLabel lblPhoneNumber;
	private JLabel lblCountry;
	private JTextField textFieldCountry;
	private JTextField textFieldCounty;
	private JLabel lblCounty;
	private JLabel lblNewLabel_5;
	private JTextField textFieldCity;
	private JButton finishRegistrationButton;

	//String 
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String confirmPassword;
	
	//BLL 
	private AdministratorBLL adminBLL=new AdministratorBLL();
	private ClientBLL clientBLL=new ClientBLL();
	private OrderBLL orderBLL=new OrderBLL();
	private ProductBLL productBLL=new ProductBLL();
	
	//Idul pentru Administrator sau Client
	private int id;
	private JTextField UpdateFNAdministrator;
	private JTextField UpdateLNAdministrator;
	private JTextField UpdateUsernameAdministrator;
	private JTextField UpdatePasswordAdministrator;
	private JButton OrderProductButton;
	private JButton DeletteClientButton;
	private JButton EditClientButton;
	private JTextField textFieldFNClient;
	private JTextField textFieldLNClient;
	private JTextField textFieldUsernameClient;
	private JTextField textFieldPasswordClient;
	private JTextField textFieldEmailClient;
	private JTextField textFieldAdressClient;
	private JTextField textFieldCountryClient;
	private JTextField textFieldCityClient;
	private JTextField textFieldPhoneClient;
	private JTextField textFieldCountyClient;
	private JButton UpdateClientButton;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JLabel Adress;
	private JLabel lblNewLabel_14;
	private JLabel lblNewLabel_15;
	private JLabel lblNewLabel_16;
	private JLabel lblNewLabel_17;
	private JTable table;
	private JTextField textFieldidProduct;
	private JLabel lblProductId;
	private JLabel lblQuantity;
	private JTextField textFieldQuantityProduct;
	private JButton btnOrder;
	//Panel
	private JPanel orderTab=new JPanel();
	private JPanel productView;
	private JTable table_1;
	private JTable table_2;
	private JButton btnViewProduct;
	private JTable table_3;
	private JTextField textFieldProductId;
	private JTextField textFieldProductName;
	private JTextField textFieldProductQuantity;
	private JTextField textFieldProductProducer;
	private JTextField textFieldProductDetails;
	private JButton btnUpdate_2;
	private JLabel lblNewLabel_19;
	private JTextField textFieldNewProductName;
	private JLabel lblQuantity_1;
	private JTextField textFieldNewProductQuantity;
	private JLabel lblProducer;
	private JTextField textFieldNewProductProducer;
	private JLabel lblDetails_1;
	private JTextField textFieldNewProductDetails;
	private JButton btnCreate;
	private JButton btnBack_2;
	private JButton btnAddProduct;
	private JButton btnNewButton;
	private JButton btnBack_3;
	private JButton btnLogout;
	/**
	 * Launch the application.
	 */
	public  void main() 
	{EventQueue.invokeLater(new Runnable() {
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
		frame.setBounds(100, 100, 460, 367);
		//100, 100, 443, 345
		//100, 100, 440, 272
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(-13, -77, 467, 405);
		frame.getContentPane().add(tabbedPane);
		
		/**
		 * Create new Tabb for Login 
		 */
		
		
		
		
		/**
		 * Create new Tabb for Registration
		 */
		JPanel loginPanel=new JPanel();
		tabbedPane.addTab("Login",loginPanel);
		loginPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(150, 55, 120, 20);
		loginPanel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 101, 120, 20);
		loginPanel.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("User name:");
		lblNewLabel.setBounds(177, 36, 93, 14);
		loginPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(177, 86, 79, 14);
		loginPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Register now!");
		lblNewLabel_2.setBounds(177, 166, 93, 14);
		lblNewLabel_2.setForeground(Color.BLUE);
		
		//Acultator pentru JLabel
		lblNewLabel_2.addMouseListener(new MouseListener() 
				{
					public void mouseClicked(MouseEvent arg0) {	
						tabbedPane.setSelectedIndex(1);
					    frame.setBounds(100, 100, 450, 355);
					}
					public void mouseEntered(MouseEvent arg0) {
					}
					public void mouseExited(MouseEvent arg0) {
					}
					public void mousePressed(MouseEvent arg0) {
					}
					public void mouseReleased(MouseEvent arg0) {
						//tabbedPane.setSelectedIndex(1);
					}
				});
		
		loginPanel.add(lblNewLabel_2);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new pushLoginButton());
		btnLogin.setBounds(167, 132, 89, 23);
		loginPanel.add(btnLogin);
		JPanel registrationPanel=new JPanel();
		tabbedPane.addTab("Registration", registrationPanel);
		registrationPanel.setLayout(null);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(20, 25, 100, 20);
		registrationPanel.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(20, 64, 100, 20);
		registrationPanel.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(20, 102, 100, 20);
		registrationPanel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(20, 11, 86, 14);
		registrationPanel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(20, 46, 151, 14);
		registrationPanel.add(lblLastName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(20, 88, 86, 14);
		registrationPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(20, 123, 100, 14);
		registrationPanel.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(20, 162, 129, 14);
		registrationPanel.add(lblConfirmPassword);
		
		JLabel lblAdministratorCode = new JLabel("Administrator Code:");
		lblAdministratorCode.setBounds(20, 205, 123, 14);
		registrationPanel.add(lblAdministratorCode);
		
		textFieldAdministratorCode = new JTextField();
		textFieldAdministratorCode.setBounds(20, 221, 100, 20);
		registrationPanel.add(textFieldAdministratorCode);
		textFieldAdministratorCode.setColumns(10);
		
		//Creeam Butonul si adaugam ascultatorul
		createAccountButton = new JButton("Create acoount");
		createAccountButton.setBounds(170, 243, 123, 23);
		registrationPanel.add(createAccountButton);
		
		textFieldConfirmPassword = new JPasswordField();
		textFieldConfirmPassword.setBounds(20, 174, 100, 20);
		registrationPanel.add(textFieldConfirmPassword);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(20, 143, 100, 20);
		registrationPanel.add(textFieldPassword);
		JPanel completeInformation=new JPanel();
		tabbedPane.addTab("Complete Infromation",completeInformation);
		completeInformation.setLayout(null);
		
		textFieldAdress = new JTextField();
		textFieldAdress.setBounds(10, 26, 100, 20);
		completeInformation.add(textFieldAdress);
		textFieldAdress.setColumns(10);
		
		textFieldPhoneNumber = new JTextField();
		textFieldPhoneNumber.setBounds(10, 97, 100, 20);
		completeInformation.add(textFieldPhoneNumber);
		textFieldPhoneNumber.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(10, 57, 100, 20);
		completeInformation.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Adress:");
		lblNewLabel_3.setBounds(10, 11, 46, 14);
		completeInformation.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Email:");
		lblNewLabel_4.setBounds(10, 46, 46, 14);
		completeInformation.add(lblNewLabel_4);
		
		lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(10, 79, 110, 14);
		completeInformation.add(lblPhoneNumber);
		
		lblCountry = new JLabel("Country:");
		lblCountry.setBounds(10, 118, 46, 14);
		completeInformation.add(lblCountry);
		
		textFieldCountry = new JTextField();
		textFieldCountry.setBounds(10, 128, 100, 20);
		completeInformation.add(textFieldCountry);
		textFieldCountry.setColumns(10);
		
		textFieldCounty = new JTextField();
		textFieldCounty.setBounds(10, 169, 100, 20);
		completeInformation.add(textFieldCounty);
		textFieldCounty.setColumns(10);
		
		lblCounty = new JLabel("County:");
		lblCounty.setBounds(10, 151, 46, 14);
		completeInformation.add(lblCounty);
		
		lblNewLabel_5 = new JLabel("City:");
		lblNewLabel_5.setBounds(10, 196, 46, 14);
		completeInformation.add(lblNewLabel_5);
		
		textFieldCity = new JTextField();
		textFieldCity.setBounds(10, 212, 100, 20);
		completeInformation.add(textFieldCity);
		textFieldCity.setColumns(10);
		
		finishRegistrationButton = new JButton("Register");
		finishRegistrationButton.setBounds(163, 239, 121, 23);
		completeInformation.add(finishRegistrationButton);
		this.finishRegistrationButton.addActionListener(new pushFinishRegistration());
		
		//Tabul pentru administratori unde unde pot selecta operatiile pe care doresc sa le faca
		JPanel administratorOperationPanel=new JPanel();
		tabbedPane.addTab("Administrator Operation",administratorOperationPanel);
		administratorOperationPanel.setLayout(null);
		
		JButton DeleteButtonAdministrator = new JButton("Delete");
		DeleteButtonAdministrator.addActionListener(new pushDeleteAdministrator());

		DeleteButtonAdministrator.setBounds(154, 174, 106, 23);
		administratorOperationPanel.add(DeleteButtonAdministrator);
		
		JButton EditAdminAccountButton = new JButton("Edit");
		EditAdminAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrator takeAdmin=adminBLL.takeAdminById(id);
				UpdateFNAdministrator.setText(takeAdmin.getFirst_name());
				UpdateLNAdministrator.setText(takeAdmin.getLast_name());
				UpdateUsernameAdministrator.setText(takeAdmin.getUsername());
				UpdatePasswordAdministrator.setText(takeAdmin.getPassword());
				setTab(4);
			}
		});
		EditAdminAccountButton.setBounds(154, 140, 106, 23);
		administratorOperationPanel.add(EditAdminAccountButton);
		
		JButton ViewClientsTableButton = new JButton("View Clients");
		ViewClientsTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTab(8);
			}
		});
		ViewClientsTableButton.setBounds(154, 106, 106, 23);
		administratorOperationPanel.add(ViewClientsTableButton);
		
		btnViewProduct = new JButton("View Product");
		btnViewProduct.addActionListener(new pushProductOperation());
		btnViewProduct.setBounds(154, 71, 106, 23);
		administratorOperationPanel.add(btnViewProduct);
		
		btnAddProduct = new JButton("Add Product ");
		btnAddProduct.addActionListener(new pushAddProduct());
		btnAddProduct.setBounds(154, 40, 106, 23);
		administratorOperationPanel.add(btnAddProduct);
		
		btnNewButton = new JButton("LogOut");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(0);
			}
		});
		btnNewButton.setBounds(154, 210, 106, 23);
		administratorOperationPanel.add(btnNewButton);
		registrationPanel.setLayout(null);
		
		//Tabul pentru Update administrator
		JPanel administratorUpdate=new JPanel();
		tabbedPane.addTab("Administraor Update", administratorUpdate);
		administratorUpdate.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("First Name:");
		lblNewLabel_6.setBounds(22, 11, 86, 14);
		administratorUpdate.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Last Name :");
		lblNewLabel_7.setBounds(20, 51, 67, 14);
		administratorUpdate.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Username :");
		lblNewLabel_8.setBounds(22, 105, 86, 14);
		administratorUpdate.add(lblNewLabel_8);
		
		JLabel lblPassword_1 = new JLabel("Password : ");
		lblPassword_1.setBounds(22, 158, 86, 14);
		administratorUpdate.add(lblPassword_1);
		
		UpdateFNAdministrator = new JTextField();
		UpdateFNAdministrator.setBounds(32, 30, 122, 20);
		administratorUpdate.add(UpdateFNAdministrator);
		UpdateFNAdministrator.setColumns(10);
		
		UpdateLNAdministrator = new JTextField();
		UpdateLNAdministrator.setText("");
		UpdateLNAdministrator.setBounds(30, 76, 124, 20);
		administratorUpdate.add(UpdateLNAdministrator);
		UpdateLNAdministrator.setColumns(10);
		
		UpdateUsernameAdministrator = new JTextField();
		UpdateUsernameAdministrator.setBounds(32, 130, 122, 20);
		administratorUpdate.add(UpdateUsernameAdministrator);
		UpdateUsernameAdministrator.setColumns(10);
		
		UpdatePasswordAdministrator = new JTextField();
		UpdatePasswordAdministrator.setBounds(32, 174, 122, 20);
		administratorUpdate.add(UpdatePasswordAdministrator);
		UpdatePasswordAdministrator.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new pushUpdateAdministratorButton());
		btnUpdate.setBounds(174, 215, 89, 23);
		administratorUpdate.add(btnUpdate);
		
		btnBack_3 = new JButton("Back");
		btnBack_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(3);
			}
		});
		btnBack_3.setBounds(277, 215, 89, 23);
		administratorUpdate.add(btnBack_3);
		
		JPanel clientOperation=new JPanel();
		clientOperation.setLayout(null);
		tabbedPane.addTab("Client operation", clientOperation);
		
		OrderProductButton = new JButton("Order");
		OrderProductButton.addActionListener(new pushOrderClient());
		OrderProductButton.setBounds(130, 90, 89, 23);
		clientOperation.add(OrderProductButton);
		
		DeletteClientButton = new JButton("Delete");
		DeletteClientButton.addActionListener(new pushDeleteClient());
		
		DeletteClientButton.setBounds(130, 124, 89, 23);
		clientOperation.add(DeletteClientButton);
		
		EditClientButton = new JButton("Edit");
		EditClientButton.addActionListener(new pushEditClient());
		EditClientButton.setBounds(130, 161, 89, 23);
		clientOperation.add(EditClientButton);
		
		btnLogout = new JButton("LogOut");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(0);
			}
		});
		btnLogout.setBounds(130, 195, 89, 23);
		clientOperation.add(btnLogout);
		
		//Tabul pentru editarea detaliilor legate de client
		JPanel editClient =new JPanel();
		tabbedPane.addTab("Edit Client", editClient);
		editClient.setLayout(null);
		
		textFieldFNClient = new JTextField();
		textFieldFNClient.setBounds(22, 24, 86, 20);
		editClient.add(textFieldFNClient);
		textFieldFNClient.setColumns(10);
		
		textFieldLNClient = new JTextField();
		textFieldLNClient.setText("");
		textFieldLNClient.setBounds(22, 68, 86, 20);
		editClient.add(textFieldLNClient);
		textFieldLNClient.setColumns(10);
		
		textFieldUsernameClient = new JTextField();
		textFieldUsernameClient.setBounds(22, 110, 86, 20);
		editClient.add(textFieldUsernameClient);
		textFieldUsernameClient.setColumns(10);
		
		textFieldPasswordClient = new JTextField();
		textFieldPasswordClient.setBounds(22, 155, 86, 20);
		editClient.add(textFieldPasswordClient);
		textFieldPasswordClient.setColumns(10);
		
		textFieldEmailClient = new JTextField();
		textFieldEmailClient.setBounds(22, 199, 86, 20);
		editClient.add(textFieldEmailClient);
		textFieldEmailClient.setColumns(10);
		
		textFieldAdressClient = new JTextField();
		textFieldAdressClient.setBounds(314, 24, 86, 20);
		editClient.add(textFieldAdressClient);
		textFieldAdressClient.setColumns(10);
		
		textFieldCountryClient = new JTextField();
		textFieldCountryClient.setBounds(314, 68, 86, 20);
		editClient.add(textFieldCountryClient);
		textFieldCountryClient.setColumns(10);
		
		textFieldCityClient = new JTextField();
		textFieldCityClient.setBounds(314, 110, 86, 20);
		editClient.add(textFieldCityClient);
		textFieldCityClient.setColumns(10);
		
		textFieldPhoneClient = new JTextField();
		textFieldPhoneClient.setBounds(314, 199, 86, 20);
		editClient.add(textFieldPhoneClient);
		textFieldPhoneClient.setColumns(10);
		
		textFieldCountyClient = new JTextField();
		textFieldCountyClient.setBounds(314, 155, 86, 20);
		editClient.add(textFieldCountyClient);
		textFieldCountyClient.setColumns(10);
		
		UpdateClientButton = new JButton("Update");
		UpdateClientButton.addActionListener(new pushUpdateClient());
		UpdateClientButton.setBounds(166, 222, 89, 23);
		editClient.add(UpdateClientButton);
		
		lblNewLabel_9 = new JLabel("Fisrt Name:");
		lblNewLabel_9.setBounds(22, 11, 86, 14);
		editClient.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("Last Name:");
		lblNewLabel_10.setBounds(22, 55, 115, 14);
		editClient.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("Username :");
		lblNewLabel_11.setBounds(22, 97, 86, 14);
		editClient.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("Password :");
		lblNewLabel_12.setBounds(22, 141, 86, 14);
		editClient.add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel("Email :");
		lblNewLabel_13.setBounds(22, 186, 46, 14);
		editClient.add(lblNewLabel_13);
		
		Adress = new JLabel("Adress :");
		Adress.setBounds(314, 11, 46, 14);
		editClient.add(Adress);
		
		lblNewLabel_14 = new JLabel("Country :");
		lblNewLabel_14.setBounds(314, 55, 46, 14);
		editClient.add(lblNewLabel_14);
		
		lblNewLabel_15 = new JLabel("County :");
		lblNewLabel_15.setBounds(314, 97, 46, 14);
		editClient.add(lblNewLabel_15);
		
		lblNewLabel_16 = new JLabel("City :");
		lblNewLabel_16.setBounds(314, 141, 46, 14);
		editClient.add(lblNewLabel_16);
		
		lblNewLabel_17 = new JLabel("Phone Number:");
		lblNewLabel_17.setBounds(314, 186, 86, 14);
		editClient.add(lblNewLabel_17);
		
		//Tabul pentru comanda unui nou produs
	
		tabbedPane.addTab("Order", orderTab);
		orderTab.setLayout(null);
		
		//table=new JTable();
		//orderTab.add(table);
		

		//Nou tab pentru Vizualiarea clientilor 
		JPanel clientView=new JPanel();
		tabbedPane.addTab("clientView",clientView);
		clientView.setLayout(null);
		
		JButton backAdministratorOperation = new JButton("Back");
		backAdministratorOperation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(3);
			}
		});
		backAdministratorOperation.setBounds(149, 226, 89, 23);
		clientView.add(backAdministratorOperation);
		
		table_2 = new JTable();
		table_2=clientBLL.createTable();
		table_2.setBounds(10, 47, 409, 153);

		clientView.add(table_2);
		
		//Tab pentru produse
		productView=new JPanel();
		tabbedPane.addTab("ProductView", productView);
		productView.setLayout(null);
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(3);
				productView.remove(table_3);
			}
		});
		btnBack.setBounds(138, 203, 89, 23);
		productView.add(btnBack);
		
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new pushDeleteProduct());
		btnDelete.setBounds(29, 160, 89, 23);
		productView.add(btnDelete);
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.addActionListener(new pushUpdateProduct());
		btnUpdate_1.setBounds(272, 160, 89, 23);
		productView.add(btnUpdate_1);
		
		textFieldProductId = new JTextField();
		textFieldProductId.setBounds(141, 161, 86, 20);
		productView.add(textFieldProductId);
		textFieldProductId.setColumns(10);
		
		
		//Tabul pentru update Produs
		JPanel updateProduct=new JPanel();
		tabbedPane.addTab("Product Update", updateProduct);
		updateProduct.setLayout(null);
		
		textFieldProductName = new JTextField();
		textFieldProductName.setBounds(22, 41, 86, 20);
		updateProduct.add(textFieldProductName);
		textFieldProductName.setColumns(10);
		
		textFieldProductQuantity = new JTextField();
		textFieldProductQuantity.setBounds(22, 92, 86, 20);
		updateProduct.add(textFieldProductQuantity);
		textFieldProductQuantity.setColumns(10);
		
		textFieldProductProducer = new JTextField();
		textFieldProductProducer.setBounds(22, 141, 86, 20);
		updateProduct.add(textFieldProductProducer);
		textFieldProductProducer.setColumns(10);
		
		textFieldProductDetails = new JTextField();
		textFieldProductDetails.setBounds(22, 195, 86, 20);
		updateProduct.add(textFieldProductDetails);
		textFieldProductDetails.setColumns(10);
		
		JLabel lblNewLabel_18 = new JLabel("Product Name :");
		lblNewLabel_18.setBounds(22, 16, 86, 14);
		updateProduct.add(lblNewLabel_18);
		
		JLabel lblProductQuantity = new JLabel("Product Quantity:");
		lblProductQuantity.setBounds(22, 72, 109, 14);
		updateProduct.add(lblProductQuantity);
		
		JLabel lblProductProducer = new JLabel("Product Producer :");
		lblProductProducer.setBounds(22, 116, 126, 14);
		updateProduct.add(lblProductProducer);
		
		JLabel lblDetails = new JLabel("Details :");
		lblDetails.setBounds(22, 172, 46, 14);
		updateProduct.add(lblDetails);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(3);
			}
		});
		btnBack_1.setBounds(123, 220, 89, 23);
		updateProduct.add(btnBack_1);
		
		btnUpdate_2 = new JButton("Update");
		btnUpdate_2.addActionListener(new pushProductUpdateField());
		btnUpdate_2.setBounds(262, 220, 89, 23);
		updateProduct.add(btnUpdate_2);
		//Tabul pentru Adaugarea unui nou Produs
		JPanel addProduct=new JPanel();
		tabbedPane.addTab("Adaugare Produs", addProduct);
		addProduct.setLayout(null);
		
		lblNewLabel_19 = new JLabel("Product Name:");
		lblNewLabel_19.setBounds(63, 11, 78, 14);
		addProduct.add(lblNewLabel_19);
		
		textFieldNewProductName = new JTextField();
		textFieldNewProductName.setBounds(63, 32, 86, 20);
		addProduct.add(textFieldNewProductName);
		textFieldNewProductName.setColumns(10);
		
		lblQuantity_1 = new JLabel("Quantity :");
		lblQuantity_1.setBounds(63, 64, 78, 14);
		addProduct.add(lblQuantity_1);
		
		textFieldNewProductQuantity = new JTextField();
		textFieldNewProductQuantity.setBounds(63, 89, 86, 20);
		addProduct.add(textFieldNewProductQuantity);
		textFieldNewProductQuantity.setColumns(10);
		
		lblProducer = new JLabel("Producer :");
		lblProducer.setBounds(63, 120, 78, 14);
		addProduct.add(lblProducer);
		
		textFieldNewProductProducer = new JTextField();
		textFieldNewProductProducer.setBounds(63, 145, 86, 20);
		addProduct.add(textFieldNewProductProducer);
		textFieldNewProductProducer.setColumns(10);
		
		lblDetails_1 = new JLabel("Details :");
		lblDetails_1.setBounds(63, 171, 46, 14);
		addProduct.add(lblDetails_1);
		
		textFieldNewProductDetails = new JTextField();
		textFieldNewProductDetails.setBounds(63, 191, 86, 20);
		addProduct.add(textFieldNewProductDetails);
		textFieldNewProductDetails.setColumns(10);
		
		btnCreate = new JButton("Create ");
		btnCreate.addActionListener(new pushProductADD());
		btnCreate.setBounds(136, 234, 89, 23);
		addProduct.add(btnCreate);
		
		btnBack_2 = new JButton("Back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTab(3);
			}
		});
		btnBack_2.setBounds(255, 234, 89, 23);
		addProduct.add(btnBack_2);
		
		/**
		 * Creeam un nou tab pentru completarea detaliilor legate de client()
		 * */
		this.createAccountButton.addActionListener(new pushCreateActionButton());
	}
	//Ascultator pentru product operation
	public class pushProductOperation implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setTab(9);
			table_3 =productBLL.createTable();
			table_3.setBounds(36, 35, 347, 114);
			productView.add(table_3);
			
			//createProductTab();
			
		}
	}
	private void createProductTab()
	{
		
		
	}
	//Create new product
	public class pushProductADD implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String name=textFieldNewProductName.getText().toString();
			String producer=textFieldNewProductProducer.getText().toString();
			String quantity=textFieldNewProductQuantity.getText().toString();
			String details=textFieldNewProductDetails.getText().toString();
			if(name.length()>0&&producer.length()>0&&quantity.length()>0&&details.length()>0)
				{
					try{
						int nr=Integer.parseInt(quantity);
						if(productBLL.addProduct( name, producer, nr, details))
							{
								showError("Adaugarea a avut loc cu succes!");
								setTab(3);
							}
						else showError("Adaugarea nu a avu loc!");
					}catch(NumberFormatException ex){
						showError("Campul cantitatii nu reprezinta un numar!");
						ex.printStackTrace();
					}
				}
			else showError("Nu ai completat toate campurile!");
		}
	}
	//Ascultator pentru adaugarea unui nou client
	public class pushAddProduct implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setTab(11);
			
		}
	}
	//Ascultator pentru terminarea update la un produs
	public class pushProductUpdateField implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String name=textFieldProductName.getText().toString();
			String quantity=textFieldProductQuantity.getText().toString();
			String producer=textFieldProductProducer.getText().toString();
			String details=textFieldProductDetails.getText().toString();
			int id=Integer.parseInt(textFieldProductId.getText().toString());
			int nr;
			try {
				nr=Integer.parseInt(quantity);
				if(productBLL.updateProduct(id, name, producer, nr, details))
				{
					showError("Produsul a fost actualizat cu succes!");
					setTab(3);
				}
				else{
					showError("Produsul nu a fost actualizat!");
					setTab(3);
				}
			}catch(NumberFormatException ex)
			{
				showError("Cantitatea introdusa nu reprezinta un numar!");
				ex.printStackTrace();
			}
			
			
		}
	}
	//Ascultator pentru update produs
	public class pushUpdateProduct implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		int id=Integer.parseInt(textFieldProductId.getText().toString());
		if(productBLL.findId(id))
			{
			Product product=productBLL.findById(id);
			textFieldProductName.setText(product.getProduct_name());
			textFieldProductQuantity.setText(product.getQuantity()+"");
			textFieldProductProducer.setText(product.getProducer());
			textFieldProductDetails.setText(product.getDetails());
			productView.remove(table_3);
			setTab(10);
			}
		}
	}
	//Clasele 
		//Ascultator pentru butonul de creeaza un nou cont 
		public class pushCreateActionButton implements ActionListener{
			
			public void actionPerformed(ActionEvent a)
				{
		//Preluam datele introduse utilizate la creearea unui nou cont
				firstName=getFirstName();
				lastName=getLastName();
				userName=getUsername();
				password=getPassword();
				confirmPassword=getConfirmPassword();
				String administratorCode=getAdministratorCode();
				
				//Verificam daca toate campurile folosite la creearea unui nou cont au fost introduse
					if(firstName.length()>0 && lastName.length()>0 && userName.length()>0 && password.length()>0 && confirmPassword.length()>0)
					{		
							//Check if the password is matching
							if(!confirmPassword.equals(password))showError("The password dosen't match!");
							else {
							System.out.println("Clientul cu datele:"+ firstName+" "+lastName+" "+userName+" "+password+" "+confirmPassword+" "+administratorCode);
							//Verificam daca s-a introdus codul pentru Administrator, in cazul in care s-a introdus acest vom salva in baza de date datele pentru un administrator
								System.out.println("MERGE");
							 	if(administratorCode.equals("ADM"))
							 		{
							 		System.out.println("AM INTRAT:");
							 			adminBLL.insertAdministrator(firstName, lastName, userName, password);
							 			setTab(0);
							 		}
									else {
										//Verificam lungimea codului pentru administrator in cazul in care nu este introdus v-am merge mai departe pentru completare datelor legate de 
											if(administratorCode.length()>0) 
												showError("Enter a wrong administrator code!");
											else
												setTab(2);
											}	
									}
					}
					else showError("Some textfield are empty!");
				}
		}
		
		//Ascultator pentru stergerea unui produs
		public class pushDeleteProduct implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
				try{
					int id=Integer.parseInt(textFieldProductId.getText().toString());
					System.out.println("DADADADADA"+id);
				}catch(NumberFormatException ex)
				{
					ex.printStackTrace();
					System.out.println("DADA"+textFieldProductId.getText());
					showError("Id introdus nu este bun!");
				}
				if(productBLL.deleteProduct(id))showError("Produsul a fost sters cu succes!");
					else showError("Produs nu a fost sters!");
					setTab(3);
					
			
				}
		}
		//Ascultator pentru comanda produs
		public class pushOrderButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try{
				int id_product=Integer.parseInt(textFieldidProduct.getText());
				int quantity=Integer.parseInt(textFieldQuantityProduct.getText());
				if(productBLL.checkQuantity(id_product, quantity)>=0)
				{
					//Comanda se poate efectua 
					//Se va face o comanda pdf
					showError("Comanda s-a realizat!");	
		
					orderTab.remove(table);
					orderTab.removeAll();
					if(orderBLL.insertOrder(id_product, id))
					{
					int order=orderBLL.getLastId();
					PrintWriter writer = new PrintWriter("Order"+order+".txt", "UTF-8");
					writer.println("Cientul cu id "+id);
					writer.println("A comandat "+quantity+" bucata din produsul :"+productBLL.findById(id_product).getProduct_name());
					writer.close();
					setTab(5);
					}
				}
				else showError("Nu se poate reailiza aceasta comanda!");
			}catch(NumberFormatException error){
				showError(error.getMessage());
				error.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
		//Ascultator pentru butonul de comanda
		public class pushOrderClient implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
				setTab(7);
				table = productBLL.createTable();
				table.setBounds(24, 28, 290, 200);
				System.out.println("dada");
				orderTab.add(table);
				createOrderTAB();
			}
		}
		//Create Order tab
		private void createOrderTAB()
		{
			textFieldidProduct = new JTextField();
			textFieldidProduct.setBounds(333, 52, 86, 20);
			orderTab.add(textFieldidProduct);
			textFieldidProduct.setColumns(10);
			
			lblProductId = new JLabel("Product id:");
			lblProductId.setBounds(333, 35, 68, 14);
			orderTab.add(lblProductId);
			
			lblQuantity = new JLabel("Quantity:");
			lblQuantity.setBounds(333, 75, 56, 14);
			orderTab.add(lblQuantity);
			
			textFieldQuantityProduct = new JTextField();
			textFieldQuantityProduct.setBounds(333, 92, 86, 20);
			orderTab.add(textFieldQuantityProduct);
			textFieldQuantityProduct.setColumns(10);
			
			btnOrder = new JButton("Order");
			btnOrder.addActionListener(new pushOrderButton());
			btnOrder.setBounds(333, 118, 89, 23);
			orderTab.add(btnOrder);
		}
		//Ascultator pentru butonul care finalizeaza crearea unui nou cont 
		public class pushFinishRegistration implements ActionListener{
			
			public void actionPerformed(ActionEvent e)
			{
				String adress=getAdress();
				String email=getEmail();
				String phoneNumber=getPhoneNumber();
				String country=getCountry();
				String county=getCounty();
				String city=getCity();
				
				//Verificam daca toate datele au fost introduse
				if(adress.length()>0 && email.length()>0 && phoneNumber.length()>0 && country.length()>0 &&county.length()>0 && city.length()>0)
				{
					//Verificam daca s-a introdus un numar de telefon corect
					try {
						int i=Integer.parseInt(phoneNumber);
						
						//Afisam un mesaj cu mesajul de informare asupra inregistrari unui nuo client
						showError("New account was created!");
						
						//Schimbam tabul la cel principal
						setTab(0);					
						
						if(clientBLL.insertClient(firstName, lastName, userName, phoneNumber, email, adress, country, county, city, phoneNumber))showError("Contul a fost creat cu succes!");
						else showError("Contul nu a putut fi creat!");
						//Schimbam dimensiunile ferestrei
						setFrame(100, 100, 440, 272);
						//ClientBll.insert(firstName, lastName, userName, password, adress, email, phoneNumber, country, county, city);
						//Adaugam clientul in baza de date
					}catch(NumberFormatException exception){
						showError("Invalid phone number:"+exception.toString());
					}
				}
				else showError("The field's isn't completed!");
			}
		}
		
	//Ascultator pentu butonul de Login
		public class pushLoginButton implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
			String username=textField.getText().toString();
			String password=passwordField.getText().toString();
			if(password.length()>0&&username.length()>0)
				{
				//Verificam daca este administrator
					id=adminBLL.checkUsernamePAssword(username, password);
					if(id>0) {
						adminBLL.takeAdminById(id);
						setTab(3);
					}
					else{
						id=clientBLL.checkUsernamePassword(username, password);
						if(id>0)setTab(5);
						else {
							showError("Datele introduse nu sunt corecte!");
							textField.setText("");
							passwordField.setText("");
							}
					}
					
						
				}
			}
	}
		
	//Ascultator pentru uodate client
	public class pushUpdateClient implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			firstName=textFieldFNClient.getText();
			lastName=textFieldLNClient.getText();
			userName=textFieldUsernameClient.getText();
			password=textFieldPassword.getText();
			String email=textFieldEmailClient.getText();
			String adress=textFieldAdressClient.getText();
			String country=textFieldCountryClient.getText();
			String county=textFieldCountryClient.getText();
			String city=textFieldCityClient.getText();
			String phoneNumber=textFieldPhoneClient.getText();
			if(clientBLL.updateClient(id, firstName, lastName, userName, phoneNumber, email, adress, country, county, city, phoneNumber))showError("Modificarile au fost facute cu succes!");
			else showError("Modificarile nu pot fi facute!");
			setTab(5);
			
		}
	}
	//Ascultator pentru stergere administrator
		public class pushDeleteAdministrator implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
			if(adminBLL.deleteById(id))
				{
					showError("Utilizatorul a fost sters cu succes !");
					setTab(0);
				}
			else showError("Utilizatorul nu a fost sters !");
			}
		}
		
	//Ascultatori pentru update administrator
		public class pushUpdateAdministratorButton implements ActionListener{
				public void actionPerformed(ActionEvent arg0) {
					firstName=UpdateFNAdministrator.getText();
					lastName=UpdateLNAdministrator.getText();
					userName=UpdateUsernameAdministrator.getText();
					password=UpdatePasswordAdministrator.getText();
					if(adminBLL.updateAdministrator(id, firstName, lastName, userName, password)) 
						{
							showError("Updateul a avut loc!");
							setTab(3);
						}
					else {
						showError("Exista unele probleme!");
					}
			}
		}
		
	//Ascultator pentru stergerea unui client
		public class pushDeleteClient implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(clientBLL.deleteById(id)){
					showError("Stergerea a avut loc cu succes!");
					setTab(0);
				}
				else showError("Stergerea nu a avut loc!");
			}
		}
	
	//Ascultator pentru butonul de edit client
	public class pushEditClient implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setTab(6);
			Client client=clientBLL.findById(id);
			textFieldFNClient.setText(client.getFirst_name());
			textFieldLNClient.setText(client.getLast_name());
			textFieldUsernameClient.setText(client.getUsername());
			textFieldPasswordClient.setText(client.getPassword());
			textFieldEmailClient.setText(client.getEmail());
			textFieldAdressClient.setText(client.getAdress());
			textFieldCountryClient.setText(client.getCountry());
			textFieldCountyClient.setText(client.getCounty());
			textFieldCityClient.setText(client.getCity());
			textFieldPhoneClient.setText(client.getPhone_number());
		}
	}
	//Metoda pentru mesaje de eroare
	public void showError(String error)
	{
		JOptionPane.showMessageDialog(frame,error);
	}
	//Metode de adaugat ascultatori
	public void addListenerFinishRegistrationButton(ActionListener e)
	{
		this.finishRegistrationButton.addActionListener(e);
	}
	public void addListenerCreateAccountButton(ActionListener e)
	{
		this.createAccountButton.addActionListener(e);
	}
	
	//Setare tab
	public void setTab(int index)
	{
		tabbedPane.setSelectedIndex(index);
	}
	
	//Set Frame
	public void setFrame(int x,int y,int width,int height)
	{
		frame.setBounds(x, y, width, height);
	}
	//Take Strings from textField
	public String getFirstName()
	{
		return this.textFieldFirstName.getText().toString();
	}
	public String getLastName()
	{
		return this.textFieldLastName.getText().toString();
	}
	public String getUsername()
	{
		return this.textFieldUsername.getText().toString();
	}
	public String getPassword()
	{
		return this.textFieldPassword.getText().toString();
	}
	public String getConfirmPassword()
	{
		return this.textFieldConfirmPassword.getText().toString();
	}
	public String getAdministratorCode()
	{
		return this.textFieldAdministratorCode.getText().toString();
	}
	public String getEmail()
	{
		return this.textFieldEmail.getText().toString();
	}
	public String getPhoneNumber()
	{
		return this.textFieldPhoneNumber.getText().toString();
	}
	public String getAdress()
	{
		return this.textFieldAdress.getText().toString();
	}
	public String getCountry()
	{
		return this.textFieldCountry.getText().toString();
	}
	public String getCounty()
	{
		return this.textFieldCounty.getText().toString();
	}
	public String getCity()
	{
		return this.textFieldCity.getText().toString();
	}
	}