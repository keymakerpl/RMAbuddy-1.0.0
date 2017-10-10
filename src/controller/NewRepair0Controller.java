package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Radek
 */
public class NewRepair0Controller implements Initializable {
    
    @FXML
    private Pane nRepair0;
    @FXML
    private Label label;
    @FXML
    private TextField imieText;
    @FXML
    private TextField nazwiskoText;
    @FXML
    private TextField firmaText;
    @FXML
    private TextField nipText;
    @FXML
    private TextField telefon1Text;
    @FXML
    private TextField telefon2Text;
    @FXML
    private TextField adresText;
    @FXML
    private TextField adrescdText;
    @FXML
    private TextField miastoText;
    @FXML
    private TextField pocztaText;
    @FXML
    private TextField emailText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    @FXML
    public String getNazwiskoText(){
        
        return nazwiskoText.getText();
    }
    @FXML
    public String getImieText(){
        
        return imieText.getText();
    }    
    @FXML
    public String getFirmaText(){
        
        return firmaText.getText();
    }
    @FXML
    public String getNipText(){
        
        return nipText.getText();
    }
    @FXML
    public String getTelefon1Text(){
        
        return telefon1Text.getText();
    }
    @FXML
    public String getTelefon2Text(){
        
        return telefon2Text.getText();
    }
    @FXML
    public String getAdresText(){
        
        return adresText.getText();
    }
    @FXML
    public String getAdrescdText(){
        
        return adrescdText.getText();
    }
    @FXML
    public String getMiastoText(){
        
        return miastoText.getText();
    }
    @FXML
    public String getPocztaText(){
        
        return pocztaText.getText();
    }
    @FXML
    public String getEmailText(){
        
        return emailText.getText();
    }
    
    @FXML
    public void setEmptyFieldsCol(){
        
        nazwiskoText.setStyle("-fx-border-color: #"+"DC143C");
        imieText.setStyle("-fx-border-color: #"+"DC143C");
        telefon1Text.setStyle("-fx-border-color: #"+"DC143C");        
    }
    
}
