/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.process;

import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.model.StudentParam;
import com.tx.simplescheduling.model.StudentSaving;
import java.util.Set;
import java.util.TreeSet;
import javax.ws.rs.BadRequestException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class StudentProcessTest {

    public StudentProcessTest() {
    }

    /**
     * Test of addStudent successful method, of class StudentProcess.
     */
    @Test
    public void testAddStudentSucessful() {
        System.out.println("Test AddStudentSucessful");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = new ClassProcess();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        classProcess.getClassGlobalSource().getClassCodeMap().put(
                classToSave1.getCode(),
                classToSave1);
        classProcess.getClassGlobalSource().getClassCodeMap().put(
                classToSave2.getCode(),
                classToSave2);
        classProcess.getClassGlobalSource().getClassTitleMap().put(
                classToSave1.getTitle(),
                classToSave1);
        classProcess.getClassGlobalSource().getClassTitleMap().put(
                classToSave2.getTitle(),
                classToSave2);
        StudentProcess instance = new StudentProcess();
        StudentSaving result = instance.addStudent(student, classProcess);

        assertNotNull(result);
        assertEquals(student.getId(), result.getId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
        assertEquals(2, result.getClassSet().size());
    }

    /**
     * Test of addStudent null class global info, of class StudentProcess.
     */
    @Test
    public void testAddStudentNullStudentParam() {
        System.out.println("Test addStudentNullStudentParam");
        StudentParam student = null;
        ClassProcess classProcess = new ClassProcess();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        classProcess.getClassGlobalSource().getClassCodeMap().put(classToSave1.
                getCode(), classToSave1);
        classProcess.getClassGlobalSource().getClassCodeMap().put(classToSave2.
                getCode(), classToSave2);
        classProcess.getClassGlobalSource().getClassTitleMap().put(classToSave1.
                getTitle(), classToSave1);
        classProcess.getClassGlobalSource().getClassTitleMap().put(classToSave2.
                getTitle(), classToSave2);
        StudentProcess instance = new StudentProcess();

        try {
            StudentSaving result = instance.addStudent(student,
                    classProcess);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(BadRequestException.class, ex));
        }
    }

    /**
     * Test of addStudent null class global info, of class StudentProcess.
     */
    @Test
    public void testAddStudentNullClassGlobalInfo() {
        System.out.println("Test AddStudentNullClassGlobalInfo");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classGlobalInfo = null;
        StudentProcess instance = new StudentProcess();

        try {
            StudentSaving result = instance.addStudent(student, classGlobalInfo);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NullPointerException.class, ex));
        }

    }

    /**
     * Test of addStudent null class global info, of class StudentProcess.
     */
    @Test
    public void testAddStudentNullClassCodeList() {
        System.out.println("Test AddStudentNullClassGlobalInfo");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        ClassProcess classGlobalSource = new ClassProcess();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        classGlobalSource.getClassGlobalSource().getClassCodeMap().put(
                classToSave1.getCode(),
                classToSave1);
        classGlobalSource.getClassGlobalSource().getClassCodeMap().put(
                classToSave2.getCode(),
                classToSave2);
        classGlobalSource.getClassGlobalSource().getClassTitleMap().put(
                classToSave1.getTitle(),
                classToSave1);
        classGlobalSource.getClassGlobalSource().getClassTitleMap().put(
                classToSave2.getTitle(),
                classToSave2);
        StudentProcess instance = new StudentProcess();
        try {
            StudentSaving result = instance.addStudent(student,
                    classGlobalSource);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NullPointerException.class, ex));
        }
    }

    private boolean containsExceptionName(Class expectedClassException,
            Exception returnedException) {

        boolean causeEvaluation = false;

        if (returnedException.getCause() != null) {
            causeEvaluation = returnedException.getCause().getClass().
                    getSimpleName().
                    contains(expectedClassException.getSimpleName());
        }

        return causeEvaluation || returnedException.getClass()
                .getSimpleName().equals(expectedClassException.getSimpleName());
    }

}
