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

    public StudentSaving retrieveByIdentifier(Integer id) {
        StudentSaving student = getIdentifierMap().get(id);

        if (student == null) {
            throw new NotFoundException("The id " + id
                    + " of a student not exist on sources");
        }

        return student;
    }

    public StudentSaving retrieveByTypicalSearch(String fullName) {
        StudentSaving student = getTypicalSearchMap().get(fullName);

        if (student == null) {
            throw new NotFoundException("The fullName " + fullName
                    + " of the student not exist on sources");
        }

        return student;
    }

    public Set<StudentSaving> retrieveAll() {
        return new TreeSet<StudentSaving>(getIdentifierMap().values());
    }

    public void add(StudentSaving studentSaving) {
        getIdentifierMap().put(studentSaving.getId(), studentSaving);
        getTypicalSearchMap().put(studentSaving.buildFullName(),
                studentSaving);
    }

    public void remove(Integer id) {
        StudentSaving student = getIdentifierMap().get(id);

        if (student == null) {
            throw new NotFoundException("The id " + id
                    + " of a student not exist on sources for removing");
        }
        getIdentifierMap().remove(id);
        getTypicalSearchMap().remove(student.buildFullName());
    }

    public void update(StudentSaving studentSaving) {
        StudentSaving retrievedStudent = retrieveByIdentifier(studentSaving.
                getId());
        getIdentifierMap().put(studentSaving.getId(), studentSaving);
        getTypicalSearchMap().remove(retrievedStudent.buildFullName());
        getTypicalSearchMap().put(studentSaving.buildFullName(),
                studentSaving);
    }

    public Student addRelatedElement(
            Integer id, com.tx.simplescheduling.model.Class classToAssign) {
        Student result = null;

        result = addRelatedElementSafely(id, getIdentifierMap(), classToAssign);
        if (result != null) {
            addRelatedElementSafely(result.buildFullName(),
                    getTypicalSearchMap(),
                    classToAssign);
        }

        return result;
    }

    public void removeRelatedElement(Student studentSaving,
            com.tx.simplescheduling.model.Class classToDisassign) {
        boolean wasRemoved = removeRelatedElementSafely(studentSaving.getId(),
                getIdentifierMap(), classToDisassign);
        if (wasRemoved) {
            removeRelatedElementSafely(studentSaving.buildFullName(),
                    getTypicalSearchMap(), classToDisassign);
        }
    }

    public Student updatingRelatedElement(
            Integer id, com.tx.simplescheduling.model.Class classToAssign) {
        Student result = null;

        result = updateRelatedElementSafely(id, getIdentifierMap(),
                classToAssign);
        updateRelatedElementSafely(id, getTypicalSearchMap(), classToAssign);
        return result;
    }

    private Student addRelatedElementSafely(Object key,
            Map map, com.tx.simplescheduling.model.Class classToAssign) {
        Student result = null;

        StudentSaving retrievedStudentSaving = (StudentSaving) map.get(key);

        if (retrievedStudentSaving != null) {
            retrievedStudentSaving.addRelatedElement(classToAssign);
            result = retrievedStudentSaving.createRelatedElement();
        }

        return result;
    }

    private boolean removeRelatedElementSafely(Object key,
            Map map, com.tx.simplescheduling.model.Class classToDisassign) {
        boolean result = false;

        StudentSaving retrievedStudentSaving = (StudentSaving) map.get(key);

        if (retrievedStudentSaving != null) {
            retrievedStudentSaving.removeRelatedElement(classToDisassign);
            result = true;
        }

        return result;
    }

    private Student updateRelatedElementSafely(
            Object key, Map map,
            com.tx.simplescheduling.model.Class classToAssign) {
        Student result = null;

        StudentSaving retrievedStudentSaving = (StudentSaving) map.get(key);

        if (retrievedStudentSaving != null) {
            retrievedStudentSaving.updateRelatedElement(classToAssign);
            result = retrievedStudentSaving.createRelatedElement();
        }

        return result;
    }

    public Map<Integer, StudentSaving> getIdentifierMap() {
        return studentIdMap;
    }

    public Map<String, StudentSaving> getTypicalSearchMap() {
        return studentNameMap;
    }

}
