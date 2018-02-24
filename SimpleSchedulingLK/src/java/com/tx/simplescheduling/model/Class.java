/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import com.tx.simplescheduling.model.param.ClassParam;
import com.tx.simplescheduling.source.StudentGlobalSource;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
@XmlRootElement
public class Class extends GenericModel<StudentGlobalSource, 
        com.tx.simplescheduling.model.Class, Student, ClassParam>
        implements Comparable<com.tx.simplescheduling.model.Class> {

    protected String code;
    protected String title;
    protected String description;

    public Class() {
    }

    public Class(String code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
    }

    public int compareTo(com.tx.simplescheduling.model.Class o) {
        return this.getCode().compareTo(o.getCode());
    }

    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 11 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 11 * hash + (this.description != null ? this.description.
                hashCode() : 0);
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
        final Class paramClass = (Class) obj;
        return (this.code == null) ? (paramClass.code == null)
                : this.code.equals(paramClass.code);
    }

    @Override
    public void setValues(ClassParam genericModel) {
        
    }

    @Override
    public Set<Student> getRelatedElementSet() {
        return new TreeSet<Student>();
    }

    @Override
    public Object getIdentifier() {
        return code;
    }

    @Override
    public Object getTypicalSearchMember() {
        return title;
    }

    @Override
    public Student createElement(Object identifier) {
        return new Student((Integer) identifier, null, null);
    }

    @Override
    public void setRelatedElementSet(Set relatedIdentifierSet) {
        
    }

    @Override
    public Class createSubInstance() {
        return new Class();
    }
}
