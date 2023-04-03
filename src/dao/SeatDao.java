package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Seat;
import util.StringUtil;

public class SeatDao {

	/**
	 * 车座信息添加
	 * @param con
	 * @param train
	 * @return
	 */
	public int add(Connection con,Seat seat)throws Exception
	{
		String sql="insert into seat values(null,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, seat.getTrain_id());
		pstmt.setInt(2, seat.getChexiang_id());
		pstmt.setInt(3, seat.getYi_shouchu());
		pstmt.setInt(4, seat.getSeat_NO());
		
		return pstmt.executeUpdate();
	}
	
	/**
	 * 根据车次查询车座信息
	 */
	public ResultSet list(Connection con,Seat seat)throws Exception
	{
		String sql="select * from seat where train_id=? and yi_shouchu=0";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, seat.getTrain_id());
		
//		String temp=sb.toString();
//		PreparedStatement pstmt=con.prepareStatement(temp);
		return pstmt.executeQuery();//返回结果集
	}
	
	/**
	 * 售出车票，yi_shouchu设置为1
	 */
	public int sell(Connection con,Seat seat)throws Exception
	{
		String sql="update seat set yi_shouchu=1 where train_id=? and chexiang_id=? and seat_NO=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, seat.getTrain_id());
		pstmt.setInt(2, seat.getChexiang_id());
		pstmt.setInt(3, seat.getSeat_NO());
		
//		String temp=sb.toString();
//		PreparedStatement pstmt=con.prepareStatement(temp);
//		return pstmt.executeQuery();//返回结果集
		return pstmt.executeUpdate();
	}
	/**
	 * 删除车座
	 * @param con
	 * @param id
	 * @return
	 */
	public int delete(Connection con,String id)throws Exception
	{
		String sql="delete from seat where seat_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 更新车座信息
	 * @param con
	 * @param train
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con,Seat seat)throws Exception
	{
		String sql="update train set train_id=?,chexiang_id=?,yi_shouchu=?,seat_NO=? where seat_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, seat.getTrain_id());
		pstmt.setInt(2, seat.getChexiang_id());
		pstmt.setInt(3, seat.getYi_shouchu());
		pstmt.setInt(4, seat.getSeat_NO());
		pstmt.setInt(5, seat.getSeat_id());
//		pstmt.setInt(6, train.getTrain_id());
		
		return pstmt.executeUpdate();
	}
	
	/**
	 * 购票功能
	 */
	public void Buy(Connection con,Seat seat)throws Exception
	{
		String sql="update train set yi_shouchu=1  where seat_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, seat.getSeat_id());
	}
	/**
	 * 退票功能
	 */
	public void Back(Connection con,Seat seat)throws Exception
	{
		String sql="update train set yi_shouchu=0  where seat_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, seat.getSeat_id());
	}
	public void Back2(Connection con,Seat seat)throws Exception
	{
		String sql="update train set yi_shouchu=0  where train_id=? and chexiang_id=? and seat_NO=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, seat.getTrain_id());
		pstmt.setInt(2, seat.getChexiang_id());
		pstmt.setInt(3, seat.getSeat_NO());
		
	}
}
