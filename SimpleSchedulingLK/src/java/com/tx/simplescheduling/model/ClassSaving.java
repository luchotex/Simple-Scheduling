/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import com.tx.simplescheduling.source.StudentGlobalSource;
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
        studentSet = new TreeSet<Student>();
    }

    public ClassSaving(String code, String title, String description) {
        super(code, title, description);
        studentSet = new TreeSet<Student>();
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Class createRelatedElement() {
        return new Class(code, title, description);
    }

    public void buildRelatedElementAdding(Set<Integer> studentIdSet,
            StudentGlobalSource studentGlobalSource) {
        for (Integer id : studentIdSet) {
            Student studentToAdd = studentGlobalSource.addRelatedElement(id,
                    this);
            if (studentToAdd != null) {
                this.studentSet.add(studentToAdd);
            }
        }
    }

    public void removeAllRelatedElements(StudentGlobalSource studentGlobalSource) {
        for (Student element : getStudentSet()) {
            studentGlobalSource.removeRelatedElement(element, this);
        }
    }

    public void updateRelatedElements(Set<Integer> studentIdSet,
            StudentGlobalSource studentGlobalSource) {

        for (Student element : this.getStudentSet()) {
            if (!studentIdSet.contains(element.getId())) {
                studentGlobalSource.removeRelatedElement(element, this);
                this.getStudentSet().remove(element);
            }
        }

        for (Integer id : studentIdSet) {
            Student created = new Student(id, null, null);
            updateRetrievedRelatedElement(created, studentGlobalSource, id);
        }
    }

    private void updateRetrievedRelatedElement(Student created,
            StudentGlobalSource studentGlobalSource, Integer id) {
        if (!this.getStudentSet().contains(created)) {
            Student studentToAdd
                    = studentGlobalSource.updatingRelatedElement(id, this);
            if (studentToAdd != null) {
                if (getStudentSet().contains(studentToAdd)) {
                    getStudentSet().remove(studentToAdd);
                }
                this.getStudentSet().add(studentToAdd);
            }
        }
    }

    public void addRelatedElement(Student student) {
        getStudentSet().add(student);
    }

    public void updateRelatedElement(Student student) {
        if (getStudentSet().contains(student)) {
            getStudentSet().remove(student);
        }
        getStudentSet().add(student);
    }

    public void removeRelatedElement(Student student) {
        getStudentSet().remove(student);
    }

    public void setValues(ClassParam classParam) {
        this.setTitle(classParam.getTitle());
        this.setDescription(classParam.getDescription());
    }
}
