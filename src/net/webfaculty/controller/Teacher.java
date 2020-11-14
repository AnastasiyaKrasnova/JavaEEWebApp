package net.webfaculty.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import net.webfaculty.dao.FacultyDAO;
import net.webfaculty.dao.StudentToFacultyDAO;
import net.webfaculty.model.Faculty;
import net.webfaculty.model.FacultyInfo;
import net.webfaculty.model.StudToFac;
import net.webfaculty.model.StudentMark;


@WebServlet("/teacher")
public class Teacher extends HttpServlet {
	private static final Logger log = Logger.getLogger(Teacher.class);
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
		log.info(action);
			switch (action) {
			case "/list_teacher":
				try {
					listMyFacultys(request, response);
				}catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert_faculty":
				try{
					insertFaculty(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			case "/delete_faculty":
				try{
					deleteFaculty(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/edit_faculty":
				try{
					showEditForm(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/update_faculty":
				try{
					updateFaculty(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/course_students":
				try{
					selectStudents(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/dismiss":
				try{
					dismissStudent(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/edit_mark":
				try{
					showEditFormMark(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/update_mark":
				try{
					updateMark(request, response);
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
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/newFaculty.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertFaculty(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ParseException, ServletException {
		
		response.setContentType("text/html; charset=utf8");
        request.setCharacterEncoding("Utf8"); 
        
		String name = request.getParameter("name");
		String start= request.getParameter("start");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date langDate = null;
	    int hours=0;
		try {
			hours= Integer.parseInt(request.getParameter("hours"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("mistake_num", 1);
			request.getRequestDispatcher("view/newFaculty.jsp").forward(request, response);
			return;
		}
		try {
			langDate = sdf.parse(start);
		} catch (ParseException e) {
			e.printStackTrace();
			request.setAttribute("mistake_num", 2);
			request.getRequestDispatcher("view/newFaculty.jsp").forward(request, response);
			return;
		}
	    java.sql.Date sqlDate = new java.sql.Date(langDate.getTime());
		Faculty fac = new Faculty(name,hours,sqlDate,"");
		facultyDAO.insert(fac, request);
		response.sendRedirect(request.getContextPath()+"/list_teacher");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Faculty fac=facultyDAO.selectById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/newFaculty.jsp");
		request.setAttribute("fac",fac);
		dispatcher.forward(request, response);

	}
	
	private void updateFaculty(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
		response.setContentType("text/html; charset=utf8");
        request.setCharacterEncoding("Utf8"); 
        int id=-1;
        try {
        	id = Integer.parseInt(request.getParameter("id"));
        	request.getSession().setAttribute("faculty_id", id);
        }
        catch(NumberFormatException e) {
			e.printStackTrace();
        	id=(int)request.getSession().getAttribute("faculty_id");
        }
		String name = request.getParameter("name");
		String teacher_name = request.getParameter("teacher_name");
		String start= request.getParameter("start");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date langDate = null;
		java.sql.Date sqlDate=null;
	    int hours=0;
		try {
			hours= Integer.parseInt(request.getParameter("hours"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			rerouteMistake(id,1,request, response);
			return;
		}
		try {
			if (start.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"))
				langDate = sdf.parse(start);
			else {
				rerouteMistake(id,2,request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			rerouteMistake(id,2,request, response);
			return;
		}
		System.out.println(langDate);
	    sqlDate = new java.sql.Date(langDate.getTime()+24*60*60*1000);
		Faculty fac = new Faculty(id,name,hours,sqlDate,teacher_name);
		facultyDAO.update(fac, request);
		response.sendRedirect(request.getContextPath()+"/list_teacher");
	}

	private void deleteFaculty(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		facultyDAO.delete(id, request);
		response.sendRedirect(request.getContextPath()+"/list_teacher");

	}
	
	private void selectStudents(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException,ServletException{
		final HttpSession session = request.getSession();
		int id=-1;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			id=(int)session.getAttribute("faculty_id");
		}
		List<StudentMark> listStud= facultyDAO.selectStudentsByFacultyId(id);
		request.setAttribute("listStud", listStud);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/studentList.jsp");
		dispatcher.forward(request, response);
	}
	
	private void dismissStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int stud_id = Integer.parseInt(request.getParameter("student_id"));
		int fac_id = Integer.parseInt(request.getParameter("faculty_id"));
		stfDAO.delete(stud_id, fac_id, request);
		final HttpSession session = request.getSession();
		session.setAttribute("faculty_id", fac_id);
		response.sendRedirect(request.getContextPath()+"/course_students");

	}
	
	private void showEditFormMark(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int stud_id = Integer.parseInt(request.getParameter("student_id"));
		int fac_id = Integer.parseInt(request.getParameter("faculty_id"));
		StudentMark info = facultyDAO.selectStudentInfoByStudFacID(stud_id, fac_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/markEdit.jsp");
		request.setAttribute("info", info);
		dispatcher.forward(request, response);

	}
	
	private void updateMark(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
		response.setContentType("text/html; charset=utf8");
        request.setCharacterEncoding("Utf8"); 
        int stud_id=-1;
        int fac_id=-1;
        try {
        	stud_id = Integer.parseInt(request.getParameter("student_id"));
    		fac_id = Integer.parseInt(request.getParameter("faculty_id"));
        	request.getSession().setAttribute("student_id", stud_id);
        	request.getSession().setAttribute("faculty_id", fac_id);
        }
        catch(NumberFormatException e) {
			e.printStackTrace();
        	fac_id=(int)request.getSession().getAttribute("faculty_id");
        	stud_id=(int)request.getSession().getAttribute("student_id");
        }
        int mark=0;
        try {
        	mark = Integer.parseInt(request.getParameter("mark"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("mistake_num", 1);
			StudentMark info = facultyDAO.selectStudentInfoByStudFacID(stud_id, fac_id);
			request.setAttribute("info", info);
			request.getRequestDispatcher("view/markEdit.jsp").forward(request, response);
			return;
		}
		
		String status= request.getParameter("status");
		StudToFac stf = new StudToFac(stud_id,fac_id,mark,status);
		stfDAO.update(stf, request);
		response.sendRedirect(request.getContextPath()+"/course_students");
	}
	
	private void rerouteMistake(int id, int num,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("mistake_num", num);
		Faculty fac=facultyDAO.selectById(id);
		request.setAttribute("fac",fac);
		request.getRequestDispatcher("view/newFaculty.jsp").forward(request, response);
	}

	

}
