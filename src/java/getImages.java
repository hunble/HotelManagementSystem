/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author hunbl
 */
public class getImages extends HttpServlet {

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
            out.println("<title>Servlet getImages</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet getImages at " + request.getContextPath() + "</h1>");
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
            int type=Integer.parseInt(request.getParameter("roomType"));
            try (PrintWriter out = response.getWriter()) {
            if(type>=1&&type<=3)
            {
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
                    
                if(type==1)
                {
                    File file=new File(root+"/web/Uploads/single");
                    List<String> table=new ArrayList<String>();

                    for (final File fileEntry : file.listFiles()) {
                       String abc=fileEntry.getPath().substring(fileEntry.getPath().lastIndexOf("Uploads\\"));
                        table.add(abc);
                    }
                    JSONObject roomPics=new JSONObject();
                    for(int i=0;i<table.size();i++)
                    {
                        roomPics.put(Integer.toString(i), table.get(i));
                    }
                    out.println(roomPics);
                }
                else if(type==2)
                {
                    File file=new File(root+"/web/Uploads/double");
                    List<String> table=new ArrayList<String>();

                    for (final File fileEntry : file.listFiles()) {
                       String abc=fileEntry.getPath().substring(fileEntry.getPath().lastIndexOf("Uploads\\"));
                        table.add(abc);
                    }
                    JSONObject roomPics=new JSONObject();
                    for(int i=0;i<table.size();i++)
                    {
                        roomPics.put(Integer.toString(i), table.get(i));
                    }
                    out.println(roomPics);
                }
                else if(type==3)
                {
                    File file=new File(root+"/web/Uploads/suite");
                    List<String> table=new ArrayList<String>();

                    for (final File fileEntry : file.listFiles()) {
                       String abc=fileEntry.getPath().substring(fileEntry.getPath().lastIndexOf("Uploads\\"));
                        table.add(abc);
                    }
                    JSONObject roomPics=new JSONObject();
                    for(int i=0;i<table.size();i++)
                    {
                        roomPics.put(Integer.toString(i), table.get(i));
                    }
                    out.println(roomPics);
                }
            }
            else{
                out.print("false");
            }
            }catch(JSONException ex)
            {
                ex.printStackTrace();
            }
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
        processRequest(request, response);
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
