package projet_info;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class panel_connection extends JPanel {

	private JButton bt_conn;
	private JLabel lblNewLabel_1;
	private JPanel panel_1;
	private JLabel label;
	private JLabel label_1;
	private JTextField login;
	private JButton bt_cre;
	private JPasswordField password;
	private JFrame f;
	private static int id_user;
	private JButton btnNewButton_1;
	
	
	/**
	 * Create the panel.
	 */
	public panel_connection(JFrame fenetre) {
		this.setBounds(0, 0, 661, 529);
		this.setBackground(Color.WHITE);
		// getContentPane().add(panel_2);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		
		this.f = fenetre;

		bt_cre= new JButton("Creer un compte");
		bt_cre.setBounds(203, 406, 262, 23);
		bt_cre.setBackground(UIManager.getColor("Button.background"));
		bt_cre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.getContentPane().removeAll();
				f.getContentPane().add(new panel_accueil(f));
				f.repaint();
			}
		});
		this.setLayout(null);
		this.add(bt_cre);

		JEditorPane dtrpnConnectezvousPourAccder = new JEditorPane();
		dtrpnConnectezvousPourAccder.setBounds(231, 209, 218, 20);
		dtrpnConnectezvousPourAccder
				.setFont(new Font("Tahoma", Font.ITALIC, 11));
		dtrpnConnectezvousPourAccder.setForeground(new Color(153, 153, 153));
		dtrpnConnectezvousPourAccder.setEditable(false);
		dtrpnConnectezvousPourAccder
				.setText("Connectez-vous pour acc\u00E9der \u00E0 vos news");
		this.add(dtrpnConnectezvousPourAccder);

		lblNewLabel_1 = new JLabel(new ImageIcon("src\\Gnews.png"));
		lblNewLabel_1.setBounds(203, 32, 207, 74);
		this.add(lblNewLabel_1);

		panel_1 = new JPanel();
		panel_1.setBounds(181, 240, 295, 148);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		this.add(panel_1);
		panel_1.setLayout(null);

		label = new JLabel("Mot de passe:");
		label.setBounds(10, 61, 72, 16);
		panel_1.add(label);
		label.setFont(new Font("Open Sans", Font.PLAIN, 11));

		label_1 = new JLabel("Login:");
		label_1.setBounds(10, 21, 33, 17);
		panel_1.add(label_1);
		label_1.setFont(new Font("Open Sans", Font.PLAIN, 12));

		login = new JTextField();
		login.setBounds(92, 20, 118, 20);
		panel_1.add(login);
		login.setColumns(10);

		password = new JPasswordField();
		password.setBounds(92, 59, 122, 20);
		panel_1.add(password);

		bt_conn = new JButton("Connexion");
		bt_conn.setBackground(UIManager.getColor("Button.background"));
		bt_conn.setBounds(166, 114, 106, 23);
		panel_1.add(bt_conn);
		
		bt_conn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id_user = testconnexion(e);

			}
		});

		JTextPane txtpnTouteLactuAvec = new JTextPane();
		txtpnTouteLactuAvec.setBounds(145, 157, 366, 28);
		txtpnTouteLactuAvec.setForeground(Color.GRAY);
		txtpnTouteLactuAvec.setFont(new Font("Georgia", Font.BOLD, 20));
		txtpnTouteLactuAvec.setText("Toute l'actu avec un seul compte");
		txtpnTouteLactuAvec.setEditable(false);
		this.add(txtpnTouteLactuAvec);
	}
	
	public int testconnexion(ActionEvent e) {
		String nomBDD = "";
		String passwordBDD = "";
		char[] pass = password.getPassword();
		String passString = new String(pass);
		String pilote = "com.mysql.jdbc.Driver";
		int id = 0;
		try {

			Class.forName(pilote);

			Connection connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "");
			System.out.println("Connexion réussie!");

			Statement instruction = connexion.createStatement();

			ResultSet res2 = instruction
					.executeQuery("SELECT * FROM utilisateurs WHERE login='"
							+ login.getText() + "' && password='" + passString
							+ "'");

			while (res2.next()) {
				if (e.getSource() == bt_conn) {
					nomBDD = res2.getString("login");
					passwordBDD = res2.getString("password");
					id = res2.getInt("ID");
				}
			}
		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		} catch (SQLException exc) {
			exc.printStackTrace();
		}

		if (login.getText().equals(nomBDD) && passString.equals(passwordBDD)) {
			f.getContentPane().removeAll();
			f.getContentPane().add(new panel_onglets(f,id_user));
			f.repaint();
			System.out.println("id utilisateur: " +id);

		} else {
			JOptionPane.showMessageDialog(this,
					"Nom d'utilisateur/Mot de passe invalide", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}
		return id;
	}
	
	public static int getid_user() {
		return id_user;
	}

}
