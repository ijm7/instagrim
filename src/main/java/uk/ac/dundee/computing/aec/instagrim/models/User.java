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
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;
    }
    
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
    
    public boolean IsAvailable(String username, String email){
        
        /*Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select from userprofiles where email =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        email));*/
        
        /*PreparedStatement ps = session.prepare("select login,email from userprofiles");
        ResultSet rs = null;
        rs = session.exec*/
        
            //for (Row row : rs) {
                /*String UserCheck = row.getString("login");
                String EmailCheck = row.getString("email");
                if (UserCheck.compareTo(username)==0 && EmailCheck.compareTo(email)==0)
                {
                    return true;
                } */
                
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
    
    public String getImageAmount()
    {
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select COUNT(*) from userpiclist");
        BoundStatement boundStatement = new BoundStatement(ps);
        ResultSet rs = null;
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        ));
        long StoredPass=0;
        for (Row row : rs) {
            StoredPass = row.getLong("count");
                
                
        }
        String passer=Long.toString(StoredPass);
       return passer;   
    }
    
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

       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
}
