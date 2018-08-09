/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.tbl_Marks;

import SE63030.connection.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author NguyenNTSE63030
 */
public class MarksDAO implements Serializable {

    List<MarksDTO> listMarksInfo = null;

    public List<MarksDTO> getListMarks() {
        return listMarksInfo;
    }

    List<MarksDTO> listMarkDetails = null;

    public List<MarksDTO> getListMarkDetails() {
        return listMarkDetails;
    }

    public void findByStudentID(String studentID) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT studentID, subjectID, blockSemester, subjectAvg, status "
                        + "FROM tbl_marks "
                        + "WHERE studentID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, studentID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    MarksDTO marksDTO = new MarksDTO();
                    marksDTO.setStudentID(rs.getString("studentID"));
                    marksDTO.setSubjectID(rs.getString("subjectID"));
                    marksDTO.setBlockSemester(rs.getString("blockSemester"));
                    marksDTO.setSubjectAvg(rs.getFloat("subjectAvg"));
                    marksDTO.setStatus(rs.getString("status"));
                    if (listMarksInfo == null) {
                        listMarksInfo = new ArrayList<>();
                    }
                    listMarksInfo.add(marksDTO);
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
                stm.close();
            }
        }
    }

    List<MarksDTO> listFeedbackSubject = null;

    public List<MarksDTO> getListFeedbackSubject() {
        return listFeedbackSubject;
    }

    public void findBySubjectIdAndStudentId(String subjectID, String studentID) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT id, classID, subjectID, blockSemester, subjectAvg, status "
                        + "FROM tbl_marks "
                        + "WHERE subjectID = ? AND studentID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, subjectID);
                stm.setString(2, studentID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    MarksDTO dto = new MarksDTO();
                    dto.setId(rs.getInt("id"));
                    dto.setClassID(rs.getString("classID"));
                    dto.setSubjectID(rs.getString("subjectID"));
                    dto.setBlockSemester(rs.getString("blockSemester"));
                    dto.setSubjectAvg(rs.getFloat("subjectAvg"));
                    dto.setStatus(rs.getString("status"));
                    if (listMarkDetails == null) {
                        listMarkDetails = new ArrayList<>();
                    }
                    listMarkDetails.add(dto);
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
    }
}
