/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model.param;

import com.tx.simplescheduling.model.Student;
import com.tx.simplescheduling.model.StudentSaving;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
@XmlRootElement(name = "studentParam")
public class StudentParam extends GenericParam {

    protected Integer id;
    protected String firstName;
    protected String lastName;

    private Set<String> classCodeList;

    public StudentParam() {
    }

    public StudentParam(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Set<String> getClassCodeList() {
        return classCodeList;
    }

    @XmlElement
    public void setClassCodeList(Set<String> classCodeList) {
        this.classCodeList = classCodeList;
    }

    public StudentSaving createSavingInstance() {
        return new StudentSaving(id, firstName, lastName);
    }

    @Override
    public String buildTypicalSearchField() {
        return getFirstName() + " " + getLastName();
    }

}
