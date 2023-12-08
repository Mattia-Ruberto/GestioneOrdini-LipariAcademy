package com.lipari.gestioneordini.Model.Item;
public class Item {
	private Integer id;
	private Integer id_order;
	private Integer id_product;
	private Integer quantity;
        private Double price;
	
	public Item(Integer id, Integer id_order, Integer id_product, Integer quantity) {
		super();
		this.id = id;
		this.id_order = id_order;
		this.id_product = id_product;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_order() {
		return id_order;
	}

	public void setId_order(Integer id_order) {
		this.id_order = id_order;
	}

	public Integer getId_product() {
		return id_product;
	}

	public void setId_product(Integer id_product) {
		this.id_product = id_product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
        
        public double getPrice(){
            return this.price;
        }
        
        public void setPrice(Double nprice){
            this.price = nprice;
        }
	
	
	
	

}
