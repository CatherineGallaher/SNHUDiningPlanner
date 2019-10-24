package com.example.testapplication3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class ReadWriteSQL {

    private Connection con;
    private String command = "";
    ResultSet rs;
    public void createConnection()
    {
        //SQLConnect connect = new SQLConnect();
        //connect.connect();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/budgetappdemo","root","parterre-classic-compile-pole");
            //here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from users");
            while(rs.next())
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getInt(4));
            //con.close();
        }catch(Exception e){ System.out.println(e);}

    }

    public void closeConnection()
    {
        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int getUserID(String userEmail) throws SQLException
    {
        command = "select * from users where email=" + userEmail;
        return rs.getInt(4);
    }

}
