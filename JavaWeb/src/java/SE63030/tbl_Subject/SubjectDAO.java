/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.tbl_Subject;

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
public class SubjectDAO implements Serializable {

    public SubjectDTO findByPrimaryKey(String subjectID) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        SubjectDTO result = null;

        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT subjectID, subjectName, NoOfSlot, credits "
                        + "FROM tbl_subject "
                        + "WHERE subjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, subjectID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = new SubjectDTO();
                    result.setSubjectID(subjectID);
                    result.setSubjectName(rs.getString("subjectName"));
                    result.setNoOfSlot(rs.getInt("NoOfSlot"));
                    result.setCredits(rs.getInt("credits"));
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
