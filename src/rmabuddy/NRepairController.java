/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmabuddy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Radek
 */
public class NRepairController implements Initializable {
    
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
    
}
