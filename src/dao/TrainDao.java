package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Train;
import util.StringUtil;

/**
 * 车次信息Dao类
 * （数据返回对象）
 * 
 * @author liyunfei
 *
 */
public class TrainDao {

	/**
	 * 车次信息添加
	 * @param con
	 * @param train
	 * @return
	 */
	public int add(Connection con,Train train)throws Exception
	{
		String sql="insert into train values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, train.getTrain_start_place());
		pstmt.setString(2, train.getTrain_final_place());
		pstmt.setString(3, train.getTrain_start_time());
		pstmt.setString(4, train.getTrain_final_time());
		pstmt.setInt(5, train.getTrain_chexiang());
		pstmt.setString(6, train.getTrain_price());
		
		return pstmt.executeUpdate();
	}
	
	/**
	 * 根据起点查询车次信息
	 */
	public ResultSet list(Connection con,Train train)throws Exception
	{
		StringBuffer sb=new StringBuffer("select * from train");
		if(StringUtil.isNotEmpty(train.getTrain_start_place())) 
		{
//			String sb="select * from train where train_start_place like '%"+train.getTrain_start_place()+"%'";
			sb.append(" and train_start_place like '%"+train.getTrain_start_place()+"%'");//在sb的sql语句结尾添加
//			PreparedStatement pstmt=con.prepareStatement(sb);
//			return pstmt.executeQuery();//返回结果集
		}
		if(StringUtil.isNotEmpty(train.getTrain_final_place())) 
		{
			sb.append(" and train_final_place like '%"+train.getTrain_final_place()+"%'");//在sb的sql语句结尾添加
		}
		if(StringUtil.isNotEmpty(train.getTrain_start_time())) 
		{
			sb.append(" and train_start_time like '%"+train.getTrain_start_time()+"%'");//在sb的sql语句结尾添加
		}
		if(StringUtil.isNotEmpty(train.getTrain_final_time())) 
		{
			sb.append(" and train_final_time like '%"+train.getTrain_final_time()+"%'");//在sb的sql语句结尾添加
		}
//		if(StringUtil.isNotEmpty(train.getTrain_chexiang())) 
//		{
//			sb.append(" and train_chexiang like '%"+train.getTrain_chexiang()+"%'");//在sb的sql语句结尾添加
//		}
		if(StringUtil.isNotEmpty(train.getTrain_price())) 
		{
			sb.append(" and train_price like '%"+train.getTrain_price()+"%'");//在sb的sql语句结尾添加
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
//		String temp=sb.toString();
//		PreparedStatement pstmt=con.prepareStatement(temp);
		return pstmt.executeQuery();//返回结果集
	}
	
	/**
	 * 删除车次
	 * @param con
	 * @param id
	 * @return
	 */
	public int delete(Connection con,String id)throws Exception
	{
		String sql="delete from train where train_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 更新车次信息
	 * @param con
	 * @param train
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con,Train train)throws Exception
	{
		String sql="update train set train_start_place=?,train_final_place=?,train_start_time=?,train_final_time=?,train_price=? where train_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, train.getTrain_start_place());
		pstmt.setString(2, train.getTrain_final_place());
		pstmt.setString(3, train.getTrain_start_time());
		pstmt.setString(4, train.getTrain_final_time());
		pstmt.setString(5, train.getTrain_price());
		pstmt.setInt(6, train.getTrain_id());
		
		return pstmt.executeUpdate();
	}
}
