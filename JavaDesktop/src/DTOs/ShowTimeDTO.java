/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author So Kai Con
 */
public class ShowTimeDTO implements Serializable {

    private int movieGenreID, roomID;
    private Time startTime, endTime;
    private Date dateShow;
    private float price;

    public ShowTimeDTO() {
    }

    public ShowTimeDTO(int movieGenreID, int roomID, Time startTime, Time endTime, Date dateShow, float price) {
        this.movieGenreID = movieGenreID;
        this.roomID = roomID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateShow = dateShow;
        this.price = price;
    }

    public int getMovieGenreID() {
        return movieGenreID;
    }

    public void setMovieGenreID(int movieGenreID) {
        this.movieGenreID = movieGenreID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getDateShow() {
        return dateShow;
    }

    public void setDateShow(Date dateShow) {
        this.dateShow = dateShow;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String printDate() {
        String dateFormat;
        dateFormat = dateShow.getDate()+ "-" + (dateShow.getMonth() + 1)+ "-" + (dateShow.getYear()+1900);
        return  dateFormat;
    }
    
    public String printTime()
    {
        String timeFormat;
        timeFormat = startTime.getHours()+"h"+ startTime.getMinutes()+"'" + " - " + endTime.getHours()+"h"+endTime.getMinutes()+"'";
        return timeFormat;
    }
}
