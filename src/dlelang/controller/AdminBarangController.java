/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.controller;

import dlelang.implement.BarangImplement;
import dlelang.model.Barang;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.layout.HBox;
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
                return new TableBarangCell(tableBarang);
            }
        });
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
            System.out.println(e.getMessage());
        }
        System.out.println(root);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


//    private void handleButtonEdit(Event actionEvent){
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dlelang/layout/AdminUpdateBarang.fxml"));
//        stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
//        Parent root = null;
//        try {
//            root = loader.load();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println(root);
//        AdminEditBarangController controller = loader.<AdminEditBarangController>getController();
//        controller.setIdBarang(1);
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

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

    public void showData() {
        dataBarang = crudBarang.get();
        tableBarang.setItems(dataBarang);
        tableBarang.getSelectionModel().clearSelection();
    }

    private class TableBarangCell extends TableCell<Barang, Boolean> {
        Stage stage;
        HBox hBox;
        final Button deleteButton = new Button("Delete");
        final Button editButton = new Button("Edit");
        final Button detailButton = new Button("Detail");

        public TableBarangCell(TableView table) {

            hBox = new HBox();

            editButton.getStyleClass().add("btn");
            editButton.getStyleClass().add("btn-primary");

            deleteButton.getStyleClass().add("btn");
            deleteButton.getStyleClass().add("btn-errors");
            hBox.getChildren().addAll(editButton, deleteButton);

            detailButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    System.out.println("jasdasdsakdhaskdjhkjsahdk");
                }
            });
            editButton.setOnAction(new EventHandler<ActionEvent>() {

                BarangImplement crudBarang = new BarangImplement();

                @Override
                public void handle(ActionEvent event) {
                    // Get Selected Item
                    Barang currentBarang =(Barang) TableBarangCell.this.getTableView().getItems().get(TableBarangCell.this.getIndex());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/dlelang/layout/AdminEditBarang.fxml"));
                    stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    AdminEditBarangController controller = loader.getController();
                    controller.setTxtIdBarang(currentBarang.getIdBarang());
                    controller.setNamaBarang(currentBarang.getNamaBarang());
                    controller.setDeskripsi(currentBarang.getDeskripsi());
                    controller.setHargaAwal(currentBarang.getHargaAwal());

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(hBox);
            }
        }
    }

}
