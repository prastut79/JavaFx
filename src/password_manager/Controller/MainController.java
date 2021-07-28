/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password_manager.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import password_manager.AlertBox.AlertBox;
import password_manager.AlertBox.AlertType;
import password_manager.Resource.Constants;
import password_manager.Resource.Useful;
import password_manager.database.DatabaseAccess;
import password_manager.model.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Netsos
 */
public class MainController implements Initializable {

    @FXML
    private Button minimizeButton;
    
    @FXML
    private StackPane stackContent;

    @FXML
    private Pane dragPane;
    
    @FXML
    private Label eventLabel;
    
    @FXML
    private Pane navBarActivePane;

    @FXML
    private Label userName;

    @FXML
    private ImageView profileImage;

    @FXML
    private StackPane stackRoot;

    @FXML
    private AnchorPane anchorRoot;

    public static Label static_label;

    private String activeScene = "Add New";

    private static StackPane contentStack;
    private static StackPane addNewScene;
    private static StackPane passwordsScene;
    private static StackPane generateScene;
    private static FXMLLoader passwordsLoader;

    private static User mainUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        static_label = this.eventLabel;
        contentStack = stackContent;
        moveablePane();

        getUser();
        setUser();
        updateUserLogin();

        loadAllPane();
        changeScene("Add New");
     }

     /*
    Nav button click event
    */
    @FXML
    private void navBarButtonClicked(ActionEvent evt){
        String button = ((Button) evt.getSource()).getText().trim();
        
        if (!activeScene.equals("Passwords") && button.equals("Passwords")){
            navBarActivePane.setLayoutY(205);
            setEventLabel("All Added Passwords.");
            
        }else if(!activeScene.equals("Generate") && button.equals("Generate")){
            navBarActivePane.setLayoutY(267);
           setEventLabel("Generate a Password.");
           
        }else if (!activeScene.equals("Add New") && button.equals("Add New")){
            navBarActivePane.setLayoutY(-100);
            setEventLabel("Add a Password.");
        }
        //Setting Active Page
        changeScene(button);
        this.activeScene = button;
    }

    /*
    Load all the panes
     */
    private void loadAllPane(){
        try {
            addNewScene = FXMLLoader.load(getClass().getResource(Constants.ADDNEW_FXML));
            passwordsLoader = new FXMLLoader(getClass().getResource(Constants.PASSWORDS_FXML));
            passwordsScene = passwordsLoader.load();
            generateScene = FXMLLoader.load(getClass().getResource(Constants.GENERATE_FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stackContent.getChildren().addAll(addNewScene, passwordsScene, generateScene);
    }
    
    /*
    To change the active page
    Loading the FXML and changing the center of the Border Pane
    */
    private void changeScene(String scene){
        switch (scene) {
            case "Passwords" -> {
                this.addNewScene.setVisible(false);
                this.generateScene.setVisible(false);
                this.passwordsScene.setVisible(true);
            }
            case "Add New" -> {
                this.generateScene.setVisible(false);
                this.passwordsScene.setVisible(false);
                this.addNewScene.setVisible(true);
            }
            case "Generate" -> {
                this.generateScene.setVisible(true);
                this.passwordsScene.setVisible(false);
                this.addNewScene.setVisible(true);
            }
        }
    }

    /*
    Getting the user Info
     */
    private void getUser(){
        mainUser = DatabaseAccess.getUser();
    }

    /*
    Setting the user
     */
//
    private void setUser(){
        try{
            if (!mainUser.getUsername().equals("")){
                this.userName.setText(mainUser.getUsername());
            }else{
                this.userName.setText("User");
            }


            File file = new File(mainUser.getProfileImage());
            if (!file.exists()){
                file = new File(getClass().getResource("../images/eren.png").getFile());
            }
            Image img = new Image(file.toURI().toString());
            profileImage.setImage(img);

            setEventLabel("Welcome "+mainUser.getFname()+" "+mainUser.getLname()+".");
        }catch (NullPointerException e){
            AlertBox.alert(AlertType.ERROR, stackRoot, anchorRoot, "There was an error while updating UserInfo");
        }
    }

    private void updateUserLogin(){
        boolean status = DatabaseAccess.updateLastLogin(Useful.getDate());
        if (!status){
            AlertBox.alert(AlertType.ERROR, stackRoot, anchorRoot, "There was an error while updating Last Login");
        }
    }

    /*
    Make the pane moveable
     */
    private void moveablePane(){
        dragPane.setOnMousePressed(pressEvent -> dragPane.setOnMouseDragged(dragEvent -> {
            Stage stage = (Stage) minimizeButton.getScene().getWindow();
            stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
            stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
        }));
    }

    /*
    Setting Event Label
    This method is used by other classes to set the text of the label
    */
    public static void setEventLabel(String action){
        static_label.setText(action + " ");
    }

    public static FXMLLoader getPasswordsLoader(){
        return passwordsLoader;
    }

    public static StackPane getAddNewScene() {
        return addNewScene;
    }

    public static StackPane getPasswordsScene() {
        return passwordsScene;
    }

    public static StackPane getGenerateScene() {
        return generateScene;
    }

    /*
    Close Button Click Event
    */
    @FXML
    private void closeSystem(){
        Platform.exit();
    }

    /*
    Minimize Button Click Event
    */
    @FXML
    private void minimizeSystem(){
        Stage obj = (Stage) minimizeButton.getScene().getWindow();
        obj.setIconified(true);
    }

    public User getMainUser(){
        return mainUser;
    }

    public static StackPane getContentStack(){
        return contentStack;
    }
}
