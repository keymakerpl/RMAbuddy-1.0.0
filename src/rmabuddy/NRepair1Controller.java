/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmabuddy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

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
    GridPane mainGrid;
    @FXML
    private Label nazwiskoLabel;
    @FXML
    private TextField hwName;
    @FXML
    private TextField sn;
    @FXML
    private TextField other;
    @FXML
    private TextArea defect;
    @FXML
    private ChoiceBox typeCB = new ChoiceBox();
    @FXML
    private ChoiceBox inStoreCB = new ChoiceBox();
    
   
    @FXML
    private AnchorPane mainPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeCB.getItems().addAll(FXCollections.observableArrayList("Laptop", "Komputer", "Telefon", "Konsola"));
        inStoreCB.getItems().addAll(FXCollections.observableArrayList("W serwisie", "Wydany"));
        mainGrid.setHgap(10);
        mainGrid.setVgap(10);
        
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

    public String getHwName() {
        return hwName.getText();
    }

    public void setHwName(TextField hwName) {
        this.hwName = hwName;
    }

    public String getSn() {
        return sn.getText();
    }

    public void setSn(TextField sn) {
        this.sn = sn;
    }

    public String getOther() {
        return other.getText();
    }

    public void setOther(TextField other) {
        this.other = other;
    }

    public String getDefect() {
        return defect.getText();
    }

    public void setDefect(TextArea defect) {
        this.defect = defect;
    }
    
    public Integer getType(){
        return typeCB.getItems().indexOf(typeCB.getSelectionModel().getSelectedItem());
    }
    
    public Integer getStore(){
        return inStoreCB.getItems().indexOf(inStoreCB.getSelectionModel().getSelectedItem());
    }
}
