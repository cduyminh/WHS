package gui;
import domain.warehouse.*;
import domain.departments.*;
import domain.items.*;
import domain.employees.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SearchItem extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Integer search_id;
	private String search_name;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		Manager manager = new Manager();
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SearchItem frame = new SearchItem(manager,1,"hello");
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
	public SearchItem(Department dep, Integer id, String name) {
		setTitle("Search item");
		search_id = id;
		search_name = name;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search Results");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(169, 11, 115, 30);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 59, 337, 134);
		contentPane.add(scrollPane);
		
//		table_1 = new JTable();
//		table_1.setModel(new DefaultTableModel(
//			new Object[][] {
//				{"12", "Pencil", "Equipment", "Storage", "5"},
//			},
//			new String[] {
//				"ID", "Name", "Type", "Department", "Quantity"
//			}
//		));
//		scrollPane.setViewportView(table_1);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		table = new JTable();
		Object[][] search_results = new Object[dep.getItems(id,name).size()][5];
		int i = 0;
		for (Item item : dep.getItems(id,name)) {
			search_results[i][0] = item.getID();
			search_results[i][1] = item.getName();
			search_results[i][2] = item.getTypeName();
			search_results[i][3] = item.getDepartmentName();
			search_results[i][4] = item.getQuantity();
			i++;
		}
		table.setModel(new DefaultTableModel(
			search_results,
			new String[] {
				"ID", "Name", "Type", "Department", "Quantity"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		for (int column = 0; column < 5; column++) {
			table.getColumnModel().getColumn(column).setCellRenderer( centerRenderer );
			}
		scrollPane.setViewportView(table);
	}
}
