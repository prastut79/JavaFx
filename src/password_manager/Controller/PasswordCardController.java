/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password_manager.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import password_manager.AlertBox.AlertBox;
import password_manager.AlertBox.AlertType;
import password_manager.model.Password;
import password_manager.Resource.Useful;

/**
 * FXML Controller class
 *
 * @author Netsos
 */
public class PasswordCardController implements Initializable {

    /**
     * Sample Skeleton for 'passwordCardFxml.fxml' Controller Class
     */
    @FXML // fx:id="passwordCard"
    private AnchorPane passwordCard; // Value injected by FXMLLoader

    @FXML // fx:id="title"
    private Label title; // Value injected by FXMLLoader

    @FXML // fx:id="info"
    private Label info; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private Label username; // Value injected by FXMLLoader

    @FXML // fx:id="passwordInfo"
    private Label password; // Value injected by FXMLLoader

    @FXML // fx:id="website"
    private Label website; // Value injected by FXMLLoader

    private Password passwordInfo;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void doubleMouseClick(MouseEvent event){
        if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount()>1){
            try {
                PasswordDetailsBox.box(this.passwordInfo);
            } catch (Exception e) {
                AlertBox.alert(AlertType.ERROR, MainController.getContentStack(), MainController.getPasswordsScene(), "Could Not load the Details Page");
                e.printStackTrace();
            }
        }
    }

    /*
    Setting values to the Password Card
     */
    public void setData(Password passwordInfo){
        this.title.setText(Useful.capitalize(passwordInfo.getTitle()));
        this.info.setText(passwordInfo.getInfo());
        this.username.setText(passwordInfo.getUsername());
        this.password.setText(passwordInfo.getPassword());
        this.website.setText(passwordInfo.getWebsite());
        this.passwordInfo = passwordInfo;
    }

    public Password getPasswords() {
        return passwordInfo;
    }

    public void loadPasswordDetails(){

    }
}

