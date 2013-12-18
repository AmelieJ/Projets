package projet_info;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.border.BevelBorder;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDesktopPane;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.JScrollBar;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;

import net.miginfocom.swing.MigLayout;
import projet_info.Article_complet;

//import test_projet.model.Fenetre_Accueil2;

public class fenetre1 extends JFrame{

	private static int id_user = 0;
	private static int idj = 0;
	private static int idarticle_like = 0;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_5;
	Connexion c = new Connexion();
	private JPasswordField password;
	private JButton btnNewButton = new JButton("Creer un compte");

	private JPanel contentPane;
	private JLabel label_1;
	private JLabel label;
	private JTextField login;
	private JButton button;
	private JLabel lblNewLabel;
	private JTextPane txtpnTouteLactu;
	private JPanel panel_1;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;
	private JTextField login2;
	private JPasswordField password2;

	// private JScrollPane onglet1 = new JScrollPane();
	private JScrollPane onglet2 = new JScrollPane();
	private JScrollPane onglet3 = new JScrollPane();

	JLabel principalLabel = new JLabel("Fenêtre Principale ");
	JLabel profilLabel = new JLabel("Votre Profil");

	private static CheckListItem item;
	private static int i=0;
	private static int id_journal_lu[] = new int[100];
	
	private static Article_complet [] a = new Article_complet[100];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fenetre1 frame = new fenetre1();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public fenetre1() {
		setTitle("GNEWS : toutes les news par les Ninjava");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 529);

		/*
		 * contentPane = new JPanel(); contentPane.setBackground(new Color(255,
		 * 255, 255)); contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 * setContentPane(contentPane); contentPane.setLayout(null);
		 */

		// ****************************** Page d'accueil
		// ***********************************************
		
		panel = new panel_accueil(this);
		getContentPane().add(panel);
		repaint();
		/*panel = new JPanel();
		panel.setBounds(0, 0, 1, 356);
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel.setLayout(null);

		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(481, 11, 138, 23);
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(panel_2);
				repaint();
			}
		});

		// contentPane.add(btnConnexion);

		JLabel lblNewLabel = new JLabel(new ImageIcon("src\\Gnews.png"));
		lblNewLabel.setBounds(10, 11, 174, 54);
		// contentPane.add(lblNewLabel);

		JButton btnCreerUnCompte1 = new JButton("Creer un compte");
		btnCreerUnCompte1.setBounds(481, 45, 138, 23);
		btnCreerUnCompte1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(panel_3);
				repaint();
			}
		});

		panel.add(lblNewLabel);
		panel.add(btnCreerUnCompte1);
		panel.add(btnConnexion);
		panel.add(lblNewLabel);
		// contentPane.add(btnCreerUnCompte);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 76, 589, 387);
		panel.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(30, 76, 589, 387);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		panel.add(scrollPane);

		try {
			String pilote = "com.mysql.jdbc.Driver";
			Class.forName(pilote);

			Connection connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "");
			System.out.println("Connexion réussie!");
			Statement instruction = connexion.createStatement();
			ResultSet resultat = instruction
					.executeQuery("SELECT * FROM journal");

			while (resultat.next()) {

				String res = textArea.getText();
				res = res + resultat.getString("nom_journal") + "\n"
						+ "* * * * *  " + resultat.getString("Titre_article")
						+ "  * * * * *" + "\n"
						+ resultat.getString("Description") + "\n" + "\n";

				textArea.setText(res + "-----------------------------" + "\n");

			}
		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		} catch (SQLException exc) {
			exc.printStackTrace();
		}*/

		// ******************************** Page de connexion
		// *********************************************

		//panel_2 = new panel_connection(this);
		//id_user=panel_connection.getid_user();
		

		// ******************************* Page de création compte
		// ***********************************

		//panel_3 = new panel_CreerCompte(this);
		// ***************************** Onglets
		// ****************************************************

		//panel_5 = new panel_onglets(this,id_user);
	

		// ******************************* onglet 1: articles
		// ***************************
		


		// ******************************************** Onglet 3 : articles likés**********************************************
		JPanel panel_6 = new JPanel();
		onglet3.setViewportView(panel_6);
		panel_6.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 618, 341);
		panel_6.add(scrollPane_1);

		JTextPane art_like_pane = new JTextPane();
		scrollPane_1.setViewportView(art_like_pane);

		try {
			String pilote = "com.mysql.jdbc.Driver";
			Class.forName(pilote);

			Connection connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "");
			System.out.println("Connexion réussie!");
			Statement instruction = connexion.createStatement();
			ResultSet resultat = instruction
					.executeQuery("SELECT * FROM likes WHERE id_utilisateur =2"); // '"
																					// +id_user+"'");

			while (resultat.next()) {
				idarticle_like = resultat.getInt("id_journal");
			}

			ResultSet res_article = instruction
					.executeQuery("SELECT * FROM journal WHERE id_journal =20"); // '"
																					// +idarticle_like+"'");

			while (res_article.next()) {
				String res = art_like_pane.getText();
				String nomj = resultat.getString("nom_journal");

				res = res + nomj + "\n" + "	* * * * * "
						+ resultat.getString("Titre_article") + " * * * * *"
						+ "\n" + " " + resultat.getString("Description") + "\n"
						+ "\n";

				art_like_pane.setText(" " + res
						+ " ----------------------------- " + "\n");
			}
		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		} catch (SQLException exc) {
			exc.printStackTrace();
		}

		

	}


	public void creercompte(ActionEvent e) {
		String nom = login2.getText();
		char[] pass = password2.getPassword();
		String passString = new String(pass);

		String pilote = "com.mysql.jdbc.Driver";
		try {
			Class.forName(pilote);

			Connection connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "");

			String requete = "SELECT login FROM utilisateurs WHERE login = ?";
			PreparedStatement statement = connexion.prepareStatement(requete);
			statement.setString(1, nom);
			ResultSet result = statement.executeQuery();
			String user = " ";
			while (result.next()) {
				user = result.getString("login");
			}

			if (!(nom.equals(user))) { // si le login n est pas deja dans la
				// table
				// insere le nouvel utilisateur dans la table

				String requete2 = "INSERT INTO utilisateurs "
						+ "VALUES (?,?,?,?)";
				PreparedStatement prestatement = connexion
						.prepareStatement(requete2);
				prestatement.setString(1, null);
				prestatement.setString(2, nom);
				prestatement.setString(3, passString);
				prestatement.setString(4, "000000000");
				prestatement.executeUpdate();
				System.out.println("Nouvel utilisateur enregistré!");
				JOptionPane.showMessageDialog(this,
						"Création de compte réussie!");
				getContentPane().removeAll();
				getContentPane().add(panel);
				repaint();
			} else {
				getContentPane().removeAll();
				getContentPane().add(panel_3);
				repaint();
				JOptionPane.showMessageDialog(this,
						"Le nom d'utilisateur existe déjà, entrez-en un autre",
						"Error Message", JOptionPane.ERROR_MESSAGE);

			}
		} catch (Exception ex) {
			System.out.println("Erreur enregistrement utilisateur " + ex);
		}
	}

	

	public static int getid_user() {
		return id_user;
	}

	public static int getid_journal() {
		return idj;
	}

	public static void afficherArticles() {

		try {

			String pilote = "com.mysql.jdbc.Driver";
			Class.forName(pilote);

			Connection connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "");
			System.out.println("Connexion réussie!");
			// recupere la liste d'abonnement de l'utilisateur
			
			String requete = "SELECT abonnement FROM utilisateurs WHERE ID = ?";
			PreparedStatement statement = connexion.prepareStatement(requete);
			
			statement.setInt(1, 2);//id_user
			ResultSet result = statement.executeQuery();
			String abonnement = "";
			int tab[] = new int[9]; // tableau contenant les valeurs
									// d'abonnement

			while (result.next()) {
				abonnement = result.getString("abonnement");
				for (int i = 0; i < 9; i++) {
					tab[i] = abonnement.charAt(i) - 48;
				}
			}

			String requete2 = "SELECT * FROM journal WHERE id_journal = ? OR id_journal = ? OR id_journal = ? OR id_journal = ? OR id_journal = ? OR id_journal = ? OR id_journal = ? OR id_journal = ? OR id_journal = ?  ";
			PreparedStatement statement2 = connexion.prepareStatement(requete2);
			statement2.setInt(1, tab[0]);
			statement2.setInt(2, tab[1]);
			statement2.setInt(3, tab[2]);
			statement2.setInt(4, tab[3]);
			statement2.setInt(5, tab[4]);
			statement2.setInt(6, tab[5]);
			statement2.setInt(7, tab[6]);
			statement2.setInt(8, tab[7]);
			statement2.setInt(9, tab[8]);
			ResultSet result2 = statement2.executeQuery();

			// +
			while (result2.next()) {
				
				/*
				String res = art_scroll.getText();
				String nomj = result2.getString("nom_journal");

				res = res + nomj + "\n" + "	* * * * * "
						+ result2.getString("Titre_article") + " * * * * *"
						+ "\n" + " " + result2.getString("Description") + "\n"
						+ "\n";

				art_scroll.removeAll();
				art_scroll.setText(" " + res + " ----------------------------- "
						+ "\n");
				*/
			}

		} catch (Exception e) {
			System.out.println("Erreur abonnement");
			e.printStackTrace();
		}
	}
	

}
