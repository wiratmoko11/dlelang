/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.controller;

import dlelang.implement.UserImplement;
import dlelang.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ultrabook
 */
public class LoginController implements Initializable {

    UserImplement userImplement;
    @FXML
    public TextField username;
    @FXML
    public TextField password;
    @FXML
    public Button loginButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginButton.setOnAction(this::handleLogin);
    }
    
    private void doLogin(){
        userImplement = new UserImplement();
        String username = this.username.getText();
        String password = this.password.getText();
        User result = userImplement.login(username, password);
        if(result != null){
            System.out.println("Login Benar");
            swicthScene();
        }else{
            System.out.println("Login Salah");
        }
    }
    
    private void handleLogin(ActionEvent action ){
      doLogin();
    }
    
    private void swicthScene(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dlelang/layout/AdminHome.fxml"));
        Parent root;
        try {
            Stage stage = (Stage) this.loginButton.getScene().getWindow();
            AdminHomeController adminController = (AdminHomeController) loader.getController();
            loader.setController(adminController);
            root = loader.load();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
}
