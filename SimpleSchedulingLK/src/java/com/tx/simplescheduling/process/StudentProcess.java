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

    public StudentSaving retrieveByIdentifier(Integer id) {
        synchronized (getStudentGlobalSource().getIdentifierMap()) {
            if (id == null) {
                throw new BadRequestException(
                        "The " + retrieveIdentifierName()
                        + " param sent is null");
            }
            try {
                return getStudentGlobalSource().retrieveByIdentifier(id);
            } catch (WebApplicationException ex) {
                throw ex;
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving the "
                        + retrieveClassName() + " happens unexpectedly");
            }
        }
    }

    public StudentSaving retrieveByTypicalSearch(String fullName) {
        synchronized (getStudentGlobalSource().getIdentifierMap()) {
            if (fullName == null) {
                throw new BadRequestException("The "
                        + retrieveTypicalSearchName() + " param sent is null");
            }

            try {
                return getStudentGlobalSource().
                        retrieveByTypicalSearch(fullName);
            } catch (WebApplicationException ex) {
                throw ex;
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving the "
                        + retrieveClassName() + " by "
                        + retrieveTypicalSearchName() + " happens unexpectedly");
            }
        }
    }

    public Set<StudentSaving> retrieveAll() {
        synchronized (getStudentGlobalSource().getIdentifierMap()) {
            try {
                return getStudentGlobalSource().retrieveAll();
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving ALL the "
                        + retrieveClassNamePlural() + " happens unexpectedlye");
            }
        }
    }

    public StudentSaving add(StudentParam studentParam,
            ClassProcess classProcess) {
        if (studentParam == null) {
            throw new BadRequestException("The " + retrieveClassName()
                    + " param sent is null");
        }
        try {
            Map<Integer, StudentSaving> identifierMap
                    = getStudentGlobalSource().
                    getIdentifierMap();
            synchronized (identifierMap) {
                StudentSaving studentSaving = studentParam.
                        createSavingInstance();
                studentSaving.buildRelatedElementAdding(studentParam.
                        getClassCodeList(),
                        classProcess.getClassGlobalSource());
                getStudentGlobalSource().add(studentSaving);

                System.out.println("Created " + retrieveClassName() + " "
                        + studentSaving.getId());

                return studentSaving;
            }
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw buildException(ex,
                    "Some error during creation of the " + retrieveClassName()
                    + " happens unexpectedly");
        }
    }

    public Response remove(Integer id,
            ClassProcess classProcess) {
        if (id == null) {
            throw new BadRequestException("The " + retrieveIdentifierName()
                    + " param sent is null");
        }
        try {
            Map<Integer, StudentSaving> studentIdMap = getStudentGlobalSource().
                    getIdentifierMap();
            synchronized (studentIdMap) {
                StudentSaving retrievedStudent = getStudentGlobalSource().
                        retrieveByIdentifier(id);
                retrievedStudent.removeAllRelatedElements(classProcess.
                        getClassGlobalSource());
                getStudentGlobalSource().remove(id);

                System.out.println("Removed " + retrieveClassName() + " "
                        + retrieveIdentifierName() + "=" + id);

                // successfully deleted, return 204 NO CONTENT
                Response response = Response.noContent().build();
                return response;
            }
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw buildException(ex,
                    "Some error during deletion of the " + retrieveClassName()
                    + " happens unexpectedly");
        }
    }

    public StudentSaving update(StudentParam studentParam,
            ClassProcess classProcess) {
        if (studentParam == null) {
            throw new BadRequestException("The " + retrieveClassName()
                    + " param sent is null");
        }
        try {
            Map<Integer, StudentSaving> identifierMap
                    = getStudentGlobalSource().
                    getIdentifierMap();
            synchronized (identifierMap) {
                StudentSaving savingElement = getStudentGlobalSource().
                        retrieveByIdentifier(studentParam.getId());

                savingElement.updateRelatedElements(studentParam.
                        getClassCodeList(), classProcess.getClassGlobalSource());
                savingElement.setValues(studentParam);
                getStudentGlobalSource().update(savingElement);

                System.out.println("Created " + retrieveIdentifierName() + " "
                        + savingElement.getId());

                return savingElement;
            }
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw buildException(ex,
                    "Some error during updating of the " + retrieveClassName()
                    + " happens unexpectedly");
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

    private String retrieveIdentifierName() {
        return "id";
    }

    private String retrieveTypicalSearchName() {
        return "full name";
    }

    private String retrieveClassName() {
        return "student";
    }

    private String retrieveClassNamePlural() {
        return "students";
    }

}
