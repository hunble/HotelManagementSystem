/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.upload;
/**
 *
 * @author hunbl
 */
public class uploadRoomPicture extends HttpServlet {

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
            out.println("<title>Servlet uploadRoomPicture</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet uploadRoomPicture at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          upload u=new upload();
        int type=Integer.parseInt(request.getParameter("roomType"));

          String root = getServletContext().getRealPath("/");
          for(int i=0;i<3;i++)
          {
            if (null != root && root.length() > 0 )
            {
                int endIndex = root.lastIndexOf("\\");
                if (endIndex != -1)  
                {
                  root = root.substring(0, endIndex); // not forgot to put check if(endIndex != -1)
                }
            }
          }
          String add="";
          if(type==1)
          {
            add=root+"/web/Uploads/single";
          }
          if(type==2)
          {
            add=root+"/web/Uploads/double";
          }
          if(type==3)
          {
            add=root+"/web/Uploads/suite";
          }
          
          File theDir = new File(add);
          
// if the directory does not exist, create it
if (!theDir.exists()) {
    //System.out.println("creating directory: " + directoryName);
    boolean result = false;

    try{
        theDir.mkdir();
        result = true;
    } 
    catch(SecurityException se){
        System.out.println(se);
    }        
    if(result) {    
        System.out.println("DIR created");  
    }
}

          u.filePath=theDir.toString()+"\\";
          if(u.uploadFile(request))
          {
              out.print("true");
          }
          else
          {
              out.print("false");
          }
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
