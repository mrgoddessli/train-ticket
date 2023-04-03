package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.TrainDao;
import model.Train;
import util.DbUtil;
import util.StringUtil;
import java.awt.SystemColor;

public class TrainAddInterFrm extends JInternalFrame {
	private JTextField strat_place_txt;
	private JTextField final_place_txt;

	private DbUtil dbUtil=new DbUtil();
	private TrainDao trainDao=new TrainDao();
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
					TrainAddInterFrm frame = new TrainAddInterFrm();
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
	public TrainAddInterFrm() {
		getContentPane().setBackground(new Color(152, 251, 152));
		setBackground(SystemColor.controlHighlight);
		setClosable(true);
		setIconifiable(true);
		setTitle("车次信息添加");
		setBounds(100, 100, 718, 449);
		
		JLabel lblNewLabel = new JLabel("起  点");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 24));
		
		JLabel lblNewLabel_1 = new JLabel("终  点");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 24));
		
		strat_place_txt = new JTextField();
		strat_place_txt.setColumns(10);
		
		final_place_txt = new JTextField();
		final_place_txt.setColumns(10);
		
		JButton btnNewButton = new JButton("添 加");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(221, 160, 221));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainAddActionPerformed(e); //“添加”按钮执行添加功能
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 20));
		
		JButton btnNewButton_1 = new JButton("重 置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 20));
		
		JLabel lblNewLabel_2 = new JLabel("发车时间");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 24));
		
		JSeparator separator = new JSeparator();
		
		start_time_txt = new JTextField();
		start_time_txt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("到站时间");
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 24));
		
		final_time_txt = new JTextField();
		final_time_txt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("价  格");
		lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 24));
		
		price_txt = new JTextField();
		price_txt.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(111)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(strat_place_txt, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(72)
									.addComponent(lblNewLabel_1))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblNewLabel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(27)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(final_time_txt)
												.addComponent(start_time_txt, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
											.addGap(18, 42, Short.MAX_VALUE)
											.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
										.addComponent(price_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(final_place_txt)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
					.addContainerGap(185, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(strat_place_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(final_place_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(start_time_txt)
								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(final_time_txt, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(price_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addGap(34))
		);
		getContentPane().setLayout(groupLayout);

	}
	/**
	 * 车次信息添加事件处理
	 * @param e
	 */
	
	private void TrainAddActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String strat_place=this.strat_place_txt.getText();
		String final_place=this.final_place_txt.getText();
		String start_time=this.start_time_txt.getText();
		String final_time=this.final_time_txt.getText();
		String price=this.price_txt.getText();

		if(StringUtil.isEmpty(strat_place)||StringUtil.isEmpty(final_place))
		{
			JOptionPane.showMessageDialog(null, "起点或终点不能为空！");
			return;
		}
		if(StringUtil.isEmpty(start_time)||StringUtil.isEmpty(final_time))
		{
			JOptionPane.showMessageDialog(null, "发车时间或到站时间不能为空！");
			return;
		}
		Train train=new Train(strat_place,final_place,start_time,final_time,price);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int n=trainDao.add(con, train);
			if(n==1) {
				JOptionPane.showMessageDialog(null, "车次信息添加成功！");
				resetValue();
			}else
			{
				JOptionPane.showMessageDialog(null, "车次信息添加失败！");

			}
		}catch(Exception e) {
			e.printStackTrace();//打印错误的栈
			JOptionPane.showMessageDialog(null, "车次信息添加失败！");
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
	 * 重置事件处理
	 * @param evt
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		this.resetValue();
	}
	
	/**
	 * 重置表单
	 */
	private void resetValue() {
		this.strat_place_txt.setText("");
		this.final_place_txt.setText("");
		this.start_time_txt.setText("");
		this.final_time_txt.setText("");
		this.price_txt.setText("");
	}
}
