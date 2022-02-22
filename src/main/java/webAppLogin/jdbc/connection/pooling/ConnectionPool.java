package webAppLogin.jdbc.connection.pooling;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class ConnectionPool {

	private static ConnectionPool connectionPool = null;
	private static GenericObjectPool gPool = null;
	private static DataSource dataSource = null;

	private ConnectionPool() {
	}

	public static synchronized ConnectionPool getInstance() {
		if (connectionPool == null) {
			connectionPool = new ConnectionPool();
			try {
				dataSource = connectionPool.setUpPool();
				connectionPool.printConnectionPoolStatus();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connectionPool;
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private DataSource getDataSource() {
		return dataSource;
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unused")
	private DataSource setUpPool() throws Exception {

		/*
		 * Properties prop = new Properties();
		 * 
		 * System.out.println(java.lang.System.getProperty("user.dir")); try
		 * (InputStream input = ClassLoader.getSystemResourceAsStream("pom.properties"))
		 * { prop.load(input); } catch (IOException ex) { ex.printStackTrace(); }
		 * 
		 * String url = prop.getProperty("db.url"); String driver =
		 * prop.getProperty("db.driver"); String username =
		 * prop.getProperty("db.username"); String password =
		 * prop.getProperty("db.password"); String maxPoolSize =
		 * prop.getProperty("db.maxPoolSize");
		 */
		
		/*
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://mysqlserv11.mysql.database.azure.com:3306/javawebapp";
		String username = "user";
		String password= "Password@123";
		String maxPoolSize = "5";
		*/
		
		String driver=System.getenv("JDBC_DRIVER");
		String url = System.getenv("JDBC_URL");
		String username = System.getenv("JDBC_USER");
		String password = System.getenv("JDBC_PASSWORD");
		String maxPoolSize = System.getenv("JDBC_MAX_POOL_SIZE");
				
		
		Class.forName(driver);

		// Creates an Instance of GenericObjectPool That Holds Our Pool of Connections Object!
		gPool = new GenericObjectPool();
		gPool.setMaxActive(Integer.parseInt(maxPoolSize));

		// Creates a ConnectionFactory Object Which Will Be Use by the Pool to Create
		// the Connection Object!
		ConnectionFactory cf = new DriverManagerConnectionFactory(url, username, password);
		System.out.println("Connection established: " + cf);

		// Creates a PoolableConnectionFactory That Will Wraps the Connection Object
		// Created by the ConnectionFactory to Add Object Pooling Functionality!
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
		return new PoolingDataSource(gPool);
	}

	// This Method Is Used To Print The Connection Pool Status
	public void printConnectionPoolStatus() {
		System.out.println("Max.: " + gPool.getMaxActive() + "; Active: " + gPool.getNumActive() + "; Idle: "
				+ gPool.getNumIdle());
	}

	public static void main(String[] args) {
		ResultSet rsObj = null;
		Connection connObj = null;
		PreparedStatement pstmtObj = null;
		try {
			// Performing Database Operation!
			System.out.println("\n=====Making A New Connection Object For Db Transaction=====\n");
			connObj = ConnectionPool.getConnection();
			ConnectionPool.getInstance().printConnectionPoolStatus();

			pstmtObj = connObj.prepareStatement("SELECT * FROM user");
			rsObj = pstmtObj.executeQuery();
			while (rsObj.next()) {
				System.out.println("id: " + rsObj.getString("id"));
			}
			System.out.println("\n=====Releasing Connection Object To Pool=====\n");
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				// Closing ResultSet Object
				if (rsObj != null) {
					rsObj.close();
				}
				// Closing PreparedStatement Object
				if (pstmtObj != null) {
					pstmtObj.close();
				}
				// Closing Connection Object
				if (connObj != null) {
					connObj.close();
				}
			} catch (Exception sqlException) {
				sqlException.printStackTrace();
			}
		}
		ConnectionPool.getInstance().printConnectionPoolStatus();
	}
}