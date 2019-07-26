package macromania.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class DatabaseListener implements ServletContextListener {

    public DatabaseListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    // au demarrage de la webapp
    public void contextInitialized(ServletContextEvent ctxEvt)  { 
    	System.out.println("initialisation DatabaseListener");
         try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection base = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/base_macromania",
					"root",
					"");
			// instanciation du dao des jeux
			// en donnant la connection a la base de donnée en parametre
			JeuxDAO dao = new JeuxDAO(base);
			
			// mise a disposition sous le nom "dao_jeux" pour les servlets
			ctxEvt.getServletContext().setAttribute("dao_jeux", dao);
			
		} catch (ClassNotFoundException e) {e.printStackTrace();}
         catch (SQLException e) {e.printStackTrace();}
         
    }
	
}
