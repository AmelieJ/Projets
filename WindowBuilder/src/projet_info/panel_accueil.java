package projet_info;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class panel_accueil extends JPanel {

	private JFrame fenetre;
	/**
	 * Create the panel.
	 */
	public panel_accueil(JFrame f) {
		
		this.fenetre=f;
		
		this.setBounds(0, 0, 1, 356);
		this.setBackground(Color.WHITE);
		fenetre.getContentPane().add(this);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		this.setLayout(null);

		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(481, 11, 138, 23);
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fenetre.getContentPane().removeAll();
				fenetre.getContentPane().add(new panel_connection(fenetre));
				fenetre.repaint();
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
				fenetre.getContentPane().removeAll();
				fenetre.getContentPane().add(new panel_CreerCompte(fenetre));
				fenetre.repaint();
			}
		});

		this.add(lblNewLabel);
		this.add(btnCreerUnCompte1);
		this.add(btnConnexion);
		this.add(lblNewLabel);
		// contentPane.add(btnCreerUnCompte);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 76, 589, 387);
		this.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(30, 76, 589, 387);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		this.add(scrollPane);

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
		}

	}

}
