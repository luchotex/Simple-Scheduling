/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.model.param;

import com.tx.simplescheduling.model.GenericModel;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Luis
 */
public abstract class GenericParam {

    public GenericParam() {
    }
    
    public abstract Object getIdentifierField();

    public abstract Set getRelatedCodeList();
    
    public abstract void setRelatedCodeList(Set studentIdList);

    public abstract GenericModel createSavingInstance();

    public abstract Object buildTypicalSearchField();
}
