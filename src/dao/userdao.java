package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
/**
 * 用户Dao类
 * @author liyunfei
 *
 */
public class userdao {
	
	/**
	 * 登录验证
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	
	public User login(Connection con,User user)throws Exception{
		User resulteUser=null;
		String sql="select * from user where user_Name=? and user_password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		
		pstmt.setString(1,user.getUserName());
		pstmt.setString(2,user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		
		if(rs.next()) {
			resulteUser=new User();
			resulteUser.setId(rs.getInt("user_id"));
			resulteUser.setUserName(rs.getString("user_name"));
			resulteUser.setPassword(rs.getString("user_password"));
		}
		return resulteUser;
	}
	
	/**
	 * 完善用户信息
	 * @param con
	 * @param user
	 * @throws Exception
	 */
	public int update(Connection con,User user)throws Exception
	{
		String sql="update user set user_age=?,user_sex=?,user_telephone=? where user_name=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		
		pstmt.setString(1,user.getUser_age());
		pstmt.setString(2,user.getUser_sex());
		pstmt.setString(3,user.getUser_phone());
		pstmt.setString(4,user.getUserName());
		
		return pstmt.executeUpdate();
		
	}
	
	public int getId(Connection con,User user)throws Exception{
		int id=0;
		ResultSet rs;
		String sql="select * from user where user_name=?";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, user.getUserName());
		rs = pre.executeQuery();
		while(rs.next()) {
			id=rs.getInt(1);
		}
		return id;
	}

}
