/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.process;

import com.tx.simplescheduling.model.StudentParam;
import com.tx.simplescheduling.model.StudentSaving;
import com.tx.simplescheduling.source.StudentGlobalSource;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class StudentProcess {

    private StudentGlobalSource studentGlobalSource;

    public StudentProcess() {
        studentGlobalSource = new StudentGlobalSource();
    }

    public StudentSaving addStudent(StudentParam studentParam,
            ClassProcess classGlobalInfo) {
        if (studentParam == null) {
            throw new BadRequestException("The student param sent is null");
        }
        try {
            Map<Integer, StudentSaving> studentIdMap = getStudentGlobalSource().
                    getStudentIdMap();
            synchronized (studentIdMap) {
                StudentSaving studentSaving = studentParam.createStudentSaving();
                studentSaving.buildClasses(studentParam.getClassCodeList(),
                        classGlobalInfo);
                getStudentGlobalSource().add(studentSaving);

                System.out.println("Created student " + studentSaving.getId());

                return studentSaving;
            }
        } catch (Exception ex) {
            throw buildException(ex,
                    "Some error during creation of the student happens unexpectedly");
        }
    }

    public StudentSaving retrieveStudent(Integer id) {
        synchronized (getStudentGlobalSource().getStudentIdMap()) {
            if (id == null) {
                throw new BadRequestException("The id param sent is null");
            }
            if (!getStudentGlobalSource().getStudentIdMap().containsKey(id)) {
                throw new NotFoundException("The id " + id
                        + " of a student not exist on sources");
            }
            try {
                return getStudentGlobalSource().retrieveById(id);
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving the student happens unexpectedly");
            }
        }
    }

    public Set<StudentSaving> retrieveAllStudent() {
        synchronized (getStudentGlobalSource().getStudentIdMap()) {
            try {
                return getStudentGlobalSource().retrieveAll();
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving ALL the student happens unexpectedlye");
            }
        }
    }

    public StudentGlobalSource getStudentGlobalSource() {
        return studentGlobalSource;
    }

    public void setStudentGlobalSource(StudentGlobalSource studentGlobalSource) {
        this.studentGlobalSource = studentGlobalSource;
    }

    private InternalServerErrorException buildException(Exception ex,
            String message) {
        InternalServerErrorException result = new InternalServerErrorException(
                message, ex);

        return result;
    }

}
