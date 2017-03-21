/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.implement;

import dlelang.Utility.DatabaseConnection;
import dlelang.interfaces.UserInterface;
import dlelang.model.Barang;
import dlelang.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ultrabook
 */
public class UserImplement implements UserInterface {
    DatabaseConnection conn;
    @Override
    public User login(String username, String password) {
        User user = null;
        PreparedStatement preparedStatement;
        conn = new DatabaseConnection();
        try {
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            preparedStatement = conn.connect().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));           
            }
        } catch (Exception e){
            System.out.println(e);
            Logger.getLogger(UserImplement.class.getName()).log(Level.SEVERE, null, e);
        }
        return user;
    }
    @Override
    public void insertUser(User user){
        PreparedStatement ps;
        try {
            ps = conn.connect().prepareStatement("insert into user values(?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getNama());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getLevelAkses());
            ps.execute();
        } catch (Exception e) {
            Logger.getLogger(UserImplement.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @Override
    public void updateUser(User user){
        PreparedStatement ps;
        try{
            ps = conn.connect().prepareStatement("update user set username = ?, nama = ?, password = ?, hak_akses = ? where username = ?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getNama());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getLevelAkses());
            
            ps.setString(5, user.getUsername());
        }catch(Exception e){
            Logger.getLogger(UserImplement.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @Override
    public void deleteUser(String username){
        PreparedStatement ps;
        try {
            ps = conn.connect().prepareStatement("Detele from user where username = ?");
            ps.setString(1, username);
        } catch (Exception e) {
            Logger.getLogger(UserImplement.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public ObservableList<User> get() {
        conn = new DatabaseConnection();
        ObservableList listData = FXCollections.observableArrayList();
        try {
            String sql = "Select * From user";
            ResultSet rs = conn.connect().createStatement().executeQuery(sql);
            while (rs.next()) {
                User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                listData.add(user);
            }
        } catch (Exception e) {
            Logger.getLogger(BarangImplement.class.getName()).log(Level.SEVERE, null, e);
        }
        return listData;
    }

    public User getUser(String username){
        User user = null;
        PreparedStatement preparedStatement;

        conn = new DatabaseConnection();
        try {
            String query = "SELECT * FROM user WHERE username = ?";
            preparedStatement = conn.connect().prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (Exception e){
            System.out.println(e);
            Logger.getLogger(UserImplement.class.getName()).log(Level.SEVERE, null, e);
        }
        return  user;
    }

}
