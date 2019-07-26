package macromania.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import macromania.metier.Jeux;
import macromania.util.JeuxDAO;

/**
 * Servlet implementation class JeuxEditServlet
 */
public class JeuxEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private JeuxDAO le_dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JeuxEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		le_dao = (JeuxDAO)getServletContext().getAttribute("dao_jeux");
	}

	// afficher le formulaire d'edition
	//	nouveau jeu
	//		GET /JeuxEditServlet/0
	//  edition jeur
	//		GET /JeuxEditServlet/id 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int editId = Integer.parseInt(request.getPathInfo().substring(1));
		Jeux edit_jeu = null;
		if (editId == 0)
			edit_jeu = new Jeux();
		else
			edit_jeu = le_dao.findOne(editId);
		request.setAttribute("edit_jeu", edit_jeu);
		getServletContext().getRequestDispatcher("/jeux/edit.jsp")
							.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Jeux jeu_save = new Jeux(Integer.parseInt(request.getParameter("id")),
					request.getParameter("titre"),
					request.getParameter("description"),
					request.getParameter("plateforme"),
					Integer.parseInt(request.getParameter("annee")));
		le_dao.save(jeu_save);
		response.sendRedirect("/macromania/");
		
	}

}
