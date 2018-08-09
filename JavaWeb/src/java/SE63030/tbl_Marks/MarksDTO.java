/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.tbl_Marks;

import java.io.Serializable;

/**
 *
 * @author NguyenNTSE63030
 */
public class MarksDTO implements Serializable {

    private int id;
    private String classID;
    private String studentID;
    private String subjectID;
    private String blockSemester;
    private float subjectAvg;
    private String status;

    public MarksDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getBlockSemester() {
        return blockSemester;
    }

    public void setBlockSemester(String blockSemester) {
        this.blockSemester = blockSemester;
    }

    public float getSubjectAvg() {
        return subjectAvg;
    }

    public void setSubjectAvg(float subjectAvg) {
        this.subjectAvg = subjectAvg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
