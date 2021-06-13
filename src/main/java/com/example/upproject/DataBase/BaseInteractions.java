package com.example.upproject.DataBase;

import com.example.upproject.Config.MainConfig;
import com.example.upproject.User.Position;
import com.example.upproject.User.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Component
public class BaseInteractions {


    public void addData(String data, String table){
        //data=(0, "76000")
        //table=users(role_id , name)
        try{
            //String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";
            //String username = "root";
            //String password = "password";
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = this.getConnector().connect()){
                Statement statement = connection.createStatement();
                int rows = statement.executeUpdate("INSERT INTO "+ table +" VALUES "+ data +"" );
                System.out.printf("Added %d rows", rows);
                this.getConnector().closeConnection();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public void updataData(User user){
        //nameData=Price - 5000
        //nameRow=Price
        try{
            //String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";
            //String username = "root";
            //String password = "password";
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = this.getConnector().connect()){
                Statement statement = conn.createStatement();
                int rows = statement.executeUpdate("UPDATE users SET " + "name" + " = '"+ user.getName() + "' WHERE Id = "+user.id);
                int rows2 = statement.executeUpdate("UPDATE users SET " + "role_id" + " = '"+ user.getPosition().ordinal() + "' WHERE Id = "+user.id);


                System.out.printf("Updated %d rows", rows);
                this.getConnector().closeConnection();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public void deleteData(int id){
        try{
            //String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";
            // String username = "root";
            //String password = "password";
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = this.getConnector().connect()){

                Statement statement = conn.createStatement();

                int rows = statement.executeUpdate("DELETE FROM users WHERE Id = "+id);
                System.out.printf("%d row(s) deleted", rows);
                this.getConnector().closeConnection();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
    public List<User> getUsers() throws SQLException {
        List<User> data = new ArrayList<>();
        Connection connection = this.getConnector().connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        while( resultSet.next()){
            data.add(new User(resultSet.getString(3)  ,parseInt(resultSet.getString(1)),Position.valueOf(getPosition(parseInt(resultSet.getString(2))))));
        }

        return data;
    };

    public String getPosition(Integer key){
        Map<Integer, String> collect = Arrays.stream(Position.values()).collect(Collectors.toMap(
                x -> x.ordinal(),
                x -> x.name()
        ));
        return collect.get(key);
    }


    public BaseConnection getConnector(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        BaseConnection connection = context.getBean("connection" , BaseConnection.class);
        context.close();
        return connection;
    }



}
