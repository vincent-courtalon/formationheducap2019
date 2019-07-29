package superQueryBuilderFrom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import superQueryBuilderFrom.QueryBuilder.TypeWhere;

public class App {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection base = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_web1",
					"root", "");
			
			Scanner lecteur = new Scanner(System.in);
			QueryBuilder builder = new QueryBuilder("films", base);
	/*		
			System.out.println("id du film a effacer ?");
			int id = Integer.parseInt(lecteur.nextLine());
			PreparedStatement deleteStat = 
					builder.addWhere("id", TypeWhere.EQUAL, 1)
							.delete()
							.build();
			
			deleteStat.setInt(1, id);
			deleteStat.executeUpdate();
		*/	
		/*	System.out.println("titre nouveau film ? ");
			String titre = lecteur.nextLine();
			
			System.out.println("longueur nouveau film? ");
			int longueur = Integer.parseInt(lecteur.nextLine());
			
			System.out.println("annee nouveau film? ");
			int annee = Integer.parseInt(lecteur.nextLine());
			
			System.out.println("genre nouveau film ? ");
			String genre = lecteur.nextLine();
			
			PreparedStatement insertStat = 
					builder.addField("titre")
							.addField("longueur")
							.addField("annee")
							.addField("genre")
							.insert()
							.build();
					
			insertStat.setString(1, titre);
			insertStat.setInt(2, longueur);
			insertStat.setInt(3, annee);
			insertStat.setString(4, genre);
			insertStat.executeUpdate();
			*/
			
			
			/*PreparedStatement updateStat =
					 builder.addField("longueur")
							.addField("titre")
							.update()
							.addWhere("id", TypeWhere.EQUAL, 1)
							.build();
			
			updateStat.setInt(1, 180); // nouvelle longueur
			updateStat.setString(2, "willow 3"); // nouveau titre
			updateStat.setInt(3, 1); // film d'id 1
			updateStat.executeUpdate();
			*/
			
			PreparedStatement selectStat = 
					builder.addField("id")
							.addField("titre")
							.addField("annee")
							.addField("longueur")
							.addWhere("annee", TypeWhere.LESS, 1)
			//				.addWhere("longueur", TypeWhere.MORE, 2)
							.addSort("longueur", false)
							.select()
							.build();
			selectStat.setInt(1, 1990);
			//selectStat.setInt(2, 120);
			
			ResultSet rs = selectStat.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("id") +  " - "
								+ rs.getString("titre") + " - " 
								+ rs.getInt("annee") + " - "
								+ rs.getInt("longueur"));
			}
			rs.close();
			
			base.close();
			
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
		


	}

}
