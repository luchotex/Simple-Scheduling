/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import java.util.TreeSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class ClassSavingTest {

    public ClassSavingTest() {
    }

    /**
     * Test of updateRelatedElement if not exist method, of class ClassSaving.
     */
    @Test
    public void testEnrollStudentUpdatingIfNotExist() {
        System.out.println("Test EnrollStudentUpdatingIfNotExist");
        Student student = new Student(1, "Test name", "test last name");
        ClassSaving instance = new ClassSaving();
        instance.setRelatedElementSet(new TreeSet());
        instance.updateRelatedElement(student);

        assertEquals(1, instance.getRelatedElementSet().size());
    }

    /**
     * Test of updateRelatedElement if not exist method, of class ClassSaving.
     */
    @Test
    public void testEnrollStudentUpdatingIfNotExistInsertingTwo() {
        System.out.println("Test EnrollStudentUpdatingIfNotExistInsertingTwo");
        Student student = new Student(1, "Test name", "test last name");
        Student student2 = new Student(2, "Test name2", "test last name2");
        ClassSaving instance = new ClassSaving();
        instance.setRelatedElementSet(new TreeSet());
        instance.updateRelatedElement(student);
        instance.updateRelatedElement(student2);

        assertEquals(2, instance.getRelatedElementSet().size());
        assertEquals(true, instance.getRelatedElementSet().contains(student));
        assertEquals(true, instance.getRelatedElementSet().contains(student2));
    }

    /**
     * Test of updateRelatedElement method if exist, of class ClassSaving.
     */
    @Test
    public void testEnrollStudentUpdatingIfExist() {
        System.out.println("Test EnrollStudentUpdatingIfNotExist");
        Student student = new Student(1, "Test name", "test last name");
        ClassSaving instance = new ClassSaving();
        instance.setRelatedElementSet(new TreeSet());
        instance.updateRelatedElement(student);

        assertEquals(1, instance.getRelatedElementSet().size());

        student.setFirstName("Test name 2");
        student.setLastName("test last name 2");

        instance.updateRelatedElement(student);
        assertEquals(1, instance.getRelatedElementSet().size());
        assertEquals(true, instance.getRelatedElementSet().contains(student));
    }

}
