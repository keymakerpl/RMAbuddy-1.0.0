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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Radek
 */
public class NRepair1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private FXMLDocumentController main;
    
    @FXML
    private Label nazwiskoLabel;
    @FXML
    private AnchorPane mainPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
    }
    
    public void setNazwiskoLabel(String str){
        nazwiskoLabel.setText(str);
    }
    
}
