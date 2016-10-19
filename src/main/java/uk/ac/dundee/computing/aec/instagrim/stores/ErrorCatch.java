/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

/**
 *
 * @author iainmorton
 */
public class ErrorCatch {
    private boolean logError=false;
    
    public void ErrorCatch(){
        
    }
    
    public void setLoginError(boolean result){
        this.logError=result;
    }
    
    public boolean getLoginError(){
        return logError;
    }
}
