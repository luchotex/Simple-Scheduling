/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.Class;
import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.model.Student;
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
     * Test of enrollStudent successful method, of class ClassGlobalSource.
     */
    @Test
    public void testEnrollStudentSuccessfull() {
        System.out.println("Test enrollStudentSuccessfull");
        String code = "class1";
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getClassCodeMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getClassCodeMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getClassTitleMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getClassTitleMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.enrollStudent(code, student);

        ClassSaving codeClassSaving = instance.getClassCodeMap().get(code);
        ClassSaving titleClassSaving = instance.getClassTitleMap().get(
                codeClassSaving.getTitle());

        assertNotNull(result);
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertEquals(1, titleClassSaving.getStudentSet().size());
    }

    /**
     * Test of enrollStudent null code method, of class ClassGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testEnrollStudentNullCode() {
        System.out.println("Test enrollStudentNullCode");
        String code = null;
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        Class result = instance.enrollStudent(code, student);
    }

    /**
     * Test of enrollStudent null student method, of class ClassGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testEnrollStudentNullStudent() {
        System.out.println("Test enrollStudentNullStudent");
        String code = "class1";
        Student student = null;

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getClassCodeMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getClassCodeMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getClassTitleMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getClassTitleMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.enrollStudent(code, student);
    }

    /**
     * Test of enrollStudent code not found method, of class ClassGlobalSource.
     */
    @Test
    public void testEnrollStudentNotFoundCode() {
        System.out.println("Test enrollStudentNotFoundCode");
        String code = "other code";
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getClassCodeMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getClassCodeMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getClassTitleMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getClassTitleMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.enrollStudent(code, student);

        assertNull(result);
    }

    /**
     * Test of enrollStudent code not found method, of class ClassGlobalSource.
     */
    @Test
    public void testEnrollStudentNotFoundOnTitleMap() {
        System.out.println("Test enrollStudentNotFoundOnTitleMap");
        String code = "class1";
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getClassCodeMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getClassCodeMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getClassTitleMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.enrollStudent(code, student);

        ClassSaving codeClassSaving = instance.getClassCodeMap().get(code);
        ClassSaving titleClassSaving = instance.getClassTitleMap().get(
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
        instance.getClassCodeMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getClassCodeMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getClassTitleMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getClassTitleMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.enrollStudent(code, student);

        ClassSaving codeClassSaving = instance.getClassCodeMap().get(code);
        ClassSaving titleClassSaving = instance.getClassTitleMap().get(
                codeClassSaving.getTitle());

        assertNotNull(result);
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertEquals(1, titleClassSaving.getStudentSet().size());

        instance.disenrollStudent(classToSave1, student);
        assertEquals(0, codeClassSaving.getStudentSet().size());
        assertEquals(0, titleClassSaving.getStudentSet().size());
    }

    /**
     * Test of enrollStudent null class method, of class ClassGlobalSource.
     */
    @Test(expected = NullPointerException.class)
    public void testDisenrollStudentNullClass() {
        System.out.println("Test disenrollStudentNullClass");
        ClassSaving classToSave1 = null;
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        instance.disenrollStudent(classToSave1, student);
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
        instance.getClassCodeMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getClassCodeMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getClassTitleMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getClassTitleMap().put(classToSave2.getTitle(),
                classToSave2);
        instance.disenrollStudent(classToSave1, student);
    }

    /**
     * Test of disenrollStudent class not found method, of class
     * ClassGlobalSource.
     */
    @Test
    public void testDiEnrollStudentNotFoundClass() {
        System.out.println("Test disenrollStudentNotFoundClass");
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getClassCodeMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getClassCodeMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getClassTitleMap().put(classToSave1.getTitle(),
                classToSave1);
        instance.getClassTitleMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.enrollStudent(classToSave1.getCode(), student);

        ClassSaving codeClassSaving = instance.getClassCodeMap().get(
                classToSave1.getCode());
        ClassSaving titleClassSaving = instance.getClassTitleMap().get(
                codeClassSaving.getTitle());

        assertNotNull(result);
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertEquals(1, titleClassSaving.getStudentSet().size());

        Class anotherClass = new Class("Another code", "another title",
                "another description");
        instance.disenrollStudent(anotherClass, student);
        
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertEquals(1, titleClassSaving.getStudentSet().size());
    }

    /**
     * Test of disenrollStudent code not found method on title map, of class ClassGlobalSource.
     */
    @Test
    public void testDisenrollStudentNotFoundOnTitleMap() {
        System.out.println("Test disenrollStudentNotFoundOnTitleMap");
        String code = "class1";
        Student student = new Student(1, "Test name", "test last name");

        ClassGlobalSource instance = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        instance.getClassCodeMap().put(classToSave1.getCode(),
                classToSave1);
        instance.getClassCodeMap().put(classToSave2.getCode(),
                classToSave2);
        instance.getClassTitleMap().put(classToSave2.getTitle(),
                classToSave2);
        Class result = instance.enrollStudent(code, student);

        ClassSaving codeClassSaving = instance.getClassCodeMap().get(code);
        ClassSaving titleClassSaving = instance.getClassTitleMap().get(
                codeClassSaving.getTitle());

        assertNotNull(result);
        assertEquals(1, codeClassSaving.getStudentSet().size());
        assertNull(titleClassSaving);
        
        instance.disenrollStudent(result, student);
        assertEquals(0, codeClassSaving.getStudentSet().size());
        assertNull(titleClassSaving);
    }

//    /**
//     * Test of enrollStudentUpdating method, of class ClassGlobalSource.
//     */
//    @Test
//    public void testEnrollStudentUpdating() {
//        System.out.println("enrollStudentUpdating");
//        Integer code = null;
//        Student student = null;
//        ClassGlobalSource instance = new ClassGlobalSource();
//        Class expResult = null;
//        Class result = instance.enrollStudentUpdating(code, student);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
