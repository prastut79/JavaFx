/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password_manager.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import password_manager.AlertBox.AlertBox;
import password_manager.AlertBox.AlertType;
import password_manager.database.DatabaseAccess;
import password_manager.model.Password;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Netsos
 */
public class PasswordsController extends MainController implements Initializable {

    @FXML
    GridPane cardGrid;
    @FXML
    Label tit;
    @FXML
    private StackPane stackPasswords;
    @FXML
    private AnchorPane rootPasswords;

    GridPane grid;

    private ArrayList<Password> passwords = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDatabase();
    }

        /*
        Add a new password card to the scrollpane
         */
    public void addPasswordCard(Password password, int column, int row){

        try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../FXML/passwordCardFxml.fxml"));
                AnchorPane passwordCard = loader.load();
                PasswordCardController passwordCardController = loader.getController();


                passwordCardController.setData(password);
                cardGrid.add(passwordCard, column, row);
                cardGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                cardGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                cardGrid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                cardGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                cardGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                cardGrid.setMaxHeight(Region.USE_PREF_SIZE);


        } catch (IOException e) {
            e.printStackTrace();
        }
        this.passwords.add(password);
    }

    public void clearGrid(){
        this.cardGrid.getChildren().clear();
        passwords.clear();
    }

    /*
    Method to determine the  index for a new password card to be added
    Returns array of column and row
     */
    public int[] getStartingIndex(){
        int gridSize = this.cardGrid.getChildren().size();
        if (gridSize==0){
            return new int[]{0,0};
        }
        Node lastCard;
        lastCard = this.cardGrid.getChildren().get(gridSize-1);
        int lastColumn = cardGrid.getColumnIndex(lastCard);
        int lastRow = cardGrid.getRowIndex(lastCard);

        int column = lastColumn==0 ? 1 :0;
        int row = lastColumn==0 ? lastRow : ++lastRow ;

        return new int[]{column, row};
    }

    public void loadDatabase(){
        ArrayList<Password> passwordList = DatabaseAccess.getAllPassword();
        if (passwordList.size()<1){
            AlertBox.alert(AlertType.ERROR, stackPasswords, rootPasswords, "Failed to Load Passwords from the Database.");
            return;
        }
        int col = getStartingIndex()[0];
        int row = getStartingIndex()[1];
        for (Password password : passwordList){
            if (col==2){
                col=0;
                row++;
            }
            addPasswordCard(password, col++, row);
        }
        this.passwords.addAll(passwordList);
    }

    @FXML
    public void hora(){
        clearGrid();
        loadDatabase();
    }

}
