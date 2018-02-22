/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tx.simplescheduling.process;

import javax.ws.rs.InternalServerErrorException;

/**
 *
 * @author Luis Kupferberg Ruiz
 */
public class GenericProcess {
    
    
    private InternalServerErrorException buildException(Exception ex,
            String message) {
        InternalServerErrorException result = new InternalServerErrorException(
                message, ex);

        return result;
    }
    
    
    
}
