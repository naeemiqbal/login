package org.naeemiqbal;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.naeemiqbal.login.LoginServlet;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
/*
@Component
@Order(1)*/
public class LogTraffic implements Filter {

	final Logger LOG = Logger.getLogger(this.getClass().getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		// LOG.log(Level.INFO, "Request is {0}", req.getRequestURI());
		// LOG.log(Level.INFO, "Response is {0}", response.getContentType());
		String path = req.getRequestURI();
		LOG.info("Path: " + path);
		if (StringUtils.hasText(path) && path.startsWith("/filter")) {
			HttpSession sess = req.getSession();
			if (sess == null || !StringUtils.hasText((String) sess.getAttribute(LoginServlet.USERID))) {
				LOG.info("Go and login " + sess==null?"null session": "hasSession");
				req.setAttribute("originalPath", path);
				if (sess!=null)
					sess.setAttribute("originalPath", path);				
				request.getRequestDispatcher("/filter/login.html").forward(request, response);
			//	return;
			} else {
				LOG.info("Already logged IN "  + path.toString());				
			}
		}
		chain.doFilter(request, response);
	}
}
