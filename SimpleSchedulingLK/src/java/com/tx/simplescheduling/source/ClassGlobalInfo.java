/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.model.Student;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class ClassGlobalInfo {

    private Map<String, ClassSaving> classCodeMap
            = new ConcurrentHashMap<String, ClassSaving>();
    private Map<String, ClassSaving> classTitleMap
            = new ConcurrentHashMap<String, ClassSaving>();

    public ClassGlobalInfo() {
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

    public com.tx.simplescheduling.model.Class enrollStudentUpdating(
            Integer code,
            Student student) {
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
