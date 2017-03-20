package dlelang.controller;

import dlelang.implement.BarangImplement;
import dlelang.model.Barang;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ultrabook on 3/12/2017.
 */
public class AdminTambahBarangController implements Initializable {

    boolean isCreate = true;
    BarangImplement crudBarang;
    @FXML
    private Button btnCancel, btnSubmit;
    @FXML
    private TextField namaBarang;
    @FXML
    private TextField hargaAwal;
    @FXML
    private TextArea deskripsi;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancel.setOnAction(this::handleCancelButton);
        btnSubmit.setOnAction(this::handleSubmitButton);
    }
    private void handleCancelButton(Event actionEvent){
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    private void handleSubmitButton(Event actionEvent){
        crudBarang = new BarangImplement();
        Barang barang = new Barang(0, namaBarang.getText(), null, Integer.parseInt(hargaAwal.getText()), "2017-02-11 20:20:20", "Deskripsi");
        crudBarang.insertBarang(barang);
    }

}
