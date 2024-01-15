package com.lipari.gestioneordini.Model.Order;

import java.util.*;

public class Order {
	private String uuid;
	private Integer id_user;
	private Date date_order;
	private String address;
	private Integer itemID;
	
	public Order() {
	}	

	public Order(Integer id_user, String address, Integer itemID) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.id_user = id_user;
		this.date_order = new Date();
		this.address = address;
		this.itemID = itemID;
	}
	public void setItemID(Integer itemID){
		this.itemID = itemID;

	}
	public void setUUID(String UUID){
		this.uuid = UUID;
	}
	public String getUUID() {
		return this.uuid;
	}

	public String generateUUID(){
		return UUID.randomUUID().toString();
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
        
        public String getId(Integer id){
            return this.uuid;
        }
        
        public Date getOrderDate(){
            return this.date_order;
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
	
	public String toString() {
		return "Info Order:"+
				"\nUUID: "+this.getUUID()+
				"\nID USER: "+this.getId_user()+
				"\nDATE ORDER: "+this.getDate_order()+
				"\nADDRESS: "+this.getAddress();
	}
	
	

	
	
	
	
	

}
