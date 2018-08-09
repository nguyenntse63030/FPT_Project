/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.tbl_Student;

import SE63030.connection.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author NguyenNTSE63030
 */
public class StudentDAO implements Serializable {

    public StudentDTO findByPrimaryKey(String studentID) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        StudentDTO result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT firstName, lastName, middleName "
                        + "FROM tbl_student "
                        + "WHERE studentID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, studentID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = new StudentDTO();
                    result.setFirstName(rs.getString("firstName"));
                    result.setLastName(rs.getString("lastName"));
                    result.setMiddleName(rs.getString("middleName"));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

}
