package dlelang.controller;

import dlelang.MenuItem;
import dlelang.MenuItemCellFactory;
import dlelang.implement.BarangImplement;
import dlelang.model.Barang;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;
import org.controlsfx.control.cell.ColorGridCell;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by Ultrabook on 3/21/2017.
 */
public class UserHomeController implements Initializable {
    @FXML
    public TableView<Barang> tblBarangUser;
    @FXML
    public TableColumn<Barang, String> colBarang;
    @FXML
    public TableColumn<Barang, Boolean> colAksi;

    ObservableList dataBarang;
    BarangImplement crudBarang = new BarangImplement();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colBarang.setCellValueFactory(new PropertyValueFactory<Barang, String>("namaBarang"));
        colAksi.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Barang, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Barang, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        colAksi.setCellFactory(new Callback<TableColumn<Barang, Boolean>, TableCell<Barang, Boolean>>() {
            @Override
            public TableCell<Barang, Boolean> call(TableColumn<Barang, Boolean> param) {
                return new UserHomeController.TableBarangCell(tblBarangUser);
            }
        });

        showData();

    }

    public void showData() {
        Platform.runLater(()->{
            dataBarang = crudBarang.getAktifBarang();
            tblBarangUser.setItems(dataBarang);
            tblBarangUser.getSelectionModel().clearSelection();
            System.out.println("Data Ditampilkan");
        });
    }

    private class TableBarangCell extends TableCell<Barang, Boolean> {
        Stage stage;
        HBox hBox;

        final Button detailButton = new Button("Tawar");

        public TableBarangCell(TableView table) {

            hBox = new HBox();

            detailButton.getStyleClass().add("btn");
            detailButton.getStyleClass().add("btn-primary");


            hBox.getChildren().addAll(detailButton);

            detailButton.setOnAction(new EventHandler<ActionEvent>() {
                BarangImplement crudBarang = new BarangImplement();
                @Override
                public void handle(ActionEvent t) {
                    Barang currentBarang = UserHomeController.TableBarangCell.this.getTableView().getItems().get(UserHomeController.TableBarangCell.this.getIndex());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/dlelang/layout/UserDetailBarang.fxml"));
                    stage = new Stage();
//                    stage.initStyle(StageStyle.UNDECORATED);
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("ID Barang = "+currentBarang.getIdBarang());
                    UserController controller = loader.getController();
                    controller.txtIdBarang.setText(""+currentBarang.getIdBarang());

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            });




//            editButton.setOnAction(new EventHandler<ActionEvent>() {
//
//                BarangImplement crudBarang = new BarangImplement();
//
//                @Override
//                public void handle(ActionEvent event) {
//                    // Get Selected Item
//                    Barang currentBarang =(Barang) AdminBarangController.TableBarangCell.this.getTableView().getItems().get(AdminBarangController.TableBarangCell.this.getIndex());
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/dlelang/layout/AdminEditBarang.fxml"));
//                    stage = new Stage();
//                    stage.initStyle(StageStyle.UNDECORATED);
//                    Parent root = null;
//                    try {
//                        root = loader.load();
//                    } catch (IOException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    AdminEditBarangController controller = loader.getController();
//                    controller.setTxtIdBarang(currentBarang.getIdBarang());
//                    controller.setNamaBarang(currentBarang.getNamaBarang());
//                    controller.setDeskripsi(currentBarang.getDeskripsi());
//                    controller.setHargaAwal(currentBarang.getHargaAwal());
//
//                    Scene scene = new Scene(root);
//                    stage.setScene(scene);
//                    stage.show();
//                }
//            });
        }

        private void swicthScene(String layout){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(layout));
            Parent root;
            try {
                Stage stage = (Stage) this.detailButton.getScene().getWindow();
                UserHomeController userHomeController = (UserHomeController) loader.getController();
                loader.setController(userHomeController);
                root = loader.load();
                stage.setScene(new Scene(root));
            } catch (Exception e) {
                System.out.println(e);
            }

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
