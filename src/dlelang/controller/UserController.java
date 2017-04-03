/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.controller;

import java.net.URL;
import java.util.ResourceBundle;

import dlelang.implement.TransaksiImplement;
import dlelang.model.Transaksi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author Ultrabook
 */
public class UserController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox paneMainContainer;

    @FXML
    private GridPane paneToolBar;

    @FXML
    private Button menuHome;

    @FXML
    private Pane paneContentArea;

    @FXML
    private GridPane paneBottomBar;

    @FXML
    private AnchorPane paneMenuNavigationDrawer;

    @FXML
    private AnchorPane paneMenuToolbar;

    @FXML
    void kirimTransaksi(ActionEvent event) {
        new TransaksiImplement().insert(new Transaksi(1, 1, "LendisFabri", Integer.parseInt(nilaiTawar.getText()), "2017-03-1 00:00:00"));
    }

    @FXML
    private TextField nilaiTawar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
