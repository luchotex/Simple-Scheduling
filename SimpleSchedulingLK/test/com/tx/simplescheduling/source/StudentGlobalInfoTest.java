/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.model.StudentParam;
import com.tx.simplescheduling.model.StudentSaving;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class StudentGlobalInfoTest {

    public StudentGlobalInfoTest() {
    }

    /**
     * Test of addStudent successful method, of class StudentGlobalInfo.
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
        ClassGlobalInfo classGlobalInfo = new ClassGlobalInfo();
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
        StudentGlobalInfo instance = new StudentGlobalInfo();
        StudentSaving result = instance.addStudent(student, classGlobalInfo);

        Map<Integer, StudentSaving> studentIdMap
                = instance.getStudentIdMap();

        Map<String, StudentSaving> studentNameMap
                = instance.getStudentNameMap();
        StudentSaving savedStudent = studentIdMap.get(1);

        assertEquals(1, studentIdMap.size());
        assertEquals(1, studentNameMap.size());
        assertEquals(2, savedStudent.getClassSet().size());
    }

    /**
     * Test of addStudent null class global info, of class StudentGlobalInfo.
     */
    @Test(expected = NullPointerException.class)
    public void testAddStudentNullStudentParam() {
        System.out.println("Test addStudentNullStudentParam");
        StudentParam student = null;
        ClassGlobalInfo classGlobalInfo = new ClassGlobalInfo();
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
        StudentGlobalInfo instance = new StudentGlobalInfo();
        StudentSaving result = instance.addStudent(student, classGlobalInfo);
    }

    /**
     * Test of addStudent null class global info, of class StudentGlobalInfo.
     */
    @Test(expected = NullPointerException.class)
    public void testAddStudentNullClassGlobalInfo() {
        System.out.println("Test AddStudentNullClassGlobalInfo");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        Set<String> codeSet = new TreeSet<String>();
        codeSet.add("class1");
        codeSet.add("class2");
        student.setClassCodeList(codeSet);
        ClassGlobalInfo classGlobalInfo = null;
        StudentGlobalInfo instance = new StudentGlobalInfo();
        StudentSaving result = instance.addStudent(student, classGlobalInfo);
    }

    /**
     * Test of addStudent null class global info, of class StudentGlobalInfo.
     */
    @Test(expected = NullPointerException.class)
    public void testAddStudentNullClassCodeList() {
        System.out.println("Test AddStudentNullClassGlobalInfo");
        StudentParam student = new StudentParam(1, "Test name",
                "test last name");
        ClassGlobalInfo classGlobalInfo = new ClassGlobalInfo();
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
        StudentGlobalInfo instance = new StudentGlobalInfo();
        StudentSaving result = instance.addStudent(student, classGlobalInfo);
    }

}
