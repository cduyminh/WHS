package com.example.restapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import domain.employees.Employee;
import domain.employees.Manager;
import domain.items.Equipment;
import domain.items.Item;
import domain.warehouse.WareHouse;

@RestController
public class Controller {
	private Manager 	manager 	= 	new Manager();
	private WareHouse	warehouse 	=	new WareHouse();
	
	@PostMapping("/user")
	public JSONObject createUser(@RequestBody JSONObject e) {
		manager.addEmployee
		(
				e.get("name").toString(), 
				e.get("description").toString(), 
				Float.parseFloat(e.get("salary").toString()), 
				Integer.parseInt(e.get("department").toString()), 
				e.get("assignment").toString()
		);
		return e;
	}
	
	@GetMapping("/users")
	public String getAllUsers()	{
		String json 				= 	new Gson().toJson(manager.viewAssignment());
		return json;	
	}
	
	@GetMapping("/users/storage")
	public String getStorageUsers() {
		String json 				= new Gson().toJson(warehouse.getStorageDept().getEmployees());
		return json;
	}
	
	@GetMapping("/users/delivery")
	public String getDeliveryUsers() {
		String json 				= new Gson().toJson(warehouse.getDeliveryDept().getEmployees());
		return json;
	}
	
	@GetMapping("/items/delivery")
	public String getDeliveryItems() {
		String json					=	new Gson().toJson(warehouse.getDeliveryDept().getItems());
		return json;
	}
	
	@GetMapping("/items/storage")
	public String getStorageItems() {
		String json					=	new Gson().toJson(warehouse.getStorageDept().getItems());
		return json;
	}
	

}
