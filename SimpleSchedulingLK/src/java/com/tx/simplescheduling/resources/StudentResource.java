/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.resources;

import com.tx.simplescheduling.process.StudentProcess;
import com.tx.simplescheduling.model.StudentParam;
import com.tx.simplescheduling.model.StudentSaving;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Luis Kupferberg Ruiz
 */
@Singleton
@Path("/student")
public class StudentResource {

    @Context
    private ResourceContext resourceContext;

    private ClassResource classResource;

    private StudentProcess studentProcess;

    /**
     * Creates a new instance of StudentResource
     */
    public StudentResource() {
        studentProcess = new StudentProcess();
    }

    @PostConstruct
    public void after() {
        if (getResourceContext() != null) {
            setClassResource(getResourceContext().getResource(
                    ClassResource.class));
        }
    }

    /**
     * Retrieves the saved student by the id, responding all the classes related
     *
     * @param id is the identifier unique and irreplaceable value in sourceF
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public StudentSaving retrieveStudent(@PathParam("id") Integer id) {
        return getStudentProcess().retrieveByIdentifier(id);
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
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public StudentSaving retrieveStudent(
            @PathParam("fullName") String fullName) {
        return getStudentProcess().retrieveByTypicalSearch(fullName);
    }

    /**
     * Retrieves ALL the saved student, responding all the class with their
     * information
     *
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Set<StudentSaving> retrieveAllStudents() {
        return getStudentProcess().retrieveAll();
    }

    /**
     * Creating the student with all their classes codes, responding all the
     * class with their information
     *
     * @param studentParam is the complete student information with related
     * class codes
     * @return
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_XML})
    public StudentSaving createStudent(StudentParam studentParam) {
        return getStudentProcess().add(studentParam,
                getClassResource().getClassProcess());
    }

    /**
     * Update the student sending all the information, responding all the class
     * with their information
     *
     * @param studentParam is the complete student information with related
     * class codes
     * @return
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public StudentSaving updateStudent(StudentParam studentParam) {
        return getStudentProcess().update(studentParam,
                getClassResource().getClassProcess());
    }

    /**
     * Delete the saved student by the id
     *
     * @param id is the identifier unique and irreplaceable value in source
     * @return
     */
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response removeStudent(@PathParam("id") Integer id) {
        return getStudentProcess().remove(id, getClassResource().
                getClassProcess());
    }

    public ClassResource getClassResource() {
        return classResource;
    }

    public void setClassResource(ClassResource classResource) {
        this.classResource = classResource;
    }

    public ResourceContext getResourceContext() {
        return resourceContext;
    }

    public void setResourceContext(ResourceContext resourceContext) {
        this.resourceContext = resourceContext;
    }

    public StudentProcess getStudentProcess() {
        return studentProcess;
    }

    public void setStudentProcess(StudentProcess studentProcess) {
        this.studentProcess = studentProcess;
    }

}
