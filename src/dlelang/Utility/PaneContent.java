/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.Utility;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author Ultrabook
 */
public class PaneContent extends Pane {
    
    
    
    public PaneContent(){
        panelLogin();
    }
    
    public void panelLogin(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/Login.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
    }
}
