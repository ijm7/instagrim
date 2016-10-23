package uk.ac.dundee.computing.aec.instagrim.models;

/*
 * Expects a cassandra columnfamily defined as
 * use keyspace2;
 CREATE TABLE Tweets (
 user varchar,
 interaction_time timeuuid,
 tweet varchar,
 PRIMARY KEY (user,interaction_time)
 ) WITH CLUSTERING ORDER BY (interaction_time DESC);
 * To manually generate a UUID use:
 * http://www.famkruithof.net/uuid/uuidgen
 */
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.Bytes;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import javax.imageio.ImageIO;
import static org.imgscalr.Scalr.*;
import org.imgscalr.Scalr.Method;
import java.util.UUID;
import java.util.Calendar;

import uk.ac.dundee.computing.aec.instagrim.lib.*;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
//import uk.ac.dundee.computing.aec.stores.TweetStore;

public class PicModel {

    Cluster cluster;

    public void PicModel() {

    }

    /**
     * 
     * @param cluster 
     */
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    /**Method that Inserts image into database
     * 
     * @param b         a byte
     * @param type      type of image  
     * @param name      name of image
     * @param user      user of image
     * @param filter    filter applied to image
     */
    public void insertPic(byte[] b, String type, String name, String user, int filter) {
        try {
            Convertors convertor = new Convertors();
            String types[]=Convertors.SplitFiletype(type);
            ByteBuffer buffer = ByteBuffer.wrap(b);
            int length = b.length;
            java.util.UUID picid = convertor.getTimeUUID();
            Boolean success = (new File("/var/tmp/instagrim/")).mkdirs();
            FileOutputStream output = new FileOutputStream(new File("/var/tmp/instagrim/" + picid));
            output.write(b);
            byte []  thumbb = picresize(picid.toString(),types[1], filter);
            int thumblength= thumbb.length;
            ByteBuffer thumbbuf=ByteBuffer.wrap(thumbb);
            byte[] processedb = picdecolour(picid.toString(),types[1], filter);
            ByteBuffer processedbuf=ByteBuffer.wrap(processedb);
            int processedlength=processedb.length;
            Session session = cluster.connect("instagrim");
            PreparedStatement psInsertPic = session.prepare("insert into pics ( picid, image,thumb,processed, user, interaction_time,imagelength,thumblength,processedlength,type,name) values(?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement psInsertPicToUser = session.prepare("insert into userpiclist ( picid, user, pic_added) values(?,?,?)");
            BoundStatement bsInsertPic = new BoundStatement(psInsertPic);
            BoundStatement bsInsertPicToUser = new BoundStatement(psInsertPicToUser);
            Date DateAdded = new Date();
            session.execute(bsInsertPic.bind(picid, buffer, thumbbuf,processedbuf, user, DateAdded, length,thumblength,processedlength, type, name));
            session.execute(bsInsertPicToUser.bind(picid, user, DateAdded));
            session.close();
        } catch (IOException ex) {
            System.out.println("Error --> " + ex);
        }
    }

    /**method to resize to thumbnail
     * 
     * @param picid     uuid of image
     * @param type      type of image
     * @param filter    filter to apply to image
     * @return          the resized thumbnail
     */
    public byte[] picresize(String picid,String type, int filter) {
        try {
            BufferedImage BI = ImageIO.read(new File("/var/tmp/instagrim/" + picid));
            BufferedImage thumbnail = createThumbnail(BI, filter);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(thumbnail, type, baos);
            baos.flush();
            
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException et) {

        }
        return null;
    }
    
    /**method to decolour the image
     * 
     * @param picid     uuid of image
     * @param type      type of image
     * @param filter    filter to be applied
     * @return          the bytes of the picture
     */
    public byte[] picdecolour(String picid,String type, int filter) {
        try {
            BufferedImage BI = ImageIO.read(new File("/var/tmp/instagrim/" + picid));
            BufferedImage processed = createProcessed(BI, filter);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(processed, type, baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException et) {
        }
        return null;
    }

    /**Creates thumbnail with filter
     * 
     * @param img       the image to be modified
     * @param filter    the filter to be applied
     * @return          the thumbnail with filter applied
     */
    public static BufferedImage createThumbnail(BufferedImage img, int filter) {
        if (filter==1)
        {//grey
            img = resize(img, Method.SPEED, 250, OP_ANTIALIAS, OP_GRAYSCALE);
        }
        else if (filter==2)
        {//brighter
            img = resize(img, Method.SPEED, 250, OP_ANTIALIAS, OP_BRIGHTER, OP_BRIGHTER,OP_BRIGHTER, OP_BRIGHTER, OP_BRIGHTER, OP_BRIGHTER);
        }
        else if (filter==3)
        {
            img = resize(img, Method.SPEED, 250, OP_ANTIALIAS, OP_DARKER, OP_DARKER,OP_DARKER,OP_DARKER,OP_DARKER,OP_DARKER);
        }
        else
        {//normal
            img = resize(img, Method.SPEED, 250, OP_ANTIALIAS);
        }
        return pad(img, 2);
    }
    
    /**Creates full size with filter
     * 
     * @param img       the image to be modified
     * @param filter    the filter to be applied
     * @return          the picture with filter applied
     */
   public static BufferedImage createProcessed(BufferedImage img, int filter) {
        int Width=img.getWidth()-1;
        if (filter==1)
        {//grey
            img = resize(img, Method.SPEED, Width, OP_ANTIALIAS, OP_GRAYSCALE);
        }
        else if (filter==2)
        {//brighter
            img = resize(img, Method.SPEED, Width, OP_ANTIALIAS, OP_BRIGHTER,  OP_BRIGHTER,OP_BRIGHTER, OP_BRIGHTER, OP_BRIGHTER, OP_BRIGHTER);
        }
        else if (filter==3)
        {//darker
            img = resize(img, Method.SPEED, Width, OP_ANTIALIAS, OP_DARKER,  OP_DARKER,OP_DARKER, OP_DARKER, OP_DARKER, OP_DARKER);
        }
        else
        {//normal
            img = resize(img, Method.SPEED, Width, OP_ANTIALIAS);
        }
        //OP_GRAYSCALE
        return pad(img, 4);
    }
   
   /**returns a linked list of pictures
    * 
    * @param User   The user whom requests the pictures
    * @return 
    */
    public java.util.LinkedList<Pic> getPicsForUser(String User) {
        java.util.LinkedList<Pic> Pics = new java.util.LinkedList<>();
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select picid,pic_added from userpiclist where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        User));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
                Pic pic = new Pic();
                java.util.UUID UUID = row.getUUID("picid");
                Date date = row.getTimestamp("pic_added");
                System.out.println("UUID" + UUID.toString());
                pic.setUUID(UUID);
                pic.setDate(date);
                pic.setUser(User);
                Pics.add(pic);
            }
        }
    return Pics;
    }

    /**Retrieves pictures from database
     * 
     * @param image_type    the type of image
     * @param picid         the uuid of pic to receive
     * @return              the picture
     */
    public Pic getPic(int image_type, java.util.UUID picid) {
        Session session = cluster.connect("instagrim");
        ByteBuffer bImage = null;
        String type = null;
        String name = null;
        Date date=null;
        int length = 0;
        try {
            Convertors convertor = new Convertors();
            ResultSet rs = null;
            PreparedStatement ps = null;
            if (image_type == Convertors.DISPLAY_IMAGE) {
                ps = session.prepare("select image,imagelength,interaction_time,type,name from pics where picid =?");
            } else if (image_type == Convertors.DISPLAY_THUMB) {
                ps = session.prepare("select thumb,imagelength,thumblength,interaction_time,type,name from pics where picid =?");
            } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                ps = session.prepare("select processed,processedlength, interaction_time,type,name from pics where picid =?");
            }
            BoundStatement boundStatement = new BoundStatement(ps);
            rs = session.execute( // this is where the query is executed
                    boundStatement.bind( // here you are binding the 'boundStatement'
                            picid));
            if (rs.isExhausted()) {
                System.out.println("No Images returned");
                return null;
            } else {
                for (Row row : rs) {
                    if (image_type == Convertors.DISPLAY_IMAGE) {
                        bImage = row.getBytes("image");
                        length = row.getInt("imagelength");
                        //date = row.getTimestamp("interaction_time");
                    } else if (image_type == Convertors.DISPLAY_THUMB) {
                        bImage = row.getBytes("thumb");
                        length = row.getInt("thumblength");
                        //date = row.getTimestamp("interaction_time");
                    } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                        bImage = row.getBytes("processed");
                        length = row.getInt("processedlength");
                        //date = row.getTimestamp("interaction_time");
                    }
                    date = row.getTimestamp("interaction_time");
                    type = row.getString("type");
                    name = row.getString("name");
                }
            }
        } catch (Exception et) {
            System.out.println("Can't get Pic" + et);
            return null;
        }
        session.close();
        Pic p = new Pic();
        p.setPic(bImage, length, date, type, name);
        //p.setPic(bImage, length, type);
        return p;
    }
    
    /**Facility for deleting pictures from database
     * 
     * @param picid     the uuid of the picture to be deleted
     * @param user      the user of the image that will be deleted
     * @return          the success of the deletion
     */
    public boolean deletePic(java.util.UUID picid, String user)
    {
       
        ResultSet rs = null;
        Date date=null;
        Session session = cluster.connect("instagrim");
        PreparedStatement findDate = session.prepare("select interaction_time from pics where picid =?");
        BoundStatement boundStatementFindDate = new BoundStatement(findDate);
        rs=session.execute(boundStatementFindDate.bind(picid));
        for (Row row : rs) {
            date = row.getTimestamp("interaction_time");
        }
        PreparedStatement psBigTable = session.prepare("delete from pics where picid =?");
        PreparedStatement psSmallTable = session.prepare("delete from userpiclist where user =? and pic_added=?");
        BoundStatement boundStatementBigTable = new BoundStatement(psBigTable);
        BoundStatement boundStatementSmallTable = new BoundStatement(psSmallTable);
        session.execute(boundStatementBigTable.bind(picid));
        session.execute(boundStatementSmallTable.bind(user,date));
        session.close();
        return true;
    }
}
