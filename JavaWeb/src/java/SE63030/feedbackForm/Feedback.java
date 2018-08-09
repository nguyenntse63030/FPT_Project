/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.feedbackForm;

import SE63030.tbl_Marks.MarksDAO;
import SE63030.tbl_Marks.MarksDTO;
import SE63030.tbl_Subject.SubjectDAO;
import SE63030.tbl_Subject.SubjectDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author NguyenNTSE63030
 */
public class Feedback implements Serializable {

    Map<Integer, FeedBackSubject> feedbackForm;

    public Feedback() {
    }

    public Map<Integer, FeedBackSubject> getFeedbackForm() {
        return feedbackForm;
    }

    public void addSubjectToFeedbackForm(String subjectID, String studentID) {
        if (feedbackForm == null) {
            feedbackForm = new HashMap<>();
        }

        try {
            MarksDAO marksDAO = new MarksDAO();
            marksDAO.findBySubjectIdAndStudentId(subjectID, studentID);
            List<MarksDTO> listSubject = marksDAO.getListMarkDetails();
            for (MarksDTO subject : listSubject) {
                if (feedbackForm.containsKey(subject.getId())) {

                }

                FeedBackSubject feedBackSubject = new FeedBackSubject();
                feedBackSubject.setCode(subject.getSubjectID());
                SubjectDAO subjectDAO = new SubjectDAO();
                SubjectDTO subjectDTO = subjectDAO.findByPrimaryKey(subject.getSubjectID());
                feedBackSubject.setName(subjectDTO.getSubjectName());
                feedBackSubject.setAvg(subject.getSubjectAvg());
                feedBackSubject.setStatus(subject.getStatus());

                feedbackForm.put(subject.getId(), feedBackSubject);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeSubjectFromFeedback(int id) {
        if (feedbackForm == null) {
            return;
        }
        
        if (feedbackForm.containsKey(id)) {
            feedbackForm.remove(id);
            if (feedbackForm.isEmpty()) {
                feedbackForm = null;
            }
        }
    }
}
