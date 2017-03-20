package dlelang.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ultrabook
 */
public class AdminController implements Initializable{
    Stage stage;
    @FXML
    public AnchorPane anchorPane; 
    @FXML
    public Button menuHome, menuBarang, menuUser;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuHome.setOnAction(this::handleMenuHome);
        menuBarang.setOnAction(this::handleMenuBarang);
        menuUser.setOnAction(this::handleMenuUser);
        
    }
    public void setStage(Stage stage) {
        this.stage = stage;
   }
    private void handleMenuHome(Event actionEvent){
        stage = (Stage) this.anchorPane.getScene().getWindow();
        changeScene(this.stage, "/dlelang/layout/AdminHome.fxml");
    }
    private void handleMenuBarang(Event actionEvent){
        stage = (Stage) this.anchorPane.getScene().getWindow();
        changeScene(this.stage, "/dlelang/layout/AdminBarang.fxml");
    }
    private void handleMenuUser(Event actionEvent){
        stage = (Stage) this.anchorPane.getScene().getWindow();
        changeScene(this.stage, "/dlelang/layout/AdminUser.fxml");
    }
    
    private void changeScene(Stage stage, String url){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root;
        try {
//            Stage stage = (Stage) this.loginButton.getScene().getWindow();
            AdminController adminController = (AdminController) loader.getController();
            loader.setController(adminController);
            root = loader.load();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
