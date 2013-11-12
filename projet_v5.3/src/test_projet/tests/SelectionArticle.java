package test_projet.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.*;

public class SelectionArticle {
	
	public int num;
	public String nom_url;
	
	public SelectionArticle() {
		this.num = 0;
		this.nom_url = "";
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getNom_url() {
		return nom_url;
	}

	public void setNom_url(String nom_url) {
		this.nom_url = nom_url;
	}

	public int choisirArticle() {
		
		System.out.println("---------------------------");
		Scanner choix_article = new Scanner(System.in); //Permet d'écrire son choix
		System.out.println("Quel article voulez vous lire?");
		int num = choix_article.nextInt();
		return num;
	}
	
	public String rechercherNomUrl(int num) {
		String nom_url = new String();
		String pilote = "com.mysql.jdbc.Driver";
		
		try{
			Class.forName(pilote);

			Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");

			Statement instruction = connexion.createStatement();

			ResultSet resultat = instruction.executeQuery("SELECT Lien FROM journal WHERE ID = " + num);
			
			while(resultat.next()){
				
				nom_url = resultat.getString("Lien");
				
				System.out.println("---------------------------");
				System.out.println("	Lien: "+ nom_url);
				}
			}
			catch(ClassNotFoundException e){
			e.printStackTrace();}
			catch(SQLException e){ e.printStackTrace();}
		
		return nom_url;
	}
	
	
}
