/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.markDetails;

import java.io.Serializable;

/**
 *
 * @author NguyenNTSE63030
 */
public class MarkDetails implements Serializable {

    private String subjectID;
    private String subjectName;
    private int block;
    private String semester;
    private int year;
    private String status;
    private int numberOfStudying;

    public MarkDetails() {
    }

    public MarkDetails(String subjectID, String subjectName, int block, String semester, int year, String status, int numberOfStudying) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.block = block;
        this.semester = semester;
        this.year = year;
        this.status = status;
        this.numberOfStudying = numberOfStudying;
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

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberOfStudying() {
        return numberOfStudying;
    }

    public void setNumberOfStudying(int numberOfStudying) {
        this.numberOfStudying = numberOfStudying;
    }

}
