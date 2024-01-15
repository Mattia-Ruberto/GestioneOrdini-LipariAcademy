package com.lipari.gestioneordini;

import java.sql.ResultSet;
import java.text.Collator;
import java.util.*;
import java.util.random.*;

import com.lipari.gestioneordini.Controller.AddressController.AddressController;
import com.lipari.gestioneordini.Controller.ItemController.ItemController;
import com.lipari.gestioneordini.Controller.OrderController.OrderController;
import com.lipari.gestioneordini.Controller.UserController.UserController;
import com.lipari.gestioneordini.Controller.Validator.Validator;
import com.lipari.gestioneordini.Model.Address.Address;
import com.lipari.gestioneordini.Model.User.User;
import com.lipari.gestioneordini.Model.Item.Item;
import com.lipari.gestioneordini.Model.Order.Order;

public class GestioneOrdini {
    private static Integer current_user = null;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean logged = false;
        Integer user_id = null;

        while (!logged) {
            System.out.println("Welcome,\nPlease choose an action to perform between:\n"
                    + "1)Register\n2)Login\n3)Quit\nInput: ");
            String command = scanner.nextLine();
            switch (command) {
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

                    while (!check_pwd) {
                        System.out.println("\nPassword: ");
                        password = scanner.nextLine();
                        System.out.println("\nConfirm Password: ");
                        conf_pwd = scanner.nextLine();
                        if (password.equals(conf_pwd) && Validator.isPassword(password)) {
                            check_pwd = true;
                        }
                    }

                    while (!check_email) {
                        System.out.println("\nEmail: ");
                        email = scanner.nextLine();
                        if (Validator.isEmail(email)) {
                            check_email = true;
                        }
                    }
                    //Creazione di un nuovo utente.
                    User new_user = new User(name, surname, username, password, email, 0);
                    UserController.Register(new_user);

                    break;
                }
                case "2" -> {
                    while (!logged) {
                        System.out.println("Username: ");
                        String username = scanner.nextLine();
                        System.out.println("Password: ");
                        String password = scanner.nextLine();
                        current_user = UserController.validateCredentials(username, password);
                        logged = true;
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
            User user = UserController.getUser(current_user);
            System.out.println("You are currently logged in as: " + user.getUsername());
            while (logged) {
                System.out.println("Choose between the following operation.\n1)Add Item\n2)List Items\n3)New Order\n4)Show Most Ordered Item\n5)Show Least Ordered Item\n6)Logout\nYour choise: ");
                String command = scanner.nextLine();
                switch (command) {
                    case "1" -> {
                        System.out.println("Type a name for the item: ");
                        String name = scanner.nextLine();
                        System.out.println("Type a description for the item: ");
                        String description = scanner.nextLine();
                        System.out.println("Enter how much item to add: ");
                        Integer qty = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter the price: ");
                        Double price = Double.parseDouble(scanner.nextLine());
                        ItemController.addItem(name,description,qty,price);
                    }
                    case "2" -> {
                        ItemController.getAllItems().forEach(item -> {
                            System.out.println(item.toString());
                        });
                    }
                    case "3" -> {
                        boolean done = false;
                        ArrayList<Item> list = new ArrayList<>();
                        ItemController.getAllItems().forEach(item -> {
                            System.out.println(item.toString());
                        });
                        while (!done) {
                            System.out.println("Type the ID of the item to add: ");
                            String id = scanner.nextLine();
                            list.add(ItemController.getItemInfo(Integer.parseInt(id)));
                            System.out.println("Want to add another item? (y/n): ");
                            String res = scanner.nextLine();
                            if (res.equals("n")) {
                                done = true;
                            }

                        }
                        //Remove the item from Stock. Only remove 1 item.
                        for (Item item : list) {
                            ItemController.rmvFromStock(item.getId_product());
                        }
                        OrderController.createOrder(current_user, list, AddressController.getAddress(current_user).get(0));
                        System.out.println("Your order has bees successfully created.\n");
                    }
                    case "4" -> {
                        System.out.println(ItemController.getMostSoldItem());
                    }
                    case "5" -> {
                        System.out.println(ItemController.getLestSoldItem());
                    }
                    case "6" -> {
                        logged = false;
                        current_user = null;
                        System.out.println("Logout successfully!");
                    }
                    default -> System.out.println("Invalid command typed. Please try again.\n");
                }
                //Aggiungi un nuovo prodotto
                //Elimina un prodotto
                //Crea un nuovo ordine
                //Elimina un ordine
                //Visualizza gli ordini effettuati dal cliente
                //LOGOUT
            }
        }
        System.gc();
    }
}