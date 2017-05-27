/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmabuddy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private Label nazwiskoLabel;
    @FXML
    private TextField hwName;
    @FXML
    private TextField sn;
    @FXML
    private TextField other;
    @FXML
    private TextField defect;

    
    @FXML
    private AnchorPane mainPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
    }
    
    public void setNazwiskoLabel(String str){
        nazwiskoLabel.setText(str);
    }
    
    public Label getNazwiskoLabel() {
        return nazwiskoLabel;
    }

    public void setNazwiskoLabel(Label nazwiskoLabel) {
        this.nazwiskoLabel = nazwiskoLabel;
    }

    public TextField getHwName() {
        return hwName;
    }

    public void setHwName(TextField hwName) {
        this.hwName = hwName;
    }

    public TextField getSn() {
        return sn;
    }

    public void setSn(TextField sn) {
        this.sn = sn;
    }

    public TextField getOther() {
        return other;
    }

    public void setOther(TextField other) {
        this.other = other;
    }

    public TextField getDefect() {
        return defect;
    }

    public void setDefect(TextField defect) {
        this.defect = defect;
    }
}
