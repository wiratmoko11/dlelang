/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.model;

import java.io.Serializable;

/**
 *
 * @author Ultrabook
 */
public class Transaksi implements Serializable {
    private int idTransaksi;
    private int idBarang;
    private String username;
    private int penawaran;
    private String waktuTransaksi;
    private String createdAt;
    private String updatedAt;

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public int getPenawaran() {
        return penawaran;
    }

    public void setPenawaran(int penawaran) {
        this.penawaran = penawaran;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Transaksi(int idTransaksi, int idBarang, String username, int penawaran, String waktuTransaksi) {
        this.idTransaksi = idTransaksi;
        this.idBarang = idBarang;
        this.username = username;
        this.penawaran = penawaran;
        this.waktuTransaksi = waktuTransaksi;
    }

    public String getWaktuTransaksi() {
        return waktuTransaksi;
    }

    public void setWaktuTransaksi(String waktuTransaksi) {
        this.waktuTransaksi = waktuTransaksi;
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



    
    
}
