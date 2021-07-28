/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password_manager;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

/**
 *
 * @author Zer0
 */
public class PasswordManager extends Application {
    
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/loginFxml.fxml"));
            Scene scene = new Scene(root);
            //For Undecorated Window
//            scene.setFill(Color.TRANSPARENT);
//            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            System.out.println("ERROR: "+ex);
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
    
    @FXML
    public void minimizeSystem(Stage stage){
        stage.setIconified(true);
    }
}
