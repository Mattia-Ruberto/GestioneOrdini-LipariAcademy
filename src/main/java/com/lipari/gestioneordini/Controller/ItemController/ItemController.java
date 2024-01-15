package com.lipari.gestioneordini.Controller.ItemController;

import com.lipari.gestioneordini.Controller.Connector.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.lipari.gestioneordini.Model.Item.Item;


public class ItemController {
    private static final String ins_query = "Insert into GestioneOrdini.Item( name, description, qty, price)" +
            "values(?,?,?,?)";

    private static final String get_query = "Select * from GestioneOrdini.Item where idItem = ?";
    private static final Item item = null;
    private static Integer getLastItem(){
        String g_query = "Select idItem from Item order by idItem desc";
        Integer res = 0;
        try(Connection conn = Connector.connect()){
            assert conn != null;
            PreparedStatement s = conn.prepareStatement(g_query);
            ResultSet r = s.executeQuery();
            r.next();
            res = r.getInt("idItem");
        }catch (Exception e){
            System.out.println(e);
        }
        return res;
    }
    public static void addItem(String name, String description, Integer qty, Double price){
        String g_query = "Select idItem from Item order by idItem desc";
        String i_query = "Insert into GestioneOrdini.OrderedItems(ItemID, qty) values(?,?)";
        try(Connection conn = Connector.connect()){
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(ins_query);
            stmt.setString(1,name);
            stmt.setString(2,description);
            stmt.setInt(3,qty);
            stmt.setDouble(4,price);
            stmt.execute();
            stmt.close();
            //ADD ITEM TO ITEM TABLE
            Integer id = getLastItem();
            PreparedStatement s = conn.prepareStatement(i_query);
            s.setInt(1,id);
            s.setInt(2,0);
            s.execute();
            conn.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static ArrayList<Item> getAllItems(){
        ArrayList<Item> list = new ArrayList<Item>();
        try(Connection conn = Connector.connect()){
            assert conn != null;
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("Select idItem,name,description,qty,price from GestioneOrdini.Item");
            while(res.next()){
                Item item = new Item();
                item.setId(res.getInt("idItem"));
                item.setName(res.getString("name"));
                item.setDescription(res.getString("description"));
                item.setQuantity(res.getInt("qty"));
                item.setPrice(res.getDouble("price"));
                list.add(item);
            }
            conn.close();
            res.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
    public static Item getItemInfo(Integer item_id){
        String query = "Select * from GestioneOrdini.Item where idItem = "+item_id.toString();
        Item item = new Item();
        try(Connection conn = Connector.connect()){
            assert conn != null;
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                item.setId(res.getInt("idItem"));
                item.setName(res.getString("name"));
                item.setDescription(res.getString("description"));
                item.setQuantity(res.getInt("qty"));
                item.setPrice(res.getDouble("price"));
            }
            conn.close();
            res.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return item;
    }
    public static void rmvFromStock(Integer item_id){
        String item_query = "Update Item set qty = qty-1 where idItem = " + item_id.toString();
        String ordereditem_query = "Update OrderedItems set qty = qty+1 where ItemID = " + item_id;
        try(Connection conn = Connector.connect()){
            assert conn != null;
            conn.prepareStatement(item_query).execute();
            conn.prepareStatement(ordereditem_query).execute();
            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static String getMostSoldItem(){
        String g_query = "Select ItemID,qty from OrderedItems order by qty desc limit 1";
        String res = "";
        try(Connection conn = Connector.connect()){
            assert conn != null;
            ResultSet r = conn.prepareStatement(g_query).executeQuery();
            r.next();
            Integer id = r.getInt("ItemID");
            res = getItemInfo(id).toString() +"\nQuantity Sold: " + r.getString("qty");
            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return res;
    }

    public static String getLestSoldItem(){
        String g_query = "Select ItemID,qty from OrderedItems order by qty asc limit 1";
        String res = "";
        try(Connection conn = Connector.connect()){
            assert conn != null;
            ResultSet r = conn.prepareStatement(g_query).executeQuery();
            r.next();
            Integer id = r.getInt("ItemID");
            res = getItemInfo(id).toString() +"\nQuantity Sold: " + r.getString("qty");
            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return res;
    }

}
