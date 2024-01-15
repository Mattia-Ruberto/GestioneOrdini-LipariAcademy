package com.lipari.gestioneordini.Controller.Connector;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
    public static Connection connect(){
        Connection conn = null;
        try{
             conn = DriverManager.getConnection(
                     "jdbc:mysql://localhost:3306/GestioneOrdini",
                    "admin","TestPassword"
             );
            return conn;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }

    }
}
