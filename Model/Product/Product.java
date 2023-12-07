package Product;
import java.util.UUID;

public class Product {
	private Integer id;
	private String code;
	private String description;
	private Double price;
	private Integer quantity;
	
	public Product(Integer id, String description, Double price, Integer qty) {
		super();
		this.id = id;
		this.code = UUID.randomUUID().toString();
		this.description = description;
		this.price = price;
		this.quantity = qty;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getUUID() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Double getPrice() {
		return this.price;
	}
	
	public Integer getQuantity() {
		return this.quantity;
	}
	
	public void addItem(int qty) {
		this.quantity += qty;
	}
	
	public void removeItem(int qty) {
		if(this.quantity == 0) {
			throw new ArithmeticException("Field quantity cannot contain negative numbers.");
		}else {
			this.quantity -= qty;
		}
	}
	
	public void updateDescription(String newDesc) {
		this.description = newDesc;
	}
	
	public void updatePrice(double newprice) {
		this.price = newprice;
	}
}



