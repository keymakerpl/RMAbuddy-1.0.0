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

/**
 *
 * @author Radek
 */
public class MainController implements Initializable {

    //hebernate con.
    Session sesja;

    // <editor-fold desc="Controllers" defaultstate="collapsed">
    NRepairController controller0;
    NRepair1Controller controller1;
    // </editor-fold>

    // <editor-fold desc="FXML elements" defaultstate="collapsed">
    @FXML
    private TableView mainTable;
    @FXML
    private Accordion menuAcc;
    @FXML
    private TitledPane menuPane;
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
    private ScrollPane mainPane;
    @FXML
    private Pane nRepair0;
    @FXML
    private Pane nRepair1;
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
    FXMLLoader loader0 = new FXMLLoader();
    FXMLLoader loader1 = new FXMLLoader();
    Map<String, String> repairMap = new HashMap<>();
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

    // <editor-fold desc="Buttons" defaultstate="collapsed">
    @FXML
    private void repairsButtonAction(ActionEvent event) {
        System.out.println("Button Clicked");

        executeSQL(repairsSQL, 0);

    }

    @FXML
    private void clientsButtonAction(ActionEvent event) {
        System.out.println("Button Clicked");
        executeSQL(clientsSQL, 1);

    }

    @FXML
    private void searchButtonAction(ActionEvent event) {
        System.out.println("Button Clicked");

    }

    @FXML
    private void settingsButtonAction(ActionEvent event) {
        System.out.println("Button Clicked");

    }

    @FXML
    private void exitButtonAction(ActionEvent event) {
        System.out.println("Button Clicked");
        System.exit(0);

    }

    @FXML
    private void nRepairButtonAction(ActionEvent event) { //pierwszy panel nowej naprawy

        loadPanel0();
        newRepairBtnGrid.setVisible(true); // to do: listener

        //newRepairNextBtn.setVisible(true);
        //newRepairBackBtn.setVisible(true);
    }

    @FXML
    private void addClientButtonAction(ActionEvent event) {

        if (!"".equals(controller0.getNazwiskoText())) {
            getDataPanel0();

            sesja.beginTransaction();

            Clients klient = new Clients();

            klient.setFname(repairMap.get("imie"));
            klient.setSname(repairMap.get("nazwisko"));
            klient.setCompany(repairMap.get("firma"));
            klient.setNip(repairMap.get("nip"));
            klient.setAddr1(repairMap.get("adres"));
            klient.setAddr2(repairMap.get("adrescd"));
            klient.setCity(repairMap.get("miasto"));
            klient.setPostcode(repairMap.get("poczta"));
            klient.setEmail(repairMap.get("email"));
            klient.setPhone1(repairMap.get("telefon1"));
            klient.setPhone2(repairMap.get("telefon2"));

            sesja.save(klient);

            sesja.getTransaction().commit();
        } else {
            controller0.setEmptyFieldsCol();
        }

    }

    @FXML
    private void nRepairNextButtonAction(ActionEvent event) throws IOException { //przechodzi do drugiego panelu nowej naprawy

        if (!"".equals(controller0.getNazwiskoText())) {
            loadPanel1();
        } else {
            controller0.setEmptyFieldsCol();
        }

    }

    @FXML
    private void nRepairBackButtonAction(ActionEvent event) throws IOException { //przechodzi do poprzedniego panelu nowej naprawy

        mainPane.setContent(nRepair0);

    }

    @FXML
    private void nRepairSaveBtnAction(ActionEvent event) {

        getDataPanel1();
        saveNewRepair();

    }
    // </editor-fold>

    // <editor-fold desc="Other methods" defaultstate="collapsed">
    private void createSession() { //to do: database error catch

        try {
            //hibernate session factory
            sesja = HibernateUtil.getSessionFactory().openSession();
        } catch (HibernateException e) {
            err.println(e);

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Błąd bazy danych.");
            alert.setHeaderText("Błąd połącznia z bazą danych.");
            alert.setContentText("Wystąpił problem z połączeniem z bazą danych lub plik bazy danych jest uszkodzony.");

            alert.showAndWait();

        }

    }

    private void loadPanel1() { //2 panel nowej naprawy

        getDataPanel0();

        //addClientBtn.setVisible(false);
        mainPane.setContent(nRepair1);
        controller1.setNazwiskoLabel(repairMap.get("imie") + " " + repairMap.get("nazwisko"));

    }

    private void preparePanels() {

        try {
            nRepair0 = (Pane) loader0.load(getClass().getResource("/view/nRepair0.fxml").openStream()); // .openStream() rozwiązało problem błędów z null           
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller0 = (NRepairController) loader0.getController();

        try {
            nRepair1 = (Pane) loader1.load(getClass().getResource("/view/nRepair1.fxml").openStream());
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller1 = (NRepair1Controller) loader1.getController();

    }

    private void getDataPanel0() {
        repairMap.put("imie", controller0.getImieText());
        repairMap.put("nazwisko", controller0.getNazwiskoText());
        repairMap.put("firma", controller0.getFirmaText());
        repairMap.put("nip", controller0.getNipText());
        repairMap.put("telefon1", controller0.getTelefon1Text());
        repairMap.put("telefon2", controller0.getTelefon2Text());
        repairMap.put("adres", controller0.getAdresText());
        repairMap.put("adrescd", controller0.getAdrescdText());
        repairMap.put("miasto", controller0.getMiastoText());
        repairMap.put("poczta", controller0.getPocztaText());
        repairMap.put("email", controller0.getEmailText());

        /*
         nRepair0Map.entrySet().stream().forEach((entry) -> {
         System.out.println(entry.getKey() + "/" + entry.getValue());
         });
         */
    }

    private void getDataPanel1() {

        LocalDate startDate = controller1.getStartDateP().getValue();
        LocalDate endDate = controller1.getEndDateP().getValue();

        repairMap.put("nazwaurz", controller1.getHwName());
        repairMap.put("sn", controller1.getSn());
        repairMap.put("other", controller1.getOther());
        repairMap.put("defect", controller1.getDefect().getText());
        repairMap.put("fix", controller1.getFix().getText());
        repairMap.put("cost", controller1.getCostText().getText());

        repairMapInts.put("typ", controller1.getType());
        repairMapInts.put("instore", controller1.getStore());
        repairMapInts.put("status", controller1.getStatCB().getSelectionModel().getSelectedIndex());

        repairMapDates.put("startdate", java.sql.Date.valueOf(startDate));
        repairMapDates.put("enddate", java.sql.Date.valueOf(endDate));

    }

    private void loadPanel0() { //1 panel nowej naprawy

        mainPane.setContent(nRepair0);
        //addClientBtn.setVisible(true);

    }

    private void saveNewRepair() {

        Clients klient = new Clients();
        Hardware sprzet = new Hardware();
        Repairs naprawa = new Repairs();

        klient.setFname(repairMap.get("imie"));
        klient.setSname(repairMap.get("nazwisko"));
        klient.setCompany(repairMap.get("firma"));
        klient.setNip(repairMap.get("nip"));
        klient.setAddr1(repairMap.get("adres"));
        klient.setAddr2(repairMap.get("adrescd"));
        klient.setCity(repairMap.get("miasto"));
        klient.setPostcode(repairMap.get("poczta"));
        klient.setEmail(repairMap.get("email"));
        klient.setPhone1(repairMap.get("telefon1"));
        klient.setPhone2(repairMap.get("telefon2"));

        sprzet.setType(repairMapInts.get("typ"));
        sprzet.setInstore(repairMapInts.get("instore"));
        sprzet.setOther(repairMap.get("other"));
        sprzet.setName(repairMap.get("nazwaurz"));
        sprzet.setSn(repairMap.get("sn"));

        StringBuilder number = new StringBuilder("");
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MMYY");
        number.append(getLastRepairId() + 1);
        number.append("/");
        number.append(df.format(date));

        naprawa.setNumber(number.toString());
        naprawa.setDefect(repairMap.get("defect"));
        naprawa.setFix(repairMap.get("fix"));
        naprawa.setStatus(repairMapInts.get("status"));
        naprawa.setCost(repairMap.get("cost"));
        naprawa.setStartdate(repairMapDates.get("startdate"));
        naprawa.setEnddate(repairMapDates.get("enddate"));

        sesja.getTransaction().begin();

        naprawa.setKlient(klient);        
        naprawa.setSprzet(sprzet);
        sprzet.setNaprawa(naprawa);
        sesja.save(klient);
        sesja.save(sprzet);
        sesja.save(naprawa);

        sesja.getTransaction().commit();
        //sesja.close();

    }

    private int getLastRepairId() {

        sesja.beginTransaction();
        Query q = sesja.createQuery(repairsSQL);

        List<Repairs> list = q.list();

        sesja.getTransaction().commit();

        return list.get(list.size() - 1).getId();
    }
    // </editor-fold>

    // <editor-fold desc="Main table views" defaultstate="collapsed">
    private void setTableRepairs(List list) {

        mainPane.setContent(mainTable);

        rIdCol = new TableColumn("ID");
        numberCol = new TableColumn("Numer naprawy");
        startCol = new TableColumn("Rozpoczeto");
        endCol = new TableColumn("Zakonczono");
        typeCol = new TableColumn("Typ urzadzenia");
        costCol = new TableColumn("Koszt naprawy");
        statusCol = new TableColumn("Status");
        defectCol = new TableColumn("Opis usterki");
        fNameCol = new TableColumn("Imie");
        sNameCol = new TableColumn("Nazwisko");
        hwNameCol = new TableColumn("Nazwa urządzenia");
        
        mainTable.getColumns().clear();
        mainTable.getColumns().addAll(rIdCol, numberCol, fNameCol, sNameCol, startCol, endCol,
                 hwNameCol,  typeCol, statusCol, costCol, defectCol);

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
            sesja.beginTransaction();
            Query q = sesja.createQuery(hql);
            //q.setFirstResult((q.list().size()) - 20);
            List list = q.list();

            switch (s) {
                case 0:
                    setTableRepairs(list);
                    sesja.getTransaction().commit();
                    err.println("case 0");
                    break;
                case 1:
                    setTableClients(list);
                    sesja.getTransaction().commit();
                    err.println("case 1");
                    break;
            }

        } catch (HibernateException he) {
            System.out.println(he);
            System.exit(-1);
        }

    }
    //End

    //Main init
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        createSession();
        newRepairBtnGrid.setHgap(10);
        newRepairBtnGrid.setVgap(10);
        newRepairBtnGrid.setVisible(false); //to do: listener
        mainPane.setContent(new Pane());
        preparePanels();

    }

    //SQL Statements, TODO: change to hibernate friendly
    private static final String clientsSQL = "from Clients";
    private static final String repairsSQL = "from Repairs";
}
