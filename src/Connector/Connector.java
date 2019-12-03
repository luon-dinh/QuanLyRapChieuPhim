package Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import Model.Account;

public class Connector<T> {

	public static Connection connection=null;
	public Connection connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection=DriverManager.getConnection("jdbc:sqlite:Account.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> select(Class<T> type,String query){
		Statement statement;
		ResultSet result=null;
		 List<T> list = new ArrayList<T>();
		try {
			connect();
			statement = connection.createStatement();
			result=statement.executeQuery(query);
			while(result.next()) {
				list.add((T)result);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Account> selectAccount(String query) {
		Statement statement;
		ResultSet result=null;
		List<Account> list = new ArrayList<Account>();
		try {
			connect();
			statement = connection.createStatement();
			result=statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(result.next()) {
				String ID=result.getString("ID");
				String Username=result.getString("Username");
				String Password=result.getString("Password");
				String Type=result.getString("Type");
				String Date=result.getString("CreatedDate");
				String Status=result.getString("Status");
				java.util.Date date=null;
				try {
					date = new SimpleDateFormat("dd/MM/yyyy").parse(Date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Account account=new Account(ID,Username,Password,Type,date,Status);
				list.add(account);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public int update(String query) {
		Statement statement;
		int result=0;
		try {
			connect();
			statement = connection.createStatement();
			result=statement.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int insert(String query) {
		Statement statement;
		int result=0;
		try {
			connect();
			statement = connection.createStatement();
			result=statement.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public int insert(String ID, String username, String password, String type, String date, String status) {
		PreparedStatement statement;
		int result=0;
		try {
			connect();
			statement = connection.prepareStatement("insert into account values(?,?,?,?,?,?)");
			statement.setString(1, ID);
			statement.setString(2, username);
			statement.setString(3, password);
			statement.setString(4, type);
			statement.setString(5, date);
			statement.setString(6, status);
			result=statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int delete(String query) {
		Statement statement;
		int result=0;
		try {
			connect();
			statement = connection.createStatement();
			result=statement.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
