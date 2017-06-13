/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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
    private GridPane mainGrid;
    @FXML
    private Label nazwiskoLabel;
    @FXML
    private TextField hwName;
    @FXML
    private TextField sn;
    @FXML
    private TextField other;
    @FXML 
    private TextField costText;
    @FXML
    private TextArea defect;
    @FXML
    private TextArea fix;
    @FXML
    private ChoiceBox typeCB = new ChoiceBox();
    @FXML
    private ChoiceBox inStoreCB = new ChoiceBox();
    @FXML
    private DatePicker startDateP = new DatePicker();
    @FXML
    private DatePicker endDateP = new DatePicker();    
    @FXML
    private AnchorPane mainPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeCB.getItems().addAll(FXCollections.observableArrayList("Laptop", "Komputer", "Telefon", "Konsola"));
        inStoreCB.getItems().addAll(FXCollections.observableArrayList("W serwisie", "Wydany"));
        startDateP.setValue(LocalDate.now());
       
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
        return typeCB.getSelectionModel().getSelectedIndex();
    }
    
    public Integer getStore(){
        return inStoreCB.getSelectionModel().getSelectedIndex();
    }

    public GridPane getMainGrid() {
        return mainGrid;
    }

    public void setMainGrid(GridPane mainGrid) {
        this.mainGrid = mainGrid;
    }

    public TextField getCostText() {
        return costText;
    }

    public void setCostText(TextField costText) {
        this.costText = costText;
    }

    public TextArea getFix() {
        return fix;
    }

    public void setFix(TextArea fix) {
        this.fix = fix;
    }

    public ChoiceBox getTypeCB() {
        return typeCB;
    }

    public void setTypeCB(ChoiceBox typeCB) {
        this.typeCB = typeCB;
    }

    public ChoiceBox getInStoreCB() {
        return inStoreCB;
    }

    public void setInStoreCB(ChoiceBox inStoreCB) {
        this.inStoreCB = inStoreCB;
    }

    public DatePicker getStartDateP() {
        return startDateP;
    }

    public void setStartDateP(DatePicker startDateP) {
        this.startDateP = startDateP;
    }

    public DatePicker getEndDateP() {
        return endDateP;
    }

    public void setEndDateP(DatePicker endDateP) {
        this.endDateP = endDateP;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(AnchorPane mainPane) {
        this.mainPane = mainPane;
    }
    
    
}
