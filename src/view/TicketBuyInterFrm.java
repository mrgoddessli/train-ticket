package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import dao.SeatDao;
import dao.userdao;
import model.Train;
import model.User;
import util.DbUtil;
import util.StringUtil;

public class TicketBuyInterFrm extends JInternalFrame {
	private JTextField user_txt;
	private JTextField phone_txt;
	private JTextField age_txt;
	private JTextField sex_txt;
	
	private DbUtil dbUtil;
	private StringUtil stringUtil;
	private SeatDao seatDao;
	private userdao userDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketBuyInterFrm frame = new TicketBuyInterFrm();
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
	public TicketBuyInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("购票");
		setBounds(100, 100, 491, 456);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8BF7\u8F93\u5165\u4E2A\u4EBA\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(157, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(134, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		
		user_txt = new JTextField();
		user_txt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("联系方式");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 22));
		
		phone_txt = new JTextField();
		phone_txt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("年  龄");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 22));
		
		age_txt = new JTextField();
		age_txt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("性  别");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 22));
		
		sex_txt = new JTextField();
		sex_txt.setColumns(10);
		
		JButton btnNewButton = new JButton("确  定");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TicketBuyActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 22));
		
		JButton btnNewButton_1 = new JButton("重  置");
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 22));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(67)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(sex_txt)
								.addComponent(age_txt)
								.addComponent(user_txt, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
								.addComponent(phone_txt, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
					.addContainerGap(164, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(user_txt)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(phone_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(age_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addComponent(sex_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(13))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	protected void TicketBuyActionPerformed(MouseEvent evt) {
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
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
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
	
	private void resetValue()
	{
		this.user_txt.setText("");
		this.sex_txt.setText("");
		this.age_txt.setText("");
		this.phone_txt.setText("");
	}

}
