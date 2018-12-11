/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import Model.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.UserProfile;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author hunbl
 */
@WebServlet(urlPatterns = {"/insertUser"})
public class insertUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet insertUser</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet insertUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 50 * 1024;
   private int maxMemSize = 4 * 1024;
   private File file ;

   public void init( ){
      // Get the file location where it would be stored.
      filePath = 
             getServletContext().getInitParameter("file-upload"); 
   }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);        
        response.setContentType("text/html;charset=UTF-8");        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            // Check that we have a file upload request   
            String inData=request.getReader().readLine();
            String a="",b="",c="";
            try {
                JSONObject jsonUserObj= new JSONObject(inData);
                a= jsonUserObj.get("name").toString();
                b=jsonUserObj.get("email").toString();
                c=jsonUserObj.get("password").toString();
                
                UserProfile user=new UserProfile(jsonUserObj.get("name").toString(),jsonUserObj.get("email").toString(),jsonUserObj.get("password").toString());
                DatabaseConnection db = (DatabaseConnection) getServletContext().getAttribute("db");
                user.addnewUser(user,db);
                
                ///////////////////////SEND Confirmation Email
 String to=b;//change accordingly  
  
  //Get the session object  
  Properties props = new Properties();  
  props.put("mail.smtp.host", "smtp.gmail.com");  
  props.put("mail.smtp.socketFactory.port", "465");  
  props.put("mail.smtp.socketFactory.class",  
            "javax.net.ssl.SSLSocketFactory");  
  props.put("mail.smtp.auth", "true");  
  props.put("mail.smtp.port", "465");  
   
  Session session = Session.getDefaultInstance(props,  
   new javax.mail.Authenticator() {  
   protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication("mu@gmail.com","pernian2010");//change accordingly  
   }}); 

  try {  
   MimeMessage message = new MimeMessage(session);  
   message.setFrom(new InternetAddress("PotatoPotato@gmail.com"));//change accordingly  
   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
   message.setSubject("Welcome "+a);
   String AuthLink="http://localhost:8084/WebApplication2/EmailVarificationSystem?VarificationCode=";
   Random rand=new Random();
   Integer AuthNumber=rand.nextInt((900000000 - 100000000) + 1) + 100000000;
   AuthLink+=AuthNumber.toString();
          
   message.setText("You have successfully Signed up now you just need to follow the link below to experience our wonderfull services! \n"+AuthLink);  
     
   //send message  
   Transport.send(message);  
  
   System.out.println("message sent successfully");  
   
  } catch (MessagingException e) {throw new RuntimeException(e);}  
  
            } catch (JSONException ex) {
                Logger.getLogger(insertUser.class.getName()).log(Level.SEVERE, null, ex);
            }


            out.println("New User Added as "+a+"\n"+b);
    
        }        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
