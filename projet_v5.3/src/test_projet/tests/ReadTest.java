package test_projet.tests;

import test_projet.model.ListeFlux;
import test_projet.model.MessageFlux;
import test_projet.read.RecupFluxRSS;

import java.sql.*;

public class ReadTest {
	public static void flux(String flux, int idJ, String nomJ, java.sql.Connection conn) {
		RecupFluxRSS parser = new RecupFluxRSS(flux);

		ListeFlux ListeFlux = parser.readListeFlux();
		System.out.println(ListeFlux);
		// On se connecte à la base de données grace au driver
		try {
			int nbflux = ListeFlux.entries.size();
			for (int i = 0; i < nbflux; i++) {

				verifDoublons(conn, idJ,nomJ, i);
			}
			ListeFlux = null;
		} catch (Exception e) {
			System.out.println("Erreur d'ecriture:  " + e);
			System.exit(-1);
		}

	}

	public static void verifDoublons(java.sql.Connection conn, int idJ,String nomJ, int i) {
		try {
			// on isere les flux dans une table provisoire
			String requete = "INSERT INTO prov " + "VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement prestatement = conn.prepareStatement(requete);
			prestatement.setString(1, null);
			prestatement.setInt(2, idJ);
			prestatement.setString(3, nomJ);
			prestatement.setString(4, ListeFlux.entries.get(i).title);
			prestatement.setString(5, ListeFlux.entries.get(i).pubDate);
			prestatement.setString(6, ListeFlux.entries.get(i).description);
			prestatement.setString(7, ListeFlux.entries.get(i).link);
			prestatement.setString(8, ListeFlux.entries.get(i).author);
			prestatement.setString(9, ListeFlux.entries.get(i).Item);

			prestatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erreur table prov: " + e);
		}

		try {
			// on compare la table provisoire à la table journal et on insere les nouvelles valeurs dans le journal
			String requete2 = "INSERT INTO journal (id_journal,nom_journal,Titre_article,Date,Description,Lien,Auteur,Item)" + 
					"SELECT id_journal,nom_journal,Titre_article,Date,Description,Lien,Auteur,Item FROM prov WHERE Titre_article NOT IN (SELECT Titre_article FROM journal) ";
			Statement statement = conn.createStatement();
			statement.executeUpdate(requete2);
			
			String requete3 = "TRUNCATE prov";
			Statement statement3 = conn.createStatement();
			statement3.executeUpdate(requete3);
			
		} catch (Exception e) {
			System.out.println("Erreur nouvelle table " + e);
		}

	}

}