/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.resources;

import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.process.ClassProcess;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ResourceContext;

/**
 * REST Web Service
 *
 * @author Luis Kupferberg Ruiz
 */
@Singleton
@Path("/class")
public class ClassResource {

    @Context
    private ResourceContext resourceContext;

    private StudentResource studentResource;

    private ClassProcess classProcess;

    /**
     * Creates a new instance of classResource
     */
    public ClassResource() {
        classProcess = new ClassProcess();
    }

    @PostConstruct
    public void after() {
        if (getResourceContext() != null) {
            setStudentResource(getResourceContext().getResource(
                    StudentResource.class));
        }
    }

    /**
     * Retrieves the saved class by the id, responding all the students related
     *
     * @param code is the identifier unique and irreplaceable value in class
     * @return
     */
    @GET
    @Path("{code}")
    @Produces("application/xml")
    public ClassSaving retrieveClass(@PathParam("id") String code) {
        try {
            return classProcess.retrieveClass(code);
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerErrorException(
                    "Some error during retrieving the class happens unexpectedly on interface");
        }
    }

    /**
     * Retrieves the saved student by the full name, responding all the classes
     * related
     *
     * @param fullName is the full name made by the concatenation of the first
     * and the last name
     * @return
     */
    @GET
    @Path("fullname/{fullName}")
    @Produces("application/xml")
    public ClassSaving retrieveClassByTitle(
            @PathParam("fullName") String fullName) {
        try {
            return classProcess.retrieveClasstByTitle(fullName);
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerErrorException(
                    "Some error during retrieving the class by full name happens unexpectedly on interface");
        }
    }

    /**
     * Retrieves ALL the saved classes, responding all the students with their
     * information
     *
     * @return
     */
    @GET
    @Produces("application/xml")
    public Set<ClassSaving> retrieveAllClasses() {
        try {
            return classProcess.retrieveAllClasses();
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerErrorException(
                    "Some error during retrieving ALL the classes happens unexpectedly on interface");
        }
    }

    public ResourceContext getResourceContext() {
        return resourceContext;
    }

    public void setResourceContext(ResourceContext resourceContext) {
        this.resourceContext = resourceContext;
    }

    public StudentResource getStudentResource() {
        return studentResource;
    }

    public void setStudentResource(StudentResource studentResource) {
        this.studentResource = studentResource;
    }

    public ClassProcess getClassProcess() {
        return classProcess;
    }

    public void setClassProcess(ClassProcess classProcess) {
        this.classProcess = classProcess;
    }

}
