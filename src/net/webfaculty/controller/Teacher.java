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
import net.webfaculty.dao.StudentToFacultyDAO;
import net.webfaculty.model.Faculty;
import net.webfaculty.model.FacultyInfo;


@WebServlet("/teacher")
public class Teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FacultyDAO facultyDAO=new FacultyDAO();
	private StudentToFacultyDAO stfDAO=new StudentToFacultyDAO();
       
    public Teacher() {
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
			switch (action) {
			case "/list_teacher":
				try {
					listMyFacultys(request, response);
				}catch(SQLException e) {
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
	
	private void createTable() throws SQLException{
		facultyDAO.createTable();
	}
	
	private void listMyFacultys(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		final HttpSession session = request.getSession();
		List<Faculty> listFac = facultyDAO.selectAllForTeacher((int)session.getAttribute("id")) ;
		request.setAttribute("listFac", listFac);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/teacherMain.jsp");
		dispatcher.forward(request, response);
	}
	

}
