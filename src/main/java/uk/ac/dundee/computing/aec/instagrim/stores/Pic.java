/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

import com.datastax.driver.core.utils.Bytes;
import java.nio.ByteBuffer;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *
 * @author Administrator
 */
public class Pic {

    private ByteBuffer bImage = null;
    private int length;
    private String type;
    private java.util.UUID UUID=null;
    private String name;
    private Date date;
    private String user;
    
    
    public void Pic() {

    }
    public void setUUID(java.util.UUID UUID){
        this.UUID =UUID;
    }
    public String getSUUID(){
        return UUID.toString();
    }
    
    /*public void setPic(ByteBuffer bImage, int length,String type) {
        this.bImage = bImage;
        this.length = length;
        this.type=type;
        
    }*/
    
    public void setPic(ByteBuffer bImage, int length,Date date, String type, String name) {
        this.bImage = bImage;
        this.length = length;
        this.date=date;
        this.type=type;
        this.name=name;
    }
    
    public void setDate(Date date)
    {
        this.date=date;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public String getTextDate()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String textDate = df.format(date);
        return textDate;
    }
    
    public ByteBuffer getBuffer() {
        return bImage;
    }

    public int getLength() {
        return length;
    }
    
    public String getType(){
        return type;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name=name;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public void setUser(String user)
    {
        this.user=user;
    }
    
    
    
    

    public byte[] getBytes() {
         
        byte image[] = Bytes.getArray(bImage);
        return image;
    }

}
