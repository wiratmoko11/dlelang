/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.controller;

import dlelang.implement.UserImplement;
import dlelang.model.Barang;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ultrabook
 */
public class AdminHomeController implements Initializable {

    Stage stage;
    @FXML
    public AnchorPane anchorPane; 
    @FXML
    public Button menuHome, menuUser;
    @FXML
    public Button menuBarang;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuHome.setOnAction(this::handleMenuHome);
        menuBarang.setOnAction(this::handleMenuBarang);
        menuUser.setOnAction(this::handleMenuUser);
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
    
    private void changeScene(Stage mstage, String url){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root;
        try {
            Stage stage = (Stage) this.anchorPane.getScene().getWindow();
            
            if(url.equals("/dlelang/layout/AdminUser.fxml")){
                AdminBarangController adminController = (AdminBarangController) loader.getController();
                loader.setController(adminController);
            }else{
                
            }
            
            root = loader.load();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
   
    
}
