package com.example.restapi;
import database.DBConnection;
import domain.employees.*;
import gui.*;
import java.awt.EventQueue;
import domain.warehouse.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		new DBConnection();

		WareHouse warehouse = new WareHouse();
		Manager manager = new Manager(warehouse);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(manager, warehouse);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		SpringApplication.run(RestapiApplication.class, args);
	}

}
