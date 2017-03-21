/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.Utility;

import dlelang.controller.AdminHomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ultrabook
 */
public class SceneHandler {
    public void changeScene(Stage stage, String url){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root;
        try {
//            Stage stage = (Stage) this.loginButton.getScene().getWindow();
//            AdminHomeController adminHomeController = (AdminHomeController) loader.getController();
            root = loader.load();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
