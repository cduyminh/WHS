package gui;
import domain.employees.Manager;
import domain.warehouse.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.awt.event.ActionEvent;

public class AddEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField des;
	private JTextField salary;
	private JTextField textField;
	private JTextField assign;

	public AddEmployee(Manager manager, WareHouse warehouse) {
		setTitle("Add employee");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(23, 55, 47, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Description");
		lblNewLabel_1.setBounds(23, 96, 113, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Salary");
		lblNewLabel_2.setBounds(23, 136, 47, 14);
		contentPane.add(lblNewLabel_2);
		
		name = new JTextField();
		name.setBounds(102, 52, 135, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		des = new JTextField();
		des.setColumns(10);
		des.setBounds(102, 93, 135, 20);
		contentPane.add(des);
		
		salary = new JTextField();
		salary.setColumns(10);
		salary.setBounds(102, 133, 135, 20);
		contentPane.add(salary);
		
		assign = new JTextField();
		assign.setBounds(102, 173, 135, 20);
		contentPane.add(assign);
		assign.setColumns(10);
		
		
		JLabel lblNewLabel_3 = new JLabel("Add Employee");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(85, 11, 107, 20);
		contentPane.add(lblNewLabel_3);
		
		
		JLabel lblNewLabel_4 = new JLabel("Assignment");
		lblNewLabel_4.setBounds(23, 176, 73, 14);
		contentPane.add(lblNewLabel_4);

		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(102, 223, 89, 23);
		contentPane.add(btnNewButton);
		
		NullBox popup = new NullBox();
		WrongType popup1 = new WrongType();
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (name.getText().equals("") || des.getText().equals("") || salary.getText().equals("") || assign.getText().equals("")) {
					popup.setVisible(true);
				}
				else {
					try {
					/*POST*/
						String payload = "{\"name\":\""
								+ name.getText()
								+ "\","
								+ "\"description\":\""
								+ des.getText()
								+ "\","
								+ "\"salary\":"
								+ salary.getText()
								+ ","
								+ "\"department\":"
								+ "0"
								+ ","
								+ "\"assignment\":\""
								+ assign.getText()
								+ "\"}";
						System.out.println(payload);
						JSONParser jp = new JSONParser();
						JSONObject jo = (JSONObject) jp.parse(payload);
						System.out.println(jo.toJSONString());
						var uri = new URI("http://localhost:8080/user");
						var client = HttpClient.newHttpClient();
						var req = HttpRequest.newBuilder(uri).POST(BodyPublishers.ofString(payload)).header("Content-type", "application/json").build();
						var res = client.send(req, BodyHandlers.discarding());
						System.out.println(res);
						if (res.statusCode()==200) {
							name.setText("");
							des.setText("");
							salary.setText("");
							assign.setText("");
						}
						else {
							popup1.setVisible(true);
						}
					}
					catch (NumberFormatException | ParseException | URISyntaxException | IOException | InterruptedException ne) {
						popup1.setVisible(true);
					}
				}
			}
		});
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

