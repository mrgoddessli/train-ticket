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

import dao.TrainDao;
import model.Train;
import util.DbUtil;
import util.StringUtil;
import java.awt.Color;

public class TrainManageInterFrm extends JInternalFrame {
	private JTable trainTable;

	private DbUtil dbUtil=new DbUtil();
	private TrainDao trainDao=new TrainDao();
	private JTextField searchTrainTxt;
	private JTextField id_txt;
	private JTextField start_txt;
	private JTextField final_txt;
	private JTextField searchfinaltxt;
	private JTextField start_time_txt;
	private JTextField final_time_txt;
	private JTextField price_txt;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainManageInterFrm frame = new TrainManageInterFrm();
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
	public TrainManageInterFrm() {
		getContentPane().setBackground(new Color(255, 218, 185));
		setIconifiable(true);
		setClosable(true);
		setTitle("车次信息管理");
		setBounds(100, 100, 964, 550);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("起点：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		searchTrainTxt = new JTextField();
		searchTrainTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("查  询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainSearchActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 18));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNewLabel_4 = new JLabel("终点：");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 18));
		
		searchfinaltxt = new JTextField();
		searchfinaltxt.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(79)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(searchTrainTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addGap(65)
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(searchfinaltxt, 159, 159, 159)
							.addGap(67)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(70)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 774, GroupLayout.PREFERRED_SIZE))))
					.addGap(108))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(searchTrainTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(searchfinaltxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(32)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
		);
		
		JLabel lblNewLabel_1 = new JLabel("车  次");
		
		id_txt = new JTextField();
		id_txt.setEditable(false);
		id_txt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("起  点");
		
		start_txt = new JTextField();
		start_txt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("终  点");
		
		final_txt = new JTextField();
		final_txt.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("修  改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trainUpdateActionEvent(e);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 16));
		
		JButton btnNewButton_2 = new JButton("删  除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trainDeleteActionEvent(e);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 16));
		
		JLabel lblNewLabel_5 = new JLabel("发车时间");
		
		start_time_txt = new JTextField();
		start_time_txt.setColumns(10);
		
		final_time_txt = new JTextField();
		final_time_txt.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("到站时间");
		
		JLabel lblNewLabel_7 = new JLabel("价格");
		
		price_txt = new JTextField();
		price_txt.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(55)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(start_time_txt, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addGap(28)
									.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(start_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(36)
									.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(final_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(53)
									.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(final_time_txt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
									.addGap(63)
									.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(price_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(124, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(192))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(start_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(final_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(start_time_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(final_time_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6)
						.addComponent(lblNewLabel_7)
						.addComponent(price_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		/**
		 * 点击表格
		 */
		trainTable = new JTable();
		trainTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TrainTableMousePressed(e);
			}
		});
		trainTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8F66\u6B21", "\u8D77\u70B9", "\u7EC8\u70B9", "\u53D1\u8F66\u65F6\u95F4", "\u5230\u7AD9\u65F6\u95F4", "\u4EF7\u683C"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		trainTable.getColumnModel().getColumn(3).setPreferredWidth(115);
		trainTable.getColumnModel().getColumn(4).setPreferredWidth(128);
		trainTable.getColumnModel().getColumn(5).setPreferredWidth(59);
		scrollPane.setViewportView(trainTable);
		getContentPane().setLayout(groupLayout);

		this.fillTable(new Train());
	}

	/**
	 * 
	 * 根据车次id删除车次信息事件处理
	 * @param e
	 */
	protected void trainDeleteActionEvent(ActionEvent evt) {
		// TODO Auto-generated method stub
		String id=id_txt.getText();
		if(StringUtil.isEmpty(id))
		{
			JOptionPane.showMessageDialog(null, "请选择删除记录");
			return;
		}
		int n=JOptionPane.showConfirmDialog(null, "确定删除该记录吗？");
		if(n==0)
		{
			Connection con=null;
			try {
				con=dbUtil.getCon();
				int deleteNum=trainDao.delete(con, id);
				if(deleteNum==1)
				{
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();  
					this.fillTable(new Train());
				}else
				{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败");
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

	/**
	 * 更新事件操作
	 * 
	 * @param evt
	 */
	private void trainUpdateActionEvent(ActionEvent evt) {
		// TODO Auto-generated method stub
		String id=id_txt.getText();
		String start_place=start_txt.getText();
		String final_place=final_txt.getText();
		String start_time=start_time_txt.getText();
		String final_time=final_time_txt.getText();
		String price=price_txt.getText();

		if(StringUtil.isEmpty(id))
		{
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
		if(StringUtil.isEmpty(start_place))
		{
			JOptionPane.showMessageDialog(null, "起点不能为空！");
			return;
		}
		Train train=new Train(Integer.parseInt(id),start_place,final_place,start_time,final_time,price);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int modifyNum=trainDao.update(con, train);
			if(modifyNum==1) {
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
				this.fillTable(new Train());
			}else {
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败");
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
	 * 表格点击
	 * @param e
	 */
	private void TrainTableMousePressed(MouseEvent evt) {
		// TODO Auto-generated method stub
		int row=trainTable.getSelectedRow();
		id_txt.setText((String)trainTable.getValueAt(row,0));
		start_txt.setText((String)trainTable.getValueAt(row, 1));
		final_txt.setText((String)trainTable.getValueAt(row, 2));
		start_time_txt.setText((String)trainTable.getValueAt(row, 3));
		final_time_txt.setText((String)trainTable.getValueAt(row, 4));
		price_txt.setText((String)trainTable.getValueAt(row, 5));
	}

	/**
	 * 车次搜索（查询）事件处理
	 * @param evt
	 */
    private void TrainSearchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String search_start_place=this.searchTrainTxt.getText();
		String search_final_place=this.searchfinaltxt.getText();
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
	 * 初始化表格
	 * @param train
	 */
	private void fillTable(Train train)   //显示信息的表格
	{
		DefaultTableModel dtm=(DefaultTableModel) trainTable.getModel();
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
	 * 重置表单
	 */
	private void resetValue()
	{
		this.id_txt.setText("");
		this.start_txt.setText("");
		this.final_txt.setText("");
		this.start_time_txt.setText("");
		this.final_time_txt.setText("");
		this.price_txt.setText("");
	}
}
