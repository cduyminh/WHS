package gui;
import domain.employees.*;
import domain.warehouse.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Assignment extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Assignment frame = new Assignment();
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
	public Assignment(Manager manager, WareHouse warehouse, Employee employee) {
		setTitle("Assign employee");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 230, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Storage");
		btnNewButton.setBounds(20, 46, 88, 42);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delivery");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.assignEmployeeToDepartment(employee, 2);
				Warehouse warehouse_frame = new Warehouse(warehouse,manager);
				warehouse_frame.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_1.setBounds(118, 45, 88, 44);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Choose one");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(20, 11, 161, 24);
		contentPane.add(lblNewLabel);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.assignEmployeeToDepartment(employee, 1);
				EmployeeInfo employeeinfo = new EmployeeInfo(manager,warehouse,employee);
				employeeinfo.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
	}
	
	
}
