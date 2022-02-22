package webAppLogin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import webAppLogin.beans.UserBeans;
import webAppLogin.jdbc.connection.pooling.ConnectionPool;

public class UserModel {

	public static long nextPk() {
		long pk = 0;
		Connection conn;
		try {
			conn = ConnectionPool.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select Max(id) from user");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public static boolean duplicateLogin(String login) {

		Connection conn = null;

		try {
			conn = ConnectionPool.getConnection();
			PreparedStatement stmt = conn.prepareStatement("Select * from user where login=?");
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConnection(conn);
		}

		return false;
	}

	public static long addUser(UserBeans user) {
		int i = 0;
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
			stmt.setLong(1, nextPk());
			stmt.setString(2, user.getFirstName());
			stmt.setString(3, user.getLastName());
			stmt.setString(4, user.getLogin());
			stmt.setString(5, user.getPassword());
			stmt.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			stmt.setString(7, user.getMobileNo());
			i = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConnection(conn);
		}

		return i;
	}

	public static UserBeans userLogin(String login, String password) {

		Connection conn = null;
		UserBeans user = null;
		try {
			conn = ConnectionPool.getConnection();
			PreparedStatement stmt = conn.prepareStatement("Select * from user where login=? and password = ?");
			stmt.setString(1, login);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new UserBeans();
				System.out.println("ID: " + rs.getLong("id"));
				user.setId(rs.getLong("id"));
				user.setFirstName(rs.getString("fname"));
				user.setLastName(rs.getString("lname"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setMobileNo(rs.getString("mobile"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConnection(conn);
		}

		return user;
	}

	public List<UserBeans> getUserlist() {
		ArrayList<UserBeans> list = new ArrayList<UserBeans>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("Select * from user");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserBeans user = new UserBeans();
				user.setId(rs.getLong("id"));
				user.setFirstName(rs.getString("fname"));
				user.setLastName(rs.getString("lname"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setMobileNo(rs.getString("mobile"));
				list.add(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConnection(conn);
		}
		return list;
	}

}
