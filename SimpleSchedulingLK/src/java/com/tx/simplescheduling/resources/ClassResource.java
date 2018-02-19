/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.resources;

import com.tx.simplescheduling.source.ClassGlobalInfo;
import com.tx.simplescheduling.model.ClassSaving;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.ejb.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.Path;
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

    private ClassGlobalInfo classGlobalInfo;

    /**
     * Creates a new instance of classResource
     */
    public ClassResource() {
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

    public ClassGlobalInfo getClassGlobalInfo() {
        return classGlobalInfo;
    }

    public void setClassGlobalInfo(ClassGlobalInfo classGlobalInfo) {
        this.classGlobalInfo = classGlobalInfo;
    }

}
