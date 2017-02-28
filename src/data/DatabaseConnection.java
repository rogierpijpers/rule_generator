package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.utility.Logger;

public class DatabaseConnection implements AutoCloseable{
	public static final String JDBC_ORACLE_THIN_ONDORA02_HU_NL_8521_CURSUS02 = "jdbc:oracle:thin:@ondora02.hu.nl:8521:cursus02";
	public static final String ORACLE_JDBC_DRIVER_ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String TOSAD_2015_2C_TEAM1 = "tosad_2015_2c_team1";
	public static final String TOSAD_2015_2C_TEAM11 = "tosad_2015_2c_team1";
	private Connection connection;
	private Statement statement;
	
	private DatabaseConnection() {
		String URL = JDBC_ORACLE_THIN_ONDORA02_HU_NL_8521_CURSUS02;
		String JDBC_DRIVER = ORACLE_JDBC_DRIVER_ORACLE_DRIVER;
		String USERNAME = TOSAD_2015_2C_TEAM1;
		String PASSWORD = TOSAD_2015_2C_TEAM11;
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

	public static DatabaseConnection createDatabaseConnection() {
		return new DatabaseConnection();
	}

	// fetching data
	public ResultSet query(String sql){
		ResultSet result = null;
		try{
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
		}catch (SQLException e){
			Logger.log(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	//inserting data
	public void insertQuery(String sql){
		try{
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		}catch(SQLException e){
			Logger.log(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() throws Exception {
		try{
			statement.close();	
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}

}
