package org.naeemiqbal.login;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/loginServlet", method = { RequestMethod.GET, RequestMethod.PUT })
@WebServlet(urlPatterns = { "/loginServlet", "/login" }, name = "LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger LOG = Logger.getLogger(getClass().getName());
	public static final String USERID = "username";
	public static final String PASSWORD = "password";

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

		String usr = request.getParameter(USERID);
		String pswd = request.getParameter(PASSWORD);
		if (StringUtils.hasText(usr) && StringUtils.hasText(pswd)) {
			if (session == null)
				session = request.getSession(true);
			session.setAttribute(USERID, usr);
			session.setAttribute(PASSWORD, pswd);
			Object path = session.getAttribute("originalPath");
			if (path != null) {
				// request.getRequestDispatcher(path.toString())..forward(request, response);
				response.sendRedirect(path.toString());
				LOG.info("Log successful, redeirecting ");
			}
			else
				LOG.warning("No path found in session/request");
			
		} else {
			LOG.warning("NO login credentials");
		}
	}

}
