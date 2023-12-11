package com.lipari.gestioneordini.Controller.Order;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.lipari.gestioneordini.Model.Item.Item;
import com.lipari.gestioneordini.Model.Order.Order;
import com.lipari.gestioneordini.View.ViewOrder.ViewOrder;

public class OrderController {
	private Order orderModel;
	private ViewOrder orderView;
	
	public OrderController(Order orderModel, ViewOrder orderView) {
		super();
		this.orderModel = orderModel;
		this.orderView = orderView;
	}

	public Order getOrderModel() {
		return orderModel;
	}

	public void setOrderModel(Order orderModel) {
		this.orderModel = orderModel;
	}
	

	public ViewOrder getOrderView() {
		return orderView;
	}

	public void setOrderView(ViewOrder orderView) {
		this.orderView = orderView;
	}
	
	public String getUUID() {
		return orderModel.getUUID();
	}
	
	public void setOrderId_user(Integer id_user) {
		orderModel.setId_user(id_user);
	}
	
	public Integer getOrderId_user() {
		return orderModel.getId_user();
	}
	
	public void setOrderDate(Date date) {
		orderModel.setDate_order(date);
	}
	
	public Date getOrderDate() {
		return orderModel.getDate_order();
	}
	
	public void setOrderAddress(String address) {
		orderModel.setAddress(address);
	}
	
	public String getOrderAddress() {
		return orderModel.getAddress();
	}
	
	public void setOrderProducts(Map<Integer, Item> items) {
		orderModel.setProducts(items);
	}
	
	public Map<Integer, Item> getOrderProducts(){
		return orderModel.getProducts();
	}
	
	
	
	
	
	
	
	
	

}
