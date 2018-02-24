/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.process;

import com.tx.simplescheduling.model.param.StudentParam;
import com.tx.simplescheduling.model.StudentSaving;
import com.tx.simplescheduling.source.GenericSource;
import com.tx.simplescheduling.source.StudentGlobalSource;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class StudentProcess extends GenericProcess<StudentSaving, ClassProcess, StudentGlobalSource, StudentParam> {

    private StudentGlobalSource studentGlobalSource;

    public StudentProcess() {
        studentGlobalSource = new StudentGlobalSource();
    }

    @Override
    public void setGlobalSource(StudentGlobalSource globalSource) {
        this.studentGlobalSource = globalSource;
    }

    @Override
    public StudentGlobalSource getGlobalSource() {
        return studentGlobalSource;
    }

    @Override
    public String retrieveIdentifierName() {
        return "id";
    }

    @Override
    public String retrieveTypicalSearchName() {
        return "full name";
    }

    public String retrieveClassName() {
        return "student";
    }

    @Override
    public String retrieveClassNamePlural() {
        return "students";
    }
}
