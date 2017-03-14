/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.model;

/**
 *
 * @author Ultrabook
 */
public class Transaksi {
    private int idTransaksi;
    private int idBarang;
    private String username;
    private int penawaran;

    public Transaksi(int idTransaksi, int idBarang, String username, int penawaran) {
        this.idTransaksi = idTransaksi;
        this.idBarang = idBarang;
        this.username = username;
        this.penawaran = penawaran;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setId_barang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPenawawaran() {
        return penawaran;
    }

    public void setPenawawaran(int penawawaran) {
        this.penawaran = penawawaran;
    }
    
    
    
}
