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
    private boolean imageDeleted=false;
    public void UploadSuccess() {

    }
    
    /**Setter for the upload success
     * 
     * @param upload bool indicating success
     */
    public void setUploadSuccess(boolean upload)
    {
        this.uploadComplete=upload;
    }
    
    /**Getter for the upload success
     *  
     * @return the bool indicating success
     */
    public boolean getUploadSuccess()
    {
        return uploadComplete;
    }
}
