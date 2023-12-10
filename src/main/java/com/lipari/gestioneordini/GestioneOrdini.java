package com.lipari.gestioneordini;
import java.util.*;
import com.lipari.gestioneordini.Controller.Validator.Validator;
import com.lipari.gestioneordini.Model.User.User;
import com.lipari.gestioneordini.Model.Item.Item;
import com.lipari.gestioneordini.Model.Order.Order;

public class GestioneOrdini {
    private static Integer id_count = 0;
    private static Integer id_count_product = 0;
    private static Integer id_count_order = 0;
    
    private static HashMap<Integer, User> users = new HashMap<>();
    private static HashMap<Integer, Item> items = new HashMap<>();
    private static HashMap<Integer, Order> orders = new HashMap<>();
    private static HashMap<Integer, String> address_list = new HashMap<>();
    
    static User admin1 = new User(++id_count,"Mattia","Ruberto","m.ruberto","?TestPassword?","mattia.ruberto@liparipeople.com",1);
    static User admin2 = new User(++id_count,"Vito","Bica","v.bica","!TestPassword!","vito.bica@liparipeople.com",1);
    
    static Item item1 = new Item(++id_count_product,id_count_product,"Prodotto 1",3,15.00);
    static Item item2 = new Item(++id_count_product,id_count_product,"Prodotto 3",5,20.00);
    // Per logica booleana ho messo 1 per indicare un account amministratore. L'id_count è pre-incrementato per
    // permettere al programma di gestire id incrementali in runtime come farebbe la chiave primaria di un DBMS con auto-increment.
    // E' stato scelto il pre-incremento per consentire ad un account di tipo admin di visualizzare il numero di utenti registrati in piattaforma.
    // E' possibile farlo anche con il post-incremento ricordandosi però di diminuire il conteggio finale di 1 in quanto ad ogni nuova registrazione
    // l'id_count viene incrementato preventivamente e non rappresenta il numero reale di utenti registrati.
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean logged = false;
        Integer user_id = null;
        
        users.put(1, admin1);
        users.put(2,admin2);
        
        items.put(item1.getId(), item1);
        items.put(item2.getId(), item2);
        
        while(!logged){
            System.out.println("Welcome,\nPlease choose an action to perform between:\n"
                    + "1)Register\n2)Login\n3)Quit\nInput: ");
            String command = scanner.nextLine();
            switch(command){
                case "1" -> {
                    boolean check_pwd = false; //Per ciclare l'inserimento della password finchè non combaciano.
                    boolean check_email = false; //Per validare l'email.
                    //Inizializzazione variabili
                   
                    String name = "";
                    String surname = "";
                    String username = "";
                    String password = "";
                    String conf_pwd = "";
                    String email = "";
                    
                    System.out.println("To register please compile the following form.\nName: ");
                    name = scanner.nextLine();
                    System.out.println("\nSurname: ");
                    surname = scanner.nextLine();
                    System.out.println("\nUsername: ");
                    username = scanner.nextLine();
                    
                    while(!check_pwd){
                        System.out.println("\nPassword: ");
                        password = scanner.nextLine();
                        System.out.println("\nConfirm Password: ");
                        conf_pwd = scanner.nextLine();
                        if(password.equals(conf_pwd) && Validator.isPassword(password)){
                            check_pwd = true;
                        }
                    }
                    
                    while(!check_email){
                        System.out.println("\nEmail: ");
                        email = scanner.nextLine();
                        if(Validator.isEmail(email)){
                            check_email = true;
                        }
                    }
                    //Creazione di un nuovo utente.
                    User new_user = new User(++id_count,name,surname,username,password,email,0);
                    //Aggiunta dell'utente alla lista degli utenti registrati.
                    users.put(id_count,new_user);
                    System.out.println("\nUser added succesfully.");
                    break;
                }
                case "2" -> {
                    while(!logged){
                        System.out.println("Username: ");
                        String username = scanner.nextLine();
                        System.out.println("Password: ");
                        String password = scanner.nextLine();
                        for(User user : users.values()){
                            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                                    user_id = user.getId();
                                    logged = true;
                                }
                                break;
                            }
                        }
                    }
                case "3" -> {
                    System.out.println("Goodbye, program will now close.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid command entered. Please try again.");
                    
                
                  
            }
        }
        if (logged) {
        	User current_user = users.get(user_id);
        	Integer count_order_user;
	        while(logged){
	            System.out.println("\nYou're logged in as: " + current_user.getUsername()+ ".\nChoose an action to perform:\n1)Add Item\n2)Remove Item\n3)New Order\n4)Cancel Order\n5)History of Orders\n6)LogOut\nInput:");
	            String command = scanner.nextLine();
	            switch(command){
	            	//Aggiungi un nuovo prodotto
	                case "1":
	                    System.out.println("Type a description for the item: ");
	                    String description = scanner.nextLine();
	                    System.out.println("Enter how much item to add: ");
	                    Integer qty = scanner.nextInt();
	                    System.out.println("Enter the price: ");
	                    Double price = scanner.nextDouble();
	                    scanner.nextLine();
	                    Item item = new Item(user_id,++id_count_product,description,qty,price);
	                    items.put(id_count_product, item);
	                    break;
	                    
	                //Elimina un prodotto    
	                case "2":
	                    boolean removed = false;
	                    while(!removed){
	                        for(Item i : items.values()){
	                            System.out.println("User ID: " + i.getId() + "\nProduct ID: " + i.getId_product() + "\nDescription: " + i.getDescription() + "\nQuantity: " + i.getQuantity().toString());
	                        }
	                        System.out.println("Choose the product to remove by typing its Product ID: ");
	                        Integer id = scanner.nextInt();
	                        scanner.nextLine();
	                        if(items.containsKey(id)){
	                            items.remove(id);
	                            removed = true;
	                            System.out.println("Item removed succesfully.");
	                        }else{
	                            System.out.println("Invalid Product ID. Try again.\n");
	                        }
	                    }
	                    break;
	                    
	                //Crea un nuovo ordine    
	                case "3":
	                	Map<Integer,Item> cart = new HashMap<>();
	                	String address = null;
	                	Boolean isComplete = false; //Per permettere l'inserimento di più prodotti all'interno dell'ordine
	                	while(!isComplete) {
	                		
	                	
	                	
		                	System.out.println("STORAGE Product list:");
		                	for (Item i : items.values()) {
		                		System.out.println("\n"+i.toString());
		                	}
		                	System.out.println("\nChoose the product to add in the cart by typing its Product ID: ");
		                	Integer id = scanner.nextInt();
		                	scanner.nextLine();
		                	
		                	// Verifica se l'ID inserito è presente nella lista dei prodotti
		                	if(items.containsKey(id)){
		                		Item current_item = items.get(id);
		                		System.out.println("Choose the quantity to add: ");
		                		qty = scanner.nextInt();
		                		scanner.nextLine();
		                		//Verifica se il magazzino contiene la quantità richiesta per il prodotto
		                		if (current_item.getQuantity()>=qty) {
		                			if (cart.containsKey(id)) {
		                				cart.get(id).setQuantity(cart.get(id).getQuantity()+qty);
		                			}
		                			else {
			                			Item cart_item = new Item(current_item.getId(),current_item.getId_product(),current_item.getDescription(),qty,current_item.getPrice());
			                			cart.put(id, cart_item);
		                			}
		                			current_item.setQuantity(current_item.getQuantity()-qty);
		                		}
		                		else {
		                			System.out.println("Error: The quantity available for the product is: "+current_item.getQuantity());
		                		}
		                	}
		                	// Visualizza prodotti nel carrello
		                	System.out.println("CART:\n");
		                	for (Item i : cart.values()) {
		                		System.out.println(i.toString());
		                	}
		                	
		                	System.out.println("\n1- Add another product to your cart\n2- Complete the order\nInput: ");
		                	String command2 = scanner.nextLine();
		                	switch(command2) {
			                	case "2":
			                		System.out.println("Select the address from those available or enter a new one: ");
			                		System.out.println("0 - Enter a new address");
			                		//Visualizza indirizzi usati in precedenza dall'utente
			                		Integer id_count_address = 0;
			                		for (Order o : orders.values()) {
			                			if (o.getId_user()==current_user.getId()) {
			                				address_list.put(++id_count_address,o.getAddress());
			                				System.out.println(id_count_address+" - "+o.getAddress());
			                			}
			                		}
			                		String address_command = scanner.nextLine();
			                		if (address_command.equals("0")){
			                			System.out.println("Enter the new address:\nInput: ");
		                				address = scanner.nextLine();
		                				address_list.put(id_count_address, address);
			                		}
			                		else if (address_list.containsKey(Integer.parseInt(address_command))){
			                			address = address_list.get(Integer.parseInt(address_command));
			                		}
			                		else {
			                			System.out.println("Invalid command!");
			                		}
			                		
			                		isComplete=true;
			                		orders.put(++id_count_order, new Order(current_user.getId(), address, cart));
			                		System.out.println(orders.get(id_count_order).toString());
			                		System.out.println("Order placed successfully!");
			                		break;
			                }
	                	}
	                	break;
	                	
	                //Elimina un ordine
	                case "4":
	                	count_order_user = 0;
	                	
	                	for (Order o : orders.values()) {
	                		if (o.getId_user()==current_user.getId()){
	                			count_order_user++;
	                			System.out.println(o.toString());
	                		}
	                	}
	                	
	                	if (count_order_user==0) {
	                		System.out.println("No orders were found");
	                	}
	                	else {
	                		System.out.println("Choose the order to cancel\nInput: ");
	                		String remove_order = scanner.nextLine();
	                		if (orders.containsKey(Integer.parseInt(remove_order))){
	                			orders.remove(Integer.parseInt(remove_order));
	                			System.out.println("Order canceled successfully!");
	                		}
	                		else {
	                			System.out.println("Invalid order ID!");
	                		}
	                		
	                	}
	                	break;
	                //Visualizza gli ordini effettuati dal cliente	
	                case "5":
	                	count_order_user = 0;
	                	for (Order o : orders.values()) {
	                		if (o.getId_user()==current_user.getId()){
	                			count_order_user++;
	                			System.out.println(o.toString());
	                		}
	                	}
	                	if (count_order_user==0) {
	                		System.out.println("No orders were found");
	                	}
	                	break;
	                	
	                	
	                	
	                //LOGOUT	
	                case "6":
	                    logged = false;
	                    user_id = null;
	                    System.out.println("Logout successfully!");
	                    break;
	                default:
	                    System.out.println("Invalid command typed. Please try again.\n");
	                    break;
	            }
	        }
        }
    }
}