package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Font;

public class InterFrm extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterFrm frame = new InterFrm();
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
	public InterFrm() {
		setBackground(Color.PINK);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(InterFrm.class.getResource("/images/瑟蕾娜1.png")));
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		setIconifiable(true);
		setClosable(true);
		setTitle("关于云飞");
		setBounds(100, 100, 1040, 696);

	}

}
