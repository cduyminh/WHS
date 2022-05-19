package gui;
import domain.employees.*;
import domain.warehouse.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EmployeeInfo extends JFrame {

	private JPanel contentPane;
	private Employee this_employee;
	public EmployeeInfo(Manager manager, WareHouse warehouse, Employee employee) {
		setTitle("Employee Information");
		this_employee = employee;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 530, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Infomation");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(192, 11, 154, 29);
		contentPane.add(lblNewLabel);
		
		JTextArea information = new JTextArea();
		information.setEditable(false);
		information.setText("ID: " 
		+ this_employee.getID()
				+ "\r\nName: " 
		+ this_employee.getName()
				+ "\r\nDescription: " 
		+ this_employee.getDescription()
				+ "\r\nSalary: " 
		+ this_employee.getSalary()
				+ "\r\nDepartment: " 
		+ this_employee.getDepartmentName()
				+ "\r\nAssignment: "
		+ this_employee.getAssignment());
		information.setBounds(134, 53, 265, 98);
		contentPane.add(information);
		
		JButton btnNewButton = new JButton("Assign to department");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Assignment assignment = new Assignment(manager,warehouse,this_employee);
				assignment.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton.setBounds(24, 186, 208, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove from department");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.removeEmployeeFromDepartment(this_employee,this_employee.getDepartment());
				Warehouse warehouse_frame = new Warehouse(warehouse,manager);
				warehouse_frame.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_1.setBounds(282, 186, 208, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Remove from warehouse");
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.removeEmployee(this_employee);
				Warehouse warehouse_frame = new Warehouse(warehouse,manager);
				warehouse_frame.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_2.setBounds(282, 229, 208, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Update");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateEmployee updateemployee = new UpdateEmployee(manager,warehouse,employee);
				updateemployee.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_3.setBackground(Color.GREEN);
		btnNewButton_3.setBounds(24, 229, 208, 23);
		contentPane.add(btnNewButton_3);
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Warehouse warehouse_frame = new Warehouse(warehouse,manager);
                warehouse_frame.setVisible(true);
                setVisible(false);
                dispose();
            }
        });
	}
}

