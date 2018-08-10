/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author So Kai Con
 */
public class MyConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENNTSE63030\\NGUYENNTSE63030:1998;databaseName=Presentation;sendTimeAsDateTime=false","sa","");
        return conn;
    }
}

