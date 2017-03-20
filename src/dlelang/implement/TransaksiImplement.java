/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.implement;

import dlelang.Utility.DatabaseConnection;
import dlelang.interfaces.TransaksiInterface;
import dlelang.model.Transaksi;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ultrabook
 */
public class TransaksiImplement implements TransaksiInterface {
    DatabaseConnection conn;
    @Override
    public void insert(Transaksi transaksi) {
        PreparedStatement ps;
        try {
            ps = conn.connect().prepareStatement("insert into transaksi(id_barang, username, penawaran) values(?, ?, ?)");
            ps.setInt(1, transaksi.getIdBarang());
            ps.setString(2, transaksi.getUsername());
            ps.setInt(3, transaksi.getPenawawaran());
            ps.execute();
        } catch (Exception e) {
            Logger.getLogger(UserImplement.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public List<Transaksi> get() {
        return null;
    }
    
    
}
