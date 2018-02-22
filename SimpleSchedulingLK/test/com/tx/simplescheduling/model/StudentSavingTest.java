/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import com.tx.simplescheduling.source.ClassGlobalSource;
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
     * Test of buildRelatedElementAdding null class global source, of class StudentSaving.
     */
    @Test
    public void testBuildClassesSuccesful() {
        System.out.println("Test buildClassesSuccesful");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        ClassGlobalSource classGlobalSource = buildDefaultClassGlobalSource();
        StudentSaving instance = new StudentSaving(1, "Test name",
                "test last name");
        instance.buildRelatedElementAdding(codeSet, classGlobalSource);

        assertEquals(2, instance.getClassSet().size());
        assertEquals(1, classGlobalSource.getIdentifierMap().get("class1").
                getStudentSet().size());
        assertEquals(1, classGlobalSource.getIdentifierMap().get("class2").
                getStudentSet().size());
    }

    /**
     * Test of buildRelatedElementAdding null class global source, of class StudentSaving.
     */
    @Test
    public void testBuildClassesSuccesfulButNotOneClass() {
        System.out.println("Test buildClassesSuccesfulButNotOneClass");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class3");
        ClassGlobalSource classGlobalSource = buildDefaultClassGlobalSource();
        StudentSaving instance = new StudentSaving(1, "Test name",
                "test last name");
        instance.buildRelatedElementAdding(codeSet, classGlobalSource);

        assertEquals(1, instance.getClassSet().size());
        assertEquals(1, classGlobalSource.getIdentifierMap().get("class1").
                getStudentSet().size());
        assertEquals(0, classGlobalSource.getIdentifierMap().get("class2").
                getStudentSet().size());
    }

    /**
     * Test of buildRelatedElementAdding null class global source, of class StudentSaving.
     */
    @Test(expected = NullPointerException.class)
    public void testBuildClassesNullCodeSet() {
        System.out.println("Test buildClassesNullCodeSet");
        ClassGlobalSource classGlobalSource = buildDefaultClassGlobalSource();
        Set<String> codeSet = null;

        StudentSaving instance = new StudentSaving();
        instance.buildRelatedElementAdding(codeSet, classGlobalSource);
    }

    /**
     * Test of buildRelatedElementAdding null class global source, of class StudentSaving.
     */
    @Test(expected = NullPointerException.class)
    public void testBuildClassesNullClassGlobalSource() {
        System.out.println("Test buildClassesNullClassGlobalSource");
        ClassGlobalSource classGlobalSource = null;

        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");

        StudentSaving instance = new StudentSaving();
        instance.buildRelatedElementAdding(codeSet, classGlobalSource);
    }

    /**
     * Test of DisenrollAllClasses successful, of class StudentSaving.
     */
    @Test
    public void testDisenrollAllClassesSuccessful() {
        System.out.println("Test disenrollAllClassesSuccessful");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        ClassGlobalSource classGlobalSource = buildDefaultClassGlobalSource();
        StudentSaving instance = new StudentSaving(1, "Test name",
                "test last name");
        instance.buildRelatedElementAdding(codeSet, classGlobalSource);
        assertEquals(2, instance.getClassSet().size());
        assertEquals(1, classGlobalSource.getIdentifierMap().get("class1").
                getStudentSet().size());
        assertEquals(1, classGlobalSource.getIdentifierMap().get("class2").
                getStudentSet().size());

        instance.removeAllRelatedElements(classGlobalSource);
        assertEquals(2, instance.getClassSet().size());
        assertEquals(0, classGlobalSource.getIdentifierMap().get("class1").
                getStudentSet().size());
        assertEquals(0, classGlobalSource.getIdentifierMap().get("class2").
                getStudentSet().size());
    }

    /**
     * Test of removeAllRelatedElements but not one Class, of class StudentSaving.
     */
    @Test
    public void testDisenrollAllClassesButNotOneClass() {
        System.out.println("Test disenrollAllClassesButNotOneClass");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class3");
        ClassGlobalSource classGlobalSource = buildDefaultClassGlobalSource();
        StudentSaving instance = new StudentSaving(1, "Test name",
                "test last name");
        instance.buildRelatedElementAdding(codeSet, classGlobalSource);

        assertEquals(1, instance.getClassSet().size());
        assertEquals(1, classGlobalSource.getIdentifierMap().get("class1").
                getStudentSet().size());
        assertEquals(0, classGlobalSource.getIdentifierMap().get("class2").
                getStudentSet().size());

        instance.removeAllRelatedElements(classGlobalSource);
        assertEquals(1, instance.getClassSet().size());
        assertEquals(0, classGlobalSource.getIdentifierMap().get("class1").
                getStudentSet().size());
        assertEquals(0, classGlobalSource.getIdentifierMap().get("class2").
                getStudentSet().size());

    }

    /**
     * Test of removeAllRelatedElements null code set, of class StudentSaving.
     */
    @Test(expected = NullPointerException.class)
    public void testDisenrollAllClassesNullCodeSet() {
        System.out.println("Test disenrollAllClassesNullCodeSet");
        ClassGlobalSource classGlobalSource = buildDefaultClassGlobalSource();

        StudentSaving instance = new StudentSaving();
        instance.removeAllRelatedElements(classGlobalSource);
    }

    /**
     * Test of removeAllRelatedElements null class global source, of class
 StudentSaving.
     */
    @Test(expected = NullPointerException.class)
    public void testDisenrollAllClassesNullClassGlobalSource() {
        System.out.println("Test disenrollAllClassesNullClassGlobalSource");
        ClassGlobalSource classGlobalSource = null;

        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");

        StudentSaving instance = new StudentSaving();
        instance.removeAllRelatedElements(classGlobalSource);
    }

    /**
     * Test of buildRelatedElementAdding updating successful , of class StudentSaving.
     */
    @Test
    public void testBuildClassesUpdatingSuccesful() {
        System.out.println("Test buildClassesUpdatingSuccesful");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        ClassGlobalSource classGlobalSource = buildDefaultClassGlobalSource();

        ClassSaving classToSave3 = new ClassSaving("class3", "class3", "class3");
        classGlobalSource.getIdentifierMap().put(classToSave3.getCode(),
                classToSave3);
        classGlobalSource.getTypicalSearchMap().put(classToSave3.getTitle(),
                classToSave3);

        StudentSaving instance = new StudentSaving(1, "Test name",
                "test last name");
        instance.buildRelatedElementAdding(codeSet, classGlobalSource);

        assertEquals(2, instance.getClassSet().size());
        assertEquals(1, classGlobalSource.getIdentifierMap().get("class1").
                getStudentSet().size());
        assertEquals(1, classGlobalSource.getIdentifierMap().get("class2").
                getStudentSet().size());

        codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class3");
        
        instance.updateRelatedElements(codeSet, classGlobalSource);
        
        assertEquals(2, instance.getClassSet().size());
        assertEquals(1, classGlobalSource.getIdentifierMap().get("class1").
                getStudentSet().size());
        assertEquals(1, classGlobalSource.getIdentifierMap().get("class3").
                getStudentSet().size());
    }

    /**
     * Test of buildRelatedElementAdding updating null class global source, of class StudentSaving.
     */
    @Test(expected = NullPointerException.class)
    public void testBuildClassesUpdatingNullCodeSet() {
        System.out.println("Test buildClassesUpdatingNullCodeSet");
        ClassGlobalSource classGlobalSource = buildDefaultClassGlobalSource();
        Set<String> codeSet = null;

        StudentSaving instance = new StudentSaving();
        instance.updateRelatedElements(codeSet, classGlobalSource);
    }

    /**
     * Test of buildRelatedElementAdding updating null class global source, of class StudentSaving.
     */
    @Test(expected = NullPointerException.class)
    public void testBuildClassesUpdatingNullClassGlobalSource() {
        System.out.println("Test buildClassesUpdatingNullClassGlobalSource");
        ClassGlobalSource classGlobalSource = null;

        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");

        StudentSaving instance = new StudentSaving();
        instance.buildRelatedElementAdding(codeSet, classGlobalSource);
    }
    
    private ClassGlobalSource buildDefaultClassGlobalSource() {
        ClassGlobalSource classGlobalSource = new ClassGlobalSource();
        ClassSaving classToSave1 = new ClassSaving("class1", "class1", "class1");
        ClassSaving classToSave2 = new ClassSaving("class2", "class2", "class2");
        classGlobalSource.getIdentifierMap().put(classToSave1.getCode(),
                classToSave1);
        classGlobalSource.getIdentifierMap().put(classToSave2.getCode(),
                classToSave2);
        classGlobalSource.getTypicalSearchMap().put(classToSave1.getTitle(),
                classToSave1);
        classGlobalSource.getTypicalSearchMap().put(classToSave2.getTitle(),
                classToSave2);
        return classGlobalSource;
    }
}
