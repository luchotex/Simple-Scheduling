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

    public void disenrollAllClasses(ClassGlobalSource classGlobalSource) {
        synchronized (classGlobalSource.getClassCodeMap()) {
            for (com.tx.simplescheduling.model.Class element : getClassSet()) {
                classGlobalSource.disenrollStudent(element, this);
            }
        }
    }

    public void buildClassesUpdating(Set<String> classCodeSet,
            ClassGlobalSource classGlobalSource) {
        synchronized (classGlobalSource.getClassCodeMap()) {

            for (com.tx.simplescheduling.model.Class element : this.
                    getClassSet()) {
                if (!classCodeSet.contains(element.getCode())) {
                    classGlobalSource.disenrollStudent(element, this);
                    this.getClassSet().remove(element);
                }
            }

            for (String code : classCodeSet) {
                com.tx.simplescheduling.model.Class created = new Class(code,
                        null, null);
                enrollClassUpdating(created, classGlobalSource, code);
            }
        }
    }

    private void enrollClassUpdating(Class created,
            ClassGlobalSource classGlobalSource, String code) {
        if (!this.getClassSet().contains(created)) {
            com.tx.simplescheduling.model.Class classToAdd
                    = classGlobalSource.enrollStudentUpdating(code, this);
            if (classToAdd != null) {
                if (classSet.contains(classToAdd)) {
                    classSet.remove(classToAdd);
                }
                this.classSet.add(classToAdd);
            }
        }
    }

    public void setValues(StudentParam studentParam) {
        this.setFirstName(studentParam.getFirstName());
        this.setLastName(studentParam.getLastName());
    }
}
