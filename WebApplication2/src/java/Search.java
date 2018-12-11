/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.DatabaseConnection;
import Model.Registration;
import Model.UserProfile;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author hunbl
 */
public class Search extends HttpServlet {

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
            out.println("<title>Servlet Search</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Search at " + request.getContextPath() + "</h1>");
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
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                String inData=request.getReader().readLine();
        String cin="",cout="";int rtype=-1,eP=0,sP=0;
        try {
            JSONObject jsonUserObj= new JSONObject(inData);
            String a,b,c;
            a=jsonUserObj.get("RoomID").toString();
            b=jsonUserObj.get("sPrice").toString();
            c=jsonUserObj.get("ePrice").toString();
            if(b.equals(""))
                sP=0;
            else
                sP=Integer.parseInt(b);
            if(c.equals(""))
                eP=0;
            else
                eP=Integer.parseInt(c);
            if(a.equals("0"))
                rtype=-1;
            else
                rtype=Integer.parseInt(a); 
            
            cin= jsonUserObj.get("checkin").toString();
            cout= jsonUserObj.get("checkout").toString();
            
            Registration reg=new Registration(0,rtype,cin,cout);
            DatabaseConnection db = (DatabaseConnection) getServletContext().getAttribute("db");
            JSONArray Result=reg.Search(db, sP, eP);

            if(Result.length()==0)
                out.println("-1");
            else
            {
                // Create a session object if it is already not  created.
                out.println(Result);
            }   
            } catch (JSONException ex) {
            Logger.getLogger(insertUser.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e)
        {
            System.out.print(e);
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
