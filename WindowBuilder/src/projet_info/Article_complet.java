package projet_info;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;





import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

import projet_info.fenetre1;
public class Article_complet extends JFrame {

	private JPanel contentPane;
	private static URL lien;
	private JButton pouce;
	private JButton pouce2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Article_complet frame = new Article_complet(20);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Article_complet(int id_journal) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 620, 432);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton Retour = new JButton("Retour");
		Retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		Retour.setBounds(496, 28, 114, 29);
		panel.add(Retour);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("src\\Gnews.png"));
		lblNewLabel.setBounds(33, 11, 174, 46);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(160, 75, 450, 346);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 75, 140, 346);
		panel.add(scrollPane_1);
				
		final JTextPane textPane = new JTextPane();
		textPane.setForeground(new Color(128, 128, 128));
		textPane.setFont(new Font("Bodoni MT", Font.BOLD | Font.ITALIC, 14));
		textPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					lien = pretraitement(textPane.getSelectedText());
					BrowserControl.openWebpage(lien);
				}
							
			}
		});
		textPane.setBackground(new Color(176, 224, 230));
		textPane.setEditable(false);
		scrollPane_1.setViewportView(textPane);
		
		pouce2 = new JButton("");
		pouce2.setIcon(new ImageIcon("C:\\Users\\Am\u00E9lie\\Documents\\Documents\\Cours FI2\\Semestre 1\\Projet info\\WindowBuilder\\src\\images2.jpg"));
		pouce2.setBounds(433, 18, 53, 46);
		panel.add(pouce2);
		pouce2.setVisible(false);
		
		pouce = new JButton(new ImageIcon("src\\images.jpeg"));
		pouce.setForeground(Color.BLACK);
		pouce.setBackground(Color.WHITE);
		pouce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				liker();
				pouce.setVisible(false);
				pouce2.setVisible(true);
			}
		});
		
		pouce.setBounds(433, 18, 53, 46);
		panel.add(pouce);
		

		
		
		try {
			String pilote = "com.mysql.jdbc.Driver";
			Class.forName(pilote);

			Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			System.out.println("Connexion réussie!");
			Statement instruction = connexion.createStatement();
			ResultSet resultat = instruction.executeQuery("SELECT * FROM journal WHERE ID = '" + id_journal +"'");
			
			

			DefaultListModel def = new DefaultListModel();
						
			String np="";
			while (resultat.next()) {

				np = resultat.getString("NP");
				
				String articleComp = resultat.getString("Article");
				articleComp = articleComp.replaceAll("retourALaLigne","\n");

				String res = textArea.getText();
				res = res + resultat.getString("nom_journal") + "\n"
						+ "* * * * *  " + resultat.getString("Titre_article")
						+ "  * * * * *" + "\n" + "\n"
						+ " "+ articleComp + "\n" + "\n";

				textArea.setText(res + "-----------------------------" + "\n");
				
				np=np.replaceAll(" ", "\n");	
							
				def.addElement(np);
							
				String text ="";
				text= text + np;
				textPane.setText(text);

			}
			
		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		
		
	}
	
public static URL pretraitement(String mot){
		
		mot = mot.replaceAll("\\p{Punct}|\\d", " ");
		mot = mot.replace("»", "");
		
		/*if (mot.charAt(mot.length())=='_') {
			mot = mot.replace("_", "");
		}*/
		
		String s = "http://fr.wikipedia.org/wiki/" + mot; // traiter les mots composés pour afficher des underscore. Spécial:Recherche
		//to do : mettre ce lien hypertexte lorsqu'on clique sur le mot ActionEvent
		
		try {
			lien= new URL(s);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lien;
	}



public static void liker() {
	try {
		
		String pilote = "com.mysql.jdbc.Driver";
		Class.forName(pilote);

		Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
		System.out.println("Connexion réussie!");
		Statement instruction = connexion.createStatement();
		
		/* On doit avoir l'id de l'article séléctionné ==> id_journal */ 
		int id_journal = 20;
		//int id_journal = fenetre1.getid_journal();
		
		/*on récupère l'id de l'utilisateur connecté*/
		int id_user = 2;
		//int id_user= fenetre1.getid_user();
		
		String requete = "INSERT INTO likes (ID, id_journal, id_utilisateur)" + " VALUES (?,?,?)";
		PreparedStatement prestatement = connexion.prepareStatement(requete);
		prestatement.setString(1, null);
		prestatement.setInt(2, id_journal);
		prestatement.setInt(3, id_user);
		prestatement.executeUpdate();
	}
	catch (Exception e) {
		System.out.println("Erreur table like: " + e);
	}
}
}
