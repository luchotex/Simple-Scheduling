/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model;

import com.tx.simplescheduling.source.GenericSource;
import java.util.Set;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public abstract class GenericModel<U extends GenericSource, V extends GenericModel, W extends GenericModel, Z extends GenericModel> {

    public GenericModel() {
    }

    public void buildRelatedElementAdding(Set relatedKeyElementSet,
            U globalSource) {
        for (Object identifier : relatedKeyElementSet) {
            W classToAdd = (W) globalSource.addRelatedElement(identifier, this);
            if (classToAdd != null) {
                this.getRelatedElementSet().add(classToAdd);
            }
        }
    }

    public void removeAllRelatedElements(U globalSource) {
        for (W element : getRelatedElementSet()) {
            globalSource.removeRelatedElement(element, this);
        }
    }

    public void updateRelatedElements(Set relatedKeyElementSet,
            U globalSource) {

        for (W element : this.getRelatedElementSet()) {
            if (!relatedKeyElementSet.contains(element.getIdentifier())) {
                globalSource.removeRelatedElement(element, this);
                this.getRelatedElementSet().remove(element);
            }
        }

        for (Object identifier : relatedKeyElementSet) {
            W created = createElement(identifier);
            updateRetrievedRelatedElement(created, globalSource, identifier);
        }
    }

    private void updateRetrievedRelatedElement(W created,
            U globalSource, Object identifier) {
        if (!this.getRelatedElementSet().contains(created)) {
            W elementToUpdate
                    = (W) globalSource.updatingRelatedElement(identifier, this);
            if (elementToUpdate != null) {
                if (getRelatedElementSet().contains(elementToUpdate)) {
                    getRelatedElementSet().remove(elementToUpdate);
                }
                this.getRelatedElementSet().add(elementToUpdate);
            }
        }
    }

    public void addRelatedElement(W elementToAdd) {
        getRelatedElementSet().add(elementToAdd);
    }

    public void updateRelatedElement(W elementToUpdate) {
        if (getRelatedElementSet().contains(elementToUpdate)) {
            getRelatedElementSet().remove(elementToUpdate);
        }
        getRelatedElementSet().add(elementToUpdate);
    }

    public void removeRelatedElement(W elementToRemove) {
        getRelatedElementSet().remove(elementToRemove);
    }

    public abstract void setValues(Z genericModel);

    public abstract Set<W> getRelatedElementSet();

    public abstract Object getIdentifier();

    public abstract Object getTypicalSearchMember();

    public abstract W createElement(Object identifier);

    public abstract void setRelatedElementSet(Set<W> relatedIdentifierSet);

    public abstract V createSubInstance();

}
