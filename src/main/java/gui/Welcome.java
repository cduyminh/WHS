package gui;
import domain.employees.*;
import domain.warehouse.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			Welcome dialog = new Welcome();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public Welcome(Manager manager, WareHouse warehouse) {
		setTitle("Welcome");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Welcome to our Warehouse Management System");
			lblNewLabel.setBounds(42, 10, 352, 17);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Feel free to manage");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblNewLabel_1.setBounds(42, 205, 370, 14);
			contentPanel.add(lblNewLabel_1);
		}
		
		JTextPane txtpnHelloThankYou = new JTextPane();
		txtpnHelloThankYou.setEditable(false);
		txtpnHelloThankYou.setText("Hello, thank you for using our application :).\r\nThis application is dedicated to our Java project at school.\r\nHope that you will be enjoyed while using our software.");
		txtpnHelloThankYou.setBounds(52, 38, 326, 154);
		contentPanel.add(txtpnHelloThankYou);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Warehouse warehouse_frame = new Warehouse(warehouse, manager);
						warehouse_frame.setVisible(true);
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

