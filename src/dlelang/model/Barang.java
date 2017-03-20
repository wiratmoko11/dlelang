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
public class Barang {
    private int idBarang;
    private String namaBarang;
    private String gambarBarang;
    private int hargaAwal;
    private String tenggatWaktu;
    private String deskripsi;

    public Barang(int idBarang, String namaBarang, String gambarBarang, int hargaAwal, String tenggatWaktu, String deskripsi) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.gambarBarang = gambarBarang;
        this.hargaAwal = hargaAwal;
        this.tenggatWaktu = tenggatWaktu;
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getGambarBarang() {
        return gambarBarang;
    }

    public void setGambarBarang(String gambarBarang) {
        this.gambarBarang = gambarBarang;
    }

    public int getHargaAwal() {
        return hargaAwal;
    }

    public void setHargaAwal(int hargaAwal) {
        this.hargaAwal = hargaAwal;
    }

    public String getTenggatWaktu() {
        return tenggatWaktu;
    }

    public void setTenggatWaktu(String tenggatWaktu) {
        this.tenggatWaktu = tenggatWaktu;
    }

    
    
    
}
