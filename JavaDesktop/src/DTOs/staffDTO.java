/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.Vector;

/**
 *
 * @author So Kai Con
 */
public class staffDTO {
    private String username, password, fullname, phone, sex;
    private int age, roleID;

    public staffDTO(String username, String password, String fullname, String phone, int age, String sex) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.age = age;
        this.sex = sex;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    

    public staffDTO() {
    }

    public staffDTO(String username, String password, String fullname, String phone, int age, int roleID, String sex) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.age = age;
        this.roleID = roleID;
        this.sex = sex;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public Vector toVector()
    {
        Vector v = new Vector();
        v.add(username);
        v.add(fullname);
        v.add(age);
        return v;
    }
}
