package com.lipari.gestioneordini.Model.Order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lipari.gestioneordini.Model.Item.*;
import java.util.UUID;

public class Order {
	private String uuid;
	private Integer id_user;
	private Date date_order;
	private String address;
	private Map<Integer,Item> products;
	private Double total_price;
	
	public Order() {
	}	

	public Order(Integer id_user, String address, Map<Integer,Item> products) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.id_user = id_user;
		this.date_order = new Date();
		this.address = address;
		this.products = products;
		this.calculateTotalPrice();
	}


	public String getUUID() {
		return this.uuid;
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

	public Map<Integer,Item> getProducts() {
		return products;
	}

	public void setProducts(Map<Integer,Item> products) {
		this.products = products;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}
	
	public void calculateTotalPrice() {
		Double tot_price = 0.00;
		for (Item item : this.getProducts().values()) {
			tot_price += item.getPrice()*item.getQuantity();
		}
		this.total_price = tot_price;
	}
	
	public String toString() {
		return "Info Order:"+
				"\nUUID: "+this.getUUID()+
				"\nID USER: "+this.getId_user()+
				"\nDATE ORDER: "+this.getDate_order()+
				"\nADDRESS: "+this.getAddress()+
				"\nTOTAL PRICE: "+this.getTotal_price();
	}
	
	

	
	
	
	
	

}
