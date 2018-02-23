/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.resources;

import com.tx.simplescheduling.model.param.ClassParam;
import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.process.ClassProcess;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

//    /**
//     * Retrieves the saved class by the code, responding all the students
//     * related
//     *
//     * @param code is the identifier unique and irreplaceable value in class
//     * @return
//     */
//    @GET
//    @Path("{code}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public ClassSaving retrieveClass(@PathParam("code") String code) {
//        return getClassProcess().retrieveByIdentifier(code);
//    }
//
//    /**
//     * Retrieves the saved class by the title, responding all the classes
//     * related
//     *
//     * @param title is the of the class and the last name
//     * @return
//     */
//    @GET
//    @Path("title/{title}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public ClassSaving retrieveClassByTitle(
//            @PathParam("title") String title) {
//        return getClassProcess().retrieveByTypicalSearch(title);
//    }
//
    /**
     * Retrieves ALL the saved classes, responding all the students with their
     * information
     *
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Set<ClassSaving> retrieveAllClasses() {
        return getClassProcess().retrieveAll();
    }
//
//    /**
//     * Creating the class with all their classes codes, responding all the
//     * Student with their information
//     *
//     * @param classParam is the complete class information with related student
//     * ids
//     * @return
//     */
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public ClassSaving createClass(ClassParam classParam) {
//        return getClassProcess().add(classParam,
//                getStudentResource().getStudentProcess());
//    }
//
//    /**
//     * Update the class sending all the information, responding all the student
//     * with their information
//     *
//     * @param classParam is the complete student information with related class
//     * codes
//     * @return
//     */
//    @PUT
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public ClassSaving updateClass(ClassParam classParam) {
//        return getClassProcess().update(classParam,
//                getStudentResource().getStudentProcess());
//    }
//
//    /**
//     * Delete the saved class by the code
//     *
//     * @param code is the identifier unique and irreplaceable value in source
//     * @return
//     */
//    @DELETE
//    @Path("{code}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response removeClass(@PathParam("code") String code) {
//        return getClassProcess().remove(code, getStudentResource().
//                getStudentProcess());
//    }

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
