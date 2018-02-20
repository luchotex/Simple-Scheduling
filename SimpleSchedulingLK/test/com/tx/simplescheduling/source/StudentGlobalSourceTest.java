/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

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

        StudentSaving retrievedStudent = instance.retrieveById(1);

        assertNotNull(retrievedStudent);
        assertEquals(studentSaving.getId(), retrievedStudent.getId());
        assertEquals(studentSaving.getFirstName(), retrievedStudent.
                getFirstName());
        assertEquals(studentSaving.getLastName(),
                retrievedStudent.getLastName());
        assertEquals(1, instance.getStudentNameMap().size());

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
     * Test of retrieveById method, of class StudentGlobalSource.
     */
    @Test
    public void testRetrieveByIdSucessful() {
        System.out.println("Test retrieveByIdSucessful");
        StudentGlobalSource instance = buildDefaultInstance();

        StudentSaving result = instance.retrieveById(1);
        assertEquals(instance.getStudentIdMap().get(1), result);
        assertEquals("Test name", result.getFirstName());
    }

    /**
     * Test of retrieveById method, of class StudentGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testRetrieveByIdNullId() {
        System.out.println("Test retrieveByIdNullId");
        StudentGlobalSource instance = buildDefaultInstance();

        StudentSaving result = instance.retrieveById(null);
    }

    /**
     * Test of retrieveById method, of class StudentGlobalSource.
     */
    @Test(expected = NotFoundException.class)
    public void testRetrieveByIdFotFound() {
        System.out.println("Test retrieveByIdFotFound");
        StudentGlobalSource instance = buildDefaultInstance();

        StudentSaving result = instance.retrieveById(3);
    }

    /**
     * Test of retrieveById method, of class StudentGlobalSource.
     */
    @Test
    public void testRetrieveByFullNameSucessful() {
        System.out.println("Test retrieveByFullNameSucessful");
        StudentGlobalSource instance = buildDefaultInstance();

        StudentSaving result = instance.retrieveByFullName("Test name "
                + "test last name");
        assertEquals(instance.getStudentNameMap().get("Test name "
                + "test last name"), result);
        assertEquals("Test name", result.getFirstName());
    }

    /**
     * Test of retrieveById method, of class StudentGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testRetrieveByFullNameNullFullName() {
        System.out.println("Test retrieveByFullNameNullFullName");
        StudentGlobalSource instance = buildDefaultInstance();

        StudentSaving result = instance.retrieveByFullName(null);
    }

    /**
     * Test of retrieveById method, of class StudentGlobalSource.
     */
    @Test(expected = NotFoundException.class)
    public void testRetrieveByFullNameFotFound() {
        System.out.println("Test retrieveByFullNameFotFound");
        StudentGlobalSource instance = buildDefaultInstance();

        StudentSaving result = instance.retrieveByFullName("Not existent full name");
    }

    /**
     * Test of retrieveAll method, of class StudentGlobalSource.
     */
    @Test
    public void testRetrieveAll() {
        System.out.println("retrieveAll");
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

    private StudentGlobalSource buildDefaultInstance() {
        StudentSaving studentSaving = new StudentSaving(1, "Test name",
                "test last name");
        StudentSaving studentSaving2 = new StudentSaving(2, "Test name2",
                "test last name2");
        StudentGlobalSource instance = new StudentGlobalSource();
        instance.getStudentIdMap().put(studentSaving.getId(), studentSaving);
        instance.getStudentIdMap().put(studentSaving2.getId(), studentSaving2);
        instance.getStudentNameMap().put(studentSaving.buildFullName(),
                studentSaving);
        instance.getStudentNameMap().put(studentSaving2.buildFullName(),
                studentSaving2);
        return instance;
    }
}
