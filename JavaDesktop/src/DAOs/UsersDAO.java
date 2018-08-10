/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author So Kai Con
 */
public class UsersDAO {
    Connection conn;
    PreparedStatement stm;
    ResultSet rs;
    
    private void closeConnection() throws Exception
    {
        if (rs!=null)
            rs.close();
        
        if (stm!=null)
            stm.close();
        
        if (conn!=null)
            conn.close();
    }
    
    public String checkLogin(String username, String password) throws Exception
    {
        String role = "";
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "Select Name From Roles Where ID IN (Select RoleID From Users Where Username = ? AND Password = ?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                
                while (rs.next())
                {
                    role = rs.getString("Name");
                }
            }
        } finally {
            closeConnection();
        }
        return role;
    }
}
