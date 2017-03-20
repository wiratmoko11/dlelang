/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.implement;

import dlelang.Utility.DatabaseConnection;
import dlelang.interfaces.BarangInterface;
import dlelang.model.Barang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ultrabook
 */
public class BarangImplement implements BarangInterface {
    DatabaseConnection conn;
    @Override
    public void insertBarang(Barang barang) {
        conn = new DatabaseConnection();
        PreparedStatement ps;
        try {
            ps = conn.connect().prepareStatement("insert into barang(nama_barang, gambar_barang, harga_awal, tenggat_waktu, deskripsi) values(?,?,?,?,?)");
            ps.setString(1, barang.getNamaBarang());
            ps.setString(2, barang.getGambarBarang());
            ps.setInt(3, barang.getHargaAwal());
            ps.setString(4, barang.getTenggatWaktu());
            ps.setString(5, barang.getDeskripsi());
            ps.execute();
        } catch (Exception e) {
            Logger.getLogger(UserImplement.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void updateBarang(Barang barang, int id) {
        PreparedStatement ps;
        try{
            ps = conn.connect().prepareStatement("update barang set nama_barang = ?, gambar_barang = ?, harga_awal = ?, tenggat_waktu = ?, deskripsi = ? where id_barang = ?");
            ps.setString(1, barang.getGambarBarang());
            ps.setString(2, barang.getGambarBarang());
            ps.setInt(3, barang.getHargaAwal());
            ps.setString(4, barang.getTenggatWaktu());
            ps.setString(5, barang.getDeskripsi());
            
            ps.setInt(6, id);
        }catch(Exception e){
            Logger.getLogger(UserImplement.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void deleteBarang(int id) {
        PreparedStatement ps;
        try {
            ps = conn.connect().prepareStatement("Detele from barang where id_barang = ?");
            ps.setInt(1, id);
        } catch (Exception e) {
            Logger.getLogger(UserImplement.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public Barang getBarangById(int id) {
        Barang barang = null;
        conn = new DatabaseConnection();
       try {
           String sql = "select * from barang where id_barang = ?";
           ResultSet rs = conn.connect().createStatement().executeQuery(sql);
            while (rs.next()) {
                barang = new Barang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
            }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return barang;
    }

    @Override
    public ObservableList<Barang> get() {
        conn = new DatabaseConnection();
        ObservableList listData = FXCollections.observableArrayList();
        try {
            String sql = "Select * From barang";
            ResultSet rs = conn.connect().createStatement().executeQuery(sql);
            while (rs.next()) {   
                Barang m = new Barang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                listData.add(m);
            }
        } catch (Exception e) {
            Logger.getLogger(BarangImplement.class.getName()).log(Level.SEVERE, null, e);
        }
        return listData;
    }    
}
