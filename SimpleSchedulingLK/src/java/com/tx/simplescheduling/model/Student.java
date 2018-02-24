/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import com.tx.simplescheduling.model.param.StudentParam;
import com.tx.simplescheduling.source.ClassGlobalSource;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
@XmlRootElement
public class Student extends GenericModel<ClassGlobalSource, Student, 
        com.tx.simplescheduling.model.Class, StudentParam>
        implements Comparable<Student> {

    protected Integer id;
    protected String firstName;
    protected String lastName;

    public Student() {
    }

    public Student(Integer id, String firsName, String lastName) {
        this.id = id;
        this.firstName = firsName;
        this.lastName = lastName;
    }

    public String buildFullName() {
        return getFirstName() + " " + getLastName();
    }

    public int compareTo(Student o) {
        return this.getId().compareTo(o.getId());
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 17 * hash + (this.firstName != null ? this.firstName.
                hashCode() : 0);
        hash = 17 * hash
                + (this.lastName != null ? this.lastName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student student = (Student) obj;
        return this.id == null ? student.getId() == null
                : this.id.equals(student.id);
    }

    @Override
    public void setValues(StudentParam genericModel) {
        
    }

    @Override
    public Set<com.tx.simplescheduling.model.Class> getRelatedElementSet() {
        return new TreeSet<Class>();
    }

    @Override
    public Object getIdentifier() {
        return id;
    }

    @Override
    public Object getTypicalSearchMember() {
        return buildFullName();
    }

    @Override
    public com.tx.simplescheduling.model.Class createElement(Object identifier) {
        return new com.tx.simplescheduling.model.Class((String) identifier, null, null);
    }

    @Override
    public void setRelatedElementSet(Set<com.tx.simplescheduling.model.Class> relatedIdentifierSet) {
        
    }

    @Override
    public Student createSubInstance() {
        return new Student();
    }

}
