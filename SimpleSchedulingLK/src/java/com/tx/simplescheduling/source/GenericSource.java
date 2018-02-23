/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.GenericModel;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.ws.rs.NotFoundException;

/**
 *w
 * @author Luis Kupferberg Ruiz
 */
public abstract class GenericSource<V extends GenericModel, W extends GenericModel, Z extends GenericModel> {

    public GenericSource() {
    }

    public W retrieveByIdentifier(Object key) {
        W retrievedElement = (W) getIdentifierMap().get(key);

        if (retrievedElement == null) {
            throw new NotFoundException("The " + retrieveIdentifierName() + " "
                    + key + " of a " + retrieveClassName()
                    + " not exist on sources");
        }

        return retrievedElement;
    }

    public W retrieveByTypicalSearch(Object typicalSearchKey) {
        W retrievedElement = (W) getTypicalSearchMap().get(typicalSearchKey);

        if (retrievedElement == null) {
            throw new NotFoundException("The " + retrieveTypicalSearchName()
                    + " of the " + retrieveClassName() + " not exist on sources");
        }

        return retrievedElement;
    }

    public Set<W> retrieveAll() {
        return new TreeSet<W>(getIdentifierMap().values());
    }

    public void add(W elementToSave) {
        getIdentifierMap().put(elementToSave.getIdentifier(), elementToSave);
        getTypicalSearchMap().put(elementToSave.getTypicalSearchMember(),
                elementToSave);
    }

    public void remove(Object key) {
        W elementToRemove = (W) getIdentifierMap().get(key);

        if (elementToRemove == null) {
            throw new NotFoundException("The " + retrieveIdentifierName() + " "
                    + key + " of a " + retrieveClassName()
                    + " not exist on sources for removing");
        }
        getIdentifierMap().remove(key);
        getTypicalSearchMap().remove(elementToRemove.getTypicalSearchMember());
    }

    public void update(W updateElement) {
        W retrievedElement = retrieveByIdentifier(updateElement.getIdentifier());
        getIdentifierMap().put(updateElement.getIdentifier(), updateElement);
        getTypicalSearchMap().remove(retrievedElement.getTypicalSearchMember());
        getTypicalSearchMap().put(updateElement.getTypicalSearchMember(),
                updateElement);
    }

    public V addRelatedElement(
            Object key, Z relatedElement) {
        V result = null;

        result
                = addRelatedElementSafely(key, getIdentifierMap(),
                        relatedElement);
        if (result != null) {
            addRelatedElementSafely(result.getTypicalSearchMember(),
                    getTypicalSearchMap(), relatedElement);
        }

        return result;
    }

    public void removeRelatedElement(
            V subElement, Z relatedElement) {
        boolean wasRemoved = removeRelatedElementSafely(subElement.
                getIdentifier(),
                getIdentifierMap(), relatedElement);
        if (wasRemoved) {
            removeRelatedElementSafely(subElement.getTypicalSearchMember(),
                    getTypicalSearchMap(), relatedElement);
        }
    }

    public V updatingRelatedElement(
            Object key, Z relatedElement) {
        V result = null;

        result = updateRelatedElementSafely(key, getIdentifierMap(),
                relatedElement);
        updateRelatedElementSafely(key, getTypicalSearchMap(), relatedElement);
        return result;
    }

    private V addRelatedElementSafely(
            Object key, Map map, Z relatedElement) {
        V result = null;

        W retrievedSaving = (W) map.get(key);

        if (retrievedSaving != null) {
            retrievedSaving.addRelatedElement(relatedElement);
            result = (V) retrievedSaving.createSubInstance();
        }

        return result;
    }

    private boolean removeRelatedElementSafely(Object key,
            Map map, Z student) {
        boolean result = false;

        W removingElement = (W) map.get(key);

        if (removingElement != null) {
            removingElement.removeRelatedElement(student);
            result = true;
        }

        return result;
    }

    private V updateRelatedElementSafely(
            Object key, Map map, Z relatedElement) {
        V result = null;

        W elementToUpdate = (W) map.get(key);

        if (elementToUpdate != null) {
            elementToUpdate.updateRelatedElement(relatedElement);
            result = (V) elementToUpdate.createSubInstance();
        }

        return result;
    }

    public abstract Map getIdentifierMap();

    public abstract Map getTypicalSearchMap();

    public abstract String retrieveIdentifierName();

    public abstract String retrieveTypicalSearchName();

    public abstract String retrieveClassName();

}
