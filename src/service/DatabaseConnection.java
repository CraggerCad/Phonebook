package service;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by User on 12/15/2017.
 */
public class DatabaseConnection {
    public static Connection getconnection(){

        String url = "jdbc:mysql://Localhost:3306/phonebook";
        String userName = "root";
        String password = "";
        Connection con = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,userName,password);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
