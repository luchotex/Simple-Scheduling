/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.process;

import com.tx.simplescheduling.model.param.ClassParam;
import com.tx.simplescheduling.model.ClassSaving;
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
public class ClassProcess /*extends GenericProcess */ {

    private ClassGlobalSource classGlobalSource;

    public ClassProcess() {
        classGlobalSource = new ClassGlobalSource();
    }

    public ClassSaving retrieveByIdentifier(String code) {
        synchronized (getClassGlobalSource().getIdentifierMap()) {
            if (code == null) {
                throw new BadRequestException(
                        "The " + retrieveIdentifierName()
                        + " param sent is null");
            }
            try {
                return getClassGlobalSource().retrieveByIdentifier(code);
            } catch (WebApplicationException ex) {
                throw ex;
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving the "
                        + retrieveClassName() + " happens unexpectedly");
            }
        }
    }

    public ClassSaving retrieveByTypicalSearch(String title) {
        synchronized (getClassGlobalSource().getIdentifierMap()) {
            if (title == null) {
                throw new BadRequestException("The "
                        + retrieveTypicalSearchName() + " param sent is null");
            }

            try {
                return getClassGlobalSource().retrieveByTypicalSearch(title);
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

    public Set<ClassSaving> retrieveAll() {
        synchronized (getClassGlobalSource().getIdentifierMap()) {
            try {
                return getClassGlobalSource().retrieveAll();
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving ALL the "
                        + retrieveClassNamePlural() + " happens unexpectedlye");
            }
        }
    }

    public ClassSaving add(ClassParam classParam,
            StudentProcess studentProcess) {
        if (classParam == null) {
            throw new BadRequestException("The " + retrieveClassName()
                    + " param sent is null");
        }
        try {
            Map<String, ClassSaving> identifierMap = getClassGlobalSource().
                    getIdentifierMap();
            synchronized (identifierMap) {
                ClassSaving classSaving = classParam.createSavingInstance();
                classSaving.buildRelatedElementAdding(classParam.
                        getStudentIdList(),
                        studentProcess.getStudentGlobalSource());
                getClassGlobalSource().add(classSaving);

                System.out.println("Created " + retrieveClassName() + " "
                        + classSaving.getCode());

                return classSaving;
            }
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw buildException(ex,
                    "Some error during creation of the " + retrieveClassName()
                    + " happens unexpectedly");
        }
    }

    public Response remove(String identifier, StudentProcess studentProcess) {
        if (identifier == null) {
            throw new BadRequestException("The " + retrieveIdentifierName()
                    + " param sent is null");
        }
        try {
            Map<String, ClassSaving> studentIdMap = getClassGlobalSource().
                    getIdentifierMap();
            synchronized (studentIdMap) {
                ClassSaving retrievedClass = getClassGlobalSource().
                        retrieveByIdentifier(identifier);
                retrievedClass.removeAllRelatedElements(studentProcess.
                        getStudentGlobalSource());

                getClassGlobalSource().remove(identifier);

                System.out.println("Removed " + retrieveClassName() + " "
                        + retrieveIdentifierName() + "=" + identifier);

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

    public ClassSaving update(ClassParam classParam,
            StudentProcess studentProcess) {
        if (classParam == null) {
            throw new BadRequestException("The " + retrieveClassName()
                    + " param sent is null");
        }
        try {
            Map<String, ClassSaving> identifierMap = getClassGlobalSource().
                    getIdentifierMap();
            synchronized (identifierMap) {
                ClassSaving savingElement = getClassGlobalSource().
                        retrieveByIdentifier(classParam.getCode());

                savingElement.updateRelatedElements(classParam.
                        getStudentIdList(), studentProcess.
                        getStudentGlobalSource());
                savingElement.setValues(classParam);
                getClassGlobalSource().update(savingElement);

                System.out.println("Created " + retrieveClassName() + " "
                        + savingElement.getCode());

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

    public ClassGlobalSource getClassGlobalSource() {
        return classGlobalSource;
    }

    public void setClassGlobalSource(ClassGlobalSource classGlobalSource) {
        this.classGlobalSource = classGlobalSource;
    }

    private String retrieveIdentifierName() {
        return "code";
    }

    private String retrieveTypicalSearchName() {
        return "title";
    }

    private String retrieveClassName() {
        return "class";
    }

    private String retrieveClassNamePlural() {
        return "classes";
    }

    protected InternalServerErrorException buildException(Exception ex,
            String message) {
        InternalServerErrorException result = new InternalServerErrorException(
                message, ex);

        return result;
    }

}
