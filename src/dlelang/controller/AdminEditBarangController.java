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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ultrabook on 3/15/2017.
 */
public class AdminEditBarangController implements Initializable {
    public int idBarang;
    private File file;

    BarangImplement crudBarang = new BarangImplement();
    Barang barang;
    @FXML
    private Button btnCancel, btnSubmit, btnUpload;
    @FXML
    private TextField namaBarang, txtIdBarang;
    @FXML
    private TextField hargaAwal;
    @FXML
    private TextArea deskripsi;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancel.setOnAction(this::handleCancelBarang);
        btnUpload.setOnAction(this::handleUploadButton);
        btnSubmit.setOnAction(this::handleUpdateBarang);

    }

    private void handleUpdateBarang(Event actionEvent){
        barang = crudBarang.getBarangById(Integer.parseInt(this.txtIdBarang.getText()));
        System.out.println("Nama Barang : "+barang.getNamaBarang());

        this.barang.setNamaBarang(this.namaBarang.getText().toString());
        this.barang.setHargaAwal(Integer.parseInt(this.hargaAwal.getText().toString()));
        this.barang.setDeskripsi(this.deskripsi.getText());
        if(file != null){
            this.barang.setGambarBarang(file.getAbsolutePath().toString());
        }

        crudBarang.updateBarang(this.barang, Integer.parseInt(this.txtIdBarang.getText().toString()));

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

//    public void setIdBarang(int id){
//        System.out.println(id);
//        this.idBarang = id;
//        System.out.println(idBarang);
//    }

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

    public void setTxtIdBarang(int txtIdBarang) {
        this.txtIdBarang.setText(""+txtIdBarang);
        System.out.println("ID Barang : "+this.txtIdBarang.getText().toString());
    }

    public void setHargaAwal(int hargaAwal) {
        this.hargaAwal.setText(""+hargaAwal);
    }

    private void handleUploadButton(Event actionEvent){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        file = fileChooser.showOpenDialog(null);
//        imageBarang = new Image(file.toURI().toString());
//        System.out.println(file.toURI().toString());
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(AdminTambahBarangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
