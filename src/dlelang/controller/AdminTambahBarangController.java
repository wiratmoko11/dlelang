package dlelang.controller;

import dlelang.implement.BarangImplement;
import dlelang.model.Barang;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ultrabook on 3/12/2017.
 */
public class AdminTambahBarangController implements Initializable {

    boolean isCreate = true;
    private Image imageBarang;
    private File file;
    BarangImplement crudBarang;
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
        btnCancel.setOnAction(this::handleCancelButton);
        btnSubmit.setOnAction(this::handleSubmitButton);
        btnUpload.setOnAction(this::handleUploadButton);
    }
    private void handleCancelButton(Event actionEvent){
        closeStage(actionEvent);
    }
    private void handleSubmitButton(Event actionEvent){
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now = format.format((date));
        crudBarang = new BarangImplement();
        Barang barang = new Barang(0, namaBarang.getText(), "", Integer.parseInt(hargaAwal.getText()), "2017-02-11 20:20:20", deskripsi.getText(), now, now);
        crudBarang.sendBarang(barang);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dlelang/layout/AdminBarang.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AdminBarangController controller = loader.getController();
        controller.showData();
        closeStage(actionEvent);
    }

    private void closeStage(Event actionEvent){
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
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

//    private void saveToFile(Image image){
//        File outputFile = new File("C:/Users/Ultrabook/IdeaProjects/Dlelang/src/dlelang/images/");
//        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
//        try {
//            ImageIO.write(bImage, "jpg", outputFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }



}
