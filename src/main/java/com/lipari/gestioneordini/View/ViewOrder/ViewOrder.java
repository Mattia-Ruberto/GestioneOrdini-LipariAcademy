package com.lipari.gestioneordini.View.ViewOrder;

import java.sql.Date;
import java.util.List;
import com.lipari.gestioneordini.Model.Item.*;

public class ViewOrder {
	
	public void printOrderInfo(Integer id, Integer id_user, Date date_order, String address, List<Item> products, Double total_price) {
		System.out.println("Order Information:"+
							"\nID Order: "+id+
							"\nID User: "+id_user+
							"\nDate: "+date_order+
							"\nShipping Address: "+address);
		for (Item item : products) {
			System.out.println("\nItems:"+
								"\n"+item.getId_product()+
								"\nQuantity: "+item.getQuantity());
		}
	}

}
