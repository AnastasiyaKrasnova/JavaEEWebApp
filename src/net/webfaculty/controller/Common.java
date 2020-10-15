package net.webfaculty.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.webfaculty.dao.FacultyDAO;
import net.webfaculty.dao.UserDAO;
import net.webfaculty.model.Faculty;
import net.webfaculty.model.User;

@WebServlet("/common")
public class Common extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FacultyDAO facultyDAO=new FacultyDAO();
	private UserDAO userDAO=new UserDAO();
       
    public Common() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		switch (action) {
		case "/courses":
			try {
				listAllFacultys(request, response);
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/profile":
			try{
				enterProfile(request, response);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void listAllFacultys(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		final HttpSession session = request.getSession();
		request.setAttribute("role", session.getAttribute("role"));
		List<Faculty> listFac = facultyDAO.selectAll() ;
		request.setAttribute("listFac", listFac);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/facultys.jsp");
		dispatcher.forward(request, response);
	}
	
	private void enterProfile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		final HttpSession session = request.getSession();
		request.setAttribute("role", session.getAttribute("role"));
		User user=userDAO.getUserById((int)session.getAttribute("id"));
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/profile.jsp");
		dispatcher.forward(request, response);
	}

}
