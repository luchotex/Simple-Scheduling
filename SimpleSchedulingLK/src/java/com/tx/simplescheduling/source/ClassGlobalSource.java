/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.ClassSaving;
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
public class ClassGlobalSource {

    private Map<String, ClassSaving> classCodeMap
            = new ConcurrentHashMap<String, ClassSaving>();
    private Map<String, ClassSaving> classTitleMap
            = new ConcurrentHashMap<String, ClassSaving>();

    public ClassSaving retrieveByCode(String code) {
        ClassSaving classSaving = getClassCodeMap().get(code);

        if (classSaving == null) {
            throw new NotFoundException("The code " + code
                    + " of a class not exist on sources");
        }

        return classSaving;
    }

    public ClassSaving retrieveByTitle(String title) {
        ClassSaving classSaving = getClassTitleMap().get(title);

        if (classSaving == null) {
            throw new NotFoundException("The title" + title
                    + " of the class not exist on sources");
        }

        return classSaving;
    }

    public Set<ClassSaving> retrieveAll() {
        return new TreeSet<ClassSaving>(getClassCodeMap().values());
    }

    public com.tx.simplescheduling.model.Class enrollStudent(String code,
            Student student) {
        com.tx.simplescheduling.model.Class result = null;

        result = enrollStudentSafely(code, getClassCodeMap(), student);
        if (result != null) {
            enrollStudentSafely(result.getTitle(), getClassTitleMap(), student);
        }

        return result;
    }

    public void add(ClassSaving classSaving) {
        getClassCodeMap().put(classSaving.getCode(), classSaving);
        getClassTitleMap().put(classSaving.getTitle(), classSaving);
    }

    public void remove(String code) {
        ClassSaving classToRemove = getClassCodeMap().get(code);

        if (classToRemove == null) {
            throw new NotFoundException("The code " + code
                    + " of a class not exist on sources for removing");
        }
        getClassCodeMap().remove(code);
        getClassTitleMap().remove(classToRemove.getTitle());
    }

    public void update(ClassSaving classSaving) {
        ClassSaving retrievedClass = retrieveByCode(classSaving.getCode());
        getClassCodeMap().put(classSaving.getCode(), classSaving);
        getClassTitleMap().remove(retrievedClass.getTitle());
        getClassTitleMap().put(classSaving.getTitle(), classSaving);
    }

    private com.tx.simplescheduling.model.Class enrollStudentSafely(Object key,
            Map map, Student student) {
        com.tx.simplescheduling.model.Class result = null;

        ClassSaving retrievedClassSaving = (ClassSaving) map.get(key);

        if (retrievedClassSaving != null) {
            retrievedClassSaving.enrollStudent(student);
            result = retrievedClassSaving.createClass();
        }

        return result;
    }

    public void disenrollStudent(com.tx.simplescheduling.model.Class classSaving,
            Student student) {
        boolean wasRemoved = disenrollStudentSafely(classSaving.getCode(),
                getClassCodeMap(), student);
        if (wasRemoved) {
            disenrollStudentSafely(classSaving.getTitle(), getClassTitleMap(),
                    student);
        }
    }

    private boolean disenrollStudentSafely(Object key,
            Map map, Student student) {
        boolean result = false;

        ClassSaving retrievedClassSaving = (ClassSaving) map.get(key);

        if (retrievedClassSaving != null) {
            retrievedClassSaving.disenrollStudent(student);
            result = true;
        }

        return result;
    }

    public com.tx.simplescheduling.model.Class enrollStudentUpdating(
            String code, Student student) {
        com.tx.simplescheduling.model.Class result = null;

        result = enrollStudentUpdatingSafely(code, getClassCodeMap(), student);
        enrollStudentUpdatingSafely(code, getClassTitleMap(), student);
        return result;
    }

    private com.tx.simplescheduling.model.Class enrollStudentUpdatingSafely(
            Object key, Map map, Student student) {
        com.tx.simplescheduling.model.Class result = null;

        ClassSaving retrievedClassSaving = (ClassSaving) map.get(key);

        if (retrievedClassSaving != null) {
            retrievedClassSaving.enrollStudentUpdating(student);
            result = retrievedClassSaving.createClass();
        }

        return result;
    }

    public Map<String, ClassSaving> getClassCodeMap() {
        return classCodeMap;
    }

    public void setClassCodeMap(Map<String, ClassSaving> classCodeMap) {
        this.classCodeMap = classCodeMap;
    }

    public Map<String, ClassSaving> getClassTitleMap() {
        return classTitleMap;
    }

    public void setClassTitleMap(Map<String, ClassSaving> classTitleMap) {
        this.classTitleMap = classTitleMap;
    }

}
