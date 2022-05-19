package gui;
import domain.employees.*;
import domain.warehouse.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Warehouse extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		Manager manager = new Manager();
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Warehouse frame = new Warehouse(manager);
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
	public Warehouse(WareHouse warehouse, Manager manager) {
		setTitle("Warehouse");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(10, 27, 666, 348);
		contentPane.add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("DASHBOARD");
		lblNewLabel_2.setBounds(204, 11, 104, 17);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 67, 272, 132);
		panel.add(scrollPane);	
		JButton btnNewButton = new JButton("Add new employee");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmployee addemployee = new AddEmployee(manager,warehouse);
				addemployee.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		btnNewButton.setBounds(320, 69, 155, 23);
		panel.add(btnNewButton);
		
		JButton btnAddNewItem = new JButton("Add new item");
		btnAddNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddItem additem = new AddItem(warehouse,manager);
				additem.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnAddNewItem.setBounds(320, 176, 155, 23);
		panel.add(btnAddNewItem);
		
		JButton btnViewDepartments = new JButton("View departments");
		btnViewDepartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartmentInfo departmentinfo = new DepartmentInfo(warehouse,manager);
				departmentinfo.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnViewDepartments.setBounds(183, 256, 138, 23);
		panel.add(btnViewDepartments);
		
		splitPane.setDividerLocation(150);
		splitPane.setDividerSize(15);
		

		/*Fetch data*/
		try {
			URL url = new URL("http://localhost:8080/users");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				throw new RuntimeException("HttpResponseCode " + responseCode);
			}
			else {
				StringBuilder sb = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());
				while(scanner.hasNext()) {
					sb.append(scanner.nextLine());
				}
				scanner.close(); 
				JSONParser jp = new JSONParser();
				JSONArray ja = (JSONArray) jp.parse(String.valueOf(sb));
				table = new JTable();
				Object[][] data = new Object[ja.size()][3];
				int i = 0;
				for (Object user: ja) {
					JSONObject ou = (JSONObject) user;
					data[i][0] = ou.get("id");
					data[i][1] = ou.get("name");
					data[i][2] = ou.get("department");
					i++;
				}

				table.setModel(new DefaultTableModel(data, new String[] {"ID", "Name", "Department"}) 
				{
					Class[] columnTypes = new Class[] {
						Integer.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(0).setPreferredWidth(15);
				table.getColumnModel().getColumn(1).setResizable(false);
				table.getColumnModel().getColumn(2).setResizable(false);
				for (int column = 0; column < 3; column++) {
					table.getColumnModel().getColumn(column).setCellRenderer( centerRenderer );
					}
				
				scrollPane.setViewportView(table);
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						if(me.getClickCount() == 2) { //double click events
							JTable target = (JTable)me.getSource();
							int row = target.getSelectedRow();
							EmployeeInfo emp_info = new EmployeeInfo(manager, warehouse, manager.viewAssignment().get(row));
							emp_info.setVisible(true);
							setVisible(false);
							dispose();
						}
					}
				});
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		JTextPane greeting = new JTextPane();
		greeting.setEditable(false);
		greeting.setFont(new Font("Tahoma", Font.PLAIN, 14));
		greeting.setText("Welcome Manager to the Warehouse!!"
				+ "\r\n\r\n\r\nWarehouse Status"
				+ "\r\nName: " 
				+ warehouse.getName()
				+ "\r\nNumber of items: "
				+ warehouse.getNumberOfItems());
		splitPane.setLeftComponent(greeting);
	}
}
