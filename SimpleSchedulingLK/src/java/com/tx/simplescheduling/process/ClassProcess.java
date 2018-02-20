/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.process;

import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.model.StudentSaving;
import com.tx.simplescheduling.source.ClassGlobalSource;
import java.util.Set;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;

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
