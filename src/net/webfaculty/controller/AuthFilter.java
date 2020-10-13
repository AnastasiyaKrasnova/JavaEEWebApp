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

import net.webfaculty.dao.LoginDAO;


public class AuthFilter implements Filter {

	private static LoginDAO dao=new LoginDAO();
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 final HttpServletRequest req = (HttpServletRequest) request;
	     final HttpServletResponse res = (HttpServletResponse) response;
	     final String email = req.getParameter("email");
	     final String password = req.getParameter("password");
	     
	     final HttpSession session = req.getSession();
	     if (session.getAttribute("email")!=null &&session.getAttribute("password")!=null) {

	            final String role = (String)session.getAttribute("role");
	            routeRole(req, res, role);


	        } else if (dao.getUserByEmailPassword(email, password)!=null) {

	            final String role = dao.getRoleByEmailPassword(email, password);
	            req.getSession().setAttribute("password", password);
	            req.getSession().setAttribute("email", email);
	            req.getSession().setAttribute("role", role);

	            routeRole(req, res, role);

	        } else {

	        	 routeRole(req, res, "UNKNOWN");
	        }
	}
	
	private void routeRole(final HttpServletRequest req,final HttpServletResponse res,final String role)
						throws ServletException, IOException {

			if (role.equals("STUDENT")) {

				req.getRequestDispatcher("studentMain.jsp").forward(req, res);

			} else if (role.equals("TEACHER")) {

				req.getRequestDispatcher("teacherMain.jsp").forward(req, res);

			} else {
				req.getRequestDispatcher("login.jsp").forward(req, res);
			}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		dao.createTable();
	}

}
