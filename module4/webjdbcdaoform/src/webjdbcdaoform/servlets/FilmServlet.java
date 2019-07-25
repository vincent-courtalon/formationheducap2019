package webjdbcdaoform.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webjdbcdaoform.dao.FilmDAO;
import webjdbcdaoform.metier.Film;

/**
 * Servlet implementation class FilmServlet
 */
public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private FilmDAO filmDAO;
	
    public FilmServlet() {
        super();
    }

    // a l'initialisation de la servlet
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// je récupere la DAO initialisé par le DatabaseListener
		this.filmDAO = (FilmDAO)getServletContext().getAttribute("filmDAO");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("path = " + request.getPathInfo());
		String pathinfo = request.getPathInfo();
		if (pathinfo != null && pathinfo.length() > 1) {
			try {
				// afficher le détail d'un film
				int id = Integer.parseInt(pathinfo.substring(1));
				// je récupere le film
				Film f = filmDAO.findOne(id);
				request.setAttribute("film", f);
				getServletContext().getRequestDispatcher("/film/edit.jsp")
									.forward(request, response);
				return;
			}
			catch( NumberFormatException ex) {ex.printStackTrace();}
		}
		// afficher la liste des films
		List<Film> films = filmDAO.findAll();
		request.setAttribute("films", films);
		getServletContext().getRequestDispatcher("/film/liste.jsp")
							.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
