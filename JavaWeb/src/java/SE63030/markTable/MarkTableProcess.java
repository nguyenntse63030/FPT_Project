/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.markTable;

import SE63030.tbl_Marks.MarksDAO;
import SE63030.tbl_Marks.MarksDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author NguyenNTSE63030
 */
public class MarkTableProcess implements Serializable {

    List<MarkTable> listMarkTable = null;

    public List<MarkTable> getListMarkTable() {
        return listMarkTable;
    }

    public void createMarkTableInfo(String studentID) throws NamingException, SQLException {
        if (listMarkTable == null) {
            listMarkTable = new ArrayList<>();
        }

        MarksDAO marksDAO = new MarksDAO();
        marksDAO.findByStudentID(studentID);

        List<MarksDTO> listMarks = marksDAO.getListMarks(); //Lấy List Mark theo tên sinh viên

        if (listMarks != null) {
            int passCredit = 0;
            float gpa = 0;
            int numberOfPassed = 0; //tổng số môn đã pass
            for (MarksDTO listMark : listMarks) {
                MarkTable markTableDTO = new MarkTable();

                //Lấy blockSemester trong listMark ra và cắt chuỗi thành semester - year - block
                String[] afterSplit = listMark.getBlockSemester().split("_");
                markTableDTO.setBlock(Integer.parseInt(afterSplit[1]));

                int length = afterSplit[0].length();
                String year = afterSplit[0].substring(length - 4);
                String semester = afterSplit[0].substring(0, length - 4);

                markTableDTO.setSemester(semester);
                markTableDTO.setYear(Integer.parseInt(year));
                //Lấy blockSemester trong listMark ra và cắt chuỗi thành semester - year - block

                markTableDTO.setAvg(listMark.getSubjectAvg());
                markTableDTO.setStatus(listMark.getStatus());

                markTableDTO.setSubjectID(listMark.getSubjectID());
                listMarkTable.add(markTableDTO);
            }
            validateListMarkTable();
        }
    }

    private void validateListMarkTable() {
        for (int i = 0; i < listMarkTable.size() - 1; i++) {
            for (int j = i + 1; j < listMarkTable.size(); j++) {
                if (listMarkTable.get(i).getSubjectID().equals(listMarkTable.get(j).getSubjectID())) {
                    if (listMarkTable.get(j).getBlock() < listMarkTable.get(i).getBlock()) {
                        listMarkTable.remove(j);
                    }
                    listMarkTable.remove(i);
                }
            }
        }
    }
}
