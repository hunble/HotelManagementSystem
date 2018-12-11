/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hunbl
 */
public class DatabaseConnection{
  
	private Connection conn = null;
 
	public DatabaseConnection(String url, String user_name, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
 
			this.conn = DriverManager.getConnection(url, user_name, password);
 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public Connection getConnection() {
		return this.conn;
	}
 
	public ResultSet SelectSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}
        public int UpdateSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeUpdate(sql);
	}
        public ResultSet MultiLineSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}
	public ResultSet preparedSelectSql(PreparedStatement sql) throws SQLException {
		return sql.executeQuery();
	}
        public int preparedUpdateSql(PreparedStatement sql) throws SQLException {
		return sql.executeUpdate();
	}
}   

