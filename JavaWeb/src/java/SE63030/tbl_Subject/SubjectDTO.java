/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.tbl_Subject;

import java.io.Serializable;

/**
 *
 * @author NguyenNTSE63030
 */
public class SubjectDTO implements Serializable {

    private String subjectID;
    private String subjectName;
    private int noOfSlot;
    private String prerequisite;
    private int credits;

    public SubjectDTO() {
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNoOfSlot() {
        return noOfSlot;
    }

    public void setNoOfSlot(int noOfSlot) {
        this.noOfSlot = noOfSlot;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

}
