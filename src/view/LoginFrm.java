package view;

import java.awt.EventQueue;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import dao.userdao;
import model.User;
import util.DbUtil;
import util.StringUtil;

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;

	private DbUtil dbUtil=new DbUtil();
	private userdao userDao=new userdao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
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
	public LoginFrm() {
		setTitle("登录");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("登录");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 35));
		
		JLabel lblNewLabel_1 = new JLabel("用户名");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 22));
		
		JLabel lblNewLabel_2 = new JLabel("密码");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 22));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		JButton loginbutton = new JButton("确    定");
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		loginbutton.setFont(new Font("宋体", Font.BOLD, 18));
		
		JButton resetbutton = new JButton("重    置");
		resetbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				resetvalueActionPerformed(evt);
			}
		});
		resetbutton.setFont(new Font("宋体", Font.BOLD, 17));
		
		passwordTxt = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(75)
								.addComponent(loginbutton, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addGap(139)
								.addComponent(resetbutton, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(94)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_1))
								.addGap(37)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(passwordTxt)
									.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(227)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
					.addGap(183))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(loginbutton)
						.addComponent(resetbutton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(45))
		);
		contentPane.setLayout(gl_contentPane);
		
		//设置界面初始居中
		//this.setLocationRelativeTo(null);
	}

	/**
	 * 登录事件处理 
	 * @param e
	 */
	protected void loginActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String userName=this.userNameTxt.getText();
		String password=new String(this.passwordTxt.getPassword());
		if(StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		User user=new User(userName,password);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser!=null) {
				//JOptionPane.showMessageDialog(null, "登陆成功！");
				dispose();//销毁界面
				new MainFrm().setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	 * 重置事件处理
	 * @param e
	 */
	protected void resetvalueActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.passwordTxt.setText("");
		this.userNameTxt.setText("");
	}
}
