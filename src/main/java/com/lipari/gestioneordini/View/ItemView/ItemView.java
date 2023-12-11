/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lipari.gestioneordini.View.ItemView;
import java.util.HashMap;
import com.lipari.gestioneordini.Model.Item.Item;

/**
 *
 * @author User
 */
public class ItemView {
    public void printItems(HashMap<Integer, Item> items){
        for(Item i : items.values()){
                                    System.out.println("User ID: " + i.getId() + "\nProduct ID: " + i.getId_product() + "\nDescription: " + i.getDescription() + "\nQuantity: " + i.getQuantity().toString() + "\n");
                                }
    }
}
