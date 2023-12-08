package com.lipari.gestioneordini.Controller.Order;

import java.sql.Date;
import java.util.List;

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
	
	public void setOrderId(Integer id) {
		orderModel.setId(id);
	}
	
	public Integer getOrderId() {
		return orderModel.getId();
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
	
	public void setOrderProducts(List<Item> items) {
		orderModel.setProducts(items);
	}
	
	public List<Item> getOrderProducts(){
		return orderModel.getProducts();
	}
	
	
	
	
	
	
	
	
	

}
