package org.naeemiqbal.login;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class Logout2
 */

@WebServlet(urlPatterns = { "/logout", "/Auth1/Logout" }, name = "Logout")
public class Logout extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger LOGGER = Logger.getLogger(getClass().getName());

	/**
	 * Default constructor.
	 */
	public Logout() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param request
	 * @param response
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPerform(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPerform(request, response);
	}

	private void doPerform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String user = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "NONE";
		String user2 = request.getRemoteUser();
		String role = request.isUserInRole("ADMIN")?"Admin": request.isUserInRole("USER")?"User": "--None--";
		

		boolean found = false;
		if (session != null) {
			session.invalidate();
			found = true;
		}
		LOGGER.log(Level.FINE, "Logout, Users is {0}\tUser2={1}\tSession={2}",
				new Object[] { user, user2, session });
		if (found) {
			ServletOutputStream out = response.getOutputStream();
			response.setContentType("text/html");
			out.println("<HTML><BODY><P>LOGOUT SERVLET  <br/>User:" + user +  "<br/>Roll:" + role + "<br/>Date:"+ new Date() + user2 + " <br/>found & logout: " + found
					+ "  </P></BODY></HTML>");
		} else {
			response.sendRedirect("index.html");
		}
	}
}
