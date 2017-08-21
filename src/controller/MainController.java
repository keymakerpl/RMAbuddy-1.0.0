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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import rmabuddy.hibernate.Clients;
import rmabuddy.hibernate.Hardware;
import rmabuddy.hibernate.HibernateUtil;
import rmabuddy.hibernate.Repairs;
import static java.lang.System.err;
import java.text.SimpleDateFormat;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.stage.WindowEvent;
import model.RMAbuddyModel;

/**
 *
 * @author Radek Kurek
 */
public class MainController implements Initializable {
    
    Session session; //TODO: move to model
    RMAbuddyModel model = new RMAbuddyModel();

    // <editor-fold desc="Controllers" defaultstate="collapsed">
    NewRepair0Controller newRepairViewOneController; //TODO: change names
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
    FXMLLoader newRepairPageOneFXMLLoader = new FXMLLoader(); //TODO change names
    FXMLLoader newReapirPageTwoFXMLLoader = new FXMLLoader();
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

            session.beginTransaction();

            Clients klient = new Clients();

            klient.setFname(repairMapStrings.get("imie"));
            klient.setSname(repairMapStrings.get("nazwisko"));
            klient.setCompany(repairMapStrings.get("firma"));
            klient.setNip(repairMapStrings.get("nip"));
            klient.setAddr1(repairMapStrings.get("adres"));
            klient.setAddr2(repairMapStrings.get("adrescd"));
            klient.setCity(repairMapStrings.get("miasto"));
            klient.setPostcode(repairMapStrings.get("poczta"));
            klient.setEmail(repairMapStrings.get("email"));
            klient.setPhone1(repairMapStrings.get("telefon1"));
            klient.setPhone2(repairMapStrings.get("telefon2"));

            session.save(klient);

            session.getTransaction().commit();
        } else {
            newRepairViewOneController.setEmptyFieldsCol();
        }

    }

    @FXML
    private void newRepairNextButtonAction(ActionEvent event) throws IOException { //przechodzi do drugiego panelu nowej naprawy

        if (!"".equals(newRepairViewOneController.getNazwiskoText())) {
            setViewToNewRepairPanelTwo();
        } else {
            newRepairViewOneController.setEmptyFieldsCol();
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
    // </editor-fold>

    // <editor-fold desc="METHODS" defaultstate="collapsed">
    private void createSession() { //to do: database error catch
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            if (session.isConnected()) {
                err.print("Connected to database.");
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Błąd bazy danych.");
                alert.setHeaderText("Błąd połącznia z bazą danych.");
                alert.setContentText("Wystąpił problem z połączeniem z bazą danych lub plik bazy danych jest uszkodzony.");

                alert.showAndWait();
            }
        } catch (HibernateException e) {
            err.println(e);

        }

    } //TODO: move to model

    private void preparePanels() {

        newRepairPaneOne = preparePanel(newRepairPaneOne, newRepairPageOneFXMLLoader, "/view/NewRepair0View.fxml");
        newRepairPaneTwo = preparePanel(newRepairPaneTwo, newReapirPageTwoFXMLLoader, "/view/NewRepair1View.fxml");
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
        newRepairViewTwoController = (NewRepair1Controller) newReapirPageTwoFXMLLoader.getController();
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
        newRepairViewTwoController.setNazwiskoLabel(repairMapStrings.get("imie") + " " + repairMapStrings.get("nazwisko"));

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

    private int getLastRepairId() {

        session.beginTransaction();
        Query q = session.createQuery(repairsSQL);

        List<Repairs> list = q.list();

        session.getTransaction().commit();

        return list.get(list.size() - 1).getId();
    } //TODO: move to model

    private void saveNewRepair() {

        Clients klient = new Clients();
        Hardware sprzet = new Hardware();
        Repairs naprawa = new Repairs();

        klient.setFname(repairMapStrings.get("imie"));
        klient.setSname(repairMapStrings.get("nazwisko"));
        klient.setCompany(repairMapStrings.get("firma"));
        klient.setNip(repairMapStrings.get("nip"));
        klient.setAddr1(repairMapStrings.get("adres"));
        klient.setAddr2(repairMapStrings.get("adrescd"));
        klient.setCity(repairMapStrings.get("miasto"));
        klient.setPostcode(repairMapStrings.get("poczta"));
        klient.setEmail(repairMapStrings.get("email"));
        klient.setPhone1(repairMapStrings.get("telefon1"));
        klient.setPhone2(repairMapStrings.get("telefon2"));

        sprzet.setType(repairMapInts.get("typ"));
        sprzet.setInstore(repairMapInts.get("instore"));
        sprzet.setOther(repairMapStrings.get("other"));
        sprzet.setName(repairMapStrings.get("nazwaurz"));
        sprzet.setSn(repairMapStrings.get("sn"));

        StringBuilder number = new StringBuilder("");
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MMYY");
        number.append(getLastRepairId() + 1);
        number.append("/");
        number.append(df.format(date));

        naprawa.setNumber(number.toString());
        naprawa.setDefect(repairMapStrings.get("defect"));
        naprawa.setFix(repairMapStrings.get("fix"));
        naprawa.setStatus(repairMapInts.get("status"));
        naprawa.setCost(repairMapStrings.get("cost"));
        naprawa.setStartdate(repairMapDates.get("startdate"));
        naprawa.setEnddate(repairMapDates.get("enddate"));

        session.getTransaction().begin();

        naprawa.setKlient(klient);
        naprawa.setSprzet(sprzet);
        sprzet.setNaprawa(naprawa);
        session.save(klient);
        session.save(sprzet);
        session.save(naprawa);

        session.getTransaction().commit();
        //sesja.close();

    } //TODO: move to model

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

    //Setting SQL
    private void executeSQL(String hql, int s) {
        try {
            session.beginTransaction();
            Query q = session.createQuery(hql);
            //q.setFirstResult((q.list().size()) - 20);
            List list = q.list();

            switch (s) {
                case 0:
                    setTableRepairs(list);
                    session.getTransaction().commit();
                    break;
                case 1:
                    setTableClients(list);
                    session.getTransaction().commit();
                    break;
            }

        } catch (HibernateException he) {
            System.out.println(he);
            System.exit(-1);
        }

    } //TODO: move to model
    //End

    //Main init
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        createSession();
        prepareViewControllers();
        preparePanels();
        prepareNewRepairBtnGrid();
        mainPane.setContent(new Pane());
        
        

    }

    //SQL Statements, TODO: change to hibernate friendly
    private static final String clientsSQL = "from Clients";
    private static final String repairsSQL = "from Repairs";
}
