/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Radek
 */
public class RMAbuddyModelTest {
    
    public RMAbuddyModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkIfDatabaseIsUp method, of class RMAbuddyModel.
     */
    @Test
    public void testCheckIfDatabaseIsUp() {
        System.out.println("checkIfDatabaseIsUp");
        RMAbuddyModel instance = new RMAbuddyModel();
        instance.checkIfDatabaseIsUp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListFromTable method, of class RMAbuddyModel.
     */
    @Test
    public void testGetListFromTable() {
        System.out.println("getListFromTable");
        String aTable = "";
        RMAbuddyModel instance = new RMAbuddyModel();
        List expResult = null;
        List result = instance.getListFromTable(aTable);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastRepairId method, of class RMAbuddyModel.
     */
    @Test
    public void testGetLastRepairId() {
        System.out.println("getLastRepairId");
        RMAbuddyModel instance = new RMAbuddyModel();
        int expResult = 0;
        int result = instance.getLastRepairId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveClientOnly method, of class RMAbuddyModel.
     */
    @Test
    public void testSaveClientOnly() {
        System.out.println("saveClientOnly");
        Map aStringMap = null;
        RMAbuddyModel instance = new RMAbuddyModel();
        instance.saveClientOnly(aStringMap);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveNewRepair method, of class RMAbuddyModel.
     */
    @Test
    public void testSaveNewRepair() {
        System.out.println("saveNewRepair");
        Map aStringMap = null;
        Map aIntegerMap = null;
        Map aDateMap = null;
        RMAbuddyModel instance = new RMAbuddyModel();
        instance.saveNewRepair(aStringMap, aIntegerMap, aDateMap);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRepair method, of class RMAbuddyModel.
     */
    @Test
    public void testDeleteRepair() {
        System.out.println("deleteRepair");
        int aId = 0;
        RMAbuddyModel instance = new RMAbuddyModel();
        instance.deleteRepair(aId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteClient method, of class RMAbuddyModel.
     */
    @Test
    public void testDeleteClient() {
        System.out.println("deleteClient");
        RMAbuddyModel instance = new RMAbuddyModel();
        instance.deleteClient();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showErrorDialogBox method, of class RMAbuddyModel.
     */
    @Test
    public void testShowErrorDialogBox() {
        System.out.println("showErrorDialogBox");
        String aErrorString = "";
        RMAbuddyModel instance = new RMAbuddyModel();
        instance.showErrorDialogBox(aErrorString);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
