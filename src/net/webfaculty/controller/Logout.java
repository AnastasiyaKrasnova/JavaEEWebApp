package net.webfaculty.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.webfaculty.dao.LoginDAO;
import net.webfaculty.model.User;

@WebServlet("/")

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static LoginDAO dao=new LoginDAO();   
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
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
			case "/register":
				showRegisterForm(request, response);
				break;
			case "/logout":
				logout(request, response);
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
	     session.removeAttribute("password");
	     session.removeAttribute("email");
	     session.removeAttribute("role");
	     response.sendRedirect(super.getServletContext().getContextPath());
	}
	
	private void showRegisterForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("registrate.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		response.setContentType("text/html; charset=utf8");
        request.setCharacterEncoding("Utf8"); 
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password =request.getParameter("password");
		String role =request.getParameter("role");
		User newUser = new User(first_name,last_name, email, password,role);
		dao.insertUser(newUser);
		response.sendRedirect(request.getContextPath());
	}

}
