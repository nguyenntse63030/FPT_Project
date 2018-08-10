/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author So Kai Con
 */
public class TicketDTO {
    private int showTimeID, customerID;
    private String staffID, seatID;
    
    public TicketDTO() {
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public int getShowTimeID() {
        return showTimeID;
    }

    public void setShowTimeID(int showTimeID) {
        this.showTimeID = showTimeID;
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public TicketDTO(String staffID, int showTimeID, String seatID, int customerID) {
        this.staffID = staffID;
        this.showTimeID = showTimeID;
        this.seatID = seatID;
        this.customerID = customerID;
    }
    
}
