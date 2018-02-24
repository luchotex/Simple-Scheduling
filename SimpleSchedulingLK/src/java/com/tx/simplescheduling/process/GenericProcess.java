/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.process;

import com.tx.simplescheduling.model.GenericModel;
import com.tx.simplescheduling.model.param.GenericParam;
import com.tx.simplescheduling.source.GenericSource;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public abstract class GenericProcess<U extends GenericModel, V extends GenericProcess, W extends GenericSource, Z extends GenericParam> {

    public Response retrieveByIdentifier(Object identifier) {
        synchronized (getGlobalSource().getIdentifierMap()) {
            if (identifier == null) {
                throw new BadRequestException(
                        "The " + retrieveIdentifierName()
                        + " param sent is null");
            }
            try {
                U retrievedElement
                        = (U) getGlobalSource().retrieveByIdentifier(identifier);
                GenericEntity entity = new GenericEntity<GenericModel>(
                        retrievedElement) {
                };
                return Response.ok(entity).build();
            } catch (WebApplicationException ex) {
                throw ex;
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving the "
                        + retrieveClassName() + " happens unexpectedly");
            }
        }
    }

    public Response retrieveByTypicalSearch(String typicalSearchField) {
        synchronized (getGlobalSource().getIdentifierMap()) {
            if (typicalSearchField == null) {
                throw new BadRequestException("The "
                        + retrieveTypicalSearchName() + " param sent is null");
            }

            try {
                U retrievedElement
                        = (U) getGlobalSource().retrieveByTypicalSearch(
                                typicalSearchField);
                GenericEntity entity = new GenericEntity<GenericModel>(
                        retrievedElement) {
                };
                return Response.ok(entity).build();
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

    public Set<U> retrieveAll() {
        synchronized (getGlobalSource().getIdentifierMap()) {
            try {
                return (Set<U>) getGlobalSource().retrieveAll();
            } catch (Exception ex) {
                throw buildException(ex,
                        "Some error during retrieving ALL the "
                        + retrieveClassNamePlural() + " happens unexpectedlye");
            }
        }
    }

    public Response add(Z param, V process) {
        if (param == null) {
            throw new BadRequestException("The " + retrieveClassName()
                    + " param sent is null");
        }
        try {
            Map identifierMap = getGlobalSource().getIdentifierMap();
            synchronized (identifierMap) {
                U savingElement = (U) param.createSavingInstance();
                savingElement.buildRelatedElementAdding(param.
                        getRelatedCodeList(),
                        process.getGlobalSource());
                getGlobalSource().add(savingElement);

                System.out.println("Created " + retrieveClassName() + " "
                        + savingElement.getTypicalSearchMember());

                GenericEntity entity = new GenericEntity<GenericModel>(
                        savingElement) {
                };
                return Response.ok(entity).build();
            }
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw buildException(ex,
                    "Some error during creation of the " + retrieveClassName()
                    + " happens unexpectedly");
        }
    }

    public Response remove(Object identifier, V process) {
        if (identifier == null) {
            throw new BadRequestException("The " + retrieveIdentifierName()
                    + " param sent is null");
        }
        try {
            Map identifierMap = getGlobalSource().getIdentifierMap();
            synchronized (identifierMap) {
                U retrievedElement = (U) getGlobalSource().retrieveByIdentifier(
                        identifier);
                retrievedElement.removeAllRelatedElements(process.
                        getGlobalSource());

                getGlobalSource().remove(identifier);

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

    public Response update(Z param, V process) {
        if (param == null) {
            throw new BadRequestException("The " + retrieveClassName()
                    + " param sent is null");
        }
        try {
            Map identifierMap = getGlobalSource().
                    getIdentifierMap();
            synchronized (identifierMap) {
                U savingElement = (U) getGlobalSource().
                        retrieveByIdentifier(param.getIdentifierField());

                savingElement.updateRelatedElements(param.
                        getRelatedCodeList(), process.getGlobalSource());
                savingElement.setValues(param);
                getGlobalSource().update(savingElement);

                System.out.println("Created " + retrieveClassName() + " "
                        + savingElement.getIdentifier());
                GenericEntity entity = new GenericEntity<GenericModel>(
                        savingElement) {
                };
                return Response.ok(entity).build();
            }
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw buildException(ex,
                    "Some error during updating of the " + retrieveClassName()
                    + " happens unexpectedly");
        }
    }

    public abstract W getGlobalSource();

    public abstract void setGlobalSource(W globalSource);

    public abstract String retrieveIdentifierName();

    public abstract String retrieveTypicalSearchName();

    public abstract String retrieveClassName();

    public abstract String retrieveClassNamePlural();

    protected InternalServerErrorException buildException(Exception ex,
            String message) {
        InternalServerErrorException result = new InternalServerErrorException(
                message, ex);

        return result;
    }

}
