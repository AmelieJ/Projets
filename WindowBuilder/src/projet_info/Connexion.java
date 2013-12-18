package projet_info;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import java.awt.Window.Type;

public class Connexion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton button;
	private JLabel label;
	private JLabel label_1;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JTextPane txtpnTouteLactu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connexion frame = new Connexion();
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
	public Connexion() {
		setType(Type.POPUP);
		setTitle("Connectez-vous \u00E0 votre compte GNEWS");
		setBounds(100, 100, 548, 373);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel.setBounds(122, 141, 263, 147);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(103, 18, 101, 25);
		panel.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(103, 47, 101, 25);
		panel.add(passwordField);
		
		button = new JButton("Connexion");
		button.setBackground(new Color(204, 204, 204));
		button.setBounds(103, 102, 101, 23);
		panel.add(button);
		
		label = new JLabel("Mot de passe:");
		label.setFont(new Font("Open Sans", Font.PLAIN, 11));
		label.setBounds(10, 52, 82, 14);
		panel.add(label);
		
		label_1 = new JLabel("Login:");
		label_1.setFont(new Font("Open Sans", Font.PLAIN, 12));
		label_1.setBounds(10, 22, 45, 14);
		panel.add(label_1);
		
		btnNewButton = new JButton("Creer un compte");
		btnNewButton.setBackground(new Color(204, 204, 204));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(170, 300, 174, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel(new ImageIcon("src\\Gnews.png"));
		lblNewLabel.setBounds(170, 11, 174, 54);
		contentPane.add(lblNewLabel);
		
		txtpnTouteLactu = new JTextPane();
		txtpnTouteLactu.setForeground(new Color(102, 102, 102));
		txtpnTouteLactu.setFont(new Font("Georgia", Font.BOLD, 15));
		txtpnTouteLactu.setEditable(false);
		txtpnTouteLactu.setText("Toute l'actu avec un seul compte");
		txtpnTouteLactu.setBounds(122, 76, 263, 23);
		contentPane.add(txtpnTouteLactu);
		
		JEditorPane dtrpnConnectezvousPourAccder = new JEditorPane();
		dtrpnConnectezvousPourAccder.setFont(new Font("Tahoma", Font.ITALIC, 11));
		dtrpnConnectezvousPourAccder.setForeground(new Color(153, 153, 153));
		dtrpnConnectezvousPourAccder.setEditable(false);
		dtrpnConnectezvousPourAccder.setText("Connectez-vous pour acc\u00E9der \u00E0 vos news");
		dtrpnConnectezvousPourAccder.setBounds(145, 110, 218, 20);
		contentPane.add(dtrpnConnectezvousPourAccder);
	}
}
