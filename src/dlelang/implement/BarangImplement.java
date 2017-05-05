/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.implement;

import dlelang.Utility.DatabaseConnection;
import dlelang.Utility.MysqlConnection;
import dlelang.interfaces.BarangInterface;
import dlelang.model.Barang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    MysqlConnection mysqlConn;



    @Override
    public void insertBarang(Barang barang) {
        conn = new DatabaseConnection();
        PreparedStatement ps;
        try {
            ps = conn.connect().prepareStatement("insert into barang(id_barang, nama_barang, gambar_barang, harga_awal, tenggat_waktu, deskripsi) values(?, ?,?,?,?,?)");
            ps.setInt(1, barang.getIdBarang());
            ps.setString(2, barang.getNamaBarang());
            ps.setString(3, barang.getGambarBarang());
            ps.setInt(4, barang.getHargaAwal());
            ps.setString(5, barang.getTenggatWaktu());
            ps.setString(6, barang.getDeskripsi());
            ps.execute();
            conn.connect().close();
        } catch (Exception e) {
            Logger.getLogger(UserImplement.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void updateBarang(Barang barang, int id) {
        conn = new DatabaseConnection();
        mysqlConn = new MysqlConnection();
        System.out.println(barang.getIdBarang());
        PreparedStatement ps, ps2;
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now = format.format((date));
        try{
            conn.connect().setAutoCommit(false);

//            ps = conn.connect().prepareStatement("UPDATE barang SET nama_barang = ? WHERE id_barang = ?");
            ps = conn.connect().prepareStatement("UPDATE barang SET nama_barang = ?, harga_awal = ?, tenggat_waktu = ?, deskripsi = ?, created_at = ?, updated_at = ? where id_barang = ?");
            ps2 = mysqlConn.connect().prepareStatement("UPDATE barang SET nama_barang = ?, harga_awal = ?, tenggat_waktu = ?, deskripsi = ?, created_at = ?, updated_at = ? where id_barang = ?");
            System.out.println(barang.getNamaBarang());
            ps.setString(1, barang.getNamaBarang());
            System.out.println(barang.getHargaAwal());
            ps.setInt(2, barang.getHargaAwal());
            System.out.println(barang.getTenggatWaktu());
            ps.setString(3, barang.getTenggatWaktu());
            ps.setString(4, barang.getDeskripsi());
            ps.setString(5, barang.getCreated_at());
            System.out.println(now);
            ps.setString(6, now);
            ps.setInt(7, barang.getIdBarang());

            ps.executeUpdate();

            ps2.setString(1, barang.getNamaBarang());
            ps2.setInt(2, barang.getHargaAwal());
            ps2.setString(3, barang.getTenggatWaktu());
            ps2.setString(4, barang.getDeskripsi());
            ps2.setString(5, barang.getCreated_at());
            ps2.setString(6, now);
            ps2.setInt(7, barang.getIdBarang());
            ps2.executeUpdate();

        }catch(Exception e){
            System.out.println(e.getMessage().toString());
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
           String sql = "Select * From barang where id_barang = "+id+"";
           ResultSet rs = conn.connect().createStatement().executeQuery(sql);
//            while (rs.next()) {
                System.out.println(rs.getInt(1));
                barang = new Barang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
//            }
       } catch (SQLException e) {
           e.printStackTrace();
       }
        System.out.println(barang.getNamaBarang());
       return barang;
    }

    @Override
    public ObservableList<Barang> get() {

        // Data Sync
        fetchBarang(mysqlGet());

        conn = new DatabaseConnection();
        ObservableList listData = FXCollections.observableArrayList();
        try {
            conn.connect().setAutoCommit(false);
            String sql = "Select * From barang";
            ResultSet rs = conn.connect().createStatement().executeQuery(sql);
            while (rs.next()) {   
                Barang m = new Barang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                listData.add(m);
            }
        } catch (Exception e) {
            Logger.getLogger(BarangImplement.class.getName()).log(Level.SEVERE, null, e);
        }
        return listData;
    }

    public ObservableList<Barang> mysqlGet(){
        mysqlConn = new MysqlConnection();
        ObservableList listData = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM barang";
            ResultSet rs = mysqlConn.connect().createStatement().executeQuery(sql);
            while (rs.next()) {
                Barang m = new Barang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                listData.add(m);
            }
        } catch (Exception e) {
            Logger.getLogger(BarangImplement.class.getName()).log(Level.SEVERE, null, e);
        }
        return listData;
    }

    public void sendBarang(Barang barang){
        mysqlConn = new MysqlConnection();
        boolean isSuccess = false;
        int lastInsertId = 0;
        PreparedStatement ps = null;
        try {
            ps = mysqlConn.connect().prepareStatement("insert into barang(id_barang, nama_barang, gambar_barang, harga_awal, tenggat_waktu, deskripsi, created_at, updated_at) values(?, ?,?,?,?,?,?,?)");
            ps.setInt(1, barang.getIdBarang());
            ps.setString(2, barang.getNamaBarang());
            ps.setString(3, barang.getGambarBarang());
            ps.setInt(4, barang.getHargaAwal());
            ps.setString(5, barang.getTenggatWaktu());
            ps.setString(6, barang.getDeskripsi());
            ps.setString(7, barang.getCreated_at());
            ps.setString(8, barang.getUpdated_at());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                lastInsertId = rs.getInt(1);
            }
            isSuccess = true;
        } catch (Exception e) {
            Logger.getLogger(UserImplement.class.getName()).log(Level.SEVERE, null, e);
        }
        if(isSuccess){
            insertBarang(new Barang(
                    lastInsertId,
                    barang.getNamaBarang(),
                    barang.getGambarBarang(),
                    barang.getHargaAwal(),
                    barang.getTenggatWaktu(),
                    barang.getDeskripsi(),
                    barang.getCreated_at(),
                    barang.getUpdated_at()));
        }

    }

    public void fetchBarang(ObservableList<Barang> dataMysql){
        conn = new DatabaseConnection();
        try {
            conn.connect().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Barang barang : dataMysql){
            System.out.println(barang.getNamaBarang());
            String sql = "SELECT * FROM barang WHERE id_barang = "+barang.getIdBarang()+"";
            int rowCount = 0, row2Count = 0;
            try {
                ResultSet rs = conn.connect().createStatement().executeQuery(sql);
                conn.connect().close();
                while (rs.next()){
                    rowCount++;
                }
                //Bila tidak ada data di sqlite
                if(rowCount == 0){
                    insertBarang(barang);
                }else{
                    String sql2 = "SELECT * FROM barang WHERE id_barang = "+barang.getIdBarang()+" AND updated_at < '"+barang.getUpdated_at()+"'";
                    ResultSet rs2 = conn.connect().createStatement().executeQuery(sql2);
                    while (rs2.next()){
                        row2Count++;
                    }
                    if(row2Count == 0){
                        updateBarang(barang, barang.getIdBarang());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
