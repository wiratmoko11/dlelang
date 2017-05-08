/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlelang.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dlelang.Listener;
import dlelang.implement.BarangImplement;
import dlelang.implement.TransaksiImplement;
import dlelang.model.Barang;
import dlelang.model.Transaksi;
import dlelang.model.Message;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author Ultrabook
 */
public class UserController implements Initializable {


    @FXML
    private Button menuHome;

    @FXML
    public TextField txtIdBarang;

    @FXML
    TableView<Transaksi> tblTransaksi;

    @FXML
    Label lblNamaBarang, lblHargaAwal, lblBatasWaktu;

    @FXML
    TableColumn<Transaksi, String> colNama;

    @FXML
    TableColumn<Transaksi, String> colPenawaran;

    @FXML
    TextField nilaiTawar;

    @FXML
    Button btnTransaksi, btnRefresh;

    ObservableList<Transaksi> dataTransaksi;
    TransaksiImplement crudTransaksi = new TransaksiImplement();
    BarangImplement crudBarang = new BarangImplement();
    String idBarang = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNama.setCellValueFactory(new PropertyValueFactory<Transaksi, String>("username"));
        colPenawaran.setCellValueFactory(new PropertyValueFactory<Transaksi, String>("penawaran"));
        btnTransaksi.setOnAction(this::kirimTransaksi);
        btnRefresh.setOnAction(this::refresh);

        showData();
        showDataBarang();


    }

    public void refresh(ActionEvent event){
        showData();
    }

    public  void showDataBarang(){
        Platform.runLater(()->{
            Barang selectedBarang = crudBarang.getBarangById(Integer.parseInt(txtIdBarang.getText()));

            lblNamaBarang.setText(selectedBarang.getNamaBarang());
            lblHargaAwal.setText(""+selectedBarang.getHargaAwal());
            lblBatasWaktu.setText(selectedBarang.getTenggatWaktu());

        });
    }

    public void showData() {
        Platform.runLater(()->{
            dataTransaksi = crudTransaksi.getByIdBarang(txtIdBarang.getText());
            tblTransaksi.setItems(dataTransaksi);
            tblTransaksi.getSelectionModel().clearSelection();
            System.out.println("Data Ditampilkan");
        });
    }

    public void kirimTransaksi(ActionEvent event){
        System.out.println("Nilai Tawar = "+nilaiTawar.getText().toString());
        System.out.println("ID Barang Aksi = "+idBarang);
        Transaksi transaksi = new Transaksi(1, Integer.parseInt(txtIdBarang.getText().toString()), "wiratmoko11", Integer.parseInt(nilaiTawar.getText().toString()), "2017-03-1 00:00:00");
        Message msg = new Message(transaksi, 2, 1, "wiratmoko11");
        try {
            Listener.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        nilaiTawar.setText("");
        showData();
    }

}
