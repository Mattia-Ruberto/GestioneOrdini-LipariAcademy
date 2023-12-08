package com.lipari.gestioneordini.Model.Order;

import java.sql.Date;
import java.util.List;
import com.lipari.gestioneordini.Model.Item.*;
public class Order {
	private Integer id;
	private Integer id_user;
	private Date date_order;
	private String address;
	private List<Item> products;
	private Double total_price;
	
	public Order() {
	}	

	public Order(Integer id, Integer id_user, Date date_order, String address, List<Item> products, Double total_price) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.date_order = date_order;
		this.address = address;
		this.products = products;
		this.total_price= total_price;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public Date getDate_order() {
		return date_order;
	}

	public void setDate_order(Date date_order) {
		this.date_order = date_order;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Item> getProducts() {
		return products;
	}

	public void setProducts(List<Item> products) {
		this.products = products;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}
	
	public void calculateTotalPrice() {
		Double total_price = 0.00;
		for (Item item : this.getProducts()) {
			total_price += item.getPrice();
		}
		this.total_price = total_price;
	}
	
	

	
	
	
	
	

}
