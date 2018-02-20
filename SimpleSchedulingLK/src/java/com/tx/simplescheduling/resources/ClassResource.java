/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.resources;

import com.tx.simplescheduling.model.ClassParam;
import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.process.ClassProcess;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
     * Retrieves the saved class by the code, responding all the students
     * related
     *
     * @param code is the identifier unique and irreplaceable value in class
     * @return
     */
    @GET
    @Path("{code}")
    @Produces("application/xml")
    public ClassSaving retrieveClass(@PathParam("code") String code) {
        try {
            return getClassProcess().retrieveClass(code);
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerErrorException(
                    "Some error during retrieving the class happens unexpectedly on interface");
        }
    }

    /**
     * Retrieves the saved class by the title, responding all the classes
     * related
     *
     * @param title is the of the class
     * and the last name
     * @return
     */
    @GET
    @Path("title/{title}")
    @Produces("application/xml")
    public ClassSaving retrieveClassByTitle(
            @PathParam("title") String title) {
        try {
            return getClassProcess().retrieveClasstByTitle(title);
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
            return getClassProcess().retrieveAllClasses();
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerErrorException(
                    "Some error during retrieving ALL the classes happens unexpectedly on interface");
        }
    }

    /**
     * Creating the class with all their classes codes, responding all the
     * Student with their information
     *
     * @param classParam is the complete class information with related student
     * ids
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public ClassSaving createClass(ClassParam classParam) {
        try {
            return getClassProcess().addClass(classParam,
                    getStudentResource().getStudentProcess());
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerErrorException(
                    "Some error during creation of the class happens unexpectedly on interface");
        }
    }

    /**
     * Update the class sending all the information, responding all the student
     * with their information
     *
     * @param classParam is the complete student information with related class
     * codes
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public ClassSaving updateClass(ClassParam classParam) {
        try {
            return getClassProcess().updateClass(classParam,
                    getStudentResource().getStudentProcess());
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerErrorException(
                    "Some error during updating of the class happens unexpectedly on interface");
        }
    }

    /**
     * Delete the saved class by the code
     *
     * @param code is the identifier unique and irreplaceable value in source
     * @return
     */
    @DELETE
    @Path("{code}")
    @Produces("application/xml")
    public Response removeClass(@PathParam("code") String code) {
        try {
            return getClassProcess().removeClass(code, getStudentResource().
                    getStudentProcess());
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerErrorException(
                    "Some error during deletion the class happens unexpectedly on interface");
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
