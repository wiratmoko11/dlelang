package dlelang.controller;

import dlelang.implement.BarangImplement;
import dlelang.model.Barang;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ultrabook on 3/15/2017.
 */
public class AdminEditBarangController implements Initializable {
    public int idBarang;
    BarangImplement crudBarang = new BarangImplement();
    Barang barang;
    @FXML
    private Button btnCancel, btnSubmit, btnUpload;
    @FXML
    private TextField namaBarang;
    @FXML
    private TextField hargaAwal;
    @FXML
    private TextArea deskripsi;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancel.setOnAction(this::handleCancelBarang);
        btnSubmit.setOnAction(this::handleUpdateBarang);
        barang = crudBarang.getBarangById(idBarang);
    }
    private Barang getBarang(int id){
        return crudBarang.getBarangById(id);
    }

    private void handleUpdateBarang(Event actionEvent){
        this.barang.setNamaBarang(this.namaBarang.getText().toString());
        this.barang.setHargaAwal(Integer.parseInt(this.hargaAwal.getText().toString()));
        this.barang.setDeskripsi(this.deskripsi.getText());
//        this.barang.getGambarBarang(this)

        crudBarang.updateBarang(this.barang, this.idBarang);

        // Update Tabel Barang
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dlelang/layout/AdminBarang.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AdminBarangController controller = loader.getController();
        controller.showData();

        closeStage((Node)actionEvent.getSource());
    }

    private void handleCancelBarang(Event actionEvent){
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setIdBarang(int id){
        System.out.println(id);
        this.idBarang = id;
        System.out.println(idBarang);
    }

    private void closeStage(Node source){
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setNamaBarang(String nama){
        this.namaBarang.setText(nama);
    }
    public void setDeskripsi(String deskripsi){
        this.deskripsi.setText(deskripsi);
    }


}
