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
        classSet = new TreeSet<Class>();
    }

    public Set<Class> getClassSet() {
        return classSet;
    }

    public void setClassSet(Set<Class> classSet) {
        this.classSet = classSet;
    }

    public Student createRelatedElement() {
        return new Student(id, firstName, lastName);
    }

    public void buildRelatedElementAdding(Set<String> classCodeSet,
            ClassGlobalSource classGlobalSource) {
        for (String code : classCodeSet) {
            Class classToAdd = classGlobalSource.addRelatedElement(code, this);
            if (classToAdd != null) {
                this.classSet.add(classToAdd);
            }
        }
    }

    public void removeAllRelatedElements(ClassGlobalSource classGlobalSource) {
        for (com.tx.simplescheduling.model.Class element : getClassSet()) {
            classGlobalSource.removeRelatedElement(element, this);
        }
    }

    public void updateRelatedElements(Set<String> classCodeSet,
            ClassGlobalSource classGlobalSource) {

        for (com.tx.simplescheduling.model.Class element : this.
                getClassSet()) {
            if (!classCodeSet.contains(element.getCode())) {
                classGlobalSource.removeRelatedElement(element, this);
                this.getClassSet().remove(element);
            }
        }

        for (String code : classCodeSet) {
            com.tx.simplescheduling.model.Class created = new Class(code,
                    null, null);
            updateRetrievedRelatedElement(created, classGlobalSource, code);
        }
    }

    private void updateRetrievedRelatedElement(Class created,
            ClassGlobalSource classGlobalSource, String code) {
        if (!this.getClassSet().contains(created)) {
            com.tx.simplescheduling.model.Class classToAdd
                    = classGlobalSource.updatingRelatedElement(code, this);
            if (classToAdd != null) {
                if (getClassSet().contains(classToAdd)) {
                    getClassSet().remove(classToAdd);
                }
                this.getClassSet().add(classToAdd);
            }
        }
    }

    public void addRelatedElement(Class classToAssign) {
        getClassSet().add(classToAssign);
    }

    public void updateRelatedElement(Class classToAssign) {
        if (getClassSet().contains(classToAssign)) {
            getClassSet().remove(classToAssign);
        }
        getClassSet().add(classToAssign);
    }

    public void removeRelatedElement(Class classToDisassign) {
        getClassSet().remove(classToDisassign);
    }

    public void setValues(StudentParam studentParam) {
        this.setFirstName(studentParam.getFirstName());
        this.setLastName(studentParam.getLastName());
    }
}
