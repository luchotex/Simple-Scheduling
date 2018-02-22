/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.Class;
import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.model.Student;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class ClassGlobalSourceTest {

    public ClassGlobalSourceTest() {
    }

    /**
     * Test of addRelatedElement successful method, of class ClassGlobalSource.
     */
    @Test
    public void testEnrollStudentSuccessfull() {
        System.out.println("Test enrollStudentSuccessfull");
        String code = "class1";
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getTypicalSearchMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.addRelatedElement(code, student);

        ClassSaving codeClassSaving = instance.getIdentifierMap().get(code);
        ClassSaving titleClassSaving = instance.getTypicalSearchMap().get(
                codeClassSaving.getTitle());

        assertNotNull(result);
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertEquals(1, titleClassSaving.getStudentSet().size());
    }

    /**
     * Test of addRelatedElement null code method, of class ClassGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testEnrollStudentNullCode() {
        System.out.println("Test enrollStudentNullCode");
        String code = null;
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        Class result = instance.addRelatedElement(code, student);
    }

    /**
     * Test of addRelatedElement null student method, of class ClassGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testEnrollStudentNullStudent() {
        System.out.println("Test enrollStudentNullStudent");
        String code = "class1";
        Student student = null;

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getTypicalSearchMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.addRelatedElement(code, student);
    }

    /**
     * Test of addRelatedElement code not found method, of class ClassGlobalSource.
     */
    @Test
    public void testEnrollStudentNotFoundCode() {
        System.out.println("Test enrollStudentNotFoundCode");
        String code = "other code";
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getTypicalSearchMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.addRelatedElement(code, student);

        assertNull(result);
    }

    /**
     * Test of addRelatedElement code not found method, of class ClassGlobalSource.
     */
    @Test
    public void testEnrollStudentNotFoundOnTitleMap() {
        System.out.println("Test enrollStudentNotFoundOnTitleMap");
        String code = "class1";
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.addRelatedElement(code, student);

        ClassSaving codeClassSaving = instance.getIdentifierMap().get(code);
        ClassSaving titleClassSaving = instance.getTypicalSearchMap().get(
                codeClassSaving.getTitle());

        assertNotNull(result);
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertNull(titleClassSaving);
    }

    /**
     * Test of Disenroll successful method, of class ClassGlobalSource.
     */
    @Test
    public void testDisenrollStudentSuccessfull() {
        System.out.println("Test disenrollStudentSuccessfull");
        String code = "class1";
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getTypicalSearchMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.addRelatedElement(code, student);

        ClassSaving codeClassSaving = instance.getIdentifierMap().get(code);
        ClassSaving titleClassSaving = instance.getTypicalSearchMap().get(
                codeClassSaving.getTitle());

        assertNotNull(result);
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertEquals(1, titleClassSaving.getStudentSet().size());

        instance.removeRelatedElement(classToSave1, student);
        assertEquals(0, codeClassSaving.getStudentSet().size());
        assertEquals(0, titleClassSaving.getStudentSet().size());
    }

    /**
     * Test of addRelatedElement null class method, of class ClassGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testDisenrollStudentNullClass() {
        System.out.println("Test disenrollStudentNullClass");
        ClassSaving classToSave1 = null;
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        instance.removeRelatedElement(classToSave1, student);
    }

    /**
     * Test of dienrollStudent null student method, of class ClassGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testDisenrollStudentNullStudent() {
        System.out.println("Test disenrollStudentNullStudent");
        String code = "class1";
        Student student = null;

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getTypicalSearchMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        instance.removeRelatedElement(classToSave1, student);
    }

    /**
     * Test of removeRelatedElement class not found method, of class
 ClassGlobalSource.
     */
    @Test
    public void testDiEnrollStudentNotFoundClass() {
        System.out.println("Test disenrollStudentNotFoundClass");
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getTypicalSearchMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.addRelatedElement(classToSave1.getCode(), student);

        ClassSaving codeClassSaving = instance.getIdentifierMap().get(
                classToSave1.getCode());
        ClassSaving titleClassSaving = instance.getTypicalSearchMap().get(
                codeClassSaving.getTitle());

        assertNotNull(result);
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertEquals(1, titleClassSaving.getStudentSet().size());

        Class anotherClass = new Class("Another code", "another title",
                "another description");
        instance.removeRelatedElement(anotherClass, student);

        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertEquals(1, titleClassSaving.getStudentSet().size());
    }

    /**
     * Test of removeRelatedElement code not found method on title map, of class
 ClassGlobalSource.
     */
    @Test
    public void testDisenrollStudentNotFoundOnTitleMap() {
        System.out.println("Test disenrollStudentNotFoundOnTitleMap");
        String code = "class1";
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.addRelatedElement(code, student);

        ClassSaving codeClassSaving = instance.getIdentifierMap().get(code);
        ClassSaving titleClassSaving = instance.getTypicalSearchMap().get(
                codeClassSaving.getTitle());

        assertNotNull(result);
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertNull(titleClassSaving);

        instance.removeRelatedElement(result, student);
        assertEquals(0, codeClassSaving.getStudentSet().size());
        assertNull(titleClassSaving);
    }

    /**
     * Test of addRelatedElement updating successful method, of class
 ClassGlobalSource.
     */
    @Test
    public void testEnrollStudentUpdatingSuccessfull() {
        System.out.println("Test enrollStudentUpdatingSuccessfull");
        String code = "class1";
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getTypicalSearchMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.addRelatedElement(code, student);

        ClassSaving codeClassSaving = instance.getIdentifierMap().get(code);
        ClassSaving titleClassSaving = instance.getTypicalSearchMap().get(
                codeClassSaving.getTitle());

        assertNotNull(result);
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertEquals(1, titleClassSaving.getStudentSet().size());

        student = new Student(1, "Test name after", "test last name after");

        result = instance.updatingRelatedElement(code, student);

        assertNotNull(result);
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertEquals(1, titleClassSaving.getStudentSet().size());

        List<Student> studentSaved = new ArrayList<Student>(codeClassSaving.
                getStudentSet());
        Student retrieveStudent = studentSaved.get(0);
        assertEquals("Test name after", retrieveStudent.getFirstName());
        assertEquals("test last name after", retrieveStudent.getLastName());

    }

    /**
     * Test of addRelatedElement updating null code method, of class
 ClassGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testEnrollStudentUpdatingNullCode() {
        System.out.println("Test enrollStudentUpdatingNullCode");
        String code = null;
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        Class result = instance.updatingRelatedElement(code, student);
    }

    /**
     * Test of addRelatedElement updating null student method, of class
 ClassGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testEnrollStudentUpdatingNullStudent() {
        System.out.println("Test enrollStudentUpdatingNullStudent");
        String code = "class1";
        Student student = null;

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getTypicalSearchMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.updatingRelatedElement(code, student);
    }

    /**
     * Test of addRelatedElement Updating code not found method, of class
 ClassGlobalSource.
     */
    @Test
    public void testEnrollStudentUpdatingNotFoundCode() {
        System.out.println("Test enrollStudentNotFoundCode");
        String code = "other code";
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getTypicalSearchMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.updatingRelatedElement(code, student);

        assertNull(result);
    }

    /**
     * Test of addRelatedElement Updating code not found method, of class
 ClassGlobalSource.
     */
    @Test
    public void testEnrollStudentUpdatingNotFoundOnTitleMap() {
        System.out.println("Test enrollStudentUpdatingNotFoundOnTitleMap");
        String code = "class1";
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.updatingRelatedElement(code, student);

        ClassSaving codeClassSaving = instance.getIdentifierMap().get(code);
        ClassSaving titleClassSaving = instance.getTypicalSearchMap().get(
                codeClassSaving.getTitle());

        assertNotNull(result);
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertNull(titleClassSaving);
    }
}
