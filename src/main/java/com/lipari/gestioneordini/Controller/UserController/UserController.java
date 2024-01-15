package com.lipari.gestioneordini.Controller.UserController;
import com.lipari.gestioneordini.Controller.Connector.Connector;
import com.lipari.gestioneordini.Model.User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserController {
    private final static String ins_query = "Insert into GestioneOrdini.User(name, surname, username, email, password, role) " +
            "VALUES(?, ?, ?, ?, ?, ?)";
    private final static String val_query = "Select password,id from GestioneOrdini.User where username = ?";
    private static Integer result = null;
    private static final User user = new User();
    public static User getUser(Integer user_id) {
        try (Connection conn = Connector.connect()) {
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement("Select * from GestioneOrdini.User where id = ?");
            stmt.setInt(1, user_id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                user.setName(res.getString("name"));
                user.setSurname(res.getString("surname"));
                user.setUsername(res.getString("username"));
                user.setEmail(res.getString("email"));
                user.setPassword(res.getString("password"));
                user.setId_role(res.getInt("role"));
                conn.close();
                res.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }
    public static void Register(User user){
        try(Connection conn = Connector.connect()){
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(ins_query);
            //Bind Parameters
            stmt.setString(1,user.getName());
            stmt.setString(2,user.getSurname());
            stmt.setString(3,user.getUsername());
            stmt.setString(4,user.getEmail());
            stmt.setString(5,user.getPassword());
            stmt.setInt(6,user.getId_role());
            //Execute Prepared Statement
            stmt.execute();
            conn.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static int validateCredentials(String username, String password){
        try(Connection conn = Connector.connect()){
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(val_query);
            stmt.setString(1,username);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                if(res.getString("password").compareTo(password) == 0){
                    result = res.getInt("id");
                }
            }
            res.close();
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }
}
