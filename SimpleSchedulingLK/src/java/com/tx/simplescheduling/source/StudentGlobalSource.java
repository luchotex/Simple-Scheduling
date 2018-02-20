/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.StudentSaving;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class StudentGlobalSource {

    private Map<Integer, StudentSaving> studentIdMap
            = new ConcurrentHashMap<Integer, StudentSaving>();
    private Map<String, StudentSaving> studentNameMap
            = new ConcurrentHashMap<String, StudentSaving>();

    public StudentGlobalSource() {
    }

    public void add(StudentSaving studentSaving) {
        getStudentIdMap().put(studentSaving.getId(), studentSaving);
        getStudentNameMap().put(studentSaving.buildFullName(),
                studentSaving);
    }

    public StudentSaving retrieveById(Integer id) {
        return getStudentIdMap().get(id);
    }

    public Set<StudentSaving> retrieveAll() {
        return new TreeSet<StudentSaving>(getStudentIdMap().values());
    }

    public Map<Integer, StudentSaving> getStudentIdMap() {
        return studentIdMap;
    }

    public void setStudentIdMap(Map<Integer, StudentSaving> studentIdMap) {
        this.studentIdMap = studentIdMap;
    }

    public Map<String, StudentSaving> getStudentNameMap() {
        return studentNameMap;
    }

    public void setStudentNameMap(
            Map<String, StudentSaving> studentNameMap) {
        this.studentNameMap = studentNameMap;
    }

}