package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane table=null; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm() {
		setTitle("售票系统主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1027, 632);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("车票信息管理");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("车次信息管理");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("车次信息添加");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainAddInterFrm trainaddInterFrm=new TrainAddInterFrm();  //内部窗口interframe
				trainaddInterFrm.setVisible(true);
				table.add(trainaddInterFrm);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("车次信息维护");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainManageInterFrm trainmanageInterFrm=new TrainManageInterFrm();  //内部窗口interframe
				trainmanageInterFrm.setVisible(true);
				table.add(trainmanageInterFrm);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_3 = new JMenu("车票信息管理");
		mnNewMenu.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("车票购买");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainBuyInterFrm trainbuyInterFrm=new TrainBuyInterFrm();  //内部窗口interframe
				trainbuyInterFrm.setVisible(true);
				table.add(trainbuyInterFrm);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("车票维护");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketDefendInterFrm ticketDefendInterFrm=new TicketDefendInterFrm();  //内部窗口interframe
				ticketDefendInterFrm.setVisible(true);
				table.add(ticketDefendInterFrm);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("安全退出");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "是否安全退出？");
				if(result==0) {     //点击确定，result为0
					dispose();      //关闭界面
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_1 = new JMenu("关于云飞");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("关于废物云飞");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterFrm interfrm=new InterFrm();  //内部窗口interframe
				interfrm.setVisible(true);
				table.add(interfrm);
			}
		});
		
		
		mntmNewMenuItem.setBackground(Color.PINK);
		mnNewMenu_1.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JDesktopPane();
		table.setBackground(SystemColor.activeCaption);
		contentPane.add(table, BorderLayout.CENTER);
		
		//界面最大化
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}


}
