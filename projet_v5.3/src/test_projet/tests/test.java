package test_projet.tests;

import java.util.Scanner;

import test_projet.tests.ReadTest;
import test_projet.tests.AffichageTest;
import test_projet.model.Utilisateur;

public class test {

	public static void main(String[] args) {

		try {

			// Connexion au driver:
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			java.sql.Connection conn;
			conn = java.sql.DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "");
			System.out.println("Connexion réussie  !!!");
			System.out.println(" ");
			
			
			// Connexion utilisateur
			int id = 0;  // d'abord 0, puis l identifiant utilisateur
			while (id == 0) {
				System.out
						.println("Pour vous connecter tapez 1, pour créer un compte tapez 2");
				Scanner sc = new Scanner(System.in);
				System.out.println("Votre choix:");
				String choix = sc.nextLine();

				if (choix.equals("1")) {
					id = Utilisateur.sIdentifier(conn);
				} else if (choix.equals("2")) {
					Utilisateur.creerCompte(conn);
				}
			}
			
			// Menu avec possibilité affichage/modif profil
			//int id=4;
			int rep = -1;
		    boolean arret = false;
		    while (!arret){
			System.out.println("--Menu Principal--");
			System.out.println("Modifier le profil : tapez 1");
			System.out.println("Afficher le profil : tapez 2");
			System.out.println("Voir les articles : tapez 3");
		    System.out.println("Quitter : tapez 0");
		    Scanner sc = new Scanner(System.in);
			System.out.println("Votre choix:  ");
			rep = Integer.parseInt(sc.nextLine());
		  			
			switch(rep){
			case 1: {
				Utilisateur.sAbonnerFlux(conn,id);
			} break;
			
			case 2: {
				Utilisateur.afficherProfil(conn, id);
			}break;
			
			case 3: {
				// Récupération de flux
				/*ReadTest.flux("http://flux.20minutes.fr/c/32497/f/479493/index.rss", 1,"20 Minutes", conn);  //20minutes
					
				//ReadTest.flux("https://news.google.fr/news/feeds?pz=1&cf=all&ned=fr&hl=fr&output=rss", "Google News",conn);
				ReadTest.flux("http://www.lequipe.fr/rss/actu_rss.xml?3431326.6002573073", 2,"l'Equipe",conn);  //equipe
				ReadTest.flux("http://www.humanite.fr/rss/actu.rss", 3,"Humanité",conn);   //humanité
				ReadTest.flux("http://rss.lefigaro.fr/lefigaro/laune",4,"le Figaro",conn);   // figaro
				ReadTest.flux("http://rss.lemonde.fr/c/205/f/3050/index.rss",5,"le Monde",conn);  //monde
				ReadTest.flux("http://rss.feedsportal.com/c/499/f/413823/index.rss",6,"les Echos",conn);   // ecos
				ReadTest.flux("http://liberation.fr.feedsportal.com/c/32268/fe.ed/rss.liberation.fr/rss/9/",7,"Libération",conn);   //liberation
				ReadTest.flux("http://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml",8,"le New-York Times",conn);   //new york times
				ReadTest.flux("http://rue89.feedsportal.com/c/33822/f/608948/index.rss", 9,"rue 89",conn);  //rue 89*/
				
				AffichageTest.afficher(conn, id);
			} break;
			
			case 0: {
				arret =true;
				System.exit(0);
			} 
			
			} //fin switch
		   }// fin while
					  
			
		} // fin try
		catch (Exception e) {
			System.out.println("Erreur:" + e);
		}
	}
}
