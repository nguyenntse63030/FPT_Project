/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Connection.MyConnection;
import DTOs.GenreDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author So Kai Con
 */
public class GenreDAO {
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    public GenreDAO() {
    }
    
    private void closeConnection() throws SQLException
    {
        if (rs != null)
            rs.close();
        
        if (stm != null)
            stm.close();
        
        if (conn != null)
            conn.close();
    }
    
    
    public List<GenreDTO> getAllGenre() throws Exception
    {
        List<GenreDTO> result = new ArrayList();
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Select ID, Name FROM Genres";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            GenreDTO dto = null;
            
            while (rs.next())
            {
                dto = new GenreDTO();
                dto.setId(rs.getInt("ID"));
                dto.setName(rs.getString("Name"));
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
}
