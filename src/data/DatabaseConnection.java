package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.utility.Logger;

public class DatabaseConnection implements AutoCloseable{
	private Connection connection;
	
	public DatabaseConnection() {
		String URL = "jdbc:oracle:thin:@ondora02.hu.nl:8521:cursus02";
		String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
		String USERNAME = "tosad_2015_2c_team1";
		String PASSWORD = "tosad_2015_2c_team1";
		try{
			Class.forName(JDBC_DRIVER);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		try{
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch(SQLException e){
			Logger.log(e.getMessage());
			e.printStackTrace();
		}
	}
	
	// fetching data
	public ResultSet query(String sql){
		ResultSet result = null;
		try{
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
			statement.close();
		}catch (SQLException e){
			Logger.log(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	//inserting data
	public void insertQuery(String sql){
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
		}catch(SQLException e){
			Logger.log(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() throws Exception {
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}
