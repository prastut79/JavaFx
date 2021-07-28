package password_manager.Resource;

import javafx.scene.effect.BoxBlur;

public class Constants {
    public static final BoxBlur BOX_BLUR_EFFECT = new BoxBlur(2,2,2);

    public static final String PASSWORD_ADDED = "Password sucessfully added to the Database.";
    public static final String PASSWORD_NOT_ADDED = "There was an error while attempting to add the password.";
    public static final String PASSWORD_UPDATED = "Password sucessfully updated.";
    public static final String PASSWORD_NOT_UPDATED = "There was an error while attempting to update the password.";
    public static final String PASSWORD_DELETED = "Password sucessfully deleted.";
    public static final String PASSWORD_NOT_DELETED = "There was an error while attempting to delete the password.";

    public static final String ADDNEW_FXML = "../FXML/addFxml.fxml";
    public static final String PASSWORDS_FXML = "../FXML/passwordsFxml.fxml";
    public static final String GENERATE_FXML= "../FXML/generateFxml.fxml";

    public static final String COLOR_GREEN = "#00FF00";
    public static final String COLOR_RED = "#E00000";
}
