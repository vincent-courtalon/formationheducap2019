package firstwebform.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CalculServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalculServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double operande1 = Double.parseDouble(request.getParameter("operande1"));
		double operande2 = Double.parseDouble(request.getParameter("operande2"));
		String operateur = request.getParameter("operateur");
		
		double resultat = 0;
		switch(operateur) {
			case "+":
				resultat = operande1 + operande2;
				break;
			case "-":
				resultat = operande1 - operande2;
				break;
			case "*":
				resultat = operande1 * operande2;
				break;
			case "/":
				resultat = operande1 / operande2;
				break;
		}
		request.setAttribute("resultat", resultat);
		
		
		getServletContext().getRequestDispatcher("/jsp/resultat.jsp")
							.forward(request, response);
		
	}

}
