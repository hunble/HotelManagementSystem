/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listners;

import Model.DatabaseConnection;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContext;
/**
 * Web application lifecycle listener.
 *
 * @author hunbl
 */
public class databaseConnection implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	ServletContext sc = sce.getServletContext();
 
    	String url = sc.getInitParameter("url");
    	String user_name = sc.getInitParameter("user_name");
    	String password = sc.getInitParameter("password");
    	String database = sc.getInitParameter("database");
    	DatabaseConnection db = new DatabaseConnection(url + database, user_name, password);

        //System.out.println("in the listener!!");
    	sc.setAttribute("db", db);    
    }
    
    

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
