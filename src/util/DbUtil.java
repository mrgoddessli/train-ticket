package util;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * 数据库工具类
 * 
 */
public class DbUtil {
	
	private String dbUrl="jdbc:mysql://localhost:3306/train_ticket";
	private String dbUserName="root";
	private String dbPassword="123456";
	private String jdbcName="com.mysql.cj.jdbc.Driver";
	
	/**
	 * 获取数据库链接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon()throws Exception
	{
		Class.forName(jdbcName);
		//Connection con =DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/train_ticket", "root", "123456");
		return con;
	}
	
	/**
	 * 关闭数据库链接
	 * @param con
	 * @throws Exception
	 */
	
	public void closeCon(Connection con)throws Exception
	{
		if(con!=null) 
		{
			con.close();
		}
	}
	
	public static void main(String[] args) 
	{
		DbUtil dbUtil=new DbUtil();
		try 
		{
			dbUtil.getCon();
			System.out.println("数据库链接成功！");
		}catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("数据库连接失败！");
		}
	}
	
	
}
