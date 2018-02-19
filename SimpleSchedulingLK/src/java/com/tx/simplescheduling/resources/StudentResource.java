/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.resources;

import com.tx.simplescheduling.source.StudentGlobalInfo;
import com.tx.simplescheduling.model.StudentParam;
import com.tx.simplescheduling.model.StudentSaving;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

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

    private StudentGlobalInfo studentGlobalInfo;

    /**
     * Creates a new instance of StudentResource
     */
    public StudentResource() {
    }

    @PostConstruct
    public void after() {
        if (resourceContext != null) {
            classResource = resourceContext.getResource(ClassResource.class);
        }
    }

    /**
     * Retrieves the saved student by the id, responding all the class with
     *
     * @param id is the identifier unique and irreplaceable value in sourceF
     * @return
     */
    @GET
    @Path("{id}")
    @Produces("application/xml")
    public StudentSaving retrieveStudent(@PathParam("id") Integer id) {
        synchronized (studentGlobalInfo.getStudentIdMap()) {
            if (id == null) {
                throw new BadRequestException("The id param sent is null");
            }
            if (!studentGlobalInfo.getStudentIdMap().containsKey(id)) {
                throw new NotFoundException("The id " + id
                        + " of a student not exist on sources");
            }
            try {
                return studentGlobalInfo.getStudentIdMap().get(id);
            } catch (Exception ex) {
                throw new InternalServerErrorException(
                        "Some error during retrieving the student happens unexpectedly");
            }
        }
    }

    /**
     * Retrieves the saved student by the id, responding all the class with
     *
     * @param fullName is the full name made by the concatenation of the first
     * and the last name
     * @return
     */
//    @GET
//    @Path("{fullName}")
//    @Produces("application/xml")
//    public List<StudentSaving> retrieveStudent(
//            @PathParam("fullName") String fullName) {
//
//        if (fullName == null) {
//            throw new BadRequestException("The fullName param sent is null");
//        }
//
//        if (!studentNameMap.containsKey(fullName)) {
//            throw new NotFoundException("The fullName " + fullName
//                    + " of the student not exist on sources");
//        }
//
//        try {
//            synchronized (studentIdMap) {
//                return studentNameMap.get(fullName);
//            }
//        } catch (Exception ex) {
//            throw new InternalServerErrorException(
//                    "Some error during retrieving the student happens unexpectedly");
//        }
//    }
    /**
     * Retrieves ALL the saved student, responding all the class with their
     * information
     *
     * @return
     */
    @GET
    @Produces("application/xml")
    public Set<StudentSaving> retrieveAllStudents() {
        try {
            return new TreeSet<StudentSaving>(studentGlobalInfo.
                    getStudentIdMap().values());
        } catch (Exception ex) {
            throw new InternalServerErrorException(
                    "Some error during retrieving ALL the student happens unexpectedly");
        }
    }

    /**
     * Retrieves the saved student by the id, responding all the class with
     * their information
     *
     * @param studentParam is the complete student information with related
     * class codes
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public StudentSaving createStudent(StudentParam studentParam) {
        if (studentParam == null) {
            throw new BadRequestException("The student param sent is null");
        }
        try {
            synchronized (studentGlobalInfo.getStudentIdMap()) {
                StudentSaving studentSaving = studentGlobalInfo.addStudent(
                        studentParam, classResource.getClassGlobalInfo());
                //TODO enroll to class map

                System.out.println("Created student " + studentSaving.getId());

                return studentSaving;
            }
        } catch (Exception ex) {
            throw new InternalServerErrorException(
                    "Some error during creation of the student happens unexpectedly");
        }
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

    public StudentGlobalInfo getStudentGlobalInfo() {
        return studentGlobalInfo;
    }

    public void setStudentGlobalInfo(StudentGlobalInfo studentGlobalInfo) {
        this.studentGlobalInfo = studentGlobalInfo;
    }

}
