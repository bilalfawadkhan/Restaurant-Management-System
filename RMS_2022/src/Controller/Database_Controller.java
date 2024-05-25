package Controller;

import javafx.scene.control.TextField;

import java.sql.*;

public class Database_Controller {
    public static Connection Databaselink;


   public static Connection getConnection() {
        String databaseName = "bwlana3jrca5fzranlwf";
        String databaseUser = "uv4zrwmhwqp8y7t4";
        String databasePassword = "zQeXP8BezSp9ldYdO8du";
        String url = "jdbc:mysql://uv4zrwmhwqp8y7t4:zQeXP8BezSp9ldYdO8du@bwlana3jrca5fzranlwf-mysql.services.clever-cloud.com:3306/" +  databaseName;
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Databaselink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        System.out.println("Connected");
        return Databaselink;

    }
    public static void closeConnection() throws SQLException {
       try {
           if(Databaselink != null && !Databaselink.isClosed()){
               Databaselink.close();
               System.out.println("Connection Closed");
           }
       }
       catch (Exception e) {
           throw e;
       }
    }
}
