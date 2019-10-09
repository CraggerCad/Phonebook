package service;

import model.User;

import javax.swing.text.PasswordView;
import javax.swing.text.html.HTMLDocument;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/14/2017.
 */
public class UserService {
    private Connection con = DatabaseConnection.getconnection();
    private int id;
    public void insert(User userInfo){
        String insert = "insert into user "+"(Username,Password) VALUES "+"(?,?)";
        try{
            PreparedStatement stmt = con.prepareStatement(insert);
            stmt.setString(1,userInfo.getUsernName());
            stmt.setString(2,userInfo.getPassword());
            stmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public int checkUser(String checkName,String password){
        User userObj = new User();
        String checkUser = "select id from user where Username=? and Password=?";
        try{
            PreparedStatement stmt = con.prepareStatement(checkUser);
            stmt.setString(1,checkName);
            stmt.setString(2,password);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                id=res.getInt("id");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }
}
