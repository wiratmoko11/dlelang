/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.Utility.customcontrol;

import dlelang.model.Barang;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Ultrabook
 */
public class TableActionCell extends TableCell<Barang, Boolean> {
    HBox hBox; 
    final Button deleteButton = new Button("Delete");
    final Button editButton = new Button("Edit");
    final Button detailButton = new Button("Detail");

    public TableActionCell() {
        
        hBox = new HBox();
        
        detailButton.getStyleClass().add("btn");
        detailButton.getStyleClass().add("btn-primary");
        
        deleteButton.getStyleClass().add("btn");
        deleteButton.getStyleClass().add("btn-errors");        
        hBox.getChildren().addAll(detailButton, deleteButton, editButton);
        
        detailButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("jasdasdsakdhaskdjhkjsahdk");
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
