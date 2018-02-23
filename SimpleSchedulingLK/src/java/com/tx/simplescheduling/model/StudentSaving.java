/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import com.tx.simplescheduling.source.ClassGlobalSource;
import java.util.Set;
import java.util.TreeSet;
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
        setRelatedElementSet(new TreeSet<Class>());
    }

    @Override
    public void setValues(StudentParam studentParam) {
        this.setFirstName(studentParam.getFirstName());
        this.setLastName(studentParam.getLastName());
    }

    @Override
    public Set<Class> getRelatedElementSet() {
        return classSet;
    }

    @Override
    public void setRelatedElementSet(Set classSet) {
        this.classSet = classSet;
    }

    @Override
    public Student createSubInstance() {
        return new Student(id, firstName, lastName);
    }
}
