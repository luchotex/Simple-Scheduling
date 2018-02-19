/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.ClassSaving;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class ClassGlobalInfo {

    private Map<Integer, ClassSaving> classCodeMap
            = new ConcurrentHashMap<Integer, ClassSaving>();
    private Map<String, List<ClassSaving>> classTitleMap
            = new ConcurrentHashMap<String, List<ClassSaving>>();

    public ClassGlobalInfo() {
    }

    public com.tx.simplescheduling.model.Class retrieveClass(Integer code) {
        com.tx.simplescheduling.model.Class result = null;

        ClassSaving classSaving = getClassCodeMap().get(code);

        if (classSaving != null) {
            result = classSaving.createClass();
        }

        return result;
    }

    public Map<Integer, ClassSaving> getClassCodeMap() {
        return classCodeMap;
    }

    public void setClassCodeMap(Map<Integer, ClassSaving> classCodeMap) {
        this.classCodeMap = classCodeMap;
    }

    public Map<String, List<ClassSaving>> getClassTitleMap() {
        return classTitleMap;
    }

    public void setClassTitleMap(
            Map<String, List<ClassSaving>> classTitleMap) {
        this.classTitleMap = classTitleMap;
    }

}
