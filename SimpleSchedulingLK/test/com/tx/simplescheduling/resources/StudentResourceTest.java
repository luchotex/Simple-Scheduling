/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.resources;

import com.tx.simplescheduling.model.StudentParam;
import com.tx.simplescheduling.model.StudentSaving;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class StudentResourceTest {

    private static EJBContainer container;
    private static StudentResource instance;

    public StudentResourceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NamingException {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        instance = (StudentResource) container.getContext().lookup(
                "java:global/classes/StudentResource");
    }

    @AfterClass
    public static void tearDownClass() {
        container.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createStudent method, of class StudentResource.
     */
    @Test (expected = Exception.class)    
    public void testCreateStudentNullParam() throws Exception {
        System.out.println("Test createStudent");
        StudentSaving expResult = null;
        StudentSaving result = instance.createStudent(null);
    }

}
