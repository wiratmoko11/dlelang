package dlelang.controller;

import dlelang.implement.BarangImplement;
import dlelang.implement.UserImplement;
import dlelang.model.Barang;
import dlelang.model.User;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ultrabook on 3/16/2017.
 */
public class AdminUserController implements Initializable {
    Stage stage;
    UserImplement crudUser = new UserImplement();
    @FXML
    public AnchorPane anchorPane;
    @FXML
    public Button menuHome, menuUser;
    @FXML
    public Button menuBarang;
    @FXML
    public Button btnTambah;
    @FXML
    public TableView<User> tableUser;
    @FXML
    public TableColumn<User, String> columnNo, columnUsername;
    @FXML
    public TableColumn<User, Boolean> columnAksi;

    ObservableList<User> dataUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnNo.setCellValueFactory(new PropertyValueFactory<User, String>("no"));
        columnUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        columnAksi.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<User, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        columnAksi.setCellFactory(new Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>>() {
            @Override
            public TableCell<User, Boolean> call(TableColumn<User, Boolean> param) {
                return new TableUserCell(tableUser);
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
    private void showData(){
        dataUser = crudUser.get();
        System.out.println(dataUser.size());
        tableUser.setItems(dataUser);
        tableUser.getSelectionModel().clearSelection();
    }

    //Cell dengan tombol
    private class TableUserCell extends TableCell<User, Boolean> {
        Stage stage;
        HBox hBox;
        final Button deleteButton = new Button("Delete");
        final Button editButton = new Button("Edit");

        public TableUserCell(TableView table) {

            hBox = new HBox();

            deleteButton.getStyleClass().add("btn");
            deleteButton.getStyleClass().add("btn-errors");
            hBox.getChildren().addAll(editButton, deleteButton);


            editButton.setOnAction(new EventHandler<ActionEvent>() {

                UserImplement crudUser = new UserImplement();

                @Override
                public void handle(ActionEvent event) {
                    // Get Selected Item
                    User currentUser =(User) AdminUserController.TableUserCell.this.getTableView().getItems().get(AdminUserController.TableUserCell.this.getIndex());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/dlelang/layout/AdminEditUser.fxml"));
                    stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
//                    AdminEditUserController controller = loader.getController();
                    //controller.setNamaBarang(currentBarang.getNamaBarang());
                    //controller.setDeskripsi(currentBarang.getDeskripsi());
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
