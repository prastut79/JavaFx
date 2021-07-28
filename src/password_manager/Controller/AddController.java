/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password_manager.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import password_manager.AlertBox.AlertBox;
import password_manager.AlertBox.AlertType;
import password_manager.Resource.Constants;
import password_manager.database.DatabaseAccess;
import password_manager.model.Password;
import password_manager.Resource.Useful;

/**
 * FXML Controller class
 *
 * @author Netsos
 */
public class AddController extends MainController implements Initializable {

    @FXML
    TextField nametf;
    @FXML
    TextField unametf;
    @FXML
    TextField pswtf;
    @FXML
    TextField linktf;
    @FXML
    TextArea additionalta;
    @FXML
    Label as;
    @FXML
    private StackPane stackAddPassword;
    @FXML
    private AnchorPane rootAddPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /*
    Clearing all fields
    */
    @FXML
    private void clearFields() {
        nametf.setText("");
        unametf.setText("");
        pswtf.setText("");
        linktf.setText("");
        additionalta.setText("");
        setEventLabel("All Fields Cleared.");
    }

    /*'
    Add button action performed
     */
    @FXML
    private void addPassword() {
        if (validateForm()) {
            Password password = new Password();
            password.setTitle(getTitle());
            password.setUsername(getUsername());
            password.setPassword(getPassword());
            password.setWebsite(getWebsite());
            password.setInfo(getInfo());
            password.setAddedDate(Useful.getDate());
            password.setModifiedDate(Useful.getDate());
            password.setAddedBy(getMainUser().getUsername());

            boolean insertStatus = DatabaseAccess.insertPassword(password);
            if (!insertStatus){
                AlertBox.alert(AlertType.ERROR, stackAddPassword, rootAddPassword, Constants.PASSWORD_NOT_ADDED);
                return;
            }
            PasswordsController passwordController = super.getPasswordsLoader().getController();
            int[] startingIndex = passwordController.getStartingIndex();
            passwordController.addPasswordCard(password, startingIndex[0], startingIndex[1]);
            setEventLabel("Password Sucessfully added.");
            AlertBox.alert(AlertType.SUCESS, stackAddPassword, rootAddPassword, Constants.PASSWORD_ADDED);
        } else {

        }
    }

    /*
    Validate Form
     */
    private boolean validateForm() {
        if (getTitle().equals("")) {
            setEventLabel("Please Enter Title.");
            nametf.requestFocus();
            return false;
        } else if (getUsername().equals("")) {
            setEventLabel("Please Enter Username.");
            return false;
        } else if (getWebsite().equals("")) {
            setEventLabel("Please Enter Website Link.");
            linktf.requestFocus();
            return false;
        } else if (getPassword().equals("")) {
            setEventLabel("Please Enter Password.");
            pswtf.requestFocus();
            return false;
        } else if (getInfo().equals("")) {
            setEventLabel("Please Enter Additional Info.");
            additionalta.requestFocus();
            return false;
        }
        return true;
    }
    /*
    Getting Value of Name Textfield
     */
    public String getTitle() {
        return nametf.getText();
    }

    /*
    Getting Value of Name Textfield
     */
    public String getWebsite() {
        return linktf.getText();
    }

    /*
    Getting Value of Name Textfield
     */
    public String getUsername() {
        return unametf.getText();
    }

    /*
    Getting Value of Name Textfield
     */
    public String getPassword() {
        return pswtf.getText();
    }

    /*
    Getting Value of Name Textfield
     */
    public String getInfo() {
        return additionalta.getText();
    }

}
