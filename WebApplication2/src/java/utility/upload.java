package utility;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hunbl
 */
public class upload {

   private boolean isMultipart;
   public String filePath;
   private int maxFileSize = 3000 * 1024;
   private int maxMemSize = 4 * 1024;
   private File file ;
   
    public boolean uploadFile(HttpServletRequest request)
    {
        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);

        if( !isMultipart ){
           return false;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax( maxFileSize );

        try{ 
        // Parse the request to get file items.
        List fileItems = upload.parseRequest(request);

        // Process the uploaded file items
        Iterator i = fileItems.iterator();

        while ( i.hasNext () ) 
        {
           FileItem fi = (FileItem)i.next();
           if ( !fi.isFormField () )	
           {
              // Get the uploaded file parameters
              String fieldName = fi.getFieldName();
              String fileName = fi.getName();
              String contentType = fi.getContentType();
              boolean isInMemory = fi.isInMemory();
              long sizeInBytes = fi.getSize();
              // Write the file
                int ik = 0;
                List<String> table=new ArrayList<String>();
                File file2=new File(filePath);
                for (final File fileEntry : file2.listFiles()) {
                   table.add(fileEntry.getPath());
                }
                for(int j=0;j<table.size();j++)
                {
                    String a=table.get(j).substring(table.get(j).lastIndexOf("\\")+1);
                    String b=fileName;
                    if(Objects.equals(a,b))
                    {
                        fileName="1"+fileName;
                    }
                }
              if( fileName.lastIndexOf("\\") >= 0 ){
                 file = new File( filePath + 
                 fileName.substring( fileName.lastIndexOf("\\"))) ;
              }else{
                 file = new File( filePath + 
                 fileName.substring(fileName.lastIndexOf("\\")+1)) ;
              }
              fi.write( file ) ;
           }
        }
     }catch(Exception ex) {
         System.out.println(ex);
         return false;
     }
        return true;
    }


}