package test_projet.tests;

import java.sql.*;
import java.util.Scanner;

import test_projet.tests.SelectionArticle;
import test_projet.tests.BrowserControl;

public class AffichageTest {

	public static void afficher(java.sql.Connection conn, int id) {
		
		try{
			// recupere la liste d'abonnement de l'utilisateur
			String requete = "SELECT abonnement FROM utilisateurs WHERE ID = ?";
			PreparedStatement statement = conn.prepareStatement(requete); 
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			String abonnement ="";
			int tab[] = new int[9];  // tableau contenant les valeurs d'abonnement
			
			while (result.next()) {
				abonnement = result.getString("abonnement");
				for (int i=0;i<9;i++) {
					tab[i]=abonnement.charAt(i)-48;
				}
			}
			
			// recupere uniquement les journaux auxquels l'utilisateur est abonné:
			String requete2 = "SELECT * FROM journal WHERE id_journal = ? OR id_journal = ? OR id_journal = ? OR id_journal = ? OR id_journal = ? OR id_journal = ? OR id_journal = ? OR id_journal = ? OR id_journal = ?  ";
			PreparedStatement statement2 = conn.prepareStatement(requete2); 
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
			
			//+
				while(result2.next()){
					// affichage des infos de l'article:
					System.out.println("---------------------------");
					// ATTENTION! Il faut mettre le nom de votre colonne!
					System.out.println("Numéro de l'article: " + result2.getInt("ID"));
					System.out.println("    Journal: " + result2.getString("nom_journal"));
					System.out.println("	Titre de l'article: "+ result2.getString("Titre_article") ); 
					System.out.println("	Description: "+ result2.getString("Description"));
				}
			
		}
		catch(SQLException e){ e.printStackTrace();}
		int choix=-1;
		boolean stop=false;
		while(!stop){
		System.out.println("Lire un article : tapez 1");
	    System.out.println("Retour au menu principal: tapez 0");
	    Scanner sc = new Scanner(System.in);
		System.out.println("Votre choix:  ");
		choix = Integer.parseInt(sc.nextLine());
		switch(choix){
		case 1:{
			SelectionArticle choix_article = new SelectionArticle();
			int numero_choix = choix_article.choisirArticle();
			String url_choix = choix_article.rechercherNomUrl(numero_choix);
			BrowserControl.displayURL(url_choix);
		}break;
		
		case 0:{
			stop=true;
		}break;
		}//fin switch
		}//fin while
		
	} 

	}
