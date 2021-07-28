package password_manager.Controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import password_manager.database.DatabaseAccess;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML
    private TextField unametf;
    
    @FXML
    private AnchorPane top;
    
    @FXML
    private PasswordField pswtf;

    @FXML
    private Label actionlbl;

    @FXML
    private BorderPane loginPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        movablePane();
    }
    
    /*
    Login Button Pressed
    Verify and login to the system
    */
    @FXML
    private void loginBtnClick(ActionEvent event) throws IOException{
        if(DatabaseAccess.validateCredentials(getUname().toLowerCase(), getPsw())){

            Parent main = FXMLLoader.load(getClass().getResource("../FXML/mainFxml.fxml"));
            Scene scene = new Scene(main);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            window.setScene(scene);
            window.show();
        }else{
            actionlbl.setText("Invalid Credentials.");
        }
    }
    
        
        
        /*
        Close the system
        */
    @FXML
    private void closeSystem(){
        Platform.exit();
    }
    
    /*
    Get Username From Textfield
    */
    private String getUname(){
        return unametf.getText();
    }
    
    /*
    Get Password From PasswordField
    */
    private String getPsw(){
        return pswtf.getText();
    }

    private void movablePane(){

        top.setOnMousePressed(pressEvent -> {
            top.setOnMouseDragged(dragEvent -> {
                Stage stage = (Stage) top.getScene().getWindow();
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                stage.setOpacity(0.8f);
            });
        });
        top.setOnMouseDragOver(dragOverEvent ->{
            Stage stage = (Stage) top.getScene().getWindow();
            stage.setOpacity(1.0f);
        });
        top.setOnMouseReleased(releaseEvent -> {
            Stage stage = (Stage) top.getScene().getWindow();
            stage.setOpacity(1.0f);
        });

    }
}
