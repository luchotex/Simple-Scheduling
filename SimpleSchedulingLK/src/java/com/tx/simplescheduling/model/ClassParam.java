/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import java.util.Set;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
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

    public void setStudentIdList(Set<Integer> studentIdList) {
        this.studentIdList = studentIdList;
    }

    public ClassSaving createSavingInstance() {
        ClassSaving result = new ClassSaving(code, title, description);
        return result;
    }

}
