package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import rmabuddy.hibernate.Clients;
import rmabuddy.hibernate.Repairs;
import static java.lang.System.err;
import static java.lang.System.out;
import model.RMAbuddyModel;

/**
 *
 * @author Radek Kurek
 */
public class MainController implements Initializable {  
    
    RMAbuddyModel model = new RMAbuddyModel();
    
    // <editor-fold desc="Controllers" defaultstate="collapsed">
    NewRepair0Controller newRepairViewOneController; 
    NewRepair1Controller newRepairViewTwoController;
    SettingsViewController settingsViewController;
    SearchViewController searchViewController;
    
    // </editor-fold>

    // <editor-fold desc="FXML elements" defaultstate="collapsed">
    @FXML
    private TableView mainTable;
    @FXML
    private ScrollPane mainPane;
    @FXML
    private TitledPane menuPane;
    @FXML
    private Accordion menuAcc;
    @FXML
    private Button newRepairBtn;
    @FXML
    private Button newRepairNextBtn;
    @FXML
    private Button newRepairBackBtn;
    @FXML
    private Button newRepairSaveBtn;
    @FXML
    private Button clientsBtn;
    @FXML
    private Button repairsBtn;
    @FXML
    private Pane newRepairPaneOne;
    @FXML
    private Pane newRepairPaneTwo;
    @FXML
    private Pane settingsPane;
    @FXML
    private Pane searchPane;
    @FXML
    private Button addClientBtn;
    @FXML
    private Button addNewRepairBtn;
    @FXML
    private GridPane newRepairBtnGrid;
    @FXML
    Alert alert;
    // </editor-fold>

    // <editor-fold desc="Other obj/var" defaultstate="collapsed">
    FXMLLoader newRepairPageOneFXMLLoader = new FXMLLoader(); 
    FXMLLoader newRepairPageTwoFXMLLoader = new FXMLLoader();
    FXMLLoader settingsFXMLLoader = new FXMLLoader();
    FXMLLoader searchFXMLLoader = new FXMLLoader();
    Map<String, String> repairMapStrings = new HashMap<>();
    Map<String, Integer> repairMapInts = new HashMap<>();
    Map<String, Date> repairMapDates = new HashMap<>();
    // </editor-fold>

    // <editor-fold desc="Table columns" defaultstate="collapsed">       
    private TableColumn cIdCol;
    private TableColumn fNameCol;
    private TableColumn sNameCol;
    private TableColumn phone1Col;
    private TableColumn companyCol;
    private TableColumn nipCol;
    private TableColumn addrCol;
    private TableColumn cityCol;
    private TableColumn postCol;
    private TableColumn emailCol;
    private TableColumn phone2Col;
    private TableColumn rIdCol;
    private TableColumn numberCol;
    private TableColumn startCol;
    private TableColumn endCol;
    private TableColumn typeCol;
    private TableColumn costCol;
    private TableColumn statusCol;
    private TableColumn defectCol;
    private TableColumn hwNameCol;

    // </editor-fold>
    
    // <editor-fold desc="Buttons Events" defaultstate="collapsed">
    @FXML
    private void repairsButtonAction(ActionEvent event) {

        setTableRepairs(model.getListFromTable("repairs"));
        newRepairBtnGrid.setVisible(false);

    }

    @FXML
    private void clientsButtonAction(ActionEvent event) {

        setTableClients(model.getListFromTable("clients"));
        newRepairBtnGrid.setVisible(false);

    }

    @FXML
    private void searchButtonAction(ActionEvent event) {

        newRepairBtnGrid.setVisible(false);
        mainPane.setContent(searchPane);

    }

    @FXML
    private void settingsButtonAction(ActionEvent event) {

        mainPane.setContent(settingsPane);
        newRepairBtnGrid.setVisible(false);

    }

    @FXML
    private void exitButtonAction(ActionEvent event) {

        System.exit(0);

    }

    @FXML
    private void newRepairButtonAction(ActionEvent event) {

        setViewToNewRepairPanelOne();
        newRepairBtnGrid.setVisible(true);

    }

    @FXML
    private void addClientOnlyButtonAction(ActionEvent event) {

        if (!"".equals(newRepairViewOneController.getNazwiskoText())) {
            getDataFromNewRepairPanelOne();

            model.saveClientOnly(repairMapStrings);
            
        } else {
            newRepairViewOneController.setEmptyFieldsCol();
        }

    }

    @FXML
    private void newRepairNextButtonAction(ActionEvent event) throws IOException { //przechodzi do drugiego panelu nowej naprawy
        
        
        if (newRepairViewOneController.getNazwiskoText().equals("")) {
            newRepairViewOneController.setEmptyFieldsCol();
        } else {
            setViewToNewRepairPanelTwo();
        }
        
    }

    @FXML
    private void newRepairBackButtonAction(ActionEvent event) throws IOException { //przechodzi do poprzedniego panelu nowej naprawy

        mainPane.setContent(newRepairPaneOne);

    }

    @FXML
    private void newRepairSaveButtonAction(ActionEvent event) {

        getDataFromNewRepairPanelTwo();
        saveNewRepair();

    }
    
    @FXML
    private void deleteRecordButtonAction(ActionEvent event){
        
        Repairs repair = (Repairs) mainTable.getSelectionModel().getSelectedItem();
        model.deleteRepair(repair.getId());
        setTableRepairs(model.getListFromTable("repairs"));
        
    }
    
    // </editor-fold>

    // <editor-fold desc="METHODS" defaultstate="collapsed">

    private void preparePanels() {

        newRepairPaneOne = preparePanel(newRepairPaneOne, newRepairPageOneFXMLLoader, "/view/NewRepair0View.fxml");
        newRepairPaneTwo = preparePanel(newRepairPaneTwo, newRepairPageTwoFXMLLoader, "/view/NewRepair1View.fxml");
        settingsPane = preparePanel(settingsPane, settingsFXMLLoader, "/view/SettingsView.fxml");
        searchPane = preparePanel(searchPane, searchFXMLLoader, "/view/SearchView.fxml");

    }

    private Pane preparePanel(Pane aPane, FXMLLoader aLoader, String aURLString) {

        try {
            aPane = (Pane) aLoader.load(getClass().getResource(aURLString).openStream());
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            err.println(ex);
        }

        return aPane;

    }

    private void prepareViewControllers() {

        newRepairViewOneController = (NewRepair0Controller) newRepairPageOneFXMLLoader.getController();
        newRepairViewTwoController = (NewRepair1Controller) newRepairPageTwoFXMLLoader.getController();
        settingsViewController = (SettingsViewController) settingsFXMLLoader.getController();
        searchViewController = (SearchViewController) searchFXMLLoader.getController();

    }

    private void prepareNewRepairBtnGrid() {

        newRepairBtnGrid.setHgap(10);
        newRepairBtnGrid.setVgap(10);
        newRepairBtnGrid.setVisible(false);

    }

    private void setViewToNewRepairPanelOne() {

        mainPane.setContent(newRepairPaneOne);

    }

    private void setViewToNewRepairPanelTwo() {

        getDataFromNewRepairPanelOne();

        mainPane.setContent(newRepairPaneTwo);
        //newRepairViewTwoController.setNazwiskoLabel(repairMapStrings.get("imie") + " " + repairMapStrings.get("nazwisko"));

    }

    private void getDataFromNewRepairPanelOne() {
        
        repairMapStrings.put("imie", newRepairViewOneController.getImieText());
        repairMapStrings.put("nazwisko", newRepairViewOneController.getNazwiskoText());
        repairMapStrings.put("firma", newRepairViewOneController.getFirmaText());
        repairMapStrings.put("nip", newRepairViewOneController.getNipText());
        repairMapStrings.put("telefon1", newRepairViewOneController.getTelefon1Text());
        repairMapStrings.put("telefon2", newRepairViewOneController.getTelefon2Text());
        repairMapStrings.put("adres", newRepairViewOneController.getAdresText());
        repairMapStrings.put("adrescd", newRepairViewOneController.getAdrescdText());
        repairMapStrings.put("miasto", newRepairViewOneController.getMiastoText());
        repairMapStrings.put("poczta", newRepairViewOneController.getPocztaText());
        repairMapStrings.put("email", newRepairViewOneController.getEmailText());    
        
    }

    private void getDataFromNewRepairPanelTwo() {

        LocalDate startDate = newRepairViewTwoController.getStartDateP().getValue();
        LocalDate endDate = newRepairViewTwoController.getEndDateP().getValue();

        repairMapStrings.put("nazwaurz", newRepairViewTwoController.getHwName());
        repairMapStrings.put("sn", newRepairViewTwoController.getSn());
        repairMapStrings.put("other", newRepairViewTwoController.getOther());
        repairMapStrings.put("defect", newRepairViewTwoController.getDefect().getText());
        repairMapStrings.put("fix", newRepairViewTwoController.getFix().getText());
        repairMapStrings.put("cost", newRepairViewTwoController.getCostText().getText());

        repairMapInts.put("typ", newRepairViewTwoController.getType());
        repairMapInts.put("instore", newRepairViewTwoController.getStore());
        repairMapInts.put("status", newRepairViewTwoController.getStatCB().getSelectionModel().getSelectedIndex());

        repairMapDates.put("startdate", java.sql.Date.valueOf(startDate));
        repairMapDates.put("enddate", java.sql.Date.valueOf(endDate));

    }

    private void saveNewRepair() {

        model.saveNewRepair(repairMapStrings, repairMapInts, repairMapDates);

    }

    // </editor-fold>
    
    // <editor-fold desc="Main table views" defaultstate="collapsed">
    private void setTableRepairs(List list) {

        mainPane.setContent(mainTable);

        rIdCol = new TableColumn("ID");
        rIdCol.setMaxWidth(30);
        rIdCol.setResizable(false);
        numberCol = new TableColumn("Numer naprawy");
        startCol = new TableColumn("Rozpoczeto");
        endCol = new TableColumn("Zakonczono");
        typeCol = new TableColumn("Typ urzadzenia");
        costCol = new TableColumn("Koszt naprawy");
        statusCol = new TableColumn("Status");
        statusCol.setMinWidth(80);
        defectCol = new TableColumn("Opis usterki");
        fNameCol = new TableColumn("Imie");
        fNameCol.setMinWidth(80);
        sNameCol = new TableColumn("Nazwisko");
        sNameCol.setMinWidth(80);
        hwNameCol = new TableColumn("Nazwa urządzenia");

        mainTable.getColumns().clear();
        mainTable.getColumns().addAll(rIdCol, numberCol, fNameCol, sNameCol, startCol, endCol,
                hwNameCol, typeCol, statusCol, costCol, defectCol);

        rIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        sNameCol.setCellValueFactory(new PropertyValueFactory<>("sname"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startdate"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("enddate"));
        hwNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        defectCol.setCellValueFactory(new PropertyValueFactory<>("defect"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        ObservableList<Repairs> data = FXCollections.observableList(list);

        mainTable.setItems(data);

    }

    private void setTableClients(List list) {

        mainPane.setContent(mainTable);

        cIdCol = new TableColumn("ID");
        fNameCol = new TableColumn("Imie");
        sNameCol = new TableColumn("Nazwisko");
        companyCol = new TableColumn("Firma");
        nipCol = new TableColumn("NIP");
        addrCol = new TableColumn("Adres");
        cityCol = new TableColumn("Miasto");
        postCol = new TableColumn("Kod");
        emailCol = new TableColumn("Email");
        phone1Col = new TableColumn("Telefon 1");
        phone2Col = new TableColumn("Telefon 2");

        //menuPane.setDisable(false);
        mainTable.getColumns().clear();
        mainTable.getColumns().addAll(cIdCol, fNameCol, sNameCol, companyCol, nipCol,
                addrCol, cityCol, postCol, emailCol, phone1Col, phone2Col);

        ObservableList<Clients> data = FXCollections.observableList(list);

        cIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        sNameCol.setCellValueFactory(new PropertyValueFactory<>("sname"));
        companyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
        nipCol.setCellValueFactory(new PropertyValueFactory<>("nip"));
        addrCol.setCellValueFactory(new PropertyValueFactory<>("addr1"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        postCol.setCellValueFactory(new PropertyValueFactory<>("post"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone1Col.setCellValueFactory(new PropertyValueFactory<>("phone1"));
        phone2Col.setCellValueFactory(new PropertyValueFactory<>("phone2"));

        mainTable.setItems(data);

    }
    // </editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        model.checkIfDatabaseIsUp();
        preparePanels();
        prepareViewControllers();
        prepareNewRepairBtnGrid();
        mainPane.setContent(new Pane());
        
        

    }

}
