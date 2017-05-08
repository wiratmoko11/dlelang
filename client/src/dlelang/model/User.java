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
public class User implements Serializable {
    String Username;
    String Nama;
    String Password;
    int levelAkses;

    String createdAt;
    String updatedAt;

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

    public User(String Username, String Nama, String Password, int levelAkses) {
        this.Username = Username;
        this.Nama = Nama;
        this.Password = Password;
        this.levelAkses = levelAkses;
    }
    

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public int getLevelAkses() {
        return levelAkses;
    }

    public void setLevelAkses(int levelAkses) {
        this.levelAkses = levelAkses;
    }
    
    
    
}
