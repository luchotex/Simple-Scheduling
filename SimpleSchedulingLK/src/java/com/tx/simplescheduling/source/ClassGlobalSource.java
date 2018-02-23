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
public class ClassGlobalSource extends
        GenericSource<com.tx.simplescheduling.model.Class, ClassSaving, Student> {

    private Map<String, ClassSaving> classCodeMap
            = new ConcurrentHashMap<String, ClassSaving>();
    private Map<String, ClassSaving> classTitleMap
            = new ConcurrentHashMap<String, ClassSaving>();

    public Map<String, ClassSaving> getIdentifierMap() {
        return classCodeMap;
    }

    public Map<String, ClassSaving> getTypicalSearchMap() {
        return classTitleMap;
    }

    @Override
    public String retrieveIdentifierName() {
        return "code";
    }

    @Override
    public String retrieveTypicalSearchName() {
        return "title";
    }

    @Override
    public String retrieveClassName() {
        return "class";
    }

}
