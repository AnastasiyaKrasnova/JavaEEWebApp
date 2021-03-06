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

import net.webfaculty.model.FacultyInfo;
import net.webfaculty.model.StudToFac;
import net.webfaculty.dao.FacultyDAO;
import net.webfaculty.dao.StudentToFacultyDAO;

@WebServlet("/student")
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FacultyDAO facultyDAO=new FacultyDAO();
	
	private StudentToFacultyDAO stfDAO=new StudentToFacultyDAO();
       
    public Student() {
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
			switch (action) {
			case "/list_student":
				try {
					listMyFacultys(request, response);
				}catch(SQLException e) {
					e.printStackTrace();
				}
			break;
			case "/leave_student":
				try{
					leaveCourse(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/join_student":
				try{
					joinCourse(request, response);
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
	
	
	private void leaveCourse(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		final HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		stfDAO.delete((int)session.getAttribute("id"), id, request);
		response.sendRedirect(request.getContextPath()+"/list_student");

	}
	
	private void joinCourse(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		final HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		StudToFac stf=new StudToFac((int)session.getAttribute("id"),id,"PROGRESS");
		stfDAO.insert(stf, request);
		response.sendRedirect(request.getContextPath()+"/list_student");

	}

}
