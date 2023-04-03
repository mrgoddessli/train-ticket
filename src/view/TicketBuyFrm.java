package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.TicketDao;
import dao.userdao;
import model.Seat;
import model.Train;
import model.User;
import util.DbUtil;
import util.StringUtil;
import java.awt.SystemColor;

public class TicketBuyFrm extends JFrame {
	
	private DbUtil dbUtil=new DbUtil();
	private userdao userDao=new userdao();
	private TicketDao ticketDao=new TicketDao();
	
	private JTextField user_txt;
	private JTextField phone_txt;
	private JTextField age_txt;
	private JTextField sex_txt;
	
	private Train train;
	private Seat seat;
	public TicketBuyFrm(Train train,Seat seat) {
		getContentPane().setBackground(SystemColor.activeCaption);
		setTitle("完善个人信息");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		
		this.train=train;
		this.seat=seat;
		
		JLabel lblNewLabel_4 = new JLabel("用户名");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 18));
		
		user_txt = new JTextField();
		user_txt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("年龄");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_6 = new JLabel("联系方式");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 18));
		
		phone_txt = new JTextField();
		phone_txt.setColumns(10);
		
		age_txt = new JTextField();
		age_txt.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("性别");
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 18));
		
		sex_txt = new JTextField();
		sex_txt.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("确  定");
		btnNewButton_2.setBackground(SystemColor.textHighlight);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketBuyActionPerformed(e);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 18));
		
		JButton btnNewButton_3 = new JButton("重  置");
		btnNewButton_3.setBackground(SystemColor.textHighlight);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		btnNewButton_3.setFont(new Font("宋体", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(61)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(user_txt, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_6)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(phone_txt, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(sex_txt))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(age_txt, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(243, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(user_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(phone_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(age_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7)
						.addComponent(sex_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public void TicketBuyActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String username=user_txt.getText();
		String age=age_txt.getText();
		String sex=sex_txt.getText();
		String phone=phone_txt.getText();

		if(StringUtil.isEmpty(username) || StringUtil.isEmpty(sex) || StringUtil.isEmpty(phone) || StringUtil.isEmpty(age))
		{
			JOptionPane.showMessageDialog(null, "请输入完整的信息");
			return;
		}
		User user=new User(username,age,sex,phone);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int modifyNum=userDao.update(con, user);
			if(modifyNum==1) {
				JOptionPane.showMessageDialog(null, "输入信息成功");
			    ticketDao.addseat(con, train, seat, username);
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "输入信息失败");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "输入信息失败");
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

	private void resetValue()
	{
		this.user_txt.setText("");
		this.sex_txt.setText("");
		this.age_txt.setText("");
		this.phone_txt.setText("");
	}
}
