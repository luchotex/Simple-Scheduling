/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.process;

import com.tx.simplescheduling.model.param.ClassParam;
import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.model.StudentSaving;
import com.tx.simplescheduling.model.param.StudentParam;
import com.tx.simplescheduling.source.ClassGlobalSource;
import com.tx.simplescheduling.source.StudentGlobalSource;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class ClassProcess extends GenericProcess <ClassSaving, StudentProcess, ClassGlobalSource, ClassParam> {

    private ClassGlobalSource classGlobalSource;

    public ClassProcess() {
        classGlobalSource = new ClassGlobalSource();
    }

    public ClassGlobalSource getGlobalSource() {
        return classGlobalSource;
    }

    public void setGlobalSource(ClassGlobalSource classGlobalSource) {
        this.classGlobalSource = classGlobalSource;
    }

    public String retrieveIdentifierName() {
        return "code";
    }

    public String retrieveTypicalSearchName() {
        return "title";
    }

    public String retrieveClassName() {
        return "class";
    }

    public String retrieveClassNamePlural() {
        return "classes";
    }

}
