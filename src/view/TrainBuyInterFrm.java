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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.SeatDao;
import dao.TicketDao;
import dao.TrainDao;
import model.Seat;
import model.Train;
import util.DbUtil;
import util.StringUtil;
import java.awt.Color;

public class TrainBuyInterFrm extends JInternalFrame {
	private JTextField Searchstart_txt;
	private JTextField Searchfinal_txt;
	private JTable Searchtable;

	private DbUtil dbUtil=new DbUtil();
	private TrainDao trainDao=new TrainDao();
	private SeatDao seatdao=new SeatDao();
	private TicketDao ticketDao=new TicketDao();
	
	private JTextField id_txt;
	private JTextField start_txt;
	private JTextField final_txt;
	private JTextField start_time_txt;
	private JTextField final_time_txt;
	private JTextField price_txt;
	private JTable seat_table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainBuyInterFrm frame = new TrainBuyInterFrm();
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
	public TrainBuyInterFrm() {
		getContentPane().setBackground(new Color(221, 160, 221));
		setTitle("车票购买");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 786, 546);
		
		JLabel lblNewLabel = new JLabel("起  点");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		Searchstart_txt = new JTextField();
		Searchstart_txt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("终  点");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 18));
		
		Searchfinal_txt = new JTextField();
		Searchfinal_txt.setColumns(10);
		
		JButton btnNewButton = new JButton("查  询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainSearchActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 18));
		
		/**(无效）
		 * 点击
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TrainTableMousePressed(e);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u4FE1\u606F\u5C55\u793A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(Searchstart_txt, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(62)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(Searchfinal_txt, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(60)
							.addComponent(btnNewButton))
						.addComponent(panel, 0, 0, Short.MAX_VALUE))
					.addGap(118))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(Searchstart_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(Searchfinal_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_2 = new JLabel("车次");
		lblNewLabel_2.setEnabled(false);
		
		id_txt = new JTextField();
		id_txt.setEditable(false);
		id_txt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("起点");
		lblNewLabel_3.setEnabled(false);
		
		start_txt = new JTextField();
		start_txt.setEditable(false);
		start_txt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("终点");
		lblNewLabel_4.setEnabled(false);
		
		final_txt = new JTextField();
		final_txt.setEditable(false);
		final_txt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("出发时间");
		lblNewLabel_5.setEnabled(false);
		
		start_time_txt = new JTextField();
		start_time_txt.setEditable(false);
		start_time_txt.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("到站时间");
		lblNewLabel_6.setEnabled(false);
		
		final_time_txt = new JTextField();
		final_time_txt.setEditable(false);
		final_time_txt.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("价格");
		lblNewLabel_7.setEnabled(false);
		
		price_txt = new JTextField();
		price_txt.setEditable(false);
		price_txt.setColumns(10);
		
		/**
		 * 鼠标点击（无效）
		 * 
		 * 
		 */
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
	//			SeatTableMousePressed(e);
			}
		});
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(final_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(start_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(price_txt)
						.addComponent(final_time_txt)
						.addComponent(start_time_txt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addGap(49))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(start_time_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(start_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_6))
									.addGap(22))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(28)
									.addComponent(final_time_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4)
								.addComponent(final_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_7)
								.addComponent(price_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		/**
		 * 点击车座（购票）
		 */
		seat_table = new JTable();
		seat_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				int row=seat_table.getSelectedRow();		
				
				int train=Integer.parseInt(id_txt.getText());
				int seat_no=Integer.parseInt((String)seat_table.getValueAt(row,0));
				int chexiang=Integer.parseInt((String)seat_table.getValueAt(row,1));
				Seat seat=new Seat(train,seat_no,chexiang);
				
				SeatTableMousePressed(e,seat);
			}
		});
		scrollPane_1.setViewportView(seat_table);
		seat_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8F66\u5EA7\u53F7", "\u8F66\u53A2\u53F7"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		seat_table.getColumnModel().getColumn(0).setResizable(false);
		panel.setLayout(gl_panel);
		
		/**
		 * 鼠标点击（有效）
		 */
		Searchtable = new JTable();
		Searchtable.setFont(new Font("宋体", Font.PLAIN, 12));
		Searchtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TrainTableMousePressed(e);
			}
		});
		scrollPane.setViewportView(Searchtable);
		Searchtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8F66\u6B21", "\u8D77\u70B9", "\u7EC8\u70B9", "\u51FA\u53D1\u65F6\u95F4", "\u5230\u7AD9\u65F6\u95F4", "\u8F66\u53A2\u6570", "\u4EF7\u683C"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		Searchtable.getColumnModel().getColumn(0).setPreferredWidth(42);
		Searchtable.getColumnModel().getColumn(3).setPreferredWidth(109);
		Searchtable.getColumnModel().getColumn(4).setPreferredWidth(108);
		getContentPane().setLayout(groupLayout);

	}
	
	/**
	 * 车次搜索
	 * @param evt
	 */
	private void TrainSearchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String search_start_place=this.Searchstart_txt.getText();
		String search_final_place=this.Searchfinal_txt.getText();
		Train train=new Train();
		if(StringUtil.isNotEmpty(search_start_place)) {
		     train.setTrain_start_place(search_start_place);
		}
		if(StringUtil.isNotEmpty(search_final_place))
		{
			train.setTrain_final_place(search_final_place);
		}
		
		this.fillTable(train);
	}
	
	/**
	 * 初始化车次表格
	 * @param train
	 */
	private void fillTable(Train train)   //显示信息的表格
	{
		DefaultTableModel dtm=(DefaultTableModel) Searchtable.getModel();
		dtm.setRowCount(0);//设置成0行，清空表格
		Connection con=null;
//		train.setTrain_start_place("");
		try {
			con=dbUtil.getCon();
			ResultSet rs=trainDao.list(con, train);
			while(rs.next())//遍历
			{
				Vector v=new Vector();
				v.add(rs.getString("train_id"));
				v.add(rs.getString("train_start_place"));
				v.add(rs.getString("train_final_place"));
				v.add(rs.getString("train_start_time"));
				v.add(rs.getString("train_final_time"));
				v.add(rs.getString("train_chexiang"));
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
	
	/**
	 * 初始化车座表格
	 * @param train
	 */
	private void SeatfillTable(Seat seat)   //显示信息的表格
	{
		DefaultTableModel dtm=(DefaultTableModel) seat_table.getModel();
		dtm.setRowCount(0);//设置成0行，清空表格
		Connection con=null;
//		train.setTrain_start_place("");
		try {
			con=dbUtil.getCon();
			ResultSet rs=seatdao.list(con, seat);
			while(rs.next())//遍历
			{
				Vector v=new Vector();
				v.add(rs.getString("seat_NO"));
				v.add(rs.getString("chexiang_id"));
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
	
	/**
	 * 表格点击车次
	 * @param e
	 */
	private void TrainTableMousePressed(MouseEvent evt) {
		// TODO Auto-generated method stub
		int row=Searchtable.getSelectedRow();
		
		id_txt.setText((String)Searchtable.getValueAt(row,0));
		start_txt.setText((String)Searchtable.getValueAt(row, 1));
		final_txt.setText((String)Searchtable.getValueAt(row, 2));
		start_time_txt.setText((String)Searchtable.getValueAt(row, 3));
		final_time_txt.setText((String)Searchtable.getValueAt(row, 4));
		price_txt.setText((String)Searchtable.getValueAt(row, 6));
		
		Seat seat=new Seat(Integer.parseInt(id_txt.getText()));
		SeatfillTable(seat);
		
	}
	
	/**
	 * 表格点击车座
	 * 进行购票
	 */
	private void SeatTableMousePressed(MouseEvent evt,Seat seat) {//seat传入车票数据
		// TODO Auto-generated method stub
		int result=JOptionPane.showConfirmDialog(null, "是否选择此票购买？");
		if(result==0) {     //点击确定，result为0
			//弹出界面输入个人信息
			Connection con=null;
			try {
				con=dbUtil.getCon();
				int r=seatdao.sell(con, seat);
				
				if(r==1)
				{
//					dispose();
					
					int op=JOptionPane.showConfirmDialog(null, "抢票成功！请尽快完善个人信息！");
					if(op==0)
					{
						createTicketBuyInterFrm();
					}
					else {
						seatdao.Back(con, seat);
						JOptionPane.showMessageDialog(null, "未及时完善信息，到手的车票没有了（摊手）");
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "抢票失败！");
					return;
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
		}else
		{
			return;
		}
		
	}

	private void createTicketBuyInterFrm() {
		// TODO Auto-generated method stub

//		TicketBuyFrm ticketbuyFrm=new TicketBuyFrm();  //内部窗口interframe
//		ticketbuyFrm.setVisible(true);
//		add(ticketbuyFrm);
		int train_id=Integer.parseInt(id_txt.getText());
		String start_place=start_txt.getText();
		String final_place=final_txt.getText();
		String start_time=start_time_txt.getText();
		String final_time=final_time_txt.getText();
		String price=price_txt.getText();
		
		int row=seat_table.getSelectedRow();		
		
		int train2=Integer.parseInt(id_txt.getText());
		int seat_no=Integer.parseInt((String)seat_table.getValueAt(row,0));
		int chexiang=Integer.parseInt((String)seat_table.getValueAt(row,1));
		Seat seat=new Seat(train2,seat_no,chexiang);
		
		Train train=new Train(train_id,start_place,final_place,start_time,final_time,price);
//		Seat seat=new Seat();
//		User user=new User();
		
		new TicketBuyFrm(train,seat).setVisible(true);

	}
	
}
