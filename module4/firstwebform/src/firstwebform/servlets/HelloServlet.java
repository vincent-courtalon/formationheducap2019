package firstwebform.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request,
						HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		pw.println("<html><head><title>bonjour!</title></head>");
		pw.println("<body><h2>bienvenue le " + LocalDateTime.now() + "</h2>");
		pw.println("<ul>");
		for (int i = 1; i <= 10; i++) {
			pw.println("<li>" + i + "</li>");
		}
		pw.println("</ul>");
		pw.println("</body></html>");
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
