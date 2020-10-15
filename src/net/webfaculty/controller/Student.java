package net.webfaculty.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.webfaculty.model.Faculty;
import net.webfaculty.model.FacultyInfo;
import net.webfaculty.dao.FacultyDAO;

@WebServlet("/student")
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FacultyDAO facultyDAO=new FacultyDAO();
       
    public Student() {
    	this.facultyDAO=new FacultyDAO();
        try{
			createTable();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		try {
			switch (action) {
			case "/courses":
					listAllFacultys(request, response);
				break;
			default:
				listMyFacultys(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	private void createTable() throws SQLException{
		facultyDAO.createTable();
	}
	
	private void listMyFacultys(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		final HttpSession session = request.getSession();
		List<FacultyInfo> listFac = facultyDAO.selectAllForStudent((int)session.getAttribute("id")) ;
		request.setAttribute("listFac", listFac);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/studentMain.jsp");
		dispatcher.forward(request, response);
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
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException,SQLException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

}
