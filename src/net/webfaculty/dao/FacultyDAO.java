package net.webfaculty.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import net.webfaculty.model.Faculty;
import net.webfaculty.model.FacultyInfo;
import net.webfaculty.model.StudentMark;

public class FacultyDAO {

	private ConnectionPool pool=ConnectionPool.getInstance();

	private static final String CREATE_FACULTY_DATABASE_SQL="CREATE TABLE IF NOT EXISTS facultys\r\n"
			+ "  (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\r\n"
			+ "  name varchar(255) NOT NULL,\r\n"
			+ "  hours int NOT NULL,\r\n"
			+ "  start date NOT NULL,\r\n"
			+ "  teacher_id int NOT NULL,\r\n"
			+ "  foreign key (teacher_id) references Users(id))\r\n"
			+ "  ENGINE=InnoDB DEFAULT CHARSET=utf8";
	
	private static final String INSERT_FACULTY_SQL = "INSERT INTO facultys" + "  (name, hours, start, teacher_id) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_FACULTY_BY_ID = "SELECT fac.id, fac.name, fac.hours, fac.start, teach.first_name, teach.last_name  \r\n"
			+ "        FROM facultys AS fac \r\n"
			+ "        LEFT JOIN users AS teach\r\n"
			+ "        ON fac.teacher_id = teach.id \r\n"
			+ "        WHERE fac.id=?\r\n";
	
	private static final String SELECT_FACULTY_BY_STUDENT_ID = "SELECT fac.name, fac.hours, fac.start, teach.first_name, teach.last_name, stf.satatus, stf.mark, stf.faculty_id, stf.student_id   \r\n"
			+ "        FROM facultys AS fac \r\n"
			+ "        LEFT JOIN users AS teach \r\n"
			+ "        ON teach.id = fac.teacher_id \r\n"
			+ "        LEFT JOIN stud_in_faculty AS stf\r\n"
			+ "        ON fac.id = stf.faculty_id \r\n"
			+ "        WHERE stf.student_id=?\r\n";
	
	private static final String SELECT_FACULTY_BY_TEACHER_ID = "SELECT fac.id,fac.name,fac.hours,fac.start, teach.first_name, teach.last_name \r\n"
			+ 			"FROM facultys AS fac \r\n"
			+ 			"LEFT JOIN users AS teach \r\n"
			+ "        ON teach.id = fac.teacher_id \r\n"
			+ "        WHERE teach.id=?\r\n";
	
	private static final String SELECT_FACULTY_TEACHER_ID_BY_ID = "SELECT teacher_id from facultys where id=?";
	
	private static final String SELECT_ALL_FACULTYS = "SELECT fac.id, fac.name, fac.hours, fac.start, teach.first_name, teach.last_name  \r\n"
			+ "        FROM facultys AS fac \r\n"
			+ "        LEFT JOIN users AS teach\r\n"
			+ "        ON fac.teacher_id = teach.id \r\n";
	
	private static final String SELECT_STUDENT_BY_FACULTY_ID="SELECT user.first_name, user.last_name, user.email, stf.satatus, stf.mark, stf.student_id   \r\n"
			+ "        FROM users AS user \r\n"
			+ "        LEFT JOIN  stud_in_faculty AS stf \r\n"
			+ "        ON user.id = stf.student_id \r\n"
			+ "        WHERE stf.faculty_id=?\r\n";
	private static final String SELECT_STUDENT_INFO_BY_STUD_FACULTY_ID="SELECT user.first_name, user.last_name, user.email, stf.satatus, stf.mark, stf.student_id   \r\n"
			+ "        FROM users AS user \r\n"
			+ "        LEFT JOIN  stud_in_faculty AS stf \r\n"
			+ "        ON user.id = stf.student_id \r\n"
			+ "        WHERE stf.faculty_id=? and stf.student_id=?\r\n";
	
	private static final String DELETE_FACULTY_SQL = "delete from facultys where id = ?;";
	
	private static final String UPDATE_FACULTY_SQL = "update facultys set name = ?,hours= ?, start =?  where id = ?;";
	
	public boolean createTable() {
		boolean tableCreated=false;
		try (Connection connection = pool.getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_FACULTY_DATABASE_SQL);) {
			tableCreated = statement.executeUpdate() > 0;
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return tableCreated;
	}
	
	public void insert(Faculty fac, HttpServletRequest request) throws SQLException {
		System.out.println(INSERT_FACULTY_SQL);
		final HttpSession session = request.getSession();
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FACULTY_SQL)) {
			preparedStatement.setString(1, fac.getName());
			preparedStatement.setInt(2, fac.getHours());
			preparedStatement.setDate(3, fac.getStart());
			preparedStatement.setInt(4, (int)session.getAttribute("id"));
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Faculty selectById(int id) {
		Faculty fac = null;
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FACULTY_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int hours = rs.getInt("hours");
				Date start = rs.getDate("start");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				fac= new Faculty(id, name, hours,start,first_name+" "+last_name);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return fac;
	}
	
	public List<FacultyInfo> selectAllForStudent(int student_id) {

		List<FacultyInfo> facultys = new ArrayList<>();
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FACULTY_BY_STUDENT_ID)) {
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, student_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int hours = rs.getInt("hours");
				Date start = rs.getDate("start");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String status=rs.getString("satatus");
				int mark = rs.getInt("mark");
				int std_id = rs.getInt("student_id");
				int fac_id = rs.getInt("faculty_id");
				facultys.add(new FacultyInfo(fac_id,std_id,name, hours,start,first_name+" "+last_name,status,mark));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return facultys;
	}
	
	public List<Faculty> selectAllForTeacher(int teacher_id) {

		List<Faculty> facultys = new ArrayList<>();
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FACULTY_BY_TEACHER_ID)) {
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, teacher_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id= rs.getInt("id");
				String name = rs.getString("name");
				int hours = rs.getInt("hours");
				Date start = rs.getDate("start");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				facultys.add(new Faculty(id,name, hours,start,first_name+" "+last_name));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return facultys;
	}
	
	public List<StudentMark> selectStudentsByFacultyId(int fac_id) {

		List<StudentMark> students = new ArrayList<>();
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_FACULTY_ID)) {
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, fac_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String status= rs.getString("satatus");
				int mark = rs.getInt("mark");
				int student_id= rs.getInt("student_id");
				students.add(new StudentMark(first_name,last_name,email,status,mark,student_id,fac_id));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return students;
	}
	
	public StudentMark selectStudentInfoByStudFacID(int stud_id,int fac_id) {

		StudentMark info = null;
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_INFO_BY_STUD_FACULTY_ID)) {
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, fac_id);
			preparedStatement.setInt(2, stud_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String status= rs.getString("satatus");
				int mark = rs.getInt("mark");
				info=new StudentMark(first_name,last_name,email,status,mark,stud_id,fac_id);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return info;
	}

	public List<Faculty> selectAll() {

		List<Faculty> facultys = new ArrayList<>();
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FACULTYS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int hours = rs.getInt("hours");
				Date start = rs.getDate("start");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				facultys.add(new Faculty(id,name, hours,start,first_name+" "+last_name));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return facultys;
	}

	public boolean delete(int id,HttpServletRequest request) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = pool.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_FACULTY_SQL);) {
			statement.setInt(1, id);
			
			final HttpSession session = request.getSession();
			if (isAvailableForTeacher((int)session.getAttribute("id"),id))
				rowDeleted = statement.executeUpdate() > 0;
			else rowDeleted=false;
		}
		return rowDeleted;
	}

	public boolean update(Faculty fac, HttpServletRequest request) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = pool.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_FACULTY_SQL);) {
			statement.setString(1, fac.getName());
			statement.setInt(2, fac.getHours());
			statement.setDate(3, fac.getStart());
			statement.setInt(4, fac.getId());
			final HttpSession session = request.getSession();
			
			if (isAvailableForTeacher((int)session.getAttribute("id"),fac.getId()))
				rowUpdated = statement.executeUpdate() > 0;
			else rowUpdated=false;
		}
		return rowUpdated;
	}
	
	private int selectTeacherIdfromID(int id) {
		int teacher_id=-1;
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FACULTY_TEACHER_ID_BY_ID );) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				teacher_id=rs.getInt("teacher_id");
			}
		} catch (SQLException e) {
		printSQLException(e);
		}
			return teacher_id;
		
		}

	public boolean isAvailableForTeacher(int curr_teacher_id,int fac_id ) {
		if (this.selectTeacherIdfromID(fac_id)==curr_teacher_id) return true;
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


