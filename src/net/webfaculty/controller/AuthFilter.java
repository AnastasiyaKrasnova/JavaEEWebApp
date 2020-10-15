package net.webfaculty.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.webfaculty.dao.UserDAO;


public class AuthFilter implements Filter {

	private static UserDAO dao=new UserDAO();
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
	    final HttpServletResponse res = (HttpServletResponse) response;
		final HttpSession session = req.getSession();
		if (session.getAttribute("email")==null &&session.getAttribute("password")==null) {
			req.getRequestDispatcher("view/login.jsp").forward(req, res);
		}
		else {
			chain.doFilter(request, response);
		}
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		dao.createTable();
	}

}
