/*

 Class under construction

 */
package model;

import static java.lang.System.err;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    Session session;
    private Alert dbAlert;

    public void createSession() { //to do: database error catch

        try {
            
            session = HibernateUtil.getSessionFactory().openSession();

        } catch (HibernateException e) {
            if (!session.isConnected()) {
                err.print("Database error. Not connected");

                dbAlert = new Alert(AlertType.ERROR, "Databese error.", ButtonType.OK);
                dbAlert.showAndWait();

                if (dbAlert.getResult() == ButtonType.OK) {
                    System.exit(-1);
                }

                err.println(e);

            }
        }
    }

    /**
     * @param aTable Table name where data is
     * @return Table content as List
     */
    public List getListFromTable(String aTable) {

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

        session.beginTransaction();
        Query q = session.createQuery(repairsSQL);

        List<Repairs> list = q.list();

        session.getTransaction().commit();

        return list.get(list.size() - 1).getId();
    }

    public void saveNewRepair(Map aStringMap, Map aIntegerMap, Map aDateMap) {

        Clients klient = new Clients();
        Hardware sprzet = new Hardware();
        Repairs naprawa = new Repairs();

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

        naprawa.setKlient(klient);
        naprawa.setSprzet(sprzet);
        sprzet.setNaprawa(naprawa);
        session.save(klient);
        session.save(sprzet);
        session.save(naprawa);

        session.getTransaction().commit();
        //sesja.close();

    }

    public void deleteRepair() {

    }

    public void deleteClient() {

    }

    //SQL Statements
    private static final String clientsSQL = "from Clients";
    private static final String repairsSQL = "from Repairs";

}
