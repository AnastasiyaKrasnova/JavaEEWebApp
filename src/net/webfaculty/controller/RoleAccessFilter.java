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


public class RoleAccessFilter implements Filter {

	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
	    final HttpServletResponse res = (HttpServletResponse) response;
		final HttpSession session = req.getSession();
		String role=(String)session.getAttribute("role");
		if (!checkAccessRole(req, role)) {
			res.sendRedirect(req.getContextPath()+"/logout");
		}
		else {
			chain.doFilter(request, response);
		}	
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	private Boolean checkAccessRole(HttpServletRequest req,String actualRole) {
		if (req.getServletContext().getServletRegistrations().get("Teacher").getMappings().contains(req.getServletPath())) {
			if (actualRole.equals("TEACHER")) return true;
			else return false;
		}
		else if (req.getServletContext().getServletRegistrations().get("Student").getMappings().contains(req.getServletPath())) {
			if (actualRole.equals("STUDENT")) return true;
			else return false;
		}
		else return true;
	}

}
