package kr.co.sadness;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/* 1. first login -> login first, save session
	 * 2. second login -> session check first
	 * if session is equal -> show "you are already login"
	 * else -> there are other user, so you can't login
	 * after logout -> session initiation
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		ServletContext sc = getServletContext();
		String username = (String) sc.getAttribute(id);
		PrintWriter out = response.getWriter();

		if (username == null) {
			// response.sendRedirect("/urlMove/a/e/test02.html");
			response.sendRedirect("/loginWithContext/register.jsp");
		} else {

			HttpSession session = request.getSession();
			if (session.isNew()) {
				System.out.println(id);
				System.out.println(session.getAttribute("id"));
				
				out.println("<html>\n" + "<head><title>" + "login"
						+ "</title></head><body>\n" + "<h1 align=\"center\">"
						+ "login confirm" + "</h1>\n" + "<p> welcome "
						+ username + "</p>\n</body></html>");

				// session setting
				session.setAttribute("id", id);
				session.setAttribute("name", username);
			}else if(session.getAttribute("id") != id){
				System.out.println(id);
				System.out.println(session.getAttribute("id"));
				out.println("<html>\n" + "<head><title>" + "login"
						+ "</title></head><body>\n" + "<h1 align=\"center\">"
						+ "login confirm" + "</h1>\n" + "<p>"
						+ "There is another user"
						+ "</p>\n</body></html>");
			}else{
				out.println("<html>\n" + "<head><title>" + "login"
						+ "</title></head><body>\n" + "<h1 align=\"center\">"
						+ "login confirm" + "</h1>\n" + "<p> welcome "
						+ username + "</p>\n" 
						+ "you are already login"
						+ "</body></html>");
			}

		}

	}
}
