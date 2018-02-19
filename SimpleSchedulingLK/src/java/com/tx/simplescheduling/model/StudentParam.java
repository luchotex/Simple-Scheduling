/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
@XmlRootElement (name = "studentParam")
public class StudentParam extends Student {

    private Set<String> classCodeList;

    public StudentParam() {
    }

    public StudentParam(Integer id, String firsName, String lastName) {
        super(id, firsName, lastName);
    }

    public Set<String> getClassCodeList() {
        return classCodeList;
    }

    @XmlElement
    public void setClassCodeList(Set<String> classCodeList) {
        this.classCodeList = classCodeList;
    }

    public StudentSaving createStudentSaving() {
        return new StudentSaving(id, firstName, lastName);
    }

}
