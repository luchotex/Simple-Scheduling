/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
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
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    @XmlElement
    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

}
