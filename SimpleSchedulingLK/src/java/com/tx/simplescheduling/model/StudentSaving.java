/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import com.tx.simplescheduling.source.ClassGlobalSource;
import java.util.Set;
import java.util.TreeSet;
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
        classSet = new TreeSet<Class>();
    }

    public Set<Class> getClassSet() {
        return classSet;
    }

    @XmlElement
    public void setClassSet(Set<Class> classSet) {
        this.classSet = classSet;
    }

    public void buildClasses(Set<String> classCodeSet,
            ClassGlobalSource classGlobalSource) {
        synchronized (classGlobalSource.getClassCodeMap()) {
            for (String code : classCodeSet) {
                Class classToAdd = classGlobalSource.enrollStudent(code, this);
                if (classToAdd != null) {
                    this.classSet.add(classToAdd);
                }
            }
        }
    }

    public void buildClassesUpdating(Set<Integer> classCodeSet,
            ClassGlobalSource classGlobalSource) {
        synchronized (classGlobalSource.getClassCodeMap()) {
            for (Integer code : classCodeSet) {
                Class classToAdd = classGlobalSource.enrollStudentUpdating(code,
                        this);
                if (classToAdd != null) {
                    if (classSet.contains(classToAdd)) {
                        classSet.remove(classToAdd);
                    }
                    this.classSet.add(classToAdd);
                }
            }
        }
    }
}
