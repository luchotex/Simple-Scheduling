/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.StudentParam;
import com.tx.simplescheduling.model.StudentSaving;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class StudentGlobalInfo {

    private Map<Integer, StudentSaving> studentIdMap
            = new ConcurrentHashMap<Integer, StudentSaving>();
    private Map<String, List<StudentSaving>> studentNameMap
            = new ConcurrentHashMap<String, List<StudentSaving>>();

    public StudentGlobalInfo() {
    }

    public StudentSaving addStudent(StudentParam studentParam,
            ClassGlobalInfo classGlobalInfo) {

        StudentSaving studentSaving = studentParam.createStudentSaving();
        studentSaving.buildClasses(studentParam.getClassCodeList(),
                classGlobalInfo);
        getStudentIdMap().put(studentSaving.getId(), studentSaving);
        String name = studentParam.getFirstName() + " " + studentParam.
                getLastName();
        List<StudentSaving> studentNameList = getStudentNameMap().get(name);
        List<StudentSaving> studentNameListToSave
                = new ArrayList<StudentSaving>();
        if (studentNameList != null) {
            studentNameListToSave = studentNameList;
        }

        studentNameListToSave.add(studentSaving);
        getStudentNameMap().put(name, studentNameListToSave);

        return studentSaving;
    }

    public Map<Integer, StudentSaving> getStudentIdMap() {
        return studentIdMap;
    }

    public void setStudentIdMap(Map<Integer, StudentSaving> studentIdMap) {
        this.studentIdMap = studentIdMap;
    }

    public Map<String, List<StudentSaving>> getStudentNameMap() {
        return studentNameMap;
    }

    public void setStudentNameMap(
            Map<String, List<StudentSaving>> studentNameMap) {
        this.studentNameMap = studentNameMap;
    }

}
