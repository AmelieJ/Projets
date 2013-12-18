package projet_info;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;


public class panel_onglets extends JPanel {

	private JScrollPane onglet2 = new JScrollPane();
	private JScrollPane onglet3 = new JScrollPane();
	
	private JLabel principalLabel = new JLabel("Fenêtre Principale ");
	private JLabel profilLabel = new JLabel("Votre Profil");
	
	private JFrame fenetre;
	private int id_user;
	private static int idj = 0;
	private static int i=0;
	private static int id_journal_lu[] = new int[100];
	
	private static Article_complet [] a = new Article_complet[100];
	
	
	
	private static CheckListItem item;

	/**
	 * Create the panel.
	 */
	public panel_onglets(JFrame f, int id) {
		
		this.fenetre=f;
		this.id_user=id;

		this.setBounds(0, 0, 661, 529);
		this.setBackground(Color.WHITE);
		// getContentPane().add(this);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		this.setLayout(null);

		JTabbedPane Onglets = new JTabbedPane(JTabbedPane.TOP);
		Onglets.setBounds(10, 96, 625, 371);
		Onglets.setBackground(Color.WHITE);
		this.add(Onglets);
		onglet3.add(profilLabel);
		
		JLabel lblNewLabel_3 = new JLabel(new ImageIcon("src\\Gnews.png"));
		lblNewLabel_3.setBounds(21, 24, 157, 61);
		this.add(lblNewLabel_3);

		JButton btnRetour = new JButton("D\u00E9connexion");
		btnRetour.setBounds(469, 24, 139, 27);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fenetre.getContentPane().removeAll();
				fenetre.getContentPane().add(new panel_accueil(fenetre));
				fenetre.repaint();
			}
		});
		this.add(btnRetour);

		JButton btnNewButton_2 = new JButton("Mon profil");
		btnNewButton_2.setBounds(469, 62, 139, 27);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		this.add(btnNewButton_2);
		
		JScrollPane onglet1 = new JScrollPane();
		Onglets.addTab("Articles", null, onglet1);
		
		JTextPane article []= new JTextPane[100];
		//art_scroll.setViewportView(article);


		JList list_flux = new JList(new CheckListItem[] {
				new CheckListItem("20 minutes"), 
				new CheckListItem("Equipe"),
				new CheckListItem("Humanité"), 
				new CheckListItem("Figaro"),
				new CheckListItem("le Monde"),
				new CheckListItem("les Echos"),
				new CheckListItem("Liberation"),
				new CheckListItem("le New-York Times"), 
				new CheckListItem("Rue 89") });
		list_flux.setValueIsAdjusting(true);

		list_flux.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		list_flux.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		onglet1.setRowHeaderView(list_flux);

		list_flux.setCellRenderer(new CheckListRenderer());

		list_flux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane art_scroll = new JScrollPane();

		onglet1.setViewportView(art_scroll);
		
		
		list_flux.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				JList list_flux = (JList) event.getSource();
				int index = list_flux.locationToIndex(event.getPoint());
				item = (CheckListItem) list_flux.getModel().getElementAt(index);
				item.setSelected(!item.isSelected());
				list_flux.repaint(list_flux.getCellBounds(index, index));

				/* Abonnement au flux & affichage des articles */
				sAbonnerFlux(id_user);
				//afficherArticles();

			}
		});
		
		
		JTextPane les_articles = new JTextPane();
		les_articles.setEditable(false);
		
		
		int x=0;
		int y=0;
				
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
				
				article[i] = new JTextPane();
				article[i].setEditable(false);
				article[i].setBounds(x, y, 473, 120);
				
				
				id_journal_lu[i] = resultat.getInt("ID");
										
				//idj = resultat.getInt("ID");
				
				String res = article[i].getText();
				String nomj = resultat.getString("nom_journal");

				res = res + nomj + "\n" + "	* * * * * "
						+ resultat.getString("Titre_article") + " * * * * *"
						+ "\n" + " " + resultat.getString("Description") + "\n"
						+ "\n";

					article[i].setText(" " + res + " ----------------------------- "
						+ "\n");
					
					
					les_articles.add(article[i]);
					y=y+120;
					i=i++;
					
					idj=id_journal_lu[i];
					
					final int j=i;
					
					article[i].addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent arg0) {
							a[j] = new Article_complet(idj);
							a[j].setVisible(true);
							
						}
					});
			}

		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		} catch (SQLException exc) {
			exc.printStackTrace();
		}

		les_articles.setPreferredSize(new Dimension(472,y));
		les_articles.setLayout(null);
		art_scroll.setViewportView(les_articles);
		

		Onglets.addTab("Journal Perso", null, onglet2);
		Onglets.addTab("Articles likés", null, onglet3);
		

	}
public static void sAbonnerFlux(int id_user) {

		
		try {
			
			String pilote = "com.mysql.jdbc.Driver";
			Class.forName(pilote);

			Connection connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "");
			System.out.println("Connexion réussie!");
			
			// on cherche la ligne correspondant a l utilisateur:
			
			String requete = "SELECT * FROM utilisateurs WHERE ID = ?";
			PreparedStatement statement = connexion.prepareStatement(requete,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE); // permet la modif des donnees
			statement.setInt(1, id_user);
			ResultSet result = statement.executeQuery();

			String nomj = item.toString();
			String requete3 = "SELECT * FROM listejournaux WHERE nom = ?";
			PreparedStatement statement2 = connexion.prepareStatement(requete3,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);  // on récupère le num du journal séléctionné
			statement2.setString(1, nomj);
		
			System.out.println(nomj);

			ResultSet res_idjour = statement2.executeQuery();


			int idjournal = 0;
			String journal ="";
			while (res_idjour.next()) {
				idjournal = res_idjour.getInt("ID");
				journal = res_idjour.getString("nom");
				}

			System.out.println("id journal : " + idjournal);
			System.out.println("nomj" + journal);
			
			String abonnement = "";
			String nouveau = "";
			int tab[] = new int[9]; // tableau contenant les valeurs
									// d'abonnement

			while (result.next()) {
				abonnement = result.getString("abonnement");
				for (int i = 0; i < 9; i++) {
					tab[i] = abonnement.charAt(i) - 48; // Correspondance du chiffre à leur table ASCII
				}
			}
			
			System.out.println("abonnement : " +abonnement);

			System.out.println(result);
			System.out.println(res_idjour);
			
			// /!\ On ne rentre pas dans le While !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			while (res_idjour.next()){
				System.out.println("nouveau: "+journal);
				if (idjournal > 0 && idjournal < 10) {
					int i = 0;
					
					while (i < 9 && tab[i] != idjournal && tab[i] != 0) {
						nouveau = nouveau + abonnement.charAt(i);
						i = i + 1; // se place correctement
						System.out.println("nouveau: "+ nouveau);
					}
					if (tab[i] == 0) {
						nouveau = nouveau + idjournal;
						for (int j = i + 1; j < 9; j++) {
							nouveau = nouveau + "0";
						}
						System.out.println("nouveau: " + nouveau);
						result.first();
						result.updateString(4, nouveau);
						result.updateRow(); // valide les modifs

						System.out.println("abonnement réussi !");

					}
				}
			}

		} catch (Exception e) {
			System.out.println("Erreur abonnement");
			e.printStackTrace();
		}

	}

}
