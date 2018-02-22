/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.source;

import com.tx.simplescheduling.model.ClassSaving;
import com.tx.simplescheduling.model.Student;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class ClassGlobalSource {

    private Map<String, ClassSaving> classCodeMap
            = new ConcurrentHashMap<String, ClassSaving>();
    private Map<String, ClassSaving> classTitleMap
            = new ConcurrentHashMap<String, ClassSaving>();

    public ClassSaving retrieveByIdentifier(String code) {
        ClassSaving classSaving = getIdentifierMap().get(code);

        if (classSaving == null) {
            throw new NotFoundException("The code " + code
                    + " of a class not exist on sources");
        }

        return classSaving;
    }

    public ClassSaving retrieveByTypicalSearch(String title) {
        ClassSaving classSaving = getTypicalSearchMap().get(title);

        if (classSaving == null) {
            throw new NotFoundException("The title" + title
                    + " of the class not exist on sources");
        }

        return classSaving;
    }

    public Set<ClassSaving> retrieveAll() {
        return new TreeSet<ClassSaving>(getIdentifierMap().values());
    }

    public void add(ClassSaving classSaving) {
        getIdentifierMap().put(classSaving.getCode(), classSaving);
        getTypicalSearchMap().put(classSaving.getTitle(), classSaving);
    }

    public void remove(String code) {
        ClassSaving classToRemove = getIdentifierMap().get(code);

        if (classToRemove == null) {
            throw new NotFoundException("The code " + code
                    + " of a class not exist on sources for removing");
        }
        getIdentifierMap().remove(code);
        getTypicalSearchMap().remove(classToRemove.getTitle());
    }

    public void update(ClassSaving classSaving) {
        ClassSaving retrievedClass = retrieveByIdentifier(classSaving.getCode());
        getIdentifierMap().put(classSaving.getCode(), classSaving);
        getTypicalSearchMap().remove(retrievedClass.getTitle());
        getTypicalSearchMap().put(classSaving.getTitle(), classSaving);
    }

    public com.tx.simplescheduling.model.Class addRelatedElement(
            String code, Student student) {
        com.tx.simplescheduling.model.Class result = null;

        result = addRelatedElementSafely(code, getIdentifierMap(), student);
        if (result != null) {
            addRelatedElementSafely(result.getTitle(), getTypicalSearchMap(),
                    student);
        }

        return result;
    }

    public void removeRelatedElement(
            com.tx.simplescheduling.model.Class classSaving, Student student) {
        boolean wasRemoved = removeRelatedElementSafely(classSaving.getCode(),
                getIdentifierMap(), student);
        if (wasRemoved) {
            removeRelatedElementSafely(classSaving.getTitle(),
                    getTypicalSearchMap(), student);
        }
    }

    public com.tx.simplescheduling.model.Class updatingRelatedElement(
            String code, Student student) {
        com.tx.simplescheduling.model.Class result = null;

        result = updateRelatedElementSafely(code, getIdentifierMap(), student);
        updateRelatedElementSafely(code, getTypicalSearchMap(), student);
        return result;
    }

    private com.tx.simplescheduling.model.Class addRelatedElementSafely(
            Object key, Map map, Student student) {
        com.tx.simplescheduling.model.Class result = null;

        ClassSaving retrievedClassSaving = (ClassSaving) map.get(key);

        if (retrievedClassSaving != null) {
            retrievedClassSaving.addRelatedElement(student);
            result = retrievedClassSaving.createRelatedElement();
        }

        return result;
    }

    private boolean removeRelatedElementSafely(Object key,
            Map map, Student student) {
        boolean result = false;

        ClassSaving retrievedClassSaving = (ClassSaving) map.get(key);

        if (retrievedClassSaving != null) {
            retrievedClassSaving.removeRelatedElement(student);
            result = true;
        }

        return result;
    }

    private com.tx.simplescheduling.model.Class updateRelatedElementSafely(
            Object key, Map map, Student student) {
        com.tx.simplescheduling.model.Class result = null;

        ClassSaving retrievedClassSaving = (ClassSaving) map.get(key);

        if (retrievedClassSaving != null) {
            retrievedClassSaving.updateRelatedElement(student);
            result = retrievedClassSaving.createRelatedElement();
        }

        return result;
    }

    public Map<String, ClassSaving> getIdentifierMap() {
        return classCodeMap;
    }

    public Map<String, ClassSaving> getTypicalSearchMap() {
        return classTitleMap;
    }

}
