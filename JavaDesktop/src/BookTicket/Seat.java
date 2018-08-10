/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookTicket;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author So Kai Con
 */
public class Seat extends JButton{
    String ID;
    ArrayList<String> list;
    JTextField txtTicket, txtTotal;
    float ticketPrice;

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public Seat() {
    }

    public Seat(String ID, JTextField txtTicket, JTextField txtTotal) {
        this.ID = ID;
        this.txtTicket = txtTicket;
        this.txtTotal = txtTotal;
        addActionListener();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    public void addActionListener()
    {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clickActionPerformed(ae);
            }
        });
    }
    
    public void clickActionPerformed(ActionEvent evt)
    {
       
        int ticket = Integer.parseInt(txtTicket.getText());
        float price = Float.parseFloat(txtTotal.getText());
        if (this.getBackground() == new JButton().getBackground())
        {
            this.setBackground(Color.RED);
            list.add(ID);
            ticket++;
            txtTicket.setText(ticket+"");
            price += ticketPrice;
            txtTotal.setText(String.valueOf(price));
        }
        else
        {
            this.setBackground(new JButton().getBackground());
            list.remove(ID);
            ticket--;
            txtTicket.setText(ticket+"");
            price -= ticketPrice;
            txtTotal.setText(String.valueOf(price));
        }   
    }
}
