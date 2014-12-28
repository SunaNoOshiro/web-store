package com.epam.ryndych.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WebStoreConnectionPool {
	private static WebStoreConnectionPool instance = null;
	//private BoneCP connectionPool = null;
	private static Connection  connection = null;
	private String user = "root";
	private String password = "goinon";
	private String jdbc = "jdbc:mysql://localhost:3306/web_store";

	private WebStoreConnectionPool() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbc, user, password);
			
//			// setup the connection pool
//			BoneCPConfig config = new BoneCPConfig();
//			config.setJdbcUrl(jdbc);
//			config.setUsername(user);
//			config.setPassword(password);
//			config.setMinConnectionsPerPartition(5);
//			config.setMaxConnectionsPerPartition(10);
//			config.setPartitionCount(1);
//			connectionPool = new BoneCP(config);			
//			
//			connection = connectionPool.getConnection();
			
			if (connection != null){
				System.out.println("Connection successful!");				
			}
		} catch (ClassNotFoundException e) {
			System.out.println("MySql Driver not found");
		} catch (SQLException e) {
			System.out.println("Can't connect to SQL DB");
		}
	}

	public static WebStoreConnectionPool getInstance() {
		if (instance == null) {
			instance = new WebStoreConnectionPool();
		}
		return instance;
	}

	public Statement createStatement() {
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			return null;
		}
	}

	public PreparedStatement prepareStatement(String sql) {
		try {
			return connection.prepareStatement(sql);
		} catch (SQLException e) {
			return null;
		}
	}

	public Connection getConnection() {
		return connection;
	}
}
