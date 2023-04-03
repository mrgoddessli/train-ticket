package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import dao.SeatDao;
import dao.TicketDao;
import model.Seat;
import model.Ticket;
import util.DbUtil;
import util.StringUtil;
import java.awt.Color;

public class TicketDefendInterFrm extends JInternalFrame {
	private JTextField username_txt;
	private JTable table;
	
	private DbUtil dbUtil=new DbUtil();
	private TicketDao ticketDao=new TicketDao();
	private SeatDao seatdao=new SeatDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketDefendInterFrm frame = new TicketDefendInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TicketDefendInterFrm() {
		getContentPane().setBackground(Color.PINK);
		setTitle("车票维护");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 866, 363);
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		username_txt = new JTextField();
		username_txt.setColumns(10);
		
		JButton btnNewButton = new JButton("查  询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketSearchActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 18));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("点击车票进行退票");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 745, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(56)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(username_txt, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(46)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(83)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(username_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(205, Short.MAX_VALUE))
		);
		
		/**
		 * 表格点击，进行退票
		 */
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=table.getSelectedRow();
				int train_id=Integer.parseInt((String)table.getValueAt(row, 1));
				int chexiang_id=Integer.parseInt((String)table.getValueAt(row, 6));
				int seat_NO=Integer.parseInt((String)table.getValueAt(row, 7));
				String username=(String)table.getValueAt(row, 0);
				
				Seat seat=new Seat(train_id,seat_NO,chexiang_id);
				Ticket ticket=new Ticket(username,train_id,chexiang_id,seat_NO);
				
				TicketTableMousePressed(e,seat,ticket);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237\u540D", "\u8F66\u6B21\u53F7", "\u8D77\u70B9", "\u7EC8\u70B9", "\u51FA\u53D1\u65F6\u95F4", "\u5230\u7AD9\u65F6\u95F4", "\u8F66\u53A2", "\u8F66\u5EA7\u53F7", "\u4EF7\u683C"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(4).setPreferredWidth(115);
		table.getColumnModel().getColumn(5).setPreferredWidth(111);
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		this.fillAlltable();
	}
protected void TicketTableMousePressed(MouseEvent evt,Seat seat,Ticket ticket) {
		// TODO Auto-generated method stub
	int result=JOptionPane.showConfirmDialog(null, "是否退票？");
	if(result==0) {     //点击确定，result为0
		//弹出界面输入个人信息
		Connection con=null;
		try {
			con=dbUtil.getCon();
			seatdao.Back2(con, seat);
			ticketDao.Back(con,ticket);
			
			JOptionPane.showMessageDialog(null, "退票成功！");
			
		}catch(Exception e) {
		    e.printStackTrace();	
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}else
	{
		return;
	}
	}

/**
 * 查询事件
 * @param e
 */
	protected void TicketSearchActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String username=username_txt.getText();
		
//		Ticket ticket=new Ticket();
		
		
		if(StringUtil.isNotEmpty(username)) {
		     this.fillTablename(username);
		}else {
			this.fillAlltable();
		}
		
		
		
	}
	
	private void fillTablename(String name) {
		// TODO Auto-generated method stub
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行，清空表格
		Connection con=null;
//		train.setTrain_start_place("");
		try {
			con=dbUtil.getCon();
			ResultSet rs=ticketDao.listUsername(con,name);
			while(rs.next())//遍历
			{
				Vector v=new Vector();
				v.add(rs.getString("user_name"));
				v.add(rs.getString("train_id"));
				v.add(rs.getString("train_start_place"));
				v.add(rs.getString("train_final_place"));
				v.add(rs.getString("train_start_time"));
				v.add(rs.getString("train_final_time"));
				v.add(rs.getString("chexiang_id"));
				v.add(rs.getString("seat_NO"));
				v.add(rs.getString("train_price"));
				dtm.addRow(v);       //添加输入进表格
			}
		}catch(Exception e) {
		    e.printStackTrace();	
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void fillAlltable()
	{
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行，清空表格
		Connection con=null;
//		train.setTrain_start_place("");
		try {
			con=dbUtil.getCon();
			ResultSet rs=ticketDao.listall(con);
			while(rs.next())//遍历
			{
				Vector v=new Vector();
				v.add(rs.getString("user_name"));
				v.add(rs.getString("train_id"));
				v.add(rs.getString("train_start_place"));
				v.add(rs.getString("train_final_place"));
				v.add(rs.getString("train_start_time"));
				v.add(rs.getString("train_final_time"));
				v.add(rs.getString("chexiang_id"));
				v.add(rs.getString("seat_NO"));
				v.add(rs.getString("train_price"));
				dtm.addRow(v);       //添加输入进表格
			}
		}catch(Exception e) {
		    e.printStackTrace();	
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void fillTable(Ticket ticket)   //显示信息的表格
	{
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行，清空表格
		Connection con=null;
//		train.setTrain_start_place("");
		try {
			con=dbUtil.getCon();
			ResultSet rs=ticketDao.list(con, ticket);
			while(rs.next())//遍历
			{
				Vector v=new Vector();
				v.add(rs.getString("user_name"));
				v.add(rs.getString("train_id"));
				v.add(rs.getString("train_start_place"));
				v.add(rs.getString("train_final_place"));
				v.add(rs.getString("train_start_time"));
				v.add(rs.getString("train_final_time"));
				v.add(rs.getString("chexiang_id"));
				v.add(rs.getString("seat_NO"));
				v.add(rs.getString("train_price"));
				dtm.addRow(v);       //添加输入进表格
			}
		}catch(Exception e) {
		    e.printStackTrace();	
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
