/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.controller;

import dlelang.Utility.customcontrol.TableActionCell;
import dlelang.implement.BarangImplement;
import dlelang.implement.UserImplement;
import dlelang.model.Barang;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 *
 * @author Ultrabook
 */
public class AdminBarangController implements Initializable {

    Stage stage;
    @FXML
    public AnchorPane anchorPane;
    @FXML
    public Button menuHome, menuUser;
    @FXML
    public Button menuBarang;
    @FXML
    public Button btnTambah;
    @FXML
    public TableView<Barang> tableBarang;
    @FXML
    public TableColumn<Barang, String> columnNo, columnNama;
    @FXML
    public TableColumn<Barang, Boolean> columnAksi;

    BarangImplement crudBarang = new BarangImplement();
    ObservableList<Barang> dataBarang;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnNo.setCellValueFactory(new PropertyValueFactory<Barang, String>("no"));
        columnNama.setCellValueFactory(new PropertyValueFactory<Barang, String>("namaBarang"));
        columnAksi.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Barang, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Barang, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        columnAksi.setCellFactory(new Callback<TableColumn<Barang, Boolean>, TableCell<Barang, Boolean>>() {
            @Override
            public TableCell<Barang, Boolean> call(TableColumn<Barang, Boolean> param) {
                return new TableActionCell();
            }
        });

        System.out.println("dhkjasdjahsds");
        menuHome.setOnAction(this::handleMenuHome);
        menuBarang.setOnAction(this::handleMenuBarang);
        menuUser.setOnAction(this::handleMenuUser);
        btnTambah.setOnAction(this::handleButtonTambah);

        showData();
    }

    private void handleMenuHome(Event actionEvent) {
        stage = (Stage) this.anchorPane.getScene().getWindow();
        changeScene(this.stage, "/dlelang/layout/AdminHome.fxml");
    }

    private void handleMenuBarang(Event actionEvent) {
        stage = (Stage) this.anchorPane.getScene().getWindow();
        changeScene(this.stage, "/dlelang/layout/AdminBarang.fxml");
    }

    private void handleMenuUser(Event actionEvent) {
        stage = (Stage) this.anchorPane.getScene().getWindow();
        changeScene(this.stage, "/dlelang/layout/AdminUser.fxml");
    }

    private void handleButtonTambah(Event actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dlelang/layout/AdminTambahBarang.fxml"));
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void changeScene(Stage stage, String url) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root;
        try {
            root = loader.load();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            Logger.getLogger(AdminBarangController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void popUpScene(String url){

    }

    private void showData() {
        dataBarang = crudBarang.get();
        System.out.println(dataBarang.size());
        tableBarang.setItems(dataBarang);
        tableBarang.getSelectionModel().clearSelection();
    }

}
