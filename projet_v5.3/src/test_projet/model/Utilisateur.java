package test_projet.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//import test_projet.tests.Fenetre;

public class Utilisateur {
	public String login;
	public String password;
	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public static void afficherProfil(java.sql.Connection conn, int id) {
		System.out.println("VOTRE PROFIL :");

		try {
			// Recuperation profil
			String requete = "SELECT * FROM utilisateurs WHERE ID = ?";
			PreparedStatement statement = conn.prepareStatement(requete);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			int tab[] = new int[9];  // tableau contenant les valeurs d'abonnement
			String tabJ[] = new String[9];
			String abonnement="";
			
			while (result.next()) {
				abonnement = result.getString("abonnement");
				for (int i=0;i<9;i++) {
					tab[i]=abonnement.charAt(i)-48;
				}
			
			
			// Affichage profil
				System.out.println("                  ID: "
						+ result.getInt("ID"));
				System.out.println("           Nom d'utilisateur: "
						+ result.getString("login"));
				System.out
						.println("---------------------------------------------------------");
				System.out.println("Vous êtes abonné à :");
				
						System.out.println(abonnement);
			}
		} catch (SQLException e) {
			System.out.println("Erreur de recuperation de profil: " + e);
		}

	}

	public static void sAbonnerFlux(java.sql.Connection conn, int id) {
		try {
			// on cherche la ligne correspondant a l utilisateur:
			String requete = "SELECT * FROM utilisateurs WHERE ID = ?";
			PreparedStatement statement = conn.prepareStatement(requete,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE); // permet la modif des donnees
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			String abonnement="";
			String nouveau="";
			int tab[] = new int[9];  // tableau contenant les valeurs d'abonnement
			
			while (result.next()) {
				abonnement = result.getString("abonnement");
				for (int i=0;i<9;i++) {
					tab[i]=abonnement.charAt(i)-48;
				}
			}

			int choix = 0;

			// liste de journaux:
			while (choix != 10) {
				System.out
						.println("A quel journal souhaitez-vous vous abonner?");
				System.out.println("   1 - 20 Minutes");
				System.out.println("   2 - l'Equipe");
				System.out.println("   3 - Humanité");
				System.out.println("   4 - Figaro");
				System.out.println("   5 - le Monde");
				System.out.println("   6 - les Echos");
				System.out.println("   7 - Libération");
				System.out.println("   8 - le New-York Times");
				System.out.println("   9 - Rue 89");
				System.out.println("   10 - Retour menu principal");

				// choix utilisateur:
				Scanner sc = new Scanner(System.in);
				System.out.println("Votre choix:");
				choix = Integer.parseInt(sc.nextLine());

				if (choix > 0 && choix < 10) {
					int i=0;
					while (i<9 && tab[i]!=choix && tab[i]!=0) {
						nouveau=nouveau+abonnement.charAt(i);
						i=i+1;  // se place correctement
					}
					if (tab[i]==choix) {
						System.out.println("Vous etes déja abonné à ce journal.");
					}
					if (tab[i]==0) {
						nouveau = nouveau+choix;
						for (int j=i+1;j<9;j++) {
							nouveau=nouveau+"0";
						}
						System.out.println("nouveau: "+nouveau);
						result.first();
						result.updateString(4, nouveau);
						result.updateRow(); // valide les modifs

						System.out.println("abonnement réussi !");
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Erreur abonnement");
		}

	}

	public static void creerCompte(java.sql.Connection conn) {

		// Dialogue avec l utilisateur
		System.out.println("Creer un nouvel utilisateur:");

		Scanner sc = new Scanner(System.in);
		System.out.println("Login :");
		String log = sc.nextLine();

		Scanner sc2 = new Scanner(System.in);
		System.out.println("Password :");
		String pw = sc2.nextLine();

		// test existence utilisateur
		try {
			String requete = "SELECT login FROM utilisateurs WHERE login = ?";
			PreparedStatement statement = conn.prepareStatement(requete);
			statement.setString(1, log);
			ResultSet result = statement.executeQuery();
			String user = " ";
			while (result.next()) {
				user = result.getString("login");
			}

			if (!(log.equals(user))) { // si le login n est pas deja dans la
										// table
				// insere le nouvel utilisateur dans la table

				String requete2 = "INSERT INTO utilisateurs "
						+ "VALUES (?,?,?,?)";
				PreparedStatement prestatement = conn
						.prepareStatement(requete2);
				prestatement.setString(1, null);
				prestatement.setString(2, log);
				prestatement.setString(3, pw);
				prestatement.setString(4, "000000000");
				prestatement.executeUpdate();
				System.out.println("Nouvel utilisateur enregistré!");

			} else {
				System.out.println("Login existant. Recommencez.");
			}
		} catch (Exception e) {
			System.out.println("Erreur enregistrement utilisateur " + e);
		}

	}


	public static int sIdentifier(java.sql.Connection conn) {
		Scanner sc = new Scanner(System.in); // Permet d'écrire son choix
		System.out.println("login :");
		String logtest = sc.nextLine();

		Scanner sc2 = new Scanner(System.in);
		System.out.println("Password :");
		String passwordtest = sc2.nextLine();

		try {
			int id = 0;
			String pw="";
			String requete = "SELECT * FROM utilisateurs WHERE login = ?";
			PreparedStatement statement = conn.prepareStatement(requete);
			statement.setString(1, logtest); // cherche le login entré par
												// l'utilisateur

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				pw = result.getString("password"); // enregistre le password
													// attendu
				id = result.getInt("ID");
			}

			if (pw.equals(passwordtest)) // vérifie
			{
				System.out.println("Utilisateur identifié !");
				return id;
			} else {
				System.out.println("Utilisateur non identifié");
				return 0;
			}
		/*int id=0;
		try{

			Fenetre f=new Fenetre();
			id= f.recupIDUser();
			*/
		} catch (Exception e) {
			System.out.println("Erreur: " + e);
			return 0;
		}
		//return id;

	}
}
