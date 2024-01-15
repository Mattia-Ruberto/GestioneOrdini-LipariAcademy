package com.lipari.gestioneordini.Controller.OrderController;
import com.lipari.gestioneordini.Controller.Connector.Connector;
import com.lipari.gestioneordini.Model.Address.Address;
import com.lipari.gestioneordini.Model.Item.Item;
import com.lipari.gestioneordini.Model.Order.Order;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private static final String ins_query = "Insert into GestioneOrdini.Order(uuid, date_order, address, u_id, itemid)" +
            "values(?,?,?,?,?)";
    public static void createOrder(Integer userID, ArrayList<Item> items, Address address){
        Order order = new Order();
        String uuid = order.generateUUID();
        Date date = Date.valueOf(LocalDate.now());
        items.forEach(item -> {
            try(Connection conn = Connector.connect()){
                assert conn != null;
                PreparedStatement stmt = conn.prepareStatement(ins_query);
                stmt.setString(1,uuid);
                stmt.setDate(2,date);
                stmt.setString(3,address.PrintFullAddress());
                stmt.setInt(4,userID);
                stmt.setInt(5,item.getId_product());
                stmt.execute();
                conn.close();
            }catch(Exception e){
                System.out.println(e);
            }

        });
    }
    public static List<Order> getOrdersbyUUID(String UUID){
        final String sel_query = "Select * from GestioneOrdini.Order where UUID = ";
        List<Order> list = new ArrayList<Order>();
        try(Connection conn = Connector.connect()){
            assert conn != null;
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sel_query + UUID);
            while(res.next()){
                Order order = new Order();
                order.setDate_order(res.getDate("date_order"));
                order.setAddress(res.getString("address"));
                order.setId_user(res.getInt("u_id"));
                order.setUUID(UUID);
                order.setItemID(res.getInt("itemid"));
                list.add(order);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
    public static List<Order> getAllOrders(){
        final String sel_query = "Select * from GestioneOrdini.Order";
        List<Order> list = new ArrayList<Order>();
        try(Connection conn = Connector.connect()){
            assert conn != null;
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sel_query);
            while(res.next()){
                Order order = new Order();
                order.setDate_order(res.getDate("date_order"));
                order.setAddress(res.getString("address"));
                order.setId_user(res.getInt("u_id"));
                order.setUUID(res.getString("uuid"));
                order.setItemID(res.getInt("itemid"));
                list.add(order);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return list;
    }

    public static String FullOrder(Integer userID){
        String order = "";
        final String query = "SELECT uuid, date_order, address, u_id, Item.name, Item.description, Item.price From GestioneOrdini.Order o INNER JOIN Item ON o.itemid = Item.idItem and o.u_id = " + userID.toString();
        try(Connection conn = Connector.connect()){
            assert conn != null;
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()) {
                order += "\nUUID: " + res.getString("uuid") + "\nDate: " + res.getDate("date_order").toString() + "\nShipment Address: " + res.getString("address") +
                        "\nProduct Name: " + res.getString("name") + "\nProduct Description" + res.getString("description") + "\nPrice: " + res.getString("price");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return order;
    }
}
