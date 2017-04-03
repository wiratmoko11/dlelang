//Menu Item Cell
package dlelang;

import dlelang.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.GridCell;

import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

public class MenuItemCell extends GridCell<MenuItem> {

    VBox vBox = new VBox();
    ImageView imageBarang = new ImageView();

    @Override
    protected void updateItem(MenuItem item, boolean empty) {
        // TODO Auto-generated method stub
        super.updateItem(item, empty);
        imageBarang.setImage(new Image(""));
        vBox.getChildren().add(imageBarang);

        if (empty || item == null) {
            setText("asdasd");
            setGraphic(vBox);
        } else {
            setText(item.getName().toString());
            setStyle("-fx-background-color: #ffffff; -fx-min-width:400; -fx-border-width: 0; -fx-padding: 10; -fx-pref-width: 400; -fx-max-width: 400; -fx-max-width: 400; -fx-pref-height: 400; -fx-max-height: 400; -fx-effect: dropshadow(three-pass-box, #93948d, 10, 0, 0, 0);");
        }
    }
}