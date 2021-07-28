package password_manager.AlertBox;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertBoxController implements Initializable {
    @FXML
    private JFXTextArea content;
    @FXML
    private Label title;
    @FXML
    private Pane colorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /*
    Close the dialog Box
     */
    @FXML
    public void closeDialog(){
        AlertBox.close();
    }

    /*
    Setting the data of the dialog box
     */
    public void setData(String title, String content, String color){
        this.title.setText(title);
        this.content.setText(content);
        this.colorPane.setStyle("-fx-background-color: "+ color+";");
    }
}
