package com.lipari.gestioneordini;
import java.util.*;
import com.lipari.gestioneordini.Controller.Validator.Validator;
import com.lipari.gestioneordini.Model.User.User;
import com.lipari.gestioneordini.Model.Item.Item;

public class GestioneOrdini {
    private static Integer id_count = 0;
    private static Integer id_count_product = 0;
    
    private static HashMap<Integer, User> users = new HashMap<>();
    private static HashMap<Integer, Item> items = new HashMap<>();
    
    static User admin1 = new User(++id_count,"Mattia","Ruberto","m.ruberto","?TestPassword?","mattia.ruberto@liparipeople.com",1);
    static User admin2 = new User(++id_count,"Vito","Bica","v.bica","!TestPassword!","vito.bica@liparipeople.com",1);
    
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
        while(logged){
            User current_user = users.get(user_id);
            System.out.println("You're logged in as: " + current_user.getUsername()+ ".\nChoose an action to perform:\n1)Add Item\n2)Remove Item\n3)New Order\n4)Cancel Order\n5)History of Orders\n6)LogOut\nInput:");
            String command = scanner.nextLine();
            switch(command){
                case "1":
                    System.out.println("Type a description for the item: ");
                    String description = scanner.nextLine();
                    System.out.println("Enter how much item to add: ");
                    Integer qty = scanner.nextInt();
                    Item item = new Item(user_id,++id_count_product,description,qty);
                    items.put(id_count_product, item);
                    break;
                case "2":
                    boolean removed = false;
                    while(!removed){
                        for(Item i : items.values()){
                            System.out.println("User ID: " + i.getId() + "\nProduct ID: " + i.getId_product() + "\nDescription: " + i.getDescription() + "\nQuantity: " + i.getQuantity().toString());
                        }
                        System.out.println("Choose the product to remove by typing its Product ID: ");
                        Integer id = scanner.nextInt();
                        if(items.containsKey(id)){
                            items.remove(id);
                            removed = true;
                            System.out.println("Item removed succesfully.");
                        }else{
                            System.out.println("Invalid Product ID. Try again.\n");
                        }
                    }
                case "6":
                    logged = false;
                    user_id = null;
                    break;
                default:
                    System.out.println("Invalid command typed. Please try again.\n");
                    break;
            }
        }
    }
}
