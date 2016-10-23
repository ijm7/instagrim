/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import java.math.BigInteger;

/**
 *
 * @author Administrator
 */
public class User {
    Cluster cluster;
    public User(){
        
    }
    
    /**Method to add a user to the database
     * 
     * @param username      the username of user
     * @param Password      the password of user
     * @param email         the email of user
     * @param first_name    the first name of user
     * @param last_name     the last name of user
     * @return              result of the method
     */
    public boolean RegisterUser(String username, String Password, String email, String first_name, String last_name){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
       //String[] emailconvert = new String[1];
       //emailconvert[0]=email;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into userprofiles (login,password,first_name,last_name,email) Values(?,?,?,?,?)");
        //PreparedStatement ps = session.prepare("insert into userprofiles (login,password,first_name,last_name) Values(?,?,?,?)");
        //PreparedStatement ps = session.prepare("insert into userprofiles (login,password) Values(?,?)");
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username,EncodedPassword,first_name,last_name,email));
                        //username,EncodedPassword,first_name,last_name));
                        //username,EncodedPassword));
        return true;
    }
    
    /**Checks if the password entered for a user is valid
     * 
     * @param username      the username entered
     * @param Password      the password entered
     * @return              the result of method
     */
    public boolean IsValidUser(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                {
                    return true;
                }
            }
        }
    return false;  
    }
    
    /**Checks if an entered username and email are available
     * 
     * @param username      the username to check
     * @param email         the email to check
     * @return 
     */
    public boolean IsAvailable(String username, String email){
                int number=0;
                Session session = cluster.connect("instagrim");
                PreparedStatement ps = session.prepare("select COUNT(*) from userprofiles where login =?");
                ResultSet rs = null;
                BoundStatement boundStatement = new BoundStatement(ps);
                rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
                for (Row row : rs) {
                long StoredPass = row.getLong("count");
                if (StoredPass == 0)
                    {
                        number++;
                    }
                }
                PreparedStatement ps2 = session.prepare("select COUNT(*) from userprofiles where email =? ALLOW FILTERING");
                ResultSet rs2 = null;
                BoundStatement boundStatement2 = new BoundStatement(ps2);
                rs2 = session.execute( // this is where the query is executed
                boundStatement2.bind( // here you are binding the 'boundStatement'
                        email));
                for (Row row2 : rs2) {
                long StoredPass2 = row2.getLong("count");
                if (StoredPass2 == 0)
                    {
                        number++;
                    }
                }
                if (number==2)
                {
                    return true;
                }
                else
                {
                    return false;
                }
    }
    
    /**gets the firstname of the user from the database
     * 
     * @param username      the username of the user
     * @return              the firstname
     */
    public String getFirstName(String username)
    {
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select first_name from userprofiles where login =?");
        BoundStatement boundStatement = new BoundStatement(ps);
        ResultSet rs = null;
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        String StoredPass=null;
        for (Row row : rs) {
                StoredPass = row.getString("first_name");
                
        }
       return StoredPass;   
    }
    
    /**method for counting the amount of images a user has uploaded
     * 
     * @return      the amount of images
     */
    public String getImageAmount(String user)
    {
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select COUNT(*) from userpiclist where user=?");
        BoundStatement boundStatement = new BoundStatement(ps);
        ResultSet rs = null;
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        user));
        long StoredPass=0;
        for (Row row : rs) {
            StoredPass = row.getLong("count");
        }
        String passer=Long.toString(StoredPass);
       return passer;   
    }
    
    /**gets the last name of the user from the database
     * 
     * @param username      the username of the user
     * @return              the last name
     */
    public String getLastName(String username)
    {
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select last_name from userprofiles where login =?");
        BoundStatement boundStatement = new BoundStatement(ps);
        ResultSet rs = null;
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        String StoredPass=null;
        for (Row row : rs) {
                StoredPass = row.getString("last_name");
        }
       return StoredPass;
    }
    
    /**gets the email of the user from the database
     * 
     * @param username      the username of the user
     * @return              the email
     */
    public String getEmail(String username)
    {
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select email from userprofiles where login =?");
        BoundStatement boundStatement = new BoundStatement(ps);
        ResultSet rs = null;
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        String StoredPass=null;
        for (Row row : rs) {
                StoredPass = row.getString("email");
        }
       return StoredPass;
    }

    /**Sets the cluster for the user
     * 
     * @param cluster   the set cluster
     */    
    public void setCluster(Cluster cluster) 
    {
        this.cluster = cluster;
    }
}
