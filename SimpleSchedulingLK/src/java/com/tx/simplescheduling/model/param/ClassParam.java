/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model.param;

import com.tx.simplescheduling.model.ClassSaving;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
@XmlRootElement
public class ClassParam extends GenericParam {

    protected String code;
    protected String title;
    protected String description;

    private Set<Integer> studentIdList;

    public ClassParam() {
    }

    public ClassParam(String code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
    }

    public Set<Integer> getStudentIdList() {
        return studentIdList;
    }

    @XmlElement
    public void setStudentIdList(Set<Integer> studentIdList) {
        this.studentIdList = studentIdList;
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

    public ClassSaving createSavingInstance() {
        ClassSaving result = new ClassSaving(code, title, description);
        return result;
    }

    @Override
    public String buildTypicalSearchField() {
        return title;
    }

}
