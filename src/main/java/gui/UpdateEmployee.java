package gui;

import java.awt.BorderLayout;
import domain.warehouse.*;
import domain.employees.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField desField;
	private JTextField salaryField;
	private JTextField assignField;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UpdateEmployee frame = new UpdateEmployee();
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
	public UpdateEmployee(Manager manager, WareHouse warehouse, Employee employee) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 60, 47, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Description");
		lblNewLabel_1.setBounds(10, 100, 133, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Salary");
		lblNewLabel_2.setBounds(10, 140, 47, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Assignment");
		lblNewLabel_3.setBounds(10, 180, 133, 14);
		contentPane.add(lblNewLabel_3);
		
		nameField = new JTextField();
		nameField.setBounds(115, 57, 144, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		nameField.setText(employee.getName());
		
		desField = new JTextField();
		desField.setBounds(115, 97, 144, 20);
		contentPane.add(desField);
		desField.setColumns(10);
		desField.setText(employee.getDescription());
		
		salaryField = new JTextField();
		salaryField.setBounds(115, 137, 144, 20);
		contentPane.add(salaryField);
		salaryField.setColumns(10);
		salaryField.setText(employee.getSalary().toString());
		
		assignField = new JTextField();
		assignField.setBounds(115, 177, 144, 20);
		contentPane.add(assignField);
		assignField.setColumns(10);
		assignField.setText(employee.getAssignment());
		
		lblNewLabel_4 = new JLabel("Update Employee");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(83, 11, 154, 36);
		contentPane.add(lblNewLabel_4);
		
		btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employee.setName(nameField.getText());
				employee.setDescription(desField.getText());
				employee.setSalary(Float.parseFloat(salaryField.getText()));
				employee.setAssignment(assignField.getText());
				manager.updateEmployee(employee);
				EmployeeInfo employeeinfo = new EmployeeInfo(manager,warehouse,employee);
				employeeinfo.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton.setBounds(93, 229, 89, 23);
		contentPane.add(btnNewButton);
	}

}
