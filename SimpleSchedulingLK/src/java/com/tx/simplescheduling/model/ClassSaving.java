/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import java.util.Set;
import java.util.TreeSet;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
@XmlRootElement
public class ClassSaving extends Class {

    private Set<Student> studentSet;

    public ClassSaving() {
    }

    public ClassSaving(String code, String title, String description) {
        super(code, title, description);
        setRelatedElementSet(new TreeSet<Student>());
    }

    @Override
    public void setValues(ClassParam classParam) {
        this.setTitle(classParam.getTitle());
        this.setDescription(classParam.getDescription());
    }

    @Override
    public Set<Student> getRelatedElementSet() {
        return studentSet;
    }

    @Override
    public void setRelatedElementSet(Set studentSet) {
        this.studentSet = studentSet;
    }

    @Override
    public Class createSubInstance() {
        return new Class(code, title, description);
    }

}
