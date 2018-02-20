/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.resources;

import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.model.StudentParam;
import com.tx.simplescheduling.model.StudentSaving;
import com.tx.simplescheduling.process.ClassProcess;
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
        studentInstance.setClassResource((ClassResource) container.getContext().
                lookup(
                        "java:global/classes/ClassResource"));
        ClassProcess classGlobalInfo
                = studentInstance.getClassResource().getClassProcess();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        classGlobalInfo.getClassGlobalSource().getClassCodeMap().put(
                classToSave1.getCode(), classToSave1);
        classGlobalInfo.getClassGlobalSource().getClassCodeMap().put(
                classToSave2.getCode(), classToSave2);
        classGlobalInfo.getClassGlobalSource().getClassTitleMap().put(
                classToSave1.getTitle(), classToSave1);
        classGlobalInfo.getClassGlobalSource().getClassTitleMap().put(
                classToSave2.getTitle(), classToSave2);
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
    public void testCreateStudentCorrectSaving() {
        System.out.println("Test createStudent correct saving");
        Integer id = 1;
        StudentParam student = new StudentParam(id, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        StudentSaving result = studentInstance.createStudent(student);

        assertNotNull(result);
        assertEquals(student.getId(), result.getId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
        assertEquals(2, result.getClassSet().size());
    }

    private boolean containsExceptionName(Class expectedClassException,
            Exception returnedException) {
        return returnedException.getCause().getClass().getSimpleName().
                contains(expectedClassException.getSimpleName());
    }

}
