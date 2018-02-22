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
import javax.ws.rs.WebApplicationException;
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
     * Test of add successful method, of class StudentProcess.
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
        StudentSaving result = instance.add(student, classProcess);

        assertNotNull(result);
        assertEquals(student.getId(), result.getId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
        assertEquals(2, result.getClassSet().size());
    }

    /**
     * Test of add null student, of class StudentProcess.
     */
    @Test
    public void testAddStudentNullStudentParam() {
        System.out.println("Test addStudentNullStudentParam");
        StudentParam student = null;
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();

        try {
            StudentSaving result = instance.add(student,
                    classProcess);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(BadRequestException.class, ex));
        }
    }

    /**
     * Test of add null class ClassProcess, of class StudentProcess.
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
            StudentSaving result = instance.add(student, classProcess);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NullPointerException.class, ex));
        }

    }

    /**
     * Test of add null class code list, of class StudentProcess.
     */
    @Test
    public void testAddStudentNullClassCodeList() {
        System.out.println("Test addStudentNullClassCodeList");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();
        try {
            StudentSaving result = instance.add(student,
                    classProcess);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NullPointerException.class, ex));
        }
    }

    /**
     * Test of retrieveByIdentifier student by id, of class StudentProcess.
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
        instance.add(student, classProcess);
        StudentSaving result = instance.retrieveByIdentifier(student.getId());

        assertNotNull(result);
        assertEquals(student.getId(), result.getId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
    }

    /**
     * Test of retrieveByIdentifier student by id, of class StudentProcess.
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
        instance.add(student, classProcess);
        try {
            StudentSaving result = instance.retrieveByIdentifier(null);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(BadRequestException.class, ex));
        }
    }

    /**
     * Test of retrieveByIdentifier student by id, of class StudentProcess.
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
        instance.add(student, classProcess);
        try {
            StudentSaving result = instance.retrieveByIdentifier(2);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NotFoundException.class, ex));
        }
    }

    /**
     * Test of retrieveByIdentifier student by id, of class StudentProcess.
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
        instance.add(student, classProcess);
        StudentSaving result = instance.retrieveByTypicalSearch(
                student.buildFullName());

        assertNotNull(result);
        assertEquals(student.getId(), result.getId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
    }

    /**
     * Test of retrieveByIdentifier student by id, of class StudentProcess.
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
        instance.add(student, classProcess);
        try {
            StudentSaving result = instance.retrieveByTypicalSearch(null);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(BadRequestException.class, ex));
        }
    }

    /**
     * Test of retrieveByIdentifier student by id, of class StudentProcess.
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
        instance.add(student, classProcess);
        try {
            StudentSaving result = instance.retrieveByTypicalSearch(
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
        instance.add(student, classProcess);

        instance.remove(student.getId(), classProcess);
        Set<StudentSaving> retrieveAllStudent = instance.retrieveAll();

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
        instance.add(student, classProcess);
        try {
            instance.remove(null, classProcess);
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
        instance.add(student, classProcess);
        try {
            instance.remove(3, classProcess);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NotFoundException.class, ex));
        }
    }

    /**
     * Test of add successful method, of class StudentProcess.
     */
    @Test
    public void testUpdateStudentSucessful() {
        System.out.println("Test updateStudentSucessful");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = buildDefaultClassProcess();
        ClassSaving classToSave3 = new ClassSaving("class3", "class3", "class3");
        classProcess.getClassGlobalSource().getIdentifierMap().put(classToSave3.
                getCode(), classToSave3);
        classProcess.getClassGlobalSource().getTypicalSearchMap().put(classToSave3.
                getTitle(), classToSave3);
        StudentProcess instance = new StudentProcess();
        StudentSaving result = instance.add(student, classProcess);

        assertNotNull(result);
        assertEquals(student.getId(), result.getId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
        assertEquals(2, result.getClassSet().size());

        student = new StudentParam(1, "Test name after", "test last name after");
        codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class3");
        student.setClassCodeList(codeSet);
        result = instance.update(student, classProcess);
        assertNotNull(result);
        assertEquals(student.getId(), result.getId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
        assertEquals(2, result.getClassSet().size());
    }

    /**
     * Test of update null student, of class StudentProcess.
     */
    @Test
    public void testUpdateStudentNullStudentParam() {
        System.out.println("Test updateStudentNullStudentParam");
        StudentParam student = null;
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();

        try {
            StudentSaving result = instance.update(student,
                    classProcess);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(BadRequestException.class, ex));
        }
    }

    /**
     * Test of update null class ClassProcess, of class StudentProcess.
     */
    @Test
    public void testUpdateStudentNullClassProcess() {
        System.out.println("Test updateStudentNullClassProcess");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassProcess classProcess = null;
        StudentProcess instance = new StudentProcess();

        try {
            instance.update(student, classProcess);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NotFoundException.class, ex));
        }

    }

    /**
     * Test of update null class code list, of class StudentProcess.
     */
    @Test
    public void testupdateStudentNullClassCodeList() {
        System.out.println("Test updateStudentNullClassCodeList");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        ClassProcess classProcess = buildDefaultClassProcess();
        StudentProcess instance = new StudentProcess();

        try {
            instance.update(student, classProcess);
        } catch (Exception ex) {
            assertTrue(containsExceptionName(NotFoundException.class, ex));
        }
    }

    private ClassProcess buildDefaultClassProcess() {
        ClassProcess classProcess = new ClassProcess();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        classProcess.getClassGlobalSource().getIdentifierMap().put(classToSave1.
                getCode(), classToSave1);
        classProcess.getClassGlobalSource().getIdentifierMap().put(classToSave2.
                getCode(), classToSave2);
        classProcess.getClassGlobalSource().getTypicalSearchMap().put(classToSave1.
                getTitle(), classToSave1);
        classProcess.getClassGlobalSource().getTypicalSearchMap().put(classToSave2.
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
