package net.webfaculty.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.webfaculty.model.User;

public class UserDAO {
	
	private ConnectionPool pool=ConnectionPool.getInstance();

	private static final String CREATE_USERS_DATABASE_SQL="create table users ( id int(4) NOT NULL AUTO_INCREMENT, first_name varchar(120) NOT NULL, last_name varchar(120) NOT NULL, email varchar(220) NOT NULL, password varchar(220) NOT NULL, role varchar(120) NOT NULL, PRIMARY KEY (id) ) default charset utf8 ";
	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (first_name, last_name, email,password, role) VALUES "
			+ " (?, ?, ?,?,?);";

	private static final String GET_USER_BY_ID = "select id,first_name, last_name, email,password, role from users where id =?";
	private static final String GET_USER_BY_EMAIL_PASSWORD = "select id,first_name, last_name, email,password, role from users where email =? and password=?";
	private static final String GET_ROLE_BY_EMAIL_PASSWORD = "select role from users where email =? and password=?";
	private static final String DELETE_USER_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USER_SQL = "update users set first_name=?, last_name=?, email=?, password=?, role=?  where id = ?;";
	
	public boolean createTable() {
		boolean tableCreated=false;
		try (Connection connection = pool.getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_USERS_DATABASE_SQL);) {
			tableCreated = statement.executeUpdate() > 0;
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return tableCreated;
	}
	
	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getFirst_name());
			preparedStatement.setString(2, user.getLast_name());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getRole());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public User getUserById(int id) {
		User user = null;
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String role = rs.getString("role");
				user = new User(id,first_name, last_name, email,password, role);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
	public User getUserByEmailPassword(String email,String password) {
		User user = null;
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_PASSWORD);) {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id=Integer.parseInt(rs.getString("id"));
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String role = rs.getString("role");
				user = new User(id,first_name, last_name, email,password, role);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
	public String getRoleByEmailPassword(String email,String password) {
		String role = null;
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ROLE_BY_EMAIL_PASSWORD);) {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				role = rs.getString("role");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return role;
	}
	
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = pool.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = pool.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);) {
			statement.setString(1, user.getFirst_name());
			statement.setString(2, user.getLast_name());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getRole());
			statement.setInt(4, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
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
