package password_manager.Controller;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import password_manager.Resource.Constants;
import password_manager.model.Password;

import java.io.IOException;

public class PasswordDetailsBox {
    private static JFXDialog dialog;
    private static Password password;

    public static void box(Password psw) throws IOException {

        StackPane dialogContainer = MainController.getContentStack();
        StackPane toBlur = MainController.getPasswordsScene();

        FXMLLoader loader = new FXMLLoader(PasswordDetailsBox.class.getResource("../FXML/passwordDetailsCard.fxml"));
        StackPane root = loader.load();
        PasswordDetailsCardController controller = loader.getController();

        controller.setData(psw);

        dialog = new JFXDialog();
        dialog.setContent(root);
        dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
        dialog.setDialogContainer(dialogContainer);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent e)->{
            toBlur.setEffect(null);
            toBlur.setDisable(false);
        });

        toBlur.setEffect(Constants.BOX_BLUR_EFFECT);
        toBlur.setDisable(true);
    }

    public static void closeDialog(){
        dialog.close();
    }
}
