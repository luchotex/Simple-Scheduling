/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.resources;

import com.tx.simplescheduling.model.StudentParam;
import com.tx.simplescheduling.model.StudentSaving;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.ws.rs.BadRequestException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class StudentResourceTest {

    private static EJBContainer container;
    private static StudentResource studentInstance;

    public StudentResourceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NamingException {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        studentInstance = (StudentResource) container.getContext().lookup(
                "java:global/classes/StudentResource");
        studentInstance.setClassResource((ClassResource) container.getContext().lookup(
                "java:global/classes/ClassResource"));
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
    @Test
    public void testCreateStudentNullParam()
            throws Exception {
        System.out.println("Test createStudent with null param");
        try {
            StudentSaving result = studentInstance.createStudent(null);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(BadRequestException.class, ex));
        }
    }
    
    /**
     * Test of createStudent method, of class StudentResource.
     */
    @Test
    public void testCreateStudentCorrectSaving()
            throws Exception {
        System.out.println("Test createStudent correct saving");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<Integer> codeSet = new TreeSet<Integer>();
        codeSet.add(1);
        codeSet.add(3);
        StudentSaving result = studentInstance.createStudent(student);
        
        Map<Integer, StudentSaving> studentIdMap
                = studentInstance.getStudentGlobalInfo().getStudentIdMap();
        
        Map<String, List<StudentSaving>> studentNameMap
                = studentInstance.getStudentGlobalInfo().getStudentNameMap();
        
        assertEquals(1, studentIdMap.size());
        assertEquals(1, studentNameMap.size());
    }

    private boolean containsExceptionName(Class expectedClassException,
            Exception returnedException) {
        return returnedException.getCause().getClass().getSimpleName().
                contains(expectedClassException.getSimpleName());
    }

}
