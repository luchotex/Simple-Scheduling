/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.process;

import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.model.Student;
import com.tx.simplescheduling.source.ClassGlobalSource;
import com.tx.simplescheduling.source.StudentGlobalSource;
import java.util.Map;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class ClassProcess {

    private ClassGlobalSource classGlobalSource;

    public ClassProcess() {
        classGlobalSource = new ClassGlobalSource();
    }

    public ClassGlobalSource getClassGlobalSource() {
        return classGlobalSource;
    }

    public void setClassGlobalSource(ClassGlobalSource classGlobalSource) {
        this.classGlobalSource = classGlobalSource;
    }

}
