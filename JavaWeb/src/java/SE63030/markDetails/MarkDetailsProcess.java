/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.markDetails;

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
public class MarkDetailsProcess implements Serializable {

    List<MarkDetails> listMarkDetails = null;

    public List<MarkDetails> getListMarkDetails() {
        return listMarkDetails;
    }

    public void process(String subjectID, String studentID) throws NamingException, SQLException {
        if (listMarkDetails == null) {
            listMarkDetails = new ArrayList<>();
        }

        MarksDAO marksDAO = new MarksDAO();
        marksDAO.findBySubjectIdAndStudentId(subjectID, studentID);
        List<MarksDTO> listMarks = marksDAO.getListMarkDetails();

        for (MarksDTO listMark : listMarks) {
            MarkDetails markDetails = new MarkDetails();
            String result[] = createBlock_Semester_Year(listMark.getBlockSemester());
            markDetails.setSemester(result[0]);
            markDetails.setYear(Integer.parseInt(result[1]));
            markDetails.setBlock(Integer.parseInt(result[2]));
            markDetails.setStatus(listMark.getStatus());

            listMarkDetails.add(markDetails);
        }
    }

    private String[] createBlock_Semester_Year(String blockSemester) {
        //result[0] - Semester 
        //result[1] - Year
        //result[2] - Block
        String[] result = new String[3];

        String[] afterSplit = blockSemester.split("_");
        //afterSplit[0] = summer2018
        //afterSplit[1] = block(1,2,3,...)
        String block = afterSplit[1];
        int length = afterSplit[0].length();
        String year = afterSplit[0].substring(length - 4);
        String semester = afterSplit[0].substring(0, length - 4);

        result[0] = semester;
        result[1] = year;
        result[2] = block;
        return result;
    }
}
