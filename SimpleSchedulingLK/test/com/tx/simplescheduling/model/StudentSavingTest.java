/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import com.tx.simplescheduling.process.ClassProcess;
import java.util.Set;
import java.util.TreeSet;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class StudentSavingTest {

    public StudentSavingTest() {
    }

    /**
     * Test of buildClasses null class global info, of class StudentSaving.
     */
    @Test
    public void testBuildClassesSuccesful() {
        System.out.println("Test buildClassesSuccesful");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        ClassProcess classGlobalInfo = new ClassProcess();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        classGlobalInfo.getClassCodeMap().put(classToSave1.getCode(),
                classToSave1);
        classGlobalInfo.getClassCodeMap().put(classToSave2.getCode(),
                classToSave2);
        classGlobalInfo.getClassTitleMap().put(classToSave1.getTitle(),
                classToSave1);
        classGlobalInfo.getClassTitleMap().put(classToSave2.getTitle(),
                classToSave2);
        StudentSaving instance = new StudentSaving(1, "Test name",
                "test last name");
        instance.buildClasses(codeSet, classGlobalInfo);

        assertEquals(2, instance.getClassSet().size());
        assertEquals(1, classToSave1.getStudentSet().size());
        assertEquals(1, classToSave2.getStudentSet().size());
    }

    /**
     * Test of buildClasses null class global info, of class StudentSaving.
     */
    @Test
    public void testBuildClassesSuccesfulButNotOneClass() {
        System.out.println("Test buildClassesSuccesfulButNotOneClass");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class3");
        ClassProcess classGlobalInfo = new ClassProcess();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        classGlobalInfo.getClassCodeMap().put(classToSave1.getCode(),
                classToSave1);
        classGlobalInfo.getClassCodeMap().put(classToSave2.getCode(),
                classToSave2);
        classGlobalInfo.getClassTitleMap().put(classToSave1.getTitle(),
                classToSave1);
        classGlobalInfo.getClassTitleMap().put(classToSave2.getTitle(),
                classToSave2);
        StudentSaving instance = new StudentSaving(1, "Test name",
                "test last name");
        instance.buildClasses(codeSet, classGlobalInfo);

        assertEquals(1, instance.getClassSet().size());
        assertEquals(1, classToSave1.getStudentSet().size());
        assertEquals(0, classToSave2.getStudentSet().size());
    }

    /**
     * Test of buildClasses null class global info, of class StudentSaving.
     */
    @Test(expected = NullPointerException.class)
    public void testBuildClassesNullCodeSet() {
        System.out.println("Test buildClassesNullCodeSet");
        ClassProcess classGlobalInfo = new ClassProcess();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        classGlobalInfo.getClassCodeMap().put(classToSave1.getCode(),
                classToSave1);
        classGlobalInfo.getClassCodeMap().put(classToSave2.getCode(),
                classToSave2);
        classGlobalInfo.getClassTitleMap().put(classToSave1.getTitle(),
                classToSave1);
        classGlobalInfo.getClassTitleMap().put(classToSave2.getTitle(),
                classToSave2);
        Set<String> codeSet = null;

        StudentSaving instance = new StudentSaving();
        instance.buildClasses(codeSet, classGlobalInfo);
    }

    /**
     * Test of buildClasses null class global info, of class StudentSaving.
     */
    @Test(expected = NullPointerException.class)
    public void testBuildClassesNullClassGlobalInfo() {
        System.out.println("Test buildClassesNullClassGlobalInfo");
        ClassProcess classGlobalInfo = null;

        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");

        StudentSaving instance = new StudentSaving();
        instance.buildClasses(codeSet, classGlobalInfo);
    }

//
//    /**
//     * Test of buildClassesUpdating method, of class StudentSaving.
//     */
//    @Test
//    public void testBuildClassesUpdating() {
//        System.out.println("buildClassesUpdating");
//        Set<Integer> classCodeSet = null;
//        ClassGlobalInfo classGlobalInfo = null;
//        StudentSaving instance = new StudentSaving();
//        instance.buildClassesUpdating(classCodeSet, classGlobalInfo);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
