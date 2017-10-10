/*

 Class under construction

 */
package model;

import java.io.IOException;
import java.io.Serializable;
import static java.lang.System.err;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import rmabuddy.hibernate.Clients;
import rmabuddy.hibernate.Hardware;
import rmabuddy.hibernate.HibernateUtil;
import rmabuddy.hibernate.Repairs;

/**
 *
 * @author Radek Kurek
 */
public class RMAbuddyModel {

    private Session session;
    private Alert dbAlert;

    public void checkIfDatabaseIsUp() {
        try {
            Socket socket = new Socket("127.0.0.1", 3306);
            socket.close();
        } catch (IOException e) {
            err.println("Server is down.");
            showErrorDialogBox(e.getMessage());
        }
    }

    private void createSession() {

        if (session == null) {
            try {

                session = HibernateUtil.getSessionFactory().openSession();

            } catch (HibernateException e) {
                err.println("Database error.");
                showErrorDialogBox(e.getMessage());
            }
        }

    }

    /**
     * @param aTable Table name where data is
     * @return Table content as List
     */
    public List getListFromTable(String aTable) {

        createSession();

        List list = null;
        try {
            session.beginTransaction();

            Query query;
            switch (aTable) {
                case "repairs":
                    query = session.createQuery(repairsSQL);
                    list = query.list();
                    session.getTransaction().commit();
                    break;
                case "clients":
                    query = session.createQuery(clientsSQL);
                    list = query.list();
                    session.getTransaction().commit();
                    break;
            }

        } catch (HibernateException he) {
            System.out.println(he);
            System.exit(-1);
        }
        return list;
    }

    public int getLastRepairId() {

        createSession();

        session.beginTransaction();
        Query q = session.createQuery(repairsSQL);

        List<Repairs> list = q.list();

        session.getTransaction().commit();

        if (list.isEmpty()) {
            return 0;
        } else {
            return list.get(list.size() - 1).getId();
        }

    }

    public void saveClientOnly(Map aStringMap) {

        createSession();
        Map repairMapStrings = aStringMap;

        session.beginTransaction();

        Clients klient = new Clients();

        klient.setFname(repairMapStrings.get("imie").toString());
        klient.setSname(repairMapStrings.get("nazwisko").toString());
        klient.setCompany(repairMapStrings.get("firma").toString());
        klient.setNip(repairMapStrings.get("nip").toString());
        klient.setAddr1(repairMapStrings.get("adres").toString());
        klient.setAddr2(repairMapStrings.get("adrescd").toString());
        klient.setCity(repairMapStrings.get("miasto").toString());
        klient.setPostcode(repairMapStrings.get("poczta").toString());
        klient.setEmail(repairMapStrings.get("email").toString());
        klient.setPhone1(repairMapStrings.get("telefon1").toString());
        klient.setPhone2(repairMapStrings.get("telefon2").toString());

        session.save(klient);

        session.getTransaction().commit();

    }

    public void saveNewRepair(Map aStringMap, Map aIntegerMap, Map aDateMap) {

        createSession();

        Clients klient = new Clients();
        Hardware sprzet = new Hardware();
        Repairs naprawa = new Repairs();

        List<Hardware> hwList = new ArrayList<>();
        List<Repairs> repList = new ArrayList<>();

        Map<String, String> repairMapStrings = aStringMap;
        Map<String, Integer> repairMapInts = aIntegerMap;
        Map<String, Date> repairMapDates = aDateMap;

        //TODO: code opt. too much lines or hard coded!
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
        
        session.save(naprawa);
        session.save(sprzet);
        session.save(klient);
        
        session.getTransaction().commit();
        
        session.refresh(klient);

    }

    public void deleteRepair(int aId) {

        createSession();
        int recordId = aId;
        int clientId;
        session.beginTransaction();
        
        Repairs record = (Repairs) session.load(Repairs.class, recordId);
        session.refresh(record);
        
        session.delete(record);

        session.getTransaction().commit();

    }

    public void deleteClient() {

    }

    public void showErrorDialogBox(String aErrorString) {

        dbAlert = new Alert(AlertType.ERROR, aErrorString, ButtonType.OK);
        dbAlert.showAndWait();

        if (dbAlert.getResult() == ButtonType.OK) {
            System.exit(-1);
        }

    }

    //SQL Statements
    private static final String clientsSQL = "from Clients";
    private static final String repairsSQL = "from Repairs";
    

}
