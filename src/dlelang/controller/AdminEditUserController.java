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
public class AdminEditUserController implements Initializable {
    public String username;
    private User user;

    private UserImplement crudUser = new UserImplement();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void handleCancelButton(Event actionEvent){

    }
    private void handleUpdateButton(Event actionEvent){

    }
    private void getUser(){
        this.user = crudUser.getUser(this.username);
    }
    private void updateUser(){
        this.crudUser.updateUser(this.user);
    }

}
