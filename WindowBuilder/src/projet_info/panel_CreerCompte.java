package projet_info;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class panel_CreerCompte extends JPanel {

	private JTextField login2;
	private JPasswordField password2;
	private JFrame fenetre;
	private int id_user;
	/**
	 * Create the panel.
	 */
	public panel_CreerCompte(JFrame f) {

		this.fenetre = f;
	
		this.setBounds(0, 0, 661, 529);
		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		this.setLayout(null);

		JTextPane txtpnCrezVotreCompte = new JTextPane();
		txtpnCrezVotreCompte.setEditable(false);
		txtpnCrezVotreCompte.setForeground(new Color(112, 128, 144));
		txtpnCrezVotreCompte.setFont(new Font("Lucida Sans Unicode", Font.BOLD,
				18));
		txtpnCrezVotreCompte.setText("Cr\u00E9ez votre compte Gnews");
		txtpnCrezVotreCompte.setBounds(188, 165, 283, 38);
		this.add(txtpnCrezVotreCompte);

		JButton btnC = new JButton("Cr\u00E9er");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creercompte(e);

			}
		});
		btnC.setBounds(250, 343, 126, 29);
		this.add(btnC);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(1, 1, 1, 1,
				(Color) new Color(0, 0, 0)));
		panel_4.setBounds(169, 214, 302, 118);
		this.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(46, 27, 64, 17);
		panel_4.add(lblLogin);

		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(46, 71, 95, 17);
		panel_4.add(lblMotDePasse);

		login2 = new JTextField();
		login2.setBounds(151, 24, 86, 20);
		panel_4.add(login2);
		login2.setColumns(10);

		password2 = new JPasswordField();
		password2.setBounds(151, 68, 86, 20);
		panel_4.add(password2);

		JLabel lblNewLabel_2 = new JLabel(new ImageIcon("src\\Gnews.png"));
		lblNewLabel_2.setBounds(188, 38, 240, 88);
		this.add(lblNewLabel_2);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fenetre.getContentPane().removeAll();
				fenetre.getContentPane().add(new panel_accueil(fenetre));
				fenetre.repaint();
			}
		});
		btnAnnuler.setBounds(250, 392, 126, 29);
		this.add(btnAnnuler);
		
		
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
				fenetre.getContentPane().removeAll();
				fenetre.getContentPane().add(new panel_connection(fenetre));
				fenetre.repaint();
			} else {
				fenetre.getContentPane().removeAll();
				fenetre.getContentPane().add(new panel_connection(fenetre));
				fenetre.repaint();
				JOptionPane.showMessageDialog(this,
						"Le nom d'utilisateur existe déjà, entrez-en un autre",
						"Error Message", JOptionPane.ERROR_MESSAGE);

			}
		} catch (Exception ex) {
			System.out.println("Erreur enregistrement utilisateur " + ex);
		}
	}

}
