package dlelang.controller;

import dlelang.implement.UserImplement;
import dlelang.model.User;
import javafx.event.Event;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ultrabook on 3/16/2017.
 */
public class AdminTambahUserController implements Initializable {

    private UserImplement crudUser = new UserImplement();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private void handleButtonCancel(Event actionEvent){

    }
    private void handleSubmitButton(Event actionEvent){

    }
    private void saveUser(User user){
        crudUser.insertUser(user);
    }
}
