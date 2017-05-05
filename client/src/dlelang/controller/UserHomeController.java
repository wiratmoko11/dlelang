package dlelang.controller;

import dlelang.MenuItem;
import dlelang.MenuItemCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;
import org.controlsfx.control.cell.ColorGridCell;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by Ultrabook on 3/21/2017.
 */
public class UserHomeController implements Initializable {
    ObservableList<Color> list;
    @FXML
    GridView<MenuItem> gridViewBarang;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i = 0; i < 10; i++) {
            gridViewBarang.getItems().addAll(new MenuItem(i));
        }
        gridViewBarang.setCellFactory(new MenuItemCellFactory());
    }
}
