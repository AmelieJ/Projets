package test_projet.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Fenetre extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame fenetre=new JFrame("Application");
	JButton bt_connexion = new JButton("Connexion");
	JTextField login = new JTextField();
	JPasswordField pwd = new JPasswordField();
	JLabel nomL = new JLabel("Login");
	JLabel nomP = new JLabel("Password");
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	
	public int id;
	
public Fenetre() {
	
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.add(nomL);
		panel.add(login);
		panel.add(nomP);
		panel.add(pwd);
		
		bt_connexion.setPreferredSize(new Dimension(200,30));
		login.setPreferredSize(new Dimension(100,30));
		pwd.setPreferredSize(new Dimension(100,30));
		
		panel2.add(bt_connexion);
		
		// Ajout du container à la fenêtre
		JPanel content = new JPanel();
		content.setLayout(new GridBagLayout());
		
		//L'objet servant à positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
	    //On positionne la case de départ du composant
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    //La taille en hauteur et en largeur
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    content.add(panel,gbc);
	    
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    content.add(panel2,gbc);
	    
	    panel.setBackground(new Color(255,211,155));
        panel.repaint();
        
        panel2.setBackground(new Color(255,211,155));
        panel2.repaint();
		
		content.setBackground(new Color(255,211,155));
        content.repaint();
	    
		fenetre.getContentPane().add(content);
		
		bt_connexion.addActionListener(this);
		
		fenetre.pack();
		//fenetre.setSize(1000,1000);
		fenetre.setVisible(true);
	
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String databaseUsername = "";
		String databasePassword = "";
		char[] pass = pwd.getPassword();  
		String passString = new String(pass); 
		
		String pilote = "com.mysql.jdbc.Driver";
		
		try{
			Class.forName(pilote);

			Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			System.out.println("Connexion réussie!");

			Statement instruction = connexion.createStatement();
			
			ResultSet resultat = instruction.executeQuery("SELECT * FROM utilisateurs WHERE login='" + login.getText() + "' && password='" + passString + "'");
			
			
			while (resultat.next()){
			if(e.getSource()==bt_connexion){
					databaseUsername = resultat.getString("login");
			        databasePassword = resultat.getString("password");
			        this.id=resultat.getInt("ID");
			}
			}
			}
			catch(ClassNotFoundException exc){
			exc.printStackTrace();}
			catch(SQLException exc){ exc.printStackTrace();}
		
		if (login.getText().equals(databaseUsername) && passString.equals(databasePassword)){
	        	JOptionPane.showMessageDialog(this,"Identification réussie!");}
	        else{
	        	JOptionPane.showMessageDialog(this,"Mot de passe invalide",
	        			"Error Message",JOptionPane.ERROR_MESSAGE);}
	}

}
	/*public int recupIDUser(){
		return this.id;
	}*/

/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Fenetre();
	}

}*/
