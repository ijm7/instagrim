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
    public void LogedIn(){
        
    }
    
    public void setUsername(String name){
        this.Username=name;
    }
    public String getUsername(){
        return Username;
    }
    public void setLogedin(){
        logedin=true;
    }
    public void setLogedout(){
        logedin=false;
    }
    
    public void setUserFirstName(String firstname)
    {
        this.First_Name=firstname;
    }
    
    public String getUserFirstName()
    {
        return First_Name;
    }
    
    public void setUserLastName(String lastname)
    {
        this.Last_Name=lastname;
    }
    
    public String getUserLastName()
    {
        return Last_Name;
    }
    
    public void setUserEmail(String email)
    {
        this.Email=email;
    }
    
    public String getUserEmail()
    {
        return Email;
    }
    
    public void setLoginState(boolean logedin){
        this.logedin=logedin;
    }
    public boolean getlogedin(){
        return logedin;
    }
    
    public void setAdmin(boolean admin)
    {
        this.admin=admin;
    }
    
    public boolean getAdmin()
    {
        return admin;
    }
    
    public String getImageCount()
    {
        return count;
    }
    
    public void addImageCount()
    {
        int a = Integer.parseInt(count);
        int b = a + 1;
        count = Integer.toString(b);
    }
    public void setImageCount(String count)
    {
        this.count=count;
    }
    
}
