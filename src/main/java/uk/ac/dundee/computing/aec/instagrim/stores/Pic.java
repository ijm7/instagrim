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
import java.util.Locale;
import java.util.TimeZone;
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
    
    /**Setter for the UUID
     * 
     * @param UUID the uuid
     */
    public void setUUID(java.util.UUID UUID){
        this.UUID =UUID;
    }
    
    /**Getter for the UUID
     * 
     * @return the uuid
     */
    public String getSUUID(){
        return UUID.toString();
    }
    
    
    /**Sets details about the picture
     * 
     * @param bImage    the image bytebuffer
     * @param length    the length of the image file
     * @param date      the date of image upload
     * @param type      the type of image
     * @param name      the name of the image
     */
    public void setPic(ByteBuffer bImage, int length,Date date, String type, String name) {
        this.bImage = bImage;
        this.length = length;
        this.date=date;
        this.type=type;
        this.name=name;
    }
    
    /**Setter for the date
     * 
     * @param date the date
     */
    public void setDate(Date date)
    {
        this.date=date;
    }
    
    /**Getter for the date
     * 
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }
    
    /**Getter for the date in text
     * 
     * @return the date in text
     */
    public String getTextDate()
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        //TimeZone zone = TimeZone.getTimeZone("UTC");
        //df.setTimeZone(zone);
        
        String textDate = df.format(date);
        return textDate;
    }
    
    /**Getter for the bytebuffer
     * 
     * @return the bytebuffer
     */
    public ByteBuffer getBuffer() {
        return bImage;
    }

    /**Getter for the length
     * 
     * @return the length
     */
    public int getLength() {
        return length;
    }
    
    /**Getter for the type
     * 
     * @return the type
     */
    public String getType(){
        return type;
    }
    
    /**Getter for the name
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**Setter for the name
     * 
     * @param name the name
     */
    public void setName(String name)
    {
        this.name=name;
    }
    
    /**Getter for the username
     * 
     * @return the username
     */
    public String getUser()
    {
        return user;
    }
    
    /**Setter for the user
     * 
     * @param user sets the user
     */
    public void setUser(String user)
    {
        this.user=user;
    }

    /**Getter for the byte array
     * 
     * @return the byte array
     */
    public byte[] getBytes() {
         
        byte image[] = Bytes.getArray(bImage);
        return image;
    }

}
