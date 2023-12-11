package com.lipari.gestioneordini.Model.Item;
public class Item {
	private Integer id;
	private Integer id_product;
    	private String description;
	private Integer quantity;
        private Double price;

	
	public Item(Integer id, Integer id_product,String description, Integer quantity, Double price) {
		super();
		this.id = id;
                this.description = description;
		this.id_product = id_product;
		this.quantity = quantity;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_product() {
		return id_product;
	}

	public void setId_product(Integer id_product) {
		this.id_product = id_product;
	}
        
        public String getDescription(){
            return this.description;
        }
        
        public void setDescription(String description){
            this.description = description;
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
        
    public String toString() {
    	return "ID Product: "+this.getId_product()+
    			"\nDescription: "+this.getDescription()+
    			"\nQuantity: "+this.getQuantity()+
    			"\nPrice: "+this.getPrice();
    			
    }
	
	
	
	

}
