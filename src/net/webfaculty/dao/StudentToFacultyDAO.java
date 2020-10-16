package net.webfaculty.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.webfaculty.model.StudToFac;

public class StudentToFacultyDAO {
	private ConnectionPool pool=ConnectionPool.getInstance();
	private FacultyDAO facultyDAO=new FacultyDAO();

	private static final String CREATE_DATABASE_SQL="CREATE TABLE IF NOT EXISTS Stud_in_Faculty\r\n"
			+ "  (student_id INT NOT NULL,\r\n"
			+ "  faculty_id INT NOT NULL,\r\n"
			+ "  status varchar(255) NOT NULL,\r\n"
			+ "  mark INT,\r\n"
			+ "  PRIMARY KEY (student_id, faculty_id), \r\n"
			+ "  CONSTRAINT Constr_student_fk FOREIGN KEY student_fk (student_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
			+ "  CONSTRAINT Constr_faculty_fk FOREIGN KEY faculty_fk (faculty_id) REFERENCES Facultys (id) ON DELETE CASCADE ON UPDATE CASCADE\r\n"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
	
	private static final String INSERT_REALATION_SQL = "INSERT INTO Stud_in_Faculty" + " (student_id, faculty_id, satatus, mark) VALUES "
			+ " (?, ?, ?, ?);";

	
	private static final String DELETE_RELATION_SQL = "delete from Stud_in_Faculty where student_id = ? and faculty_id=?;";
	
	private static final String UPDATE_RELATION_SQL = "update Stud_in_Faculty set satatus = ?,mark= ? where student_id = ? and faculty_id=?;";
	
	public boolean createTable() {
		boolean tableCreated=false;
		try (Connection connection = pool.getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_DATABASE_SQL)) {
			tableCreated = statement.executeUpdate() > 0;
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return tableCreated;
	}
	
	public void insert(StudToFac stf, HttpServletRequest request) throws SQLException {
		System.out.println(INSERT_REALATION_SQL);
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REALATION_SQL)) {
			preparedStatement.setInt(1, stf.getStudent_id());
			preparedStatement.setInt(2, stf.getFaculty_id());
			preparedStatement.setString(3, stf.getStatus());
			preparedStatement.setInt(4, stf.getMark());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public boolean delete(int student_id,int faculty_id,HttpServletRequest request) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = pool.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_RELATION_SQL);) {
			statement.setInt(1, student_id);
			statement.setInt(2, faculty_id);
			System.out.println(statement);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean update(StudToFac stf, HttpServletRequest request) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = pool.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_RELATION_SQL);) {
			statement.setString(1, stf.getStatus());
			statement.setInt(2, stf.getMark());
			statement.setInt(3, stf.getStudent_id());
			statement.setInt(4, stf.getFaculty_id());
			final HttpSession session = request.getSession();
		
			if (facultyDAO.isAvailableForTeacher((int)session.getAttribute("id"),stf.getFaculty_id()))
				rowUpdated = statement.executeUpdate() > 0;
			else rowUpdated=false;
		}
		return rowUpdated;
	}
	
	private boolean isAvailableForStudent(int curr_student_id,int req_student_id ) {
		if (curr_student_id==req_student_id) return true;
		else return false;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
