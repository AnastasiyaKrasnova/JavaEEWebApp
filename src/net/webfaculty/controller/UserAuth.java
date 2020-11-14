package net.webfaculty.controller;

import java.io.IOException;
import org.apache.log4j.Logger;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.webfaculty.dao.UserDAO;
import net.webfaculty.model.User;

@WebServlet("/")

public class UserAuth extends HttpServlet {
	//private static final Logger log = Logger.getLogger(UserAuth.class);
	private static final long serialVersionUID = 1L;
    private static UserDAO dao=new UserDAO();   
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		//log.info(action);
		try {
			switch (action) {
			case "/insert":
				try{
					insertUser(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/route":
					checkSession(request, response);
				break;
			case "/register":
				showRegisterForm(request, response);
				break;
			case "/logout":
				logout(request, response);
				break;
			case "/change_lang":
				changeLang(request, response);
				break;
			default:
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
		
	private void logout(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		final HttpSession session = request.getSession();
	     session.removeAttribute("role");
	     session.removeAttribute("id");
	     response.sendRedirect(super.getServletContext().getContextPath());
	}
	
	private void showRegisterForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/registrate.jsp");
		dispatcher.forward(request, response);
	}
	
	private void checkSession(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String email = request.getParameter("email");
	    final String password = request.getParameter("password");
	    /*if (email==null||password==null) {
	    	routeRole(request, response, "UNKNOWN");
	    	return;
	    }*/
		final HttpSession session = request.getSession();
		if (session.getAttribute("id")!=null &&session.getAttribute("role")!=null) {

            routeRole(request, response, (String)session.getAttribute("role"));


        } else if (dao.getUserByEmailPassword(email, password)!=null) {

            final User user = dao.getUserByEmailPassword(email, password);
            request.getSession().setAttribute("role", user.getRole());
            request.getSession().setAttribute("id", user.getId());

            routeRole(request, response, user.getRole());

        } else {
        	
        	 routeRole(request, response, "UNKNOWN");
        }
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, ServletException,IOException {
		
		response.setContentType("text/html; charset=utf8");
        request.setCharacterEncoding("Utf8"); 
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password =request.getParameter("password");
		String role =request.getParameter("role");
		User newUser = new User(first_name,last_name, email, password,role);
		if (dao.getUserByEmail(email)==-1) {
			dao.insertUser(newUser);
			response.sendRedirect(request.getContextPath());
		}
		else {
			request.setAttribute("mistake_num", 1);
			request.getRequestDispatcher("view/registrate.jsp").forward(request, response);
		}
	}
	
	private void routeRole(final HttpServletRequest req,final HttpServletResponse res,final String role)
			throws ServletException, IOException {

		if (role.equals("STUDENT")) {
			res.sendRedirect(req.getContextPath()+"/list_student");

	
		} else if (role.equals("TEACHER")) {
			res.sendRedirect(req.getContextPath()+"/list_teacher");

		} else {
			req.setAttribute("mistake_num", 1);
			//res.sendRedirect(req.getContextPath()+"/logout");
			req.getRequestDispatcher("view/login.jsp").forward(req, res);
		}
	}
	
	private void changeLang(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	final HttpSession session = request.getSession();
	
	System.out.println(request.getServletPath());
	String lang = request.getParameter("lang");
	session.setAttribute("lang",lang);
	if (request.getHeader("referer")==null) {
		response.sendRedirect(request.getContextPath()+"/logout");
	}
	else
		response.sendRedirect(request.getHeader("referer"));
}

}
