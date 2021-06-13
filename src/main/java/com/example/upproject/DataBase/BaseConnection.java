package com.example.upproject.DataBase;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
@Component
public class BaseConnection {
    static Connection connection = null;
    static boolean connected = false;

    public static Connection connect(){

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-79-178.eu-west-1.compute.amazonaws.com:5432/d4am615tqn7fq3","ugrhebsleflarf","b1a58307a65281150d163559af0d8b3ede580b24952c424cbb738d5d48778699");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        connected = true;
        return connection;
    }
    public static void closeConnection() throws SQLException {
        if(connected){
            connection.close();
            connected = false;
        }

    }
}
