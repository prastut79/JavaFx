package password_manager.Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import password_manager.AlertBox.AlertBox;
import password_manager.AlertBox.AlertType;
import password_manager.Resource.Constants;
import password_manager.Resource.Useful;
import password_manager.database.DatabaseAccess;
import password_manager.model.Password;


import java.net.URL;
import java.util.ResourceBundle;

public class PasswordDetailsCardController implements Initializable{

    @FXML
    private JFXTextField titletf;
    @FXML
    private JFXTextField usernametf;
    @FXML
    private JFXTextField passwordtf;
    @FXML
    private JFXTextField linktf;
    @FXML
    private JFXTextArea infota;
    @FXML
    private Label idlbl;
    @FXML
    private Label addedBylbl;
    @FXML
    private Label addedOnlbl;
    @FXML
    private Label lastModifiedlbl;
    @FXML
    private JFXButton okaybtn;
    @FXML
    private JFXButton editbtn;
    @FXML
    private JFXButton cancelbtn;
    @FXML
    private JFXButton savebtn;
    @FXML
    private JFXButton deletebtn;
    @FXML
    private AnchorPane rootAnchor;
    @FXML
    private StackPane rootStack;

    private Password password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeToView();
    }

    /*
    Change the Pane to View only
     */
    private void changeToView(){
        this.titletf.setEditable(false);
        this.usernametf.setEditable(false);
        this.passwordtf.setEditable(false);
        this.linktf.setEditable(false);
        this.infota.setEditable(false);
        this.editbtn.setVisible(true);
        this.okaybtn.setVisible(true);
        this.deletebtn.setVisible(true);
        this.cancelbtn.setVisible(false);
        this.savebtn.setVisible(false);
        this.rootAnchor.requestFocus();
        okaybtn.requestFocus();
    }

    /*
    Change the Pane to edit
     */
    private void changeToEdit(){
        this.titletf.setEditable(true);
        titletf.requestFocus();
        this.usernametf.setEditable(true);
        this.passwordtf.setEditable(true);
        this.linktf.setEditable(true);
        this.infota.setEditable(true);
        this.editbtn.setVisible(false);
        this.okaybtn.setVisible(false);
        this.deletebtn.setVisible(false);
        this.cancelbtn.setVisible(true);
        this.savebtn.setVisible(true);

    }

    @FXML
    private void cancelbtnAction(){
        changeToView();
    }
    @FXML
    private  void okaybtnAction(){
        PasswordDetailsBox.closeDialog();
    }

    @FXML
    private void editbtnAction(){
        changeToEdit();
    }

    @FXML
    private void savebtnAction(){
        this.password.setTitle(titletf.getText());
        this.password.setUsername(usernametf.getText());
        this.password.setPassword(passwordtf.getText());
        this.password.setWebsite(linktf.getText());
        this.password.setInfo(infota.getText());
        this.password.setModifiedDate(Useful.getDate());
        boolean status = DatabaseAccess.updatePassword(this.password);
        if (status){
            AlertBox.alert(AlertType.SUCESS, this.rootStack, this.rootAnchor, Constants.PASSWORD_UPDATED);
        }else{
            AlertBox.alert(AlertType.ERROR, this.rootStack, this.rootAnchor, Constants.PASSWORD_NOT_UPDATED);
        }
        setData(this.password);
        cancelbtnAction();
    }

    @FXML
    private void deletebtnAction(){
        boolean status = DatabaseAccess.deletePassword(this.password.getId());
        okaybtnAction();
        if (status){
            AlertBox.alert(AlertType.SUCESS, MainController.getContentStack(), MainController.getPasswordsScene(), Constants.PASSWORD_DELETED);
        }else{
            AlertBox.alert(AlertType.ERROR, MainController.getContentStack(), MainController.getPasswordsScene(), Constants.PASSWORD_NOT_DELETED);
        }
        PasswordsController passwordsController = MainController.getPasswordsLoader().getController();
        passwordsController.clearGrid();
        passwordsController.loadDatabase();
    }

    public void setData(Password password){
        this.titletf.setText(password.getTitle());
        this.usernametf.setText(password.getUsername());
        this.passwordtf.setText(password.getPassword());
        this.linktf.setText(password.getWebsite());
        this.infota.setText(password.getInfo());
        this.idlbl.setText(String.valueOf(password.getId()));
        this.addedBylbl.setText(password.getAddedBy());
        this.addedOnlbl.setText(password.getAddedDate());
        this.lastModifiedlbl.setText(password.getModifiedDate());
        this.password=password;
    }
}
