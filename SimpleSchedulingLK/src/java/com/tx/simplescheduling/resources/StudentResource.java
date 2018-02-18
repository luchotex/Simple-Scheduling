/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.resources;

import com.tx.simplescheduling.model.StudentParam;
import com.tx.simplescheduling.model.StudentSaving;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.ejb.Singleton;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Luis Kupferberg Ruiz
 */
@Singleton
@Path("/student")
public class StudentResource {

    private Map<Integer, StudentSaving> studentMap
            = new ConcurrentHashMap<Integer, StudentSaving>();

    /**
     * Creates a new instance of StudentResource
     */
    public StudentResource() {
    }

    public Map<Integer, StudentSaving> getStudentMap() {
        return studentMap;
    }

    public void setStudentMap(Map<Integer, StudentSaving> studentMap) {
        this.studentMap = studentMap;
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public StudentSaving createStudent(StudentParam studentParam) {
        StudentSaving studentSaving = studentParam.createStudentSaving();
        studentMap.put(studentSaving.getId(), studentSaving);
        System.out.println("Created student " + studentSaving.getId());

        //TODO enroll to class map
        return studentSaving;
    }

    /**
     * Retrieves representation of an instance of
     * com.tx.simplescheduling.resources.StudentResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of StudentResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
