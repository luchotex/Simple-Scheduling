/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.StudentSaving;
import java.util.Set;
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

        StudentSaving result = instance.retrieveById(studentSaving.getId());
        assertEquals(studentSaving, result);
        assertEquals(studentSaving.getFirstName(), result.getFirstName());
    }

    /**
     * Test of retrieveById method, of class StudentGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testRetrieveByNullId() {
        System.out.println("Test retrieveByNullId");
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

        StudentSaving result = instance.retrieveById(null);
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
}
