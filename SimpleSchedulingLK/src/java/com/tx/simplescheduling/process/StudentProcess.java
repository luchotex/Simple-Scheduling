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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

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
            ClassProcess classProcess) {
        if (studentParam == null) {
            throw new BadRequestException("The student param sent is null");
        }
        try {
            Map<Integer, StudentSaving> studentIdMap = getStudentGlobalSource().
                    getStudentIdMap();
            synchronized (studentIdMap) {
                StudentSaving studentSaving = studentParam.createStudentSaving();
                studentSaving.buildClasses(studentParam.getClassCodeList(),
                        classProcess.getClassGlobalSource());
                getStudentGlobalSource().add(studentSaving);

                System.out.println("Created student " + studentSaving.getId());

                Response.ok(studentSaving).build();

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
            try {
                return getStudentGlobalSource().retrieveById(id);
            } catch (WebApplicationException ex) {
                throw ex;
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving the student happens unexpectedly");
            }
        }
    }

    public StudentSaving retrieveStudentByFullName(String fullName) {
        synchronized (getStudentGlobalSource().getStudentIdMap()) {
            if (fullName == null) {
                throw new BadRequestException("The fullName param sent is null");
            }

            try {
                return getStudentGlobalSource().retrieveByFullName(fullName);
            } catch (WebApplicationException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new InternalServerErrorException(
                        "Some error during retrieving the student by full name happens unexpectedly");
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
