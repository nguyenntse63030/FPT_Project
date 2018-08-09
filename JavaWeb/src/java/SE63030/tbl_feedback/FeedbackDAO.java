/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.tbl_feedback;

import SE63030.connection.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.naming.NamingException;

/**
 *
 * @author NguyenNTSE63030
 */
public class FeedbackDAO implements Serializable {

    public boolean insertFeedback(FeedbackDTO dto) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tbl_feedback(fbDate,contents,studentID,status) "
                        + "VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, sdf.format(dto.getFbDate()));
                stm.setString(2, dto.getContents());
                stm.setString(3, dto.getStudentID());
                stm.setBoolean(4, dto.isStatus());
                result = stm.executeUpdate() > 0;
            }
        } finally {
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
