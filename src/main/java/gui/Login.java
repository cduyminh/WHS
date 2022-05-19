package gui;
import domain.employees.*;
import domain.warehouse.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		WareHouse warehouse = new WareHouse();
//		Manager manager = new Manager(warehouse);
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login(manager, warehouse);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Login(Manager manager, WareHouse warehouse) {
		setTitle("Warehouse Management System Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(167, 170, 100, 30);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(85, 98, 72, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(85, 129, 72, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(167, 95, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(167, 126, 100, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Warehouse Management System");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(85, 28, 285, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_fake = new JLabel("Is Fake, No Account Required");
		lblNewLabel_fake.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_fake.setBounds(85, 50, 285, 30);
		contentPane.add(lblNewLabel_fake);
		
		JLabel lblNewLabel_3 = new JLabel("");
		contentPane.add(lblNewLabel_3);
		
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
//			if (manager.validateManager()) {
				lblNewLabel_3.setBounds(140,200,200,30);
				lblNewLabel_3.setText("Logged in successfully.");
				Welcome welcome = new Welcome(manager,warehouse);
				welcome.setVisible(true);
				setVisible(false);
				dispose();
//			}
//			else {
//				lblNewLabel_3.setBounds(120,200,200,30);
//				lblNewLabel_3.setText("Wrong username or password.");
//			}
			}
		}
	);
	}
}

