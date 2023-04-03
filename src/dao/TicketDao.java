package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Seat;
import model.Ticket;
import model.Train;
import util.StringUtil;

public class TicketDao {
	
	public int addseat(Connection con,Train train,Seat seat,String username)throws Exception
	{
		String sql="insert into ticket values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(2, train.getTrain_id());
		pstmt.setString(1, username);
		pstmt.setString(9, train.getTrain_price());
		pstmt.setString(3, train.getTrain_start_place());
		pstmt.setString(4, train.getTrain_final_place());
		pstmt.setString(5, train.getTrain_start_time());
		pstmt.setString(6, train.getTrain_final_time());
		pstmt.setInt(7, seat.getChexiang_id());
		pstmt.setInt(8, seat.getSeat_NO());
		
		return pstmt.executeUpdate();
	}
	
	public ResultSet list(Connection con,Ticket ticket)throws Exception
	{
		StringBuffer sb=new StringBuffer("select * from ticket");
		if(StringUtil.isNotEmpty(ticket.getTrain_start_place())) 
		{

			sb.append(" and train_start_place like '%"+ticket.getTrain_start_place()+"%'");//在sb的sql语句结尾添加

		}
		if(StringUtil.isNotEmpty(ticket.getTrain_final_place())) 
		{
			sb.append(" and train_final_place like '%"+ticket.getTrain_final_place()+"%'");//在sb的sql语句结尾添加
		}
		if(StringUtil.isNotEmpty(ticket.getTrain_start_time())) 
		{
			sb.append(" and train_start_time like '%"+ticket.getTrain_start_time()+"%'");//在sb的sql语句结尾添加
		}
		if(StringUtil.isNotEmpty(ticket.getTrain_final_time())) 
		{
			sb.append(" and train_final_time like '%"+ticket.getTrain_final_time()+"%'");//在sb的sql语句结尾添加
		}
//		if(StringUtil.isNotEmpty(train.getTrain_chexiang())) 
//		{
//			sb.append(" and train_chexiang like '%"+train.getTrain_chexiang()+"%'");//在sb的sql语句结尾添加
//		}
		if(StringUtil.isNotEmpty(ticket.getTrain_price())) 
		{
			sb.append(" and train_price like '%"+ticket.getTrain_price()+"%'");//在sb的sql语句结尾添加
		}
		if(StringUtil.isNotEmpty(ticket.getUser_name())) 
		{
			sb.append(" and user_name like '%"+ticket.getUser_name()+"%'");//在sb的sql语句结尾添加
		}
		if(StringUtil.isNotEmpty(Integer.toString(ticket.getTrain_id()))) 
		{
			sb.append(" and train_id like '%"+ticket.getTrain_id()+"%'");//在sb的sql语句结尾添加
		}
		if(StringUtil.isNotEmpty(Integer.toString(ticket.getChexiang_id()))) 
		{
			sb.append(" and chexiang_id like '%"+ticket.getChexiang_id()+"%'");//在sb的sql语句结尾添加
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
//		String temp=sb.toString();
//		PreparedStatement pstmt=con.prepareStatement(temp);
		return pstmt.executeQuery();//返回结果集
	}
	
	public ResultSet listall(Connection con)throws Exception
	{
		String sb=new String("select * from ticket");
		PreparedStatement pstmt=con.prepareStatement(sb);
		return pstmt.executeQuery();//返回结果集
	}
	
	public ResultSet listUsername(Connection con,String username)throws Exception
	{
		String sql="select * from ticket where user_name=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, username);
		
		return pstmt.executeQuery();//返回结果集
	}
	
	public int Back(Connection con,Ticket ticket)throws Exception
	{
		String sql="delete from ticket where user_name=? and train_id=? and chexiang_id=? and seat_NO=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, ticket.getUser_name());
		pstmt.setInt(2, ticket.getTrain_id());
		pstmt.setInt(3, ticket.getChexiang_id());
		pstmt.setInt(4, ticket.getSeat_NO());
		

		
		return pstmt.executeUpdate();
	}
}
