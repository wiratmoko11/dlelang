/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.interfaces;

import dlelang.model.Transaksi;
import java.util.List;

/**
 *
 * @author Ultrabook
 */
public interface TransaksiInterface {
    public void insert(Transaksi transaksi);
    public List<Transaksi> get();
}
