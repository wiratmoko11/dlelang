/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.implement;

import dlelang.Utility.DatabaseConnection;
import dlelang.Utility.MysqlConnection;
import dlelang.interfaces.TransaksiInterface;
import dlelang.model.Barang;
import dlelang.model.Transaksi;
import dlelang.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ultrabook
 */
public class TransaksiImplement implements TransaksiInterface {
    DatabaseConnection conn;
    MysqlConnection mysqlConn;
    @Override
    public void insert(Transaksi transaksi) {
        conn = new DatabaseConnection();
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now = format.format((date));
        PreparedStatement ps;
        try {
            ps = conn.connect().prepareStatement("insert into transaksi(id_barang, username, penawaran, updated_at) values(?, ?, ?, ?)");
            ps.setInt(1, transaksi.getIdBarang());
            ps.setString(2, transaksi.getUsername());
            ps.setInt(3, transaksi.getPenawaran());
            ps.setString(4, now);

            ps.execute();
        } catch (Exception e) {
            Logger.getLogger(UserImplement.class.getName()).log(Level.SEVERE, null, e);
        }
    }



    @Override
    public List<Transaksi> getPemenang() {
        return null;
    }

    @Override
    public ObservableList<Transaksi> get() {

        //Fetch Data
        //fetchTransaksi(getMysql());

        ObservableList listData = FXCollections.observableArrayList();
        try {
            String sql = "Select * From transaksi";
            ResultSet rs = conn.connect().createStatement().executeQuery(sql);
            while (rs.next()) {
                Transaksi transaksi = new Transaksi(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                listData.add(transaksi);
            }
        } catch (Exception e) {
            Logger.getLogger(BarangImplement.class.getName()).log(Level.SEVERE, null, e);
        }
        return listData;
    }

    public ObservableList<Transaksi> getMysql(){
        mysqlConn = new MysqlConnection();
        ObservableList listData = FXCollections.observableArrayList();
        try {
            String sql = "Select * From transaksi";
            ResultSet rs = mysqlConn.connect().createStatement().executeQuery(sql);
            while (rs.next()) {
                Transaksi transaksi = new Transaksi(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                listData.add(transaksi);
            }
        } catch (Exception e) {
            Logger.getLogger(BarangImplement.class.getName()).log(Level.SEVERE, null, e);
        }
        return listData;
    }

    public ObservableList<Transaksi> getByIdBarang(String idBarang){
        conn = new DatabaseConnection();
        ObservableList listData = FXCollections.observableArrayList();
        try {
            String sql = "Select * From transaksi where id_barang = "+idBarang+"";
            ResultSet rs = conn.connect().createStatement().executeQuery(sql);
            while (rs.next()) {
                Transaksi transaksi = new Transaksi(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                listData.add(transaksi);
            }
        } catch (Exception e) {
            Logger.getLogger(BarangImplement.class.getName()).log(Level.SEVERE, null, e);
        }
        return listData;
    }

    private void fetchTransaksi(ObservableList<Transaksi> dataMysql){
        conn = new DatabaseConnection();
        try {
            conn.connect().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Transaksi transaksi : dataMysql){
            System.out.println(transaksi.getPenawaran());
            String sql = "SELECT * FROM transaksi WHERE id_transaksi = "+transaksi.getIdTransaksi()+"";
            int rowCount = 0;
            try {
                ResultSet rs = conn.connect().createStatement().executeQuery(sql);
                while (rs.next()){
                    rowCount++;
                }
                //Bila tidak ada data di sqlite
                if(rowCount == 0){
                    insert(transaksi);
                }
//                }else{
//                    String sql2 = "SELECT * FROM transaksi WHERE id_transaksi = "+transaksi.getIdTransaksi()+" AND updated_at < '"+transaksi.getUpdatedAt()+"'";
//                    ResultSet rs2 = conn.connect().createStatement().executeQuery(sql2);
//                    while (rs2.next()){
//                        row2Count++;
//                    }
//                    if(row2Count == 0){
//                        update(barang, barang.getIdBarang());
//                    }
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
