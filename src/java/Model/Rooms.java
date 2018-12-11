/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.json.JSONObject;
import org.json.JSONArray;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import org.json.JSONException;

/**
 *
 * @author hunbl
 */
public class Rooms {
    private int roomType;
    private float price;
    private float aprovedPrice;
    private int quantity;
    private int aprovedQuantity;
    public Rooms(int roomtype,float price,float approvedPrice,int quantity,int approvedQuantity){
    this.roomType=roomtype;
    this.quantity=quantity;
    this.aprovedPrice=approvedPrice;
    this.price=price;
    this.aprovedQuantity=approvedQuantity;
    }
            
    public boolean addRooms(Rooms room,DatabaseConnection db)
    {
        if(room.roomType<=3&&room.roomType>=1)
        {
            try{
                String sql = "update roomtype set quantity=quantity+1 where roomID="+room.roomType+";";
                db.UpdateSql(sql);
                
                sql="INSERT INTO hotel.rooms(type) VALUES ("+room.roomType+");";
                db.UpdateSql(sql);
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean deleteRooms(Rooms room,DatabaseConnection db)
    {
                if(room.roomType<=3&&room.roomType>=1)
        {
            try{
                String sql = "update roomtype set quantity=quantity-1 where roomID="+room.roomType+";";
                db.UpdateSql(sql);
                
                sql="delete from rooms where type="+room.roomType+" order by id desc limit 1;";
                db.UpdateSql(sql);
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean updatePrice(Rooms room,DatabaseConnection db)
    {
        if(room.roomType<=3&&room.roomType>=1)
        {
            try{
                String sql = "update roomtype set price="+room.price+" where roomID="+room.roomType+";";
                db.UpdateSql(sql);
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean approvePrice(Rooms room,DatabaseConnection db)
    {
        if(room.roomType<=3&&room.roomType>=1)
        {
            try{
                String sql = "update roomtype set aprovedPrice=price where roomID="+room.roomType+";";
                db.UpdateSql(sql);
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean updateApprovedPrice(Rooms room,DatabaseConnection db)
    {
        if(room.roomType<=3&&room.roomType>=1)
        {
            try{
                String sql = "update roomtype set aprovedPrice="+room.aprovedPrice+" where roomID="+room.roomType+";";
                db.UpdateSql(sql);
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }
        }
        return true;
    }    
    public boolean approveQuantity(Rooms room,DatabaseConnection db)
    {
        if(room.roomType<=3&&room.roomType>=1)
        {
            try{
                String sql = "update roomtype set aprovedQuantity=quantity where roomID="+room.roomType+";";
                db.UpdateSql(sql);
                
                sql = "update rooms set status=1 where type="+room.roomType+";";
                db.UpdateSql(sql);
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean Updatefeature(Rooms room,DatabaseConnection db,JSONArray feature)
    { 
        if(room.roomType<=3&&room.roomType>=1)
        {
            try{
                String sql = "delete from roomfeatures  where roomID="+room.roomType+";";
                db.UpdateSql(sql);
                for(int i=0;i<feature.length();i++)
                {
                    sql = "insert into roomfeatures(roomID,featureID) values("+room.roomType+","+feature.getInt(i)+");";
                    db.UpdateSql(sql);
                }
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }catch(JSONException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public JSONArray getFeatures(DatabaseConnection db) throws SQLException
    { 
        if(this.roomType<=3&&this.roomType>=1)
        {
            try{
                JSONArray a=new JSONArray();
                String sql = "";
                    sql = "SELECT featureID FROM hotel.roomfeatures where roomID="+this.roomType+";";
                    ResultSet r=db.SelectSql(sql);
                    while(r.next())
                    {
                        a.put(r.getInt("featureID"));
                    }
                    return a;
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }
        }
        return null;
    }
    public JSONObject laodRoom(int type,DatabaseConnection db)
    {
        if(type<=3&&type>=1)
        {
            JSONObject result=new JSONObject();
            try{
                String sql = "SELECT roomID, price, quantity, aprovedPrice, aprovedQuantity FROM hotel.roomtype where roomID="+type+";";
                ResultSet r=db.SelectSql(sql);
                while(r.next())
                {
                    result.put("type",type);
                    result.put("price",r.getFloat("price"));
                    result.put("approvedPrice",r.getFloat("aprovedPrice"));
                    result.put("quantity",r.getInt("quantity"));
                    result.put("approvedQuantity",r.getInt("aprovedQuantity"));
                    return result;
                }
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return null;
            }catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    public int getType()
    {
        return this.roomType;
    }
    public float getPrice()
    {
        return this.price;
    }
    public float getApprovedPrice()
    {
        return this.aprovedPrice;
    }
    public int getQantity()
    {
        return this.quantity;
    }
    public int getApprovedQantity()
    {
        return this.aprovedQuantity;
    }    
    public void searchRooms(){}
}
