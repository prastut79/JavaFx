/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password_manager.Controller;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Netsos
 */
public class GenerateController extends MainController implements Initializable {

    @FXML
    private JFXToggleButton capitaljfx;
    @FXML
    private JFXToggleButton symboljfx;
    @FXML
    private JFXToggleButton smalljfx;
    @FXML
    private JFXToggleButton digitjfx;
    @FXML
    private Slider lengthslider;
    @FXML
    private TextField lengthtf;
    @FXML
    private TextField generatedPswtf;
    @FXML
    private ImageView copy;
    @FXML
    private StackPane stackGenerate;
    @FXML
    private AnchorPane rootGenerate;

    private final int  minLength=8;
    private final int maxLength=49;
    
    private static final String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();
    private static final String DIGIT = "0123456789";
    private static final String SYMBOL = "!@#&()?/*";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            lengthtf.textProperty().bindBidirectional(lengthslider.valueProperty(), new StringConverter<Number>(){

                //Get value from slider and Update in textfield 
                @Override
                public String toString(Number t)
                {
                
                return String.valueOf(t.intValue());
                }
                
                //Get From Textfield and Update Slider
                @Override
                public Number fromString(String string){
                    try{
                        return Integer.parseInt(string);
                    }catch(NumberFormatException e){
                        return minLength;
                    }
                }

            });
    }
    
    /*'
    Generate button action performed
    */
    @FXML
    private void generatePassword(ActionEvent event) {
        
        if (lengthtf.getText().equals("") || getLength()<this.minLength){
            lengthtf.setText(String.valueOf(this.minLength));
        }else if(getLength()> this.maxLength){
            lengthtf.setText(String.valueOf(this.maxLength));
        }
        
        if(!getCapital() && !getSmall() && !getDigit() && !getSymbol()){
            setEventLabel("Please Select at least one category.");
            return;
        }
        
        if (lengthtf.getText().equals("") || getLength()<3){
            lengthtf.setText(String.valueOf(this.minLength));
        }
        
        String list = "";
        if(getCapital()){
            list += CHAR_UPPERCASE;
        }
        if (getSmall()){
            list+= CHAR_LOWERCASE;
        }
        if(getDigit()){
            list+=DIGIT;
        }
        if(getSymbol()){
            list+=SYMBOL;
        }
        
        String password="";
        Random rand = new Random();
        for (int i=0; i<getLength(); i++){
            password+= list.charAt(rand.nextInt(list.length()));
        }

        generatedPswtf.setText(password);
        setEventLabel("Password Generated.");
    }

    /*
    Copy to System CLipboard
    */
    @FXML
    private void copyToClipboard(){
        
        if (getGeneratedPsw().equals("")){
            setEventLabel("Generate a Password First.");
            return;
        }
        
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(getGeneratedPsw());
//        content.putHtml("<b>Some</b> text");
        clipboard.setContent(content);
        
        setEventLabel("Copied to Clipboard !!");
    }
    /*
    Clearing all fields
    */
    @FXML
    private void clearField() {
        
        capitaljfx.setSelected(false);
        symboljfx.setSelected(false);
        smalljfx.setSelected(false);
        digitjfx.setSelected(false);
        lengthtf.setText(String.valueOf(this.minLength));
        generatedPswtf.setText("");
        
        setEventLabel("Fields Reset Complete.");
    }
    
    /*
    Length Text Field Key Pressed Evenet
    Limiting the Textfield entry to digits and within range 3-49
    */
    @FXML
    private void lengthKeyPressed(KeyEvent event) {
        if(event.getCode().isDigitKey()){
            

            int key;
            try{
                key=getLength();
            }catch(Exception e){
                key=0;
            }
            
            if (key>4){
                lengthtf.setEditable(false);
            }else{
                lengthtf.setEditable(true);
            }


        }else if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE){
            lengthtf.setEditable(true);
        }
        else{
            lengthtf.setEditable(false);
        }
    }
    
    /*
    Length Text Field Key Released Evenet
    Setting the Length TextField Editable back to true
    */
    @FXML
    private void lengthKeyRelease(KeyEvent event) {
        if (!lengthtf.isEditable()){
            lengthtf.setEditable(true);
        }
    }
    
    /*
    Get Capital Letters Toggle Button Status
    */
    public boolean getCapital(){
        return capitaljfx.isSelected();
    }
    
    /*
    Get Small Letters Toggle Button Status
    */
    public boolean getSmall(){
        return smalljfx.isSelected();
    }
    
    /*
    Get Digits Toggle Button Status
    */
    public boolean getDigit(){
        return digitjfx.isSelected();
    }
    
    /*
    Get Symbols Toggle Button Status
    */
    public boolean getSymbol(){
        return symboljfx.isSelected();
    }
    
    /*
    Get Length
    */
    public int getLength(){
        return Integer.parseInt(lengthtf.getText());
    }
    /*
    Get Generated Password
    */
    public String getGeneratedPsw(){
        return generatedPswtf.getText();
    }
}
