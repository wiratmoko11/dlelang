/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.interfaces;

import dlelang.model.User;

import java.util.List;

/**
 *
 * @author Ultrabook
 */
public interface UserInterface {
    User login(String username, String password);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(String username);
    List<User> get();

}
