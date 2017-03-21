/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang;

import dlelang.Utility.DatabaseConnection;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ultrabook
 */
public class DLelang extends Application {
    
    @Override
    public void start(Stage stage) throws IOException{
        stage.setTitle(("Form Login"));
        Parent root = FXMLLoader.load(getClass().getResource("layout/Login.fxml"));
        Scene scene = new Scene(root, 840, 480);
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseConnection database = new DatabaseConnection();
        database.connect();
        launch(args);
    }
    
}
