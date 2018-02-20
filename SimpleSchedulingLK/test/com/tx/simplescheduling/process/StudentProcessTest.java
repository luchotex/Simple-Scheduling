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
import javax.ws.rs.NotFoundException;
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
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();
        StudentSaving result = instance.addStudent(student, classProcess);

        assertNotNull(result);
        assertEquals(student.getId(), result.getId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
        assertEquals(2, result.getClassSet().size());
    }

    /**
     * Test of addStudent null student, of class StudentProcess.
     */
    @Test
    public void testAddStudentNullStudentParam() {
        System.out.println("Test addStudentNullStudentParam");
        StudentParam student = null;
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();

        try {
            StudentSaving result = instance.addStudent(student,
                    classProcess);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(BadRequestException.class, ex));
        }
    }

    /**
     * Test of addStudent null class ClassProcess, of class StudentProcess.
     */
    @Test
    public void testAddStudentNullClassProcess() {
        System.out.println("Test AddStudentNullClassProcess");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = null;
        StudentProcess instance = new StudentProcess();

        try {
            StudentSaving result = instance.addStudent(student, classProcess);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NullPointerException.class, ex));
        }

    }

    /**
     * Test of addStudent null class code list, of class StudentProcess.
     */
    @Test
    public void testAddStudentNullClassCodeList() {
        System.out.println("Test addStudentNullClassCodeList");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        ClassProcess classGlobalSource = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();
        try {
            StudentSaving result = instance.addStudent(student,
                    classGlobalSource);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NullPointerException.class, ex));
        }
    }

    /**
     * Test of retrieve student by id, of class StudentProcess.
     */
    @Test
    public void testRetrieveStudentByIdSucessful() {
        System.out.println("Test retrieveStudentByIdSucessful");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();
        instance.addStudent(student, classProcess);
        StudentSaving result = instance.retrieveStudent(student.getId());

        assertNotNull(result);
        assertEquals(student.getId(), result.getId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
    }

    /**
     * Test of retrieve student by id, of class StudentProcess.
     */
    @Test
    public void testRetrieveStudentByIdNullId() {
        System.out.println("Test retrieveStudentByIdNullId");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();
        instance.addStudent(student, classProcess);
        try {
            StudentSaving result = instance.retrieveStudent(null);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(BadRequestException.class, ex));
        }
    }

    /**
     * Test of retrieve student by id, of class StudentProcess.
     */
    @Test
    public void testRetrieveStudenByIdNotFound() {
        System.out.println("Test retrieveStudentByIdNotFound");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();
        instance.addStudent(student, classProcess);
        try {
            StudentSaving result = instance.retrieveStudent(2);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NotFoundException.class, ex));
        }
    }

    /**
     * Test of retrieve student by id, of class StudentProcess.
     */
    @Test
    public void testRetrieveStudentByFullNameSucessful() {
        System.out.println("Test retrieveStudentByFullNameSucessful");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();
        instance.addStudent(student, classProcess);
        StudentSaving result = instance.retrieveStudentByFullName(
                student.buildFullName());

        assertNotNull(result);
        assertEquals(student.getId(), result.getId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
    }

    /**
     * Test of retrieve student by id, of class StudentProcess.
     */
    @Test
    public void testRetrieveStudentByFullNameNullFullName() {
        System.out.println("Test retrieveStudentByFullNameNullFullName");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();
        instance.addStudent(student, classProcess);
        try {
            StudentSaving result = instance.retrieveStudentByFullName(null);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(BadRequestException.class, ex));
        }
    }

    /**
     * Test of retrieve student by id, of class StudentProcess.
     */
    @Test
    public void testRetrieveStudenByFullNameNotFound() {
        System.out.println("Test retrieveStudentByFullNameNotFound");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();
        instance.addStudent(student, classProcess);
        try {
            StudentSaving result = instance.retrieveStudentByFullName(
                    "Inexistent full name");
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NotFoundException.class, ex));
        }
    }

    /**
     * Test of remove student by id successful, of class StudentProcess.
     */
    @Test
    public void testRemoveStudentSucessful() {
        System.out.println("Test removeStudentSucessful");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();
        instance.addStudent(student, classProcess);

        instance.removeStudent(student.getId(), classProcess);
        Set<StudentSaving> retrieveAllStudent = instance.retrieveAllStudent();

        assertEquals(0, retrieveAllStudent.size());

    }

    /**
     * Test of remove student by id null id, of class StudentProcess.
     */
    @Test
    public void testRemoveStudentNullId() {
        System.out.println("Test removeStudentNullId");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();
        instance.addStudent(student, classProcess);
        try {
            instance.removeStudent(null, classProcess);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(BadRequestException.class, ex));
        }
    }

    /**
     * Test of remove student by id but not found, of class StudentProcess.
     */
    @Test
    public void testRemoveStudenByIdFound() {
        System.out.println("Test removeStudenByIdFound");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();
        instance.addStudent(student, classProcess);
        try {
            instance.removeStudent(3, classProcess);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NotFoundException.class, ex));
        }
    }

    private ClassProcess buildDefaultClassProcess() {
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
        return classProcess;
    }

    private boolean containsExceptionName(Class expectedClassException,
            Exception returnedException) {

        boolean causeEvaluation = false;

        if (returnedException.getCause() != null) {
            causeEvaluation = returnedException.getCause().getClass().
                    getSimpleName().contains(expectedClassException.
                            getSimpleName());
        }

        return causeEvaluation || returnedException.getClass()
                .getSimpleName().equals(expectedClassException.getSimpleName());
    }

}
