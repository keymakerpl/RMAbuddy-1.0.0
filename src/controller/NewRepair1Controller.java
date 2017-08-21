
package controller;

import java.net.URL;
import java.time.LocalDate;
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
public class NewRepair1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //<editor-fold desc="FXML Elements">
    @FXML
    private GridPane mainGrid;
    @FXML
    private AnchorPane mainPane;
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
    private ChoiceBox statCB = new ChoiceBox();
    @FXML
    private DatePicker startDateP = new DatePicker();
    @FXML
    private DatePicker endDateP = new DatePicker();    
    
    //</editor-fold>
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeCB.getItems().addAll(FXCollections.observableArrayList("Laptop", "Komputer", "Telefon", "Konsola"));
        inStoreCB.getItems().addAll(FXCollections.observableArrayList("W serwisie", "Wydany"));
        startDateP.setValue(LocalDate.now());
        endDateP.setValue(LocalDate.now());
        statCB.getItems().addAll(FXCollections.observableArrayList("Otwarta", "Zamknięta"));
        statCB.getSelectionModel().selectFirst();
        typeCB.getSelectionModel().selectFirst();
        inStoreCB.getSelectionModel().selectFirst();
       
    }
    
    //<editor-fold desc="Getters/Setters">
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

    public TextArea getDefect() {
        return defect;
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

    public ChoiceBox getStatCB() {
        return statCB;
    }

    public void setStatCB(ChoiceBox statCB) {
        this.statCB = statCB;
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
    //</editor-fold>
    
}
