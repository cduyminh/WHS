package gui;

import java.awt.BorderLayout;
import domain.warehouse.*;
import domain.employees.*;
import domain.items.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateItem extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField desField;
	private JTextField priceField;
	private JTextField quantityField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UpdateItem frame = new UpdateItem();
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
	public UpdateItem(WareHouse warehouse, Manager manager, Item item) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 60, 47, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Description");
		lblNewLabel_1.setBounds(10, 100, 105, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setBounds(10, 140, 47, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setBounds(10, 180, 75, 14);
		contentPane.add(lblNewLabel_3);
		
		nameField = new JTextField();
		nameField.setBounds(95, 60, 151, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		nameField.setText(item.getName());
		
		desField = new JTextField();
		desField.setBounds(95, 100, 151, 20);
		contentPane.add(desField);
		desField.setColumns(10);
		desField.setText(item.getDescription());
		
		priceField = new JTextField();
		priceField.setBounds(95, 140, 151, 20);
		contentPane.add(priceField);
		priceField.setColumns(10);
		priceField.setText(Double.toString(item.getPrice()));
		
		quantityField = new JTextField();
		quantityField.setBounds(95, 180, 151, 20);
		contentPane.add(quantityField);
		quantityField.setColumns(10);
		quantityField.setText(item.getQuantity().toString());
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.setName(nameField.getText());
				item.setDescription(desField.getText());
				item.setPrice(Double.parseDouble(priceField.getText()));
				item.setQuantity(Integer.parseInt(quantityField.getText()));
				warehouse.updateItem(item);
				ItemInfo iteminfo = new ItemInfo(warehouse,manager,item);
				iteminfo.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton.setBounds(114, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Update Item");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(111, 23, 105, 26);
		contentPane.add(lblNewLabel_4);
	}

}
