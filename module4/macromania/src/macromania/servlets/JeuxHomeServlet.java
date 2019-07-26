package macromania.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import macromania.metier.Jeux;
import macromania.util.JeuxDAO;

/**
 * Servlet implementation class JeuxHomeServlet
 */
public class JeuxHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private JeuxDAO le_dao;
	
    public JeuxHomeServlet() {
        super();
        System.out.println("construction servlet JeuxHomeServlet");
    }


	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("init servlet JeuxHomeServlet");
		// je recupere dans mon controleur le dao préparé
		// par le database listener
		le_dao = (JeuxDAO)getServletContext().getAttribute("dao_jeux");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HTTP GET servlet JeuxHomeServlet");
		String path = request.getPathInfo();
		List<Jeux> jeux = null;
		
		if (path != null && path.length() > 1)
			jeux = le_dao.findAllByplateforme(path.substring(1));
		else
			jeux = le_dao.findAll();
		
		// passer la collection des jeux dans la requette
		request.setAttribute("liste_jeux", jeux);
		// passer le controle a la page jsp de notre choix pour generer le html
		getServletContext().getRequestDispatcher("/jeux/liste.jsp")
						   .forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int supprId = Integer.parseInt(request.getParameter("supprId"));
		le_dao.deleteOne(supprId);
		response.sendRedirect("/macromania/");
	}

}
