package webjdbcdaoform.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import webjdbcdaoform.dao.FilmDAO;

/**
 * Application Lifecycle Listener implementation class DatabaseListener
 *
 */
public class DatabaseListener implements ServletContextListener {

    public DatabaseListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent ctxEvt)  { 
         // TODO Auto-generated method stub
    }


    public void contextInitialized(ServletContextEvent ctxEvt)  {
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection base = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_web1",
															"root", "");
			// instanciation du dao des films
			FilmDAO dao = new FilmDAO(base);
			// mise a dispodition de celui-ci pour les servlets
			ctxEvt.getServletContext().setAttribute("filmDAO", dao);
			
		} catch (ClassNotFoundException e) {e.printStackTrace();} 
    	catch (SQLException e) {e.printStackTrace();}
    	
    }
	
}
