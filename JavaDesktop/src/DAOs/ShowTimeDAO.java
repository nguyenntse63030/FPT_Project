/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Connection.MyConnection;
import DTOs.ShowTimeDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author So Kai Con
 */
public class ShowTimeDAO {
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    private void closeConnection() throws Exception
    {
        if (rs!=null)
            rs.close();
        
        if (stm!=null)
            stm.close();
        
        if (conn!=null)
            conn.close();
    }
    
    public List<ShowTimeDTO> getAllDateShow() throws Exception
    {
        List<ShowTimeDTO> result = null;
        
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "Select Distinct DateShow FROM ShowTimes";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                result = new ArrayList<>();
                ShowTimeDTO dto = null;
                
                while (rs.next())
                {
                    dto = new ShowTimeDTO();
                    dto.setDateShow(rs.getDate("DateShow"));
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public List<ShowTimeDTO> getTime (Date date, int movieID) throws Exception, NullPointerException
    {
        List<ShowTimeDTO> result = null;
        
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "Select StartTime, EndTime FROM ShowTimes WHERE DateShow = ? AND MovieID = ?";
                stm = conn.prepareStatement(sql);
                stm.setDate(1, date);
                stm.setInt(2, movieID);
                rs = stm.executeQuery();
                
                result = new ArrayList<>();
                ShowTimeDTO dto = null;
                
                while (rs.next())
                {
                    dto = new ShowTimeDTO();
                    dto.setStartTime(rs.getTime("StartTIme"));
                    dto.setEndTime(rs.getTime("EndTime"));
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public List<ShowTimeDTO> getRoom(int movieID, Date dateShow, Time startTime, Time endTime) throws Exception
    {
        List<ShowTimeDTO> result = null;
        
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "Select RoomID From ShowTimes WHERE MovieID = ? AND DateShow = ? AND StartTime = ? AND EndTime = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, movieID);
                stm.setString(2, dateShow.toString());
                stm.setString(3, startTime.toString());
                stm.setString(4, endTime.toString());
                rs = stm.executeQuery();
                
                result = new ArrayList<>();
                ShowTimeDTO dto = null;
                
                while (rs.next())
                {
                    dto = new ShowTimeDTO();
                    dto.setRoomID(rs.getInt("RoomID"));
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public int getPrice(Date date, int movieId, Time startTime, Time endTime, int roomId) throws Exception
    {
        int result = 0;
        
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "Select Price From ShowTimes Where MovieID = ? AND RoomID = ? AND DateShow = ? AND StartTime = ? AND EndTime = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, movieId);
                stm.setInt(2, roomId);
                stm.setDate(3, date);
                stm.setString(4, startTime.toString());
                stm.setString(5, endTime.toString());
                rs = stm.executeQuery();
                
                while (rs.next())
                {
                   result = rs.getInt("Price");
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public int getShowTimeID(Date date, int movieId, Time startTime, Time endTime, int roomId, float price) throws Exception
    {
        int result = -1;
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "Select ID From ShowTimes Where MovieID = ? AND RoomID = ? AND DateShow = ? AND StartTime = ? AND EndTime = ? And Price = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, movieId);
                stm.setInt(2, roomId);
                stm.setDate(3, date);
                stm.setString(4, startTime.toString());
                stm.setString(5, endTime.toString());
                stm.setFloat(6, price);
                rs = stm.executeQuery();
                
                if (rs.next())
                {
                    result = rs.getInt("ID");
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
