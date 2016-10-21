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
public class UploadSuccess {
    
    private boolean uploadComplete=false;
    public void UploadSuccess() {

    }
    
    public void setUploadSuccess(boolean upload)
    {
        this.uploadComplete=upload;
    }
    
    public boolean getUploadSuccess()
    {
        return uploadComplete;
    }
    
    
}
