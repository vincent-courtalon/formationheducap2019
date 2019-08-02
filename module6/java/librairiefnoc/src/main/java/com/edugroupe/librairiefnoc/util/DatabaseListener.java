package com.edugroupe.librairiefnoc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.edugroupe.librairiefnoc.dao.LivreDAO;

/**
 * Application Lifecycle Listener implementation class DatabaseListener
 *
 */
public class DatabaseListener implements ServletContextListener {

    public DatabaseListener() {
        // TODO Auto-generated constructor stub
    }


    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }


    public void contextInitialized(ServletContextEvent ctxEvt)  { 
		try {
			Class.forName(ctxEvt.getServletContext().getInitParameter("driverClass"));
			Connection base = DriverManager.getConnection(
					ctxEvt.getServletContext().getInitParameter("databaseUrl"),
					ctxEvt.getServletContext().getInitParameter("username"),
					ctxEvt.getServletContext().getInitParameter("password"));
			LivreDAO dao = new LivreDAO(base);
			ctxEvt.getServletContext().setAttribute("livreDAO", dao);
			
		} catch (ClassNotFoundException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
		
    }
	
}
