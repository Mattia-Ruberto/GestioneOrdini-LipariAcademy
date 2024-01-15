package com.lipari.gestioneordini.Controller.AddressController;
import com.lipari.gestioneordini.Controller.Connector.Connector;
import com.lipari.gestioneordini.Model.Address.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AddressController {
    private static final String ins_query = "Insert into GestioneOrdini.Address(street, number, city, cap, userid)" +
            "values(?,?,?,?,?)";
    private static final String get_query = "Select * from GestioneOrdini.Address where userid = ?";
    public static void addAddress(String street, Integer number, String city, String CAP, Integer userID){
        try(Connection conn = Connector.connect()){
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(ins_query);
            stmt.setString(1,street);
            stmt.setInt(2,number);
            stmt.setString(3,city);
            stmt.setString(4,CAP);
            stmt.setInt(5,userID);
            stmt.execute();
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static ArrayList<Address> getAddress(Integer userID){
        ArrayList<Address> list = new ArrayList<Address>();
        try(Connection conn = Connector.connect()){
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(get_query);
            stmt.setInt(1,userID);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Address address = new Address();
                address.setStreet(res.getString("street"));
                address.setNumber(res.getInt("number"));
                address.setCity(res.getString("city"));
                address.setCAP(res.getString("cap"));
                address.setUserID(res.getInt("userid"));
                list.add(address);
            }
            conn.close();
            res.close();

        }catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
}
