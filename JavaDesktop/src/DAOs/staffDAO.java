/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Connection.MyConnection;
import DTOs.staffDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author So Kai Con
 */
public class staffDAO {
    Connection conn = null;
    PreparedStatement prstm = null;
    ResultSet rs = null;

    public staffDAO() {
    }
    
    private void closeConnection() throws SQLException
    {
        if (rs!=null)
            rs.close();
        
        if (prstm!=null)
            prstm.close();
        
        if (conn!=null)
            conn.close();
    }
    
    public List<staffDTO> findByStaffName (String search) throws Exception
    {
        List<staffDTO> result = new ArrayList();
        
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT Username, Fullname, Age FROM Users WHERE RoleID = 1 AND Fullname LIKE ?";
            prstm = conn.prepareStatement(sql);
            prstm.setString(1, "%" + search + "%");
            rs = prstm.executeQuery();
            
            staffDTO dto = null;
            
            while (rs.next())
            {
                dto = new staffDTO();
                dto.setUsername(rs.getString("Username"));
                dto.setFullname(rs.getString("Fullname"));
                dto.setAge(rs.getInt("Age"));
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public staffDTO findByPrimaryKey (String username) throws Exception
    {
        staffDTO dto = null;
        
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT Fullname, Phone, Age, Sex FROM Users WHERE Username = ?";
            prstm = conn.prepareStatement(sql);
            prstm.setString(1, username);
            rs = prstm.executeQuery();
            
            if (rs.next())
            {
                dto = new staffDTO();
                dto.setUsername(username);
                dto.setFullname(rs.getString("Fullname"));
                dto.setPhone(rs.getString("phone"));
                dto.setAge(rs.getInt("age"));
                dto.setSex(rs.getString("sex"));
                
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean insert (staffDTO dto) throws Exception
    {
        boolean check = false;
        
        
        try {
            conn = MyConnection.getConnection();
            String sql = "INSERT INTO Users (Username,Password,Fullname,RoleID,Phone,Age,Sex) VALUES(?,?,?,?,?,?,?)";
            prstm = conn.prepareStatement(sql);
            prstm.setString(1, dto.getUsername());
            prstm.setString(2, dto.getPassword());
            prstm.setString(3, dto.getFullname());
            prstm.setInt(4, dto.getRoleID());
            prstm.setString(5, dto.getPhone());    
            prstm.setInt(6, dto.getAge());
            prstm.setString(7, (dto.getSex()));
            
            check = prstm.executeUpdate() > 0;
            
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean update (staffDTO dto) throws Exception
    {
        boolean check = false;
        
        try {
            conn = MyConnection.getConnection();
            String sql = "UPDATE Users SET Password = ?, Fullname = ?, phone = ?, age = ?, sex = ? WHERE Username = ?";
            prstm = conn.prepareStatement(sql);
            prstm.setString(1, dto.getPassword());
            prstm.setString(2, dto.getFullname());
            prstm.setString(3, dto.getPhone());
            prstm.setInt(4, dto.getAge());
            prstm.setString(5, dto.getSex());
            prstm.setString(6, dto.getUsername());
            check = prstm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        
        return check;
    }
    
    public boolean delete (String username) throws Exception
    {
        boolean check = false;
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Delete FROM Users Where Username = ?";
            prstm = conn.prepareStatement(sql);
            prstm.setString(1, username);
            check = prstm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        
        return check;
    }
}
