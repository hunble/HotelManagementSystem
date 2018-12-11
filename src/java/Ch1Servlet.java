/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.*;

/**
 *
 * @author hunbl
 */
@WebServlet(urlPatterns = {"/Ch1Servlet"})
public class Ch1Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Ch1Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Submitted Username : " + username +" and Password : "+ password +"</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
         out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title></head>\n" +
         "<body bgcolor=\"#f0f0f0\">\n" +
         "<h1 align=\"center\">" + title + "</h1>\n");
	DatabaseConnection db = (DatabaseConnection) getServletContext().getAttribute("db");
        String sql = "SELECT ID, Code, Name, Cr_Hrs, NickName \n" + "FROM giftsdb.cources;";
        
         try{

         ResultSet rs = db.SelectSql(sql);

         // Extract data from result set
         while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("ID");
            String Code = rs.getString("Code");
            String Name = rs.getString("Name");
            String Cr_Hrs = rs.getString("Cr_Hrs");

            //Display values
            out.println("ID: " + id + " ");
            out.println(", Code: " + Code+" ");
            out.println(", Name: " + Name+" ");
            out.println(", Cr_Hrs: " + Cr_Hrs+"<br>");

         }         // Clean-up environment
         rs.close();
         }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
         }

         out.println("</body></html>");
      
         
      UserProfile up=new UserProfile("","","");
      out.print(up.showUser(db));
  }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
