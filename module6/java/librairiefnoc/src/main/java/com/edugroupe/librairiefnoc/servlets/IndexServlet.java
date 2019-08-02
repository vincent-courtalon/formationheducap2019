package com.edugroupe.librairiefnoc.servlets;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.edugroupe.librairiefnoc.dao.LivreDAO;
import com.edugroupe.librairiefnoc.metier.Livre;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class IndexServlet extends HttpServlet {
	
	private static Logger log = LogManager.getLogger(IndexServlet.class); 
	private static final long serialVersionUID = 1L;

	private LivreDAO dao;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.dao = (LivreDAO)getServletContext().getAttribute("livreDAO");
	}




	public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

      response.setContentType("application/json");
      PrintWriter out = response.getWriter();

      List<Livre> livres = null;
      String search = request.getParameter("search");
      String nbPages = request.getParameter("nbPages");
      
      if (search != null && search.length() > 0) {
    	  livres = dao.findByTitre(search);
      }
      else if (nbPages != null && nbPages.length() > 0) {
    	  livres = dao.findByNbPages(Integer.parseInt(nbPages));
      }
      else {
    	  livres = dao.findAll();
      }
      
      GsonBuilder builder = new GsonBuilder();
      Gson gson = builder.create();
      
      out.print(gson.toJson(livres));
      
      out.close();
   }




	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Livre l = new Livre(Integer.parseInt(req.getParameter("id")),
							req.getParameter("titre"),
							req.getParameter("isbn"),
							Integer.parseInt(req.getParameter("nbPages")),
							req.getParameter("auteur"));
		
		
		// infos sur la sauvegarde
		Map<String, Object> result = new HashMap<>();
		result.put("nblignesSauvees", dao.save(l));
		
		// preparation de la reponse
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		// classes pour transformer mes objets java metier en json
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		// reponse
		out.print(gson.toJson(result));
		
		out.close();

	}

	
	
	
}
