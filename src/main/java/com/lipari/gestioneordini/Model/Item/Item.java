package com.lipari.gestioneordini.Model.Item;
public class Item {
	private Integer id_product;
	private String name;
	private String description;
	private Integer quantity;
	private Double price;

	public Item(){
		super();
	}
	public Item(String name, String description, Integer quantity, Double price) {
		super();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}
	public Integer getId_product() {
		return id_product;
	}
	public void setId(Integer id){
		this.id_product = id;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public String getName(){return this.name;}
	public void setName(String name){this.name = name;}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
        
	public double getPrice(){
		return this.price;
	}
        
	public void setPrice(Double nprice){
		this.price = nprice;
	}
        
    @Override
	public String toString() {
    	return "\nID Product: "+this.getId_product()+
				"\nName: "+ this.getName()+
    			"\nDescription: "+this.getDescription()+
    			"\nQuantity: "+this.getQuantity()+
    			"\nPrice: "+this.getPrice();
    }
}
