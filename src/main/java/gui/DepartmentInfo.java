package gui;
import domain.departments.*;
import javax.swing.table.DefaultTableCellRenderer;
import domain.warehouse.*;
import domain.items.*;
import domain.employees.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;

import java.awt.event.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DepartmentInfo extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTextField searchID;
	private JTable table_4;
	private JTable table_5;
	private JTextField searchName;
	private JTextField searchID_delivery;
	private JTextField searchName_delivery;
	public DepartmentInfo(WareHouse warehouse, Manager manager) {
		setTitle("Departments Informantion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 61, 980, 370);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Storage", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 402, 227);
		panel.add(scrollPane);
		
		table = new JTable();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		/*FETCH STORAGE*/
		try {
			URL url = new URL("http://localhost:8080/items/storage");
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
//				System.out.println(String.valueOf(sb));
				JSONParser jp = new JSONParser();
//				JSONObject jo = (JSONObject) jp.parse(String.valueOf(sb));
//				System.out.println(jo.get("items"));
				JSONArray ja = (JSONArray) jp.parse(String.valueOf(sb));
//				System.out.println(ja.get(0));
				table = new JTable();
				Object[][] storage = new Object[ja.size()][3];
				int i = 0;
				for (Object item: ja) {
					JSONObject oi = (JSONObject) item;
					storage[i][0] = oi.get("id");
					storage[i][1] = oi.get("name");
					storage[i][2] = oi.get("quantity");
					i++;
				}
				table.setModel(new DefaultTableModel(
						storage,
						new String[] {
							"ID", "Name", "Quantity"
						}
					) {
						Class[] columnTypes = new Class[] {
							Integer.class, String.class, Integer.class
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
					for (int column = 0; column < 3; column++) {
					table.getColumnModel().getColumn(column).setCellRenderer( centerRenderer );
					}
					scrollPane.setViewportView(table);
					table.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent me) {
							if(me.getClickCount() == 2) { //double click events
								JTable target = (JTable)me.getSource();
								int row = target.getSelectedRow();
								ItemInfo item_infor = new ItemInfo(warehouse,manager,warehouse.getStorageDept().getItems().get(row));
								item_infor.setVisible(true);
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
		
		/*LEGACY STORAGE*/
//		Object[][] items_data_storage = new Object[warehouse.getStorageDept().getItems().size()][3];
//		int i = 0;
//		for (Item item : warehouse.getStorageDept().getItems()) {
//			items_data_storage[i][0] = item.getID();
//			items_data_storage[i][1] = item.getName();
//			items_data_storage[i][2] = item.getQuantity();
//			i++;
//		}
//		table.setModel(new DefaultTableModel(
//			items_data_storage,
//			new String[] {
//				"ID", "Name", "Quantity"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				Integer.class, String.class, Integer.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//			boolean[] columnEditables = new boolean[] {
//				false, false, false
//			};
//			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
//			}
//		});
//		table.getColumnModel().getColumn(0).setResizable(false);
//		table.getColumnModel().getColumn(0).setPreferredWidth(15);
//		for (int column = 0; column < 3; column++) {
//		table.getColumnModel().getColumn(column).setCellRenderer( centerRenderer );
//		}
//		scrollPane.setViewportView(table);
//		table.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent me) {
//				if(me.getClickCount() == 2) { //double click events
//					JTable target = (JTable)me.getSource();
//					int row = target.getSelectedRow();
//					ItemInfo item_infor = new ItemInfo(warehouse,manager,warehouse.getStorageDept().getItems().get(row));
//					item_infor.setVisible(true);
//					setVisible(false);
//					dispose();
//				}
//			}
//		});
		/*LEGACY USER*/
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(496, 75, 255, 227);
		panel.add(scrollPane_1);
		table_1 = new JTable();
		Object[][] employee_data_storage = new Object[warehouse.getStorageDept().getEmployees().size()][2];
		int i = 0;
		for (Employee e : warehouse.getStorageDept().getEmployees()) {
			employee_data_storage[i][0] = e.getID();
			employee_data_storage[i][1] = e.getName();
			i++;
		}
		table_1.setModel(new DefaultTableModel(
			employee_data_storage,
			new String[] {
				"ID", "Name"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(15);
		table_1.getColumnModel().getColumn(1).setResizable(false);
		for (int column = 0; column < 2; column++) {
			table_1.getColumnModel().getColumn(column).setCellRenderer( centerRenderer );
			}
		
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel_2 = new JLabel("Items");
		lblNewLabel_2.setBounds(10, 50, 47, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Employees");
		lblNewLabel_3.setBounds(497, 50, 139, 14);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("Move to Delivery");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selected_items_storage = table.getSelectedRows();
				for (int k: selected_items_storage) {
				warehouse.getStorageDept().issue(warehouse.getStorageDept().getItems().get(k).getID());
				System.out.println(k);

				}
				DepartmentInfo newFrame = new DepartmentInfo(warehouse,manager);
				newFrame.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnNewButton_3.setBounds(144, 308, 134, 23);
		panel.add(btnNewButton_3);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Delivery", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 75, 402, 227);
		panel_1.add(scrollPane_2);
		
		/*FETCH DELIVERY*/
		try {
			URL url = new URL("http://localhost:8080/items/delivery");
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
//				System.out.println(String.valueOf(sb));
				JSONParser jp = new JSONParser();
//				JSONObject jo = (JSONObject) jp.parse(String.valueOf(sb));
//				System.out.println(jo.get("items"));
				JSONArray ja = (JSONArray) jp.parse(String.valueOf(sb));
//				System.out.println(ja.get(0));
				table_2 = new JTable();
				Object[][] storage = new Object[ja.size()][3];
				i = 0;
				for (Object item: ja) {
					JSONObject oi = (JSONObject) item;
					storage[i][0] = oi.get("id");
					storage[i][1] = oi.get("name");
					storage[i][2] = oi.get("quantity");
					i++;
				}
				table_2.setModel(new DefaultTableModel(
						storage,
						new String[] {
							"ID", "Name", "Quantity"
						}
					) {
						Class[] columnTypes = new Class[] {
							Integer.class, String.class, Integer.class
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
				table_2.getColumnModel().getColumn(0).setResizable(false);
				table_2.getColumnModel().getColumn(0).setPreferredWidth(15);
					for (int column = 0; column < 3; column++) {
						table_2.getColumnModel().getColumn(column).setCellRenderer( centerRenderer );
					}
					scrollPane_2.setViewportView(table_2);
					table_2.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent me) {
							if(me.getClickCount() == 2) { //double click events
								JTable target = (JTable)me.getSource();
								int row = target.getSelectedRow();
								ItemInfo item_infor = new ItemInfo(warehouse,manager,warehouse.getStorageDept().getItems().get(row));
								item_infor.setVisible(true);
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
		
		
		/*LEGACY USER*/

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(496, 75, 255, 227);
		panel_1.add(scrollPane_3);
		
		table_3 = new JTable();
		Object[][] employee_data_delivery = new Object[warehouse.getDeliveryDept().getEmployees().size()][2];
		i = 0;
		for (Employee e : warehouse.getDeliveryDept().getEmployees()) {
			employee_data_delivery[i][0] = e.getID();
			employee_data_delivery[i][1] = e.getName();
			i++;
		}
		table_3.setModel(new DefaultTableModel(
			employee_data_delivery,
			new String[] {
				"ID", "Name"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_3.getColumnModel().getColumn(0).setResizable(false);
		table_3.getColumnModel().getColumn(0).setPreferredWidth(15);
		table_3.getColumnModel().getColumn(1).setResizable(false);
		for (int column = 0; column < 2; column++) {
			table_3.getColumnModel().getColumn(column).setCellRenderer( centerRenderer );
			}
		scrollPane_3.setViewportView(table_3);
		
		JLabel lblNewLabel_4 = new JLabel("Items");
		lblNewLabel_4.setBounds(10, 50, 47, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Employees");
		lblNewLabel_5.setBounds(496, 50, 110, 14);
		panel_1.add(lblNewLabel_5);
		
		JButton btnNewButton_2 = new JButton("Export");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selected_items_delivery = table_2.getSelectedRows();
				for (int k: selected_items_delivery) {
					warehouse.getDeliveryDept().issue(warehouse.getDeliveryDept().getItems().get(k).getID());
				}
				DepartmentInfo newFrame = new DepartmentInfo(warehouse,manager);
				newFrame.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_2.setBounds(163, 308, 89, 23);
		panel_1.add(btnNewButton_2);
		
		
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

