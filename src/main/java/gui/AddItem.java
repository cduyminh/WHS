package gui;
import domain.items.*;
import domain.warehouse.*;
import domain.departments.*;
import domain.employees.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class AddItem extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField des;
	private JTextField price;
	private JTextField quantity;

	public AddItem(WareHouse warehouse, Manager manager) {
		setTitle("Add item");
		List<Item> storage_item = new ArrayList<Item>();
		List<Item> delivery_item = new ArrayList<Item>();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 340, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 55, 47, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Description");
		lblNewLabel_1.setBounds(10, 80, 93, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setBounds(10, 105, 47, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Type");
		lblNewLabel_3.setBounds(10, 130, 47, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Department");
		lblNewLabel_4.setBounds(10, 155, 93, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Quantity");
		lblNewLabel_5.setBounds(10, 180, 70, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(48, 205, 89, 23);
		contentPane.add(btnNewButton);
		
		name = new JTextField();
		name.setBounds(110, 52, 149, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		des = new JTextField();
		des.setColumns(10);
		des.setBounds(110, 77, 149, 20);
		contentPane.add(des);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(110, 102, 149, 20);
		contentPane.add(price);
		
		JComboBox type = new JComboBox();
		type.setModel(new DefaultComboBoxModel(new String[] {"Equipment", "Refreshment"}));
		type.setBounds(110, 126, 149, 20);
		contentPane.add(type);
		
		JComboBox dep = new JComboBox();
		dep.setModel(new DefaultComboBoxModel(new String[] {"Storage", "Delivery"}));
		dep.setBounds(110, 151, 149, 20);
		contentPane.add(dep);
		
		quantity = new JTextField();
		quantity.setBounds(110, 177, 149, 20);
		contentPane.add(quantity);
		quantity.setColumns(10);
		
		NullBox popup = new NullBox();
		WrongType popup1 = new WrongType();
		NonZero popup2 = new NonZero();
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name.getText().equals("") || des.getText().equals("") || price.getText().equals("") || quantity.getText().equals(""))
					popup.setVisible(true);
				else if (quantity.getText().equals("0")) {
					popup2.setVisible(true);
				}		
				else {
				Item item;
				try {
						item = new Equipment();
						if(dep.getItemAt(dep.getSelectedIndex()) == "Storage") {
							item.setName(name.getText());
							item.setDescription(des.getText());
							item.setPrice(Double.parseDouble(price.getText()));
							item.setType(1);
							item.setQuantity(Integer.parseInt(quantity.getText()));
							item.setDepartment(1);
						}
						else {
							item.setName(name.getText());
							item.setDescription(des.getText());
							item.setPrice(Double.parseDouble(price.getText()));
							item.setType(1);
							item.setQuantity(Integer.parseInt(quantity.getText()));
							item.setDepartment(2);
						}
//						/*POST*/
//						String payload = "{\"name\":\""
//								+ name.getText()
//								+ "\","
//								+ "\"description\":\""
//								+ des.getText()
//								+ "\","
//								+ "\"salary\":"
//								+ price.getText()
//								+ ","
//								+ "\"department\":"
//								+ "0"
//								+ ","
//								+ "\"quantity\":\""
//								+ quantity.getText()
//								+ "\"}";
//						System.out.println(payload);
//						JSONParser jp = new JSONParser();
//						JSONObject jo = (JSONObject) jp.parse(payload);
//						System.out.println(jo.toJSONString());
						warehouse.receipt(item);

					
				}
				catch (NumberFormatException ne) {
					popup1.setVisible(true);
				}
				}
				name.setText("");
				des.setText("");
				price.setText("");
				quantity.setText("");
			}
		});
		
		JLabel lblNewLabel_6 = new JLabel("Add item");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(134, 11, 65, 33);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton_1 = new JButton("Finish");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				warehouse.receipt(storage_item);
				warehouse.receipt(delivery_item);
				setVisible(false);
				Warehouse warehouse_frame = new Warehouse(warehouse,manager);
				warehouse_frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(178, 205, 89, 23);
		contentPane.add(btnNewButton_1);
		
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

