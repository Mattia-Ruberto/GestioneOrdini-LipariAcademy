package com.lipari.gestioneordini.View.ViewOrder;

import com.lipari.gestioneordini.Model.Order.Order;

import com.lipari.gestioneordini.Model.Item.*;

public class ViewOrder {
	
	public void printOrderInfo(Order order) {
		System.out.println("Order Information:"+
							"\nUUID Order: "+order.getUUID()+
							"\nID User: "+order.getId_user()+
							"\nDate: "+order.getDate_order()+
							"\nShipping Address: "+order.getAddress());
		for (Item item : order.getProducts().values()) {
			System.out.println("\nItems:"+
								"\n"+item.getId_product()+
								"\nQuantity: "+item.getQuantity());
		}
	}

}
