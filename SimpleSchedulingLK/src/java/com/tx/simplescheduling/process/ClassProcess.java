/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.process;

import com.tx.simplescheduling.model.ClassParam;
import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.model.StudentParam;
import com.tx.simplescheduling.model.StudentSaving;
import com.tx.simplescheduling.source.ClassGlobalSource;
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
public class ClassProcess {

    private ClassGlobalSource classGlobalSource;

    public ClassProcess() {
        classGlobalSource = new ClassGlobalSource();
    }

    public ClassSaving retrieveClass(String code) {
        synchronized (getClassGlobalSource().getClassCodeMap()) {
            if (code == null) {
                throw new BadRequestException("The code param sent is null");
            }
            try {
                return getClassGlobalSource().retrieveByCode(code);
            } catch (WebApplicationException ex) {
                throw ex;
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving the class happens unexpectedly");
            }
        }
    }

    public ClassSaving retrieveClasstByTitle(String title) {
        synchronized (getClassGlobalSource().getClassCodeMap()) {
            if (title == null) {
                throw new BadRequestException("The title param sent is null");
            }

            try {
                return getClassGlobalSource().retrieveByTitle(title);
            } catch (WebApplicationException ex) {
                throw ex;
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving the class by title happens unexpectedly");
            }
        }
    }

    public Set<ClassSaving> retrieveAllClasses() {
        synchronized (getClassGlobalSource().getClassCodeMap()) {
            try {
                return getClassGlobalSource().retrieveAll();
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving ALL the classes happens unexpectedlye");
            }
        }
    }

    public ClassSaving addClass(ClassParam classParam,
            StudentProcess studentProcess) {
        if (classParam == null) {
            throw new BadRequestException("The class param sent is null");
        }
        try {
            Map<String, ClassSaving> studentIdMap = getClassGlobalSource().
                    getClassCodeMap();
            synchronized (studentIdMap) {
                ClassSaving classSaving = classParam.createStudentSaving();
                classSaving.buildStudents(classParam.getStudentIdList(),
                        studentProcess.getStudentGlobalSource());
                getClassGlobalSource().add(classSaving);

                System.out.println("Created class " + classSaving.getCode());

                return classSaving;
            }
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw buildException(ex,
                    "Some error during creation of the class happens unexpectedly");
        }
    }

    public Response removeClass(String code, StudentProcess studentProcess) {
        if (code == null) {
            throw new BadRequestException("The code param sent is null");
        }
        try {
            Map<String, ClassSaving> studentIdMap = getClassGlobalSource().
                    getClassCodeMap();
            synchronized (studentIdMap) {
                ClassSaving retrievedClass = getClassGlobalSource().
                        retrieveByCode(code);
                retrievedClass.disassignAllStudents(studentProcess.
                        getStudentGlobalSource());

                getClassGlobalSource().remove(code);

                System.out.println("Removed class code=" + code);

                // successfully deleted, return 204 NO CONTENT
                Response response = Response.noContent().build();
                return response;
            }
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw buildException(ex,
                    "Some error during deletion of the class happens unexpectedly");
        }
    }

    public ClassSaving updateClass(ClassParam classParam,
            StudentProcess studentProcess) {
        if (classParam == null) {
            throw new BadRequestException("The class param sent is null");
        }
        try {
            Map<String, ClassSaving> studentIdMap = getClassGlobalSource().
                    getClassCodeMap();
            synchronized (studentIdMap) {
                ClassSaving classSaving = getClassGlobalSource().
                        retrieveByCode(classParam.getCode());

                classSaving.buildStudentUpdating(classParam.
                        getStudentIdList(), studentProcess.
                        getStudentGlobalSource());
                classSaving.setValues(classParam);
                getClassGlobalSource().update(classSaving);

                System.out.println("Created class " + classSaving.getCode());

                return classSaving;
            }
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw buildException(ex,
                    "Some error during updating of the class happens unexpectedly");
        }
    }

    public ClassGlobalSource getClassGlobalSource() {
        return classGlobalSource;
    }

    public void setClassGlobalSource(ClassGlobalSource classGlobalSource) {
        this.classGlobalSource = classGlobalSource;
    }

    private InternalServerErrorException buildException(Exception ex,
            String message) {
        InternalServerErrorException result = new InternalServerErrorException(
                message, ex);

        return result;
    }

}
