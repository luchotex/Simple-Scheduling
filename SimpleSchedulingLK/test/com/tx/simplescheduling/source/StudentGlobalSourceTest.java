/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.Student;
import com.tx.simplescheduling.model.StudentSaving;
import java.util.Set;
import javax.ws.rs.NotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class StudentGlobalSourceTest {

    public StudentGlobalSourceTest() {
    }

    /**
     * Test of add method, of class StudentGlobalSource.
     */
    @Test
    public void testAddSuccessful() {
        System.out.println("Test addSuccessful");
        StudentSaving studentSaving = new StudentSaving(1, "Test name",
                "test last name");
        StudentGlobalSource instance = new StudentGlobalSource();
        instance.add(studentSaving);

        StudentSaving retrievedStudent = instance.retrieveByIdentifier(1);

        assertNotNull(retrievedStudent);
        assertEquals(studentSaving.getId(), retrievedStudent.getId());
        assertEquals(studentSaving.getFirstName(), retrievedStudent.
                getFirstName());
        assertEquals(studentSaving.getLastName(),
                retrievedStudent.getLastName());
        assertEquals(1, instance.getTypicalSearchMap().size());

    }

    /**
     * Test of add method, of class StudentGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testAddNullStudent() {
        System.out.println("Test addNullStudent");
        StudentSaving studentSaving = null;
        StudentGlobalSource instance = new StudentGlobalSource();
        instance.add(studentSaving);
    }

    /**
     * Test of retrieveByIdentifier method, of class StudentGlobalSource.
     */
    @Test
    public void testRetrieveByIdSucessful() {
        System.out.println("Test retrieveByIdSucessful");
        StudentGlobalSource instance = buildDefaultInstance();

        StudentSaving result = instance.retrieveByIdentifier(1);
        assertEquals(instance.getIdentifierMap().get(1), result);
        assertEquals("Test name", result.getFirstName());
    }

    /**
     * Test of retrieveByIdentifier method, of class StudentGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testRetrieveByIdNullId() {
        System.out.println("Test retrieveByIdNullId");
        StudentGlobalSource instance = buildDefaultInstance();

        StudentSaving result = instance.retrieveByIdentifier(null);
    }

    /**
     * Test of retrieveByIdentifier method, of class StudentGlobalSource.
     */
    @Test(expected = NotFoundException.class)
    public void testRetrieveByIdFotFound() {
        System.out.println("Test retrieveByIdFotFound");
        StudentGlobalSource instance = buildDefaultInstance();

        StudentSaving result = instance.retrieveByIdentifier(3);
    }

    /**
     * Test of retrieveByIdentifier method, of class StudentGlobalSource.
     */
    @Test
    public void testRetrieveByFullNameSucessful() {
        System.out.println("Test retrieveByFullNameSucessful");
        StudentGlobalSource instance = buildDefaultInstance();

        StudentSaving result = instance.retrieveByTypicalSearch("Test name "
                + "test last name");
        assertEquals(instance.getTypicalSearchMap().get("Test name "
                + "test last name"), result);
        assertEquals("Test name", result.getFirstName());
    }

    /**
     * Test of retrieveByIdentifier method, of class StudentGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testRetrieveByFullNameNullFullName() {
        System.out.println("Test retrieveByFullNameNullFullName");
        StudentGlobalSource instance = buildDefaultInstance();

        StudentSaving result = instance.retrieveByTypicalSearch(null);
    }

    /**
     * Test of retrieveByIdentifier method, of class StudentGlobalSource.
     */
    @Test(expected = NotFoundException.class)
    public void testRetrieveByFullNameFotFound() {
        System.out.println("Test retrieveByFullNameFotFound");
        StudentGlobalSource instance = buildDefaultInstance();

        StudentSaving result = instance.retrieveByTypicalSearch(
                "Not existent full name");
    }

    /**
     * Test of retrieveAll method, of class StudentGlobalSource.
     */
    @Test
    public void testRetrieveAllSucessful() {
        System.out.println("Test retrieveAllSucessful");
        StudentSaving studentSaving = new StudentSaving(1, "Test name",
                "test last name");
        StudentSaving studentSaving2 = new StudentSaving(2, "Test name2",
                "test last name2");
        StudentGlobalSource instance = new StudentGlobalSource();
        instance.add(studentSaving);
        instance.add(studentSaving2);

        Set<StudentSaving> allStudents = instance.retrieveAll();

        assertEquals(2, allStudents.size());
        assertTrue(allStudents.contains(studentSaving));
        assertTrue(allStudents.contains(studentSaving2));
    }

    /**
     * Test of add method, of class StudentGlobalSource.
     */
    @Test
    public void testUpdateSuccessful() {
        System.out.println("Test addSuccessful");
        StudentSaving studentSaving = new StudentSaving(1, "Test name",
                "test last name");
        StudentGlobalSource instance = new StudentGlobalSource();
        instance.add(studentSaving);

        StudentSaving retrievedStudent = instance.retrieveByIdentifier(1);

        assertNotNull(retrievedStudent);
        assertEquals(studentSaving.getId(), retrievedStudent.getId());
        assertEquals(studentSaving.getFirstName(), retrievedStudent.
                getFirstName());
        assertEquals(studentSaving.getLastName(), retrievedStudent.getLastName());
        assertEquals(1, instance.getTypicalSearchMap().size());

        studentSaving = new StudentSaving(1, "Test name after",
                "test last name after");

        instance.update(studentSaving);

        retrievedStudent = instance.retrieveByIdentifier(1);
        assertNotNull(retrievedStudent);
        assertEquals(studentSaving.getId(), retrievedStudent.getId());
        assertEquals(studentSaving.getFirstName(), retrievedStudent.
                getFirstName());
        assertEquals(studentSaving.getLastName(), retrievedStudent.getLastName());
        assertEquals(1, instance.getTypicalSearchMap().size());

    }

    /**
     * Test of add method, of class StudentGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testUpdateNullStudent() {
        System.out.println("Test updateNullStudent");
        StudentSaving studentSaving = null;
        StudentGlobalSource instance = new StudentGlobalSource();
        instance.update(studentSaving);
    }

    private StudentGlobalSource buildDefaultInstance() {
        StudentSaving studentSaving = new StudentSaving(1, "Test name",
                "test last name");
        StudentSaving studentSaving2 = new StudentSaving(2, "Test name2",
                "test last name2");
        StudentGlobalSource instance = new StudentGlobalSource();
        instance.getIdentifierMap().put(studentSaving.getId(), studentSaving);
        instance.getIdentifierMap().put(studentSaving2.getId(), studentSaving2);
        instance.getTypicalSearchMap().put(studentSaving.buildFullName(),
                studentSaving);
        instance.getTypicalSearchMap().put(studentSaving2.buildFullName(),
                studentSaving2);
        return instance;
    }

}
