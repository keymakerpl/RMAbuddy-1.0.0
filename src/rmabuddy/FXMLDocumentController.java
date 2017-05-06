/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmabuddy;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import rmabuddy.hibernate.Clients;
import rmabuddy.hibernate.HibernateUtil;
import rmabuddy.hibernate.Repairs;

/**
 *
 * @author Radek
 */
public class FXMLDocumentController implements Initializable {
    
    //Elements
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
    private Button clientsBtn;
    @FXML
    private Button repairsBtn;
    @FXML
    private ScrollPane mainPane;
    @FXML
    private Pane nRepair0;
    @FXML
    private Pane nRepair1;
    //End
    
    //Table Elements
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
    //End
    
    //Main buttons
    @FXML
    private void repairsButtonAction(ActionEvent event) {
        System.out.println("Button Clicked");
        
        executeSQL(repairsSQL, 0);
        
    }
    
    @FXML
    private void clientsButtonAction(ActionEvent event) {
        System.out.println("Button Clicked");
        executeSQL(clientsSQL,1);
        
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
    private void nRepairButtonAction(ActionEvent event){ //pierwszy panel nowej naprawy
        
        try {
            nRepair0 = (Pane) FXMLLoader.load(getClass().getResource("/rmabuddy/nRepair0.fxml"));
            mainPane.setContent(nRepair0);
            nRepair0.setPrefSize(mainPane.getPrefWidth()-5, mainPane.getPrefHeight()-5);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
        
    }
    private void nRepairNextButtonAction(ActionEvent event){ //przechodzi do drugiego panelu nowej naprawy
        
        try {
            nRepair1 = (Pane) FXMLLoader.load(getClass().getResource("/rmabuddy/nRepair1.fxml"));
            mainPane.setContent(nRepair1);
            nRepair1.setPrefSize(mainPane.getPrefWidth()-5, mainPane.getPrefHeight()-5);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
        
    }
    //End
    
    //Table views
    @FXML
    private void setTableRepairs(List list){
        
        mainPane.setContent(mainTable);
        
        rIdCol = new TableColumn("ID");
        numberCol = new TableColumn("Numer naprawy");
        startCol = new TableColumn("Rozpoczeto");
        endCol = new TableColumn("Zakonczono");
        typeCol = new TableColumn("Typ urzadzenia");
        costCol = new TableColumn("Koszt naprawy");
        statusCol = new TableColumn("Status");
        defectCol = new TableColumn("Opis usterki");
        //hwIdCol = new TableColumn("Sprzet");
        
        mainTable.getColumns().clear();
        mainTable.getColumns().addAll(rIdCol, numberCol, startCol, endCol, 
                typeCol, costCol, statusCol, defectCol);
        
        rIdCol.setCellValueFactory(new PropertyValueFactory<Clients, Integer>("id"));
        numberCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("number"));
        startCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("startdate"));
        endCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("enddate"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("type"));
        costCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("cost"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("status"));
        defectCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("defect"));
        
        ObservableList<Repairs> data = FXCollections.observableList(list);
        
        mainTable.setItems(data);
        
    }
    
    @FXML
    private void setTableClients(List list){

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
        
        menuPane.setDisable(false);
        
        mainTable.getColumns().clear();
        mainTable.getColumns().addAll(cIdCol, fNameCol, sNameCol, companyCol, nipCol, 
                addrCol, cityCol, postCol, emailCol, phone1Col, phone2Col);        
  
        ObservableList<Clients> data = FXCollections.observableList(list);
        
        cIdCol.setCellValueFactory(new PropertyValueFactory<Clients, Integer>("id"));
        fNameCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("fname"));
        sNameCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("sname"));
        companyCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("company"));
        nipCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("nip"));
        addrCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("addr1"));
        cityCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("city"));
        postCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("post"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Clients, String>("email"));
        phone1Col.setCellValueFactory(new PropertyValueFactory<Clients, String>("phone1"));
        phone2Col.setCellValueFactory(new PropertyValueFactory<Clients, String>("phone2"));
        
        mainTable.setItems(data);
        
    }
    //End
    
    //Setting SQL
    private void executeSQL(String hql, int s) {
        try {
            Session sesja = HibernateUtil.getSessionFactory().openSession();
            sesja.beginTransaction();
            Query q = sesja.createQuery(hql);
            //q.setFirstResult((q.list().size()) - 20);
            List list = q.list();

            switch (s) {
                case 0:
                    setTableRepairs(list);
                    break;
                case 1:
                    setTableClients(list);
                    break;
            }
            sesja.getTransaction().commit();
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
        
        mainPane.setContent(null);
        //menuPane.setDisable(true);
    }    
    
    //SQL Statements
    private static final String clientsSQL = "from Clients";
    private static final String repairsSQL = "from Repairs";
    
}