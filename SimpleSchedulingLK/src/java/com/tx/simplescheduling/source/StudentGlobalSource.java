/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.Student;
import com.tx.simplescheduling.model.StudentSaving;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class StudentGlobalSource extends
        GenericSource<Student, StudentSaving, com.tx.simplescheduling.model.Class> {

    private Map<Integer, StudentSaving> studentIdMap
            = new ConcurrentHashMap<Integer, StudentSaving>();
    private Map<String, StudentSaving> studentNameMap
            = new ConcurrentHashMap<String, StudentSaving>();

    public StudentGlobalSource() {
    }

    public Map<Integer, StudentSaving> getIdentifierMap() {
        return studentIdMap;
    }

    public Map<String, StudentSaving> getTypicalSearchMap() {
        return studentNameMap;
    }

    @Override
    public String retrieveIdentifierName() {
        return "id";
    }

    @Override
    public String retrieveTypicalSearchName() {
        return "full name";
    }

    @Override
    public String retrieveClassName() {
        return "student";
    }
}
