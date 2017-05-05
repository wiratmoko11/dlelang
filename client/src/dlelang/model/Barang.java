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
public class Barang implements Serializable{
    private int idBarang;
    private String namaBarang;
    private String gambarBarang;
    private int hargaAwal;
    private String tenggatWaktu;
    private String deskripsi;
    private String created_at;
    private String updated_at;

    public Barang(int idBarang, String namaBarang, String gambarBarang, int hargaAwal, String tenggatWaktu, String deskripsi, String created_at, String updated_at) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.gambarBarang = gambarBarang;
        this.hargaAwal = hargaAwal;
        this.tenggatWaktu = tenggatWaktu;
        this.deskripsi = deskripsi;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
