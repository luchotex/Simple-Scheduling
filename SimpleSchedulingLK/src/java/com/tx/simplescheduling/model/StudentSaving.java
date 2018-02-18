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
public class StudentSaving extends Student {

    private Set<Class> classSet;

    public StudentSaving() {
    }

    public StudentSaving(Integer id, String firsName, String lastName) {
        super(id, firsName, lastName);
    }

    public Set<Class> getClassSet() {
        return classSet;
    }

    @XmlElement
    public void setClassSet(Set<Class> classSet) {
        this.classSet = classSet;
    }

}
