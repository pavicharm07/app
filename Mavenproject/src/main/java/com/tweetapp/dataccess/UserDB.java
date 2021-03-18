package com.tweetapp.dataccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tweetapp.business.User;

public class UserDB {
	public static String getemail;

	public static int insert(User user) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		String query = "INSERT INTO users (first_name, last_name, gender, dob, email, password,numTwits,lastLogin,salt) "
				+ "VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getFirst_name());
			ps.setString(2, user.getLast_name());
			ps.setString(3, user.getGender());
			ps.setString(4, user.getDob());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPassword());
			ps.setString(7, user.getNumTwits());
			ps.setString(7, user.getLastLogin());
			ps.setString(7, user.getSalt());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static User search(String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM users " + "WHERE Email = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			User user = null;
			if (rs.next()) {
				user = new User();
				user.setFirst_name(rs.getString("first_name"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setGender(rs.getString("gender"));
				user.setDob(rs.getString("dob"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setNumTwits(rs.getString("numTwits"));
				user.setLastLogin(rs.getString("lastLogin"));
				user.setSalt(rs.getString("salt"));
			}
			return user;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static int update(User user) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		String query = "UPDATE users SET " + "first_name = ?," + "last_name = ?," + "gender = ?," + "dob = ?,"
				+ "email = ?," + "password = ?" + "numTwits=?" + "lastLogin = ?," + "salt = ?,";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getFirst_name());
			ps.setString(2, user.getLast_name());
			ps.setString(3, user.getGender());
			ps.setString(4, user.getDob());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPassword());
			ps.setString(6, user.getNumTwits());
			ps.setString(6, user.getLastLogin());
			ps.setString(6, user.getSalt());

			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static ArrayList<User> selectAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM users ";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			ArrayList<User> userList = new ArrayList<User>();

			User user = null;

			while (rs.next()) {
				user = new User();
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setGender(rs.getString("gender"));
				user.setDob(rs.getString("dob"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setNumTwits(rs.getString("numTwits"));
				user.setLastLogin(rs.getString("lastLogin"));
				user.setSalt(rs.getString("salt"));

				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static int delete(User user) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		String query = "DELETE FROM User " + "WHERE Email = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getEmail());

			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static boolean emailExists(String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT Email FROM users " + "WHERE Email = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static String getFirstName(String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT first_name FROM users " + "WHERE email = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			String fullname = "";
			if (rs.next()) {
				fullname = rs.getString("first_name");
			}
			return fullname;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
}
