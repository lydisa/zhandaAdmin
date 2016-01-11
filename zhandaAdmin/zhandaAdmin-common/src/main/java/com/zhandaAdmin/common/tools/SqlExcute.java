package com.zhandaAdmin.common.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;



public class SqlExcute {
	
	private Connection connection;

	/*mysql url的连接字符串*/
	private static String url = "jdbc:mysql://127.0.0.1/udal_dev1?useUnicode=true&characterEncoding=utf-8&useOldAliasMetadataBehavior=true";
	//账号
	private static String user = "root";
	//密码
	private static String password = "root";
	//表名
	private static String table = "travelrecord";
	
	private Vector<String> vector = new Vector<String>();
	//mysql jdbc的java包驱动字符串
	private String driverClassName = "com.mysql.jdbc.Driver";
	
	
	public SqlExcute(){
		initConnection();
	}
	
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	
	//获得链接
	public void initConnection(){
		try {
			//驱动注册
			Class.forName(driverClassName);
			if (connection == null || connection.isClosed())
				connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
				System.out.println("Oh,not");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Oh,not");
			}
	}
	
	public static void main(String[] args)
	{
		try
		{
			SqlExcute sqlExcute = new SqlExcute(); 
			Connection con = sqlExcute.getConnection();
			Statement st = con.createStatement();
			//执行SQL语句
			for(int i = 1;i<=100;i++)
			{
				//批量插入
				String sql = "insert "+table+" values("+i+","+i %10+",'1000000','1','1')";
				//System.out.println(sql);
				st.execute(sql);
				
			}
		}
		catch(SQLException ex)
		{
			System.err.println("SQLException:" + ex.getMessage());
		}
	}
	
}
