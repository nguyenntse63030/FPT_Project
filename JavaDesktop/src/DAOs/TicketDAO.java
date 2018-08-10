/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Connection.MyConnection;
import DTOs.TicketDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author So Kai Con
 */
public class TicketDAO {
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
    
    public boolean purchase(TicketDTO dto) throws Exception
    {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "INSERT INTO Ticket (StaffID,ShowTimeID,SeatID,CustomerID) VALUES(?,?,?,'3')";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getStaffID());
                stm.setInt(2, dto.getShowTimeID());
                stm.setString(3, dto.getSeatID());
                
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public List<TicketDTO> findByPrimaryKey (int showTimeId) throws Exception
    {
        List<TicketDTO> list = null;
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "Select SeatID From Ticket Where ShowTimeID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, showTimeId);
                rs = stm.executeQuery();
                TicketDTO dto = null;
                list = new ArrayList<>();
                String seatID = null;
                
                while (rs.next())
                {
                    dto = new TicketDTO();
                    seatID = rs.getString("SeatID");
                    dto.setSeatID(seatID);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
