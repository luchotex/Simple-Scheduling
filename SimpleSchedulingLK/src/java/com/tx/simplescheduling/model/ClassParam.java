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
@XmlRootElement
public class ClassParam extends Class {

    private Set<Integer> studentIdList;

    public ClassParam() {
    }

    public ClassParam(String code, String title, String description) {
        super(code, title, description);
    }

    public Set<Integer> getStudentIdList() {
        return studentIdList;
    }

    @XmlElement
    public void setStudentIdList(Set<Integer> studentIdList) {
        this.studentIdList = studentIdList;
    }

    public ClassSaving createSavingInstance() {
        ClassSaving result = new ClassSaving(code, title, description);
        return result;
    }

}
