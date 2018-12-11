/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author hunbl
 */
public class Registration {
    private int userID;
    private int roomType;
    private String checkin;
    private String checkout;
    public Registration(int u,int r,String ci,String co)
    {
        userID=u;
        roomType=r;
        checkin=ci;
        checkout=co;
    }
    
    public boolean Register(DatabaseConnection db){
        if(this.roomType<=3&&this.roomType>=1)
        {
            try{
                int approvedQuantity=0;
                String sql1 = "select aprovedQuantity from roomtype where roomID="+this.roomType+";";
                ResultSet r2=db.SelectSql(sql1);
                while(r2.next()){
                approvedQuantity=r2.getInt("aprovedQuantity");
                }
                
                String sql = 
"select count(RoomID) as bookedRooms from registration where BookedDates in (select * from \n" +
"(select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1,\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v\n" +
"where selected_date between '"+this.checkin+"' and '"+this.checkout+"') and RoomID="+this.roomType+" group by BookedDates;";

                
                boolean isAvai=true;
                ResultSet r=db.SelectSql(sql);
                while(r.next())
                {
                    if(r.getInt("bookedRooms")>=approvedQuantity)
                        isAvai=false;
                }
                
                if(isAvai)
                {
                    String sql2="INSERT INTO hotel.registration(RoomID,UserID,BookedDates) select "+this.roomType+","+this.userID+",selected_date from \n" +
                                "(select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from\n" +
                                " (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,\n" +
                                " (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1,\n" +
                                " (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,\n" +
                                " (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,\n" +
                                " (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v\n" +
                                "where selected_date between '"+this.checkin+"' and '"+this.checkout+"';";
                    db.UpdateSql(sql2);
                    return true;
                }
                else
                {
                    return false;
                }
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }
        }
        return false;
    }
    
    public boolean getFreeDates()
    {
     return false;   
    }
    public JSONArray getRegistration(int id,DatabaseConnection db)
    {
        JSONArray ResultArr= new JSONArray();

        
try{
         String sql = "SELECT ID, RoomID, UserID, BookedDates FROM hotel.registration where UserID="+id+";";
         ResultSet rs = db.SelectSql(sql);
         
         while(rs.next())
         {
            JSONObject Result=new JSONObject();
             Result.put("RoomID",rs.getString("RoomID"));
             Result.put("BookedDate",rs.getString("BookedDates"));
             ResultArr.put(Result);
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
        return ResultArr;        
    }
        public JSONArray Search(DatabaseConnection db,float sPrice,float ePrice)
    {
        JSONArray ResultArr= new JSONArray();    
    try{
        String sql="";
        if(ePrice==0) ePrice=2147483647;
        
        if(checkin==""||checkout=="")
        {
            System.out.print("Please Specify a Check in and Check out date");
            return null; 
        }
        if (roomType==-1)
        {
            sql = "select registration.RoomID,registration.BookedDates,aprovedQuantity-count(registration.RoomID) RemianiningReg,aprovedPrice,aprovedQuantity FROM registration left outer join roomtype ON roomtype.roomID = registration.RoomID  where BookedDates in\n" +
"(select * from \n" +
"(select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1,\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v\n" +
"where selected_date between '"+checkin+"' and '"+checkout+"') and roomtype.aprovedPrice between '"+sPrice+"' and '"+ePrice+"' group by RoomID , registration.BookedDates;";
        }
        else 
        {
            sql = "select registration.RoomID,registration.BookedDates,aprovedQuantity-count(registration.RoomID) RemianiningReg,aprovedPrice,aprovedQuantity FROM registration left outer join roomtype ON roomtype.roomID = registration.RoomID  where BookedDates in\n" +
"(select * from \n" +
"(select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1,\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,\n" +
" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v\n" +
"where selected_date between '"+checkin+"' and '"+checkout+"') and roomtype.aprovedPrice between '"+sPrice+"' and '"+ePrice+"' and registration.RoomID = "+roomType+" group by RoomID , registration.BookedDates;";
        }
         
         ResultSet rs = db.SelectSql(sql);
         
         while(rs.next())
         {
            JSONObject Result=new JSONObject();
            Result.put("RoomID",rs.getString("RoomID"));
            Result.put("BookedDate",rs.getString("BookedDates"));
            Result.put("RemainingRoom",rs.getString("RemianiningReg"));
            Result.put("aprovedPrice",rs.getString("aprovedPrice"));
            Result.put("aprovedQuantity",rs.getString("aprovedQuantity"));
            ResultArr.put(Result);
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
        return ResultArr;        
    }
}
