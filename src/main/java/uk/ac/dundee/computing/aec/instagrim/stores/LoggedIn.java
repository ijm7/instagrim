/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.stores;
import uk.ac.dundee.computing.aec.instagrim.models.User;
/**
 *
 * @author Administrator
 */
public class LoggedIn {
    private boolean logedin=false;
    private String Username=null;
    private String First_Name=null;
    private String Last_Name=null;
    private String Email=null;
    private boolean logError=false;
    private boolean admin=false;
    private String count=null;
    private boolean delete=false;
    public void LogedIn(){
        
    }
    
    /**Setter for the username
     * 
     * @param name the username of the user
     */
    public void setUsername(String name){
        this.Username=name;
    }
    
    /**Getter for the username
     * 
     * @return  the username
     */
    public String getUsername(){
        return Username;
    }
    
    /**Sets the login attribute as true
     * 
     */
    public void setLogedin(){
        logedin=true;
    }
    
    /**Sets the login attribute as false
     * 
     */
    public void setLogedout(){
        logedin=false;
    }
    
    /**Setter for the first name
     * 
     * @param firstname     the first name
     */
    public void setUserFirstName(String firstname)
    {
        this.First_Name=firstname;
    }
    
    /**Getter for the users first name
     * 
     * @return the first name
     */
    public String getUserFirstName()
    {
        return First_Name;
    }
    
    /**Setter for the users last name
     * 
     * @param lastname the last name of the user
     */
    public void setUserLastName(String lastname)
    {
        this.Last_Name=lastname;
    }
    
    /**Getter for the users last name
     * 
     * @return the users last name
     */
    public String getUserLastName()
    {
        return Last_Name;
    }
    
    /**Setter for the users email
     * 
     * @param email the email of the user
     */
    public void setUserEmail(String email)
    {
        this.Email=email;
    }
    
    /**Getter for the users email
     * 
     * @return the email address of the user
     */
    public String getUserEmail()
    {
        return Email;
    }
    
    /**Setter for the loginstate
     * 
     * @param logedin indicates the value of login
     */
    public void setLoginState(boolean logedin){
        this.logedin=logedin;
    }
    
    /**Getter for the loginstate
     * 
     * @return the logged in user
     */
    public boolean getlogedin(){
        return logedin;
    }
    
    /**Getter for the image count
     * 
     * @return a count as a string
     */
    public String getImageCount()
    {
        return count;
    }
    
    /**Adds one to the image count
     * 
     */
    public void addImageCount()
    {
        int a = Integer.parseInt(count);
        int b = a + 1;
        count = Integer.toString(b);
    }
    
    /**Decrements one from the image count
     * 
     */
    public void minusImageCount()
    {
        int a = Integer.parseInt(count);
        int b = a - 1;
        count = Integer.toString(b);
    }
    
    /**Sets the value of the image count
     * 
     * @param count the amount of images uploaded by the user
     */
    public void setImageCount(String count)
    {
        this.count=count;
    }
    
    /**sets whether the user has deleted a image
     * 
     * @param delete bool for whether an image has been deleted
     */
    public void setDelete(boolean delete)
    {
        this.delete=delete;
    }
    
    /**Gets if the user has deleted an image
     * 
     * @return the delete bool
     */
    public boolean getDelete()
    {
        return delete;
    }
    
}
