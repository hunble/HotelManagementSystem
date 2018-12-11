package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hunbl
 */
import java.sql.PreparedStatement;
import org.json.JSONObject;
import org.json.JSONArray;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserProfile {
    public String name;
    private String email;
    private String password;
    public UserProfile(String pname,String pemail,String ppassword)
    {
        name=pname;
        email=pemail;
        password=ppassword;
    }
    public void addnewUser(UserProfile obj,DatabaseConnection db){
        try{
            String sql = "INSERT INTO hotel.userprofile(userName,email,userPassword) VALUES (?,?,?);";
            PreparedStatement ps=db.getConnection().prepareStatement(sql);
            ps.setString(1,obj.name);
            ps.setString(2,obj.email);
            ps.setString(3,obj.password);
            db.preparedUpdateSql(ps);
         // Clean-up environment
        }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
        }
    }
    public JSONObject showUser(DatabaseConnection db){
         // JDBC driver name and database URL        
             int i=0;
             JSONObject mJSONObject=new JSONObject();             

         try{
         String sql = "select * from userprofile;";
         ResultSet rs = db.SelectSql(sql);
         
         // Extract data from result set


         while(rs.next())
         {  
             mJSONObject.put("email",rs.getString("email"));
             mJSONObject.put("userName", rs.getString("userName"));
             i++;
         }

         // Clean-up environment
         rs.close();
        }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
        }catch(Exception e){
         //Handle errors for Class.forName
          e.printStackTrace();
        }
         if(i>=1)
         {
            return mJSONObject; 
         }
         else
            return null;

}
public JSONObject login(String name,String password,DatabaseConnection db)
    {
         JSONObject Result=new JSONObject();
        
try{
         String sql = "select * from userprofile where (email=? or userName=?) and userPassword=?;";

            PreparedStatement ps=db.getConnection().prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,name);
            ps.setString(3,password);
            ResultSet rs = db.preparedSelectSql(ps);

         // Extract data from result set
             int i=0;
         while(rs.next())
         {
             Result.put("userID",rs.getString("userID"));
             Result.put("name",rs.getString("userName"));
             Result.put("email",rs.getString("email"));
             Result.put("aStatus",rs.getString("activationStatus"));
             i++;
         }
         // Clean-up environment
         rs.close();
        }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
        }catch(Exception e){
         //Handle errors for Class.forName
          e.printStackTrace();
        }
        return Result;        
    }

 public boolean UpdateProfile(DatabaseConnection db,int userID)
    {
        int rs=0;
try{
    
         String sql = "UPDATE hotel.userprofile SET userName = ? ,email = ? ,userPassword = ? WHERE userID = ?;";
            PreparedStatement ps=db.getConnection().prepareStatement(sql);
            ps.setString(1,this.name);
            ps.setString(2,this.email);
            ps.setString(3,this.password);
            ps.setInt(4,userID);
            rs=db.preparedUpdateSql(ps);
    
         // Clean-up environment
        }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
        }catch(Exception e){
         //Handle errors for Class.forName
          e.printStackTrace();
        }
         if(rs>0)
             return true;
         else
             return false;
    }
   

}