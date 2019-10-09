package service;

import model.PhoneBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/14/2017.
 */
public class PhoneBookService {
    Connection con = DatabaseConnection.getconnection();
    public boolean insertInfo(PhoneBook phnObj){
//        String message ="not yet";
        int a=0;
        String insert = "INSERT INTO phonebook (UserId, Name, PhoneNumber) " + " VALUES (?, ?, ?)";
        try{
            PreparedStatement stmt = con.prepareStatement(insert);
            stmt.setInt(1,phnObj.getUserId());
            stmt.setString(2,phnObj.getName());
            stmt.setString(3,phnObj.getPhoneNumber());
            a = stmt.executeUpdate();
//            message="method called";
        }catch(Exception e){
            e.printStackTrace();
        }
        if(a==0){
            return false;
        }else{
            return true;
        }
    }
    public void delete(int id){
        String delete = "delete from phonebook where id = ?";
//        String message="not yet";
        try{
            PreparedStatement stmt = con.prepareStatement(delete);
            stmt.setInt(1,id);
            stmt.executeUpdate();
//            message="inside";
        }catch (Exception e){
            e.printStackTrace();
        }
//        return message;
    }

    public void updatePhoneInfo(int id,PhoneBook phoneBookObj){
        String update = "update phonebook set Name=?, PhoneNumber =? where id = ?";
        System.out.println("Id in phonebook service ::"+id);
        try{
            PreparedStatement stmt = con.prepareStatement(update);
            stmt.setString(1,phoneBookObj.getName());
            stmt.setString(2,phoneBookObj.getPhoneNumber());
            stmt.setInt(3,id);
            int a = stmt.executeUpdate();
            System.out.println(a);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public PhoneBook getPhoneDataToEdit(int id){
        String select = "select Name,PhoneNumber,UserId from phonebook where id = ?";
        PhoneBook phoneBookObj = new PhoneBook();
        phoneBookObj.setId(id);
        try{
            PreparedStatement stmt = con.prepareStatement(select);
            stmt.setInt(1,id);
            ResultSet res = stmt.executeQuery();
            while (res.next()){

                phoneBookObj.setName(res.getString("Name"));
                phoneBookObj.setPhoneNumber(res.getString("PhoneNumber"));
                phoneBookObj.setUserId(res.getInt("UserId"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return phoneBookObj;
    }
    public List<PhoneBook> getDataUsingId(int id){
        String select="select Name,PhoneNumber,id from phonebook where UserId = ?";
        List<PhoneBook> phoneData = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement(select);
            stmt.setInt(1,id);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                PhoneBook phbObj = new PhoneBook();
                phbObj.setName(res.getString("Name"));
                phbObj.setPhoneNumber(res.getString("PhoneNumber"));
                phbObj.setId(res.getInt("id"));
                phoneData.add(phbObj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return phoneData;
    }
}
