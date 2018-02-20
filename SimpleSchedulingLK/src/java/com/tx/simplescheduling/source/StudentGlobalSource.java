/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.Student;
import com.tx.simplescheduling.model.StudentSaving;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.NotFoundException;

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

    public StudentSaving retrieveById(Integer id) {
        StudentSaving student = getStudentIdMap().get(id);

        if (student == null) {
            throw new NotFoundException("The id " + id
                    + " of a student not exist on sources");
        }

        return student;
    }

    public StudentSaving retrieveByFullName(String fullName) {
        StudentSaving student = getStudentNameMap().get(fullName);

        if (student == null) {
            throw new NotFoundException("The fullName " + fullName
                    + " of the student not exist on sources");
        }

        return student;
    }

    public Set<StudentSaving> retrieveAll() {
        return new TreeSet<StudentSaving>(getStudentIdMap().values());
    }

    public void add(StudentSaving studentSaving) {
        getStudentIdMap().put(studentSaving.getId(), studentSaving);
        getStudentNameMap().put(studentSaving.buildFullName(),
                studentSaving);
    }

    public void remove(Integer id) {
        StudentSaving student = getStudentIdMap().get(id);

        if (student == null) {
            throw new NotFoundException("The id " + id
                    + " of a student not exist on sources for removing");
        }
        getStudentIdMap().remove(id);
        getStudentNameMap().remove(student.buildFullName());
    }

    public void update(StudentSaving studentSaving) {
        StudentSaving retrievedStudent = retrieveById(studentSaving.getId());
        getStudentIdMap().put(studentSaving.getId(), studentSaving);
        getStudentNameMap().remove(retrievedStudent.buildFullName());
        getStudentNameMap().put(studentSaving.buildFullName(),
                studentSaving);
    }

    private Student assignClassSafely(Object key,
            Map map, com.tx.simplescheduling.model.Class classToAssign) {
        Student result = null;

        StudentSaving retrievedStudentSaving = (StudentSaving) map.get(key);

        if (retrievedStudentSaving != null) {
            retrievedStudentSaving.assignClass(classToAssign);
            result = retrievedStudentSaving.createStudent();
        }

        return result;
    }

    public void disassignClass(Student studentSaving,
            com.tx.simplescheduling.model.Class classToDisassign) {
        boolean wasRemoved = disassignClassSafely(studentSaving.getId(),
                getStudentIdMap(), classToDisassign);
        if (wasRemoved) {
            disassignClassSafely(studentSaving.buildFullName(),
                    getStudentNameMap(), classToDisassign);
        }
    }

    private boolean disassignClassSafely(Object key,
            Map map, com.tx.simplescheduling.model.Class classToDisassign) {
        boolean result = false;

        StudentSaving retrievedStudentSaving = (StudentSaving) map.get(key);

        if (retrievedStudentSaving != null) {
            retrievedStudentSaving.disassignClass(classToDisassign);
            result = true;
        }

        return result;
    }

    public Student assignClassUpdating(
            Integer id, com.tx.simplescheduling.model.Class classToAssign) {
        Student result = null;

        result = assignClassUpdatingSafely(id, getStudentIdMap(), classToAssign);
        assignClassUpdatingSafely(id, getStudentNameMap(), classToAssign);
        return result;
    }

    private Student assignClassUpdatingSafely(
            Object key, Map map, com.tx.simplescheduling.model.Class classToAssign) {
        Student result = null;

        StudentSaving retrievedStudentSaving = (StudentSaving) map.get(key);

        if (retrievedStudentSaving != null) {
            retrievedStudentSaving.assignClassUpdating(classToAssign);
            result = retrievedStudentSaving.createStudent();
        }

        return result;
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
