package password_manager.AlertBox;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import password_manager.Resource.Constants;

import java.io.IOException;

public class AlertBox {
    private static String title;
    private static String color;
    private static String content;
    private static JFXDialog dialog;

    public static void alert(AlertType type, StackPane dialogContainer, Node nodeToBlur, String body){
        try {
            setData(type, body);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AlertBox.class.getResource("alertBoxFxml.fxml"));
            AnchorPane root = loader.load();
            AlertBoxController alertBoxController = loader.getController();
            alertBoxController.setData(title, content, color);


            dialog = new JFXDialog();
            dialog.setContent(root);
            dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialog.setDialogContainer(dialogContainer);
            dialog.show();
            dialog.setOnDialogClosed((JFXDialogEvent e)->{
                nodeToBlur.setEffect(null);
                nodeToBlur.setDisable(false);
            });

            nodeToBlur.setEffect(Constants.BOX_BLUR_EFFECT);
            nodeToBlur.setDisable(true);
            dialogContainer.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setData(AlertType type, String body){
        switch (type){
            case SUCESS:
                title = "Sucess!";
                color = Constants.COLOR_GREEN;
                break;

            case ERROR:
                title = "Error!";
                color = Constants.COLOR_RED;
                break;
        }
        content=body;
    }

    public static void close(){
        if (dialog!=null){
            dialog.close();
        }
    }
}
