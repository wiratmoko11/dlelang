/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.interfaces;

import dlelang.model.Barang;
import java.util.List;


/**
 *
 * @author Ultrabook
 */
public interface BarangInterface {
    void insertBarang(Barang barang);
    void updateBarang(Barang barang, int id);
    void deleteBarang(int id);
    List<Barang> get();
}
