package gui;
import domain.items.*;
import domain.warehouse.*;
import domain.employees.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemInfo extends JFrame {

	private JPanel contentPane;
	private Item this_item;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		Refreshment item1 = new Refreshment();
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ItemInfo frame = new ItemInfo(item1);
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
	public ItemInfo(WareHouse warehouse, Manager manager, Item item) {
		setTitle("Item Information");
		this_item = item;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea infomation = new JTextArea();
		infomation.setEditable(false);
		infomation.setBounds(81, 51, 265, 136);
		infomation.setText("ID: " 
		+ this_item.getID()
		+ "\r\nName: " 
				+ this_item.getName()
		+ "\r\nPrice: " 
				+ this_item.getPrice()
		+ "\r\nType: " 
				+ this_item.getTypeName()
		+ "\r\nDescription: " 
				+ this_item.getDescription()
		+ "\r\nDepartment: " 
				+ this_item.getDepartmentName()
		+ "\r\nQuantity: "
				+ this_item.getQuantity());
		contentPane.add(infomation);
		
		JLabel lblNewLabel = new JLabel("Item Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(148, 11, 126, 29);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateItem updateitem = new UpdateItem(warehouse,manager,item);
				updateitem.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(162, 229, 89, 23);
		contentPane.add(btnNewButton);
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                DepartmentInfo departmentinfo = new DepartmentInfo(warehouse,manager);
                departmentinfo.setVisible(true);
                setVisible(false);
                dispose();
            }
        });
	}
}

