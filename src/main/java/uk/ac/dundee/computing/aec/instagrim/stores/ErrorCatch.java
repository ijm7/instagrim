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
    
    /**Setter for the login error
     * 
     * @param result 
     */
    public void setLoginError(boolean result){
        this.logError=result;
    }
    
    /**Getter for the login error
     * 
     * @return 
     */
    public boolean getLoginError(){
        return logError;
    }
}
