/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Connection.MyConnection;
import DTOs.GenreDTO;
import DTOs.MovieDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author So Kai Con
 */
public class MovieDAO {
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public MovieDAO() {
    }
    
    private void closeConnection() throws SQLException 
    {
        if (rs!=null)
            rs.close();
        
        if (stm!=null)
            stm.close();
        
        if (conn!=null)
            conn.close();
    }
    
    public List<MovieDTO> findByMovieName (String search) throws Exception
    {
        List<MovieDTO> result = new ArrayList();
        
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT ID, Name, MoviesLength, GenreID FROM Movies WHERE Name LIKE ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            rs = stm.executeQuery();
            
            MovieDTO dto = null;
            
            while (rs.next())
            {
                dto = new MovieDTO();
                dto.setId(rs.getInt("ID"));
                dto.setName(rs.getString("Name"));
                dto.setMovieLength(rs.getInt("MoviesLength"));
                dto.setGenreID(rs.getInt("GenreID"));
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean update(MovieDTO dto) throws Exception
    {
        boolean check = false;
        
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "UPDATE Movies SET Name = ?, MoviesLength = ?, GenreID = ? WHERE Id = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setInt(2, dto.getMovieLength());
                stm.setInt(3, dto.getGenreID());
                stm.setInt(4, dto.getId());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        
        return check;
    }
    
    public MovieDTO findByPrimaryKey (int id) throws Exception
    {
        MovieDTO dto = null;
        
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "Select Name, MoviesLength, GenreID from Movies Where ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                
                if (rs.next())
                {
                    dto = new MovieDTO();
                    dto.setId(id);
                    dto.setName(rs.getString("Name"));
                    dto.setMovieLength(rs.getInt("MoviesLength"));
                    dto.setGenreID(rs.getInt("GenreID"));
                }
            }
        } finally {
            closeConnection();
        }
        
        return dto;
    }
    
    public boolean delete (int id) throws Exception
    {
        boolean check = false;
        
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "DELETE FROM Movies WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean insert (MovieDTO dto) throws Exception
    {
        boolean check = false;
        
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "INSERT INTO Movies (Name,MoviesLength,GenreID) VALUES(?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setInt(2, dto.getMovieLength());
                stm.setInt(3, dto.getGenreID());
                
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        
        return check;
    }
    
    public List<MovieDTO> getMovieNameByMovieIdInShowTimeTable(Date dateShow) throws Exception, NullPointerException
    {
        List<MovieDTO> result = null;
        
        try {
            conn = MyConnection.getConnection();
            if (conn!=null)
            {
                String sql = "Select ID, Name From Movies Where ID In (Select MovieID From ShowTimes Where DateShow = ?)";
                stm = conn.prepareStatement(sql);
                stm.setDate(1, dateShow);
                rs = stm.executeQuery();
                result = new ArrayList<>();
                MovieDTO dto = null;
                
                while (rs.next())
                {
                    dto = new MovieDTO();
                    dto.setName(rs.getString("Name"));
                    dto.setId(rs.getInt("ID"));
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
}
