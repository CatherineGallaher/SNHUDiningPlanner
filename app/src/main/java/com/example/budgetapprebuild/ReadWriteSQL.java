package com.example.budgetapprebuild;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ReadWriteSQL {

    private Connection con;
    private Statement stmt;
    private List<String> dates = new ArrayList<String>();
    private List<String> times = new ArrayList<String> ();
    private List<String> amounts = new ArrayList<String>();

    ResultSet rs;

    //UNUSED CLASS, ORIGINALLY MEANT TO CONTACT THE MYSQL DATABASE.  OBSOLETE BUT MAINTAINED JUST IN CASE
    public void createConnection()
    {
        //SQLConnect connect = new SQLConnect();
        //connect.connect();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String databaseName = "budgetappdemo";
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + databaseName,"root","parterre-classic-compile-pole");
            stmt=con.createStatement();

            //MUST HAVE: database with tables users (email varchar(30), password varchar(100), lastDateAccessed varchar(30), amountLeft varchar(20), id int(100), PRIMARY KEY(id))
            // and transactionInfo (date varchar(30), time varchar(10), amountSpent varchar(30), transactionID int(100), userId int(100), primary key(transactionid), foreign key(userId) references users(id) )
            stmt.execute("SET foreign_key_checks = 0");
            stmt.execute("delete from users");
            stmt.execute("delete from transactioninfo");
            stmt.execute("SET foreign_key_checks = 1");


            stmt.execute("INSERT INTO users VALUES('john.canducci@snhu.edu','ri6d7o8fyiguh:{JH','October 3', '$999.99',1)");
            stmt.execute("INSERT INTO users VALUES('catherine.gallaher@snhu.edu',':{$#Uhjkfafdsfess','October 23', '$1200.45', 2)");
            stmt.execute("INSERT INTO users VALUES('dylan.bryant@snhu.edu',';kgj%*$GF:KHDAS','October 16', '$34.80', 3)");

            stmt.execute("INSERT INTO transactionInfo VALUES('October 23, 2019', '5:55PM', '$15.00', 1, 2)");
            stmt.execute("INSERT INTO transactionInfo VALUES('October 23, 2019', '1:50PM', '$3.29', 2, 2)");
            stmt.execute("INSERT INTO transactionInfo VALUES('October 23, 2019', '11:37AM', '$6.96', 3, 2)");
            stmt.execute("INSERT INTO transactionInfo VALUES('October 22, 2019', '11:01AM', '$14.08', 4, 2)");
            stmt.execute("INSERT INTO transactionInfo VALUES('October 21, 2019', '7:49PM', '$5.76', 5, 2)");
            stmt.execute("INSERT INTO transactionInfo VALUES('October 21, 2019', '12:24PM', '$9.49', 6, 2)");
            stmt.execute("INSERT INTO transactionInfo VALUES('October 20, 2019', '4:42PM', '$4.90', 7, 2)");


            rs=stmt.executeQuery("select * from users");
            System.out.println("\n-----------------------------------------------\nCurrent Users (passwords are encrypted): ");
            while(rs.next())  {
                System.out.println("*\t"+ rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getInt(5) + "\n");  }

            rs=stmt.executeQuery("select * from transactioninfo");
            System.out.println("\n-----------------------------------------------\nUser 2's recent transactions: \n");
            while(rs.next())  {
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4) + "\t"+rs.getInt(5));
                dates.add(rs.getString(1));
                times.add(rs.getString(2));
                amounts.add(rs.getString(3));
            }


        }catch(Exception e){ System.out.println(e);}

    }

    public void addInfo(String[] info)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String databaseName = "budgetappdemo";
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + databaseName,"root","parterre-classic-compile-pole");
            stmt=con.createStatement();

            String sqlStat = "INSERT INTO transactionInfo VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sqlStat);

            //MUST HAVE: database with tables users (email varchar(30), password varchar(100), lastDateAccessed varchar(30), amountLeft varchar(20), id int(100), PRIMARY KEY(id))
            // and transactionInfo (date varchar(30), time varchar(10), amountSpent varchar(30), transactionID int(100), userId int(100), primary key(transactionid), foreign key(userId) references users(id) )
            stmt.execute("SET foreign_key_checks = 0");
            stmt.execute("delete from users");
            stmt.execute("delete from transactioninfo");
            stmt.execute("SET foreign_key_checks = 1");

            for (int i = 1; i < info.length - 1; i++)
            {
                ps.setString(0, info[i-1]);
                ps.setString(1, info[i]);
                ps.setString(2, info[i+1]);
                ps.setInt(3, i+1);
                ps.setInt(4, 2);
                ps.executeUpdate();
            }

            rs=stmt.executeQuery("select * from users");
            while(rs.next())  {
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getInt(5) + "\n");  }

            rs=stmt.executeQuery("select * from transactioninfo");
            while(rs.next())  {
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4) + "\t"+rs.getInt(5));
                dates.add(rs.getString(1));
                times.add(rs.getString(2));
                amounts.add(rs.getString(3));
            }


        }catch(Exception e){ System.out.println(e);}
    }

    public void closeConnection()
    {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUserID(String userEmail) throws SQLException
    {
        rs=stmt.executeQuery("select * from users where email=" + userEmail);
        return rs.getInt(4);
    }

    public List<String> getDates()
    {
        return dates;
    }

    public List<String> getTimes()
    {
        return times;
    }

    public List<String> getAmounts()
    {
        return amounts;
    }

    public String getAmountLeft(String userEmail) throws SQLException
    {
        rs=stmt.executeQuery("select * from users where email=" + userEmail);
        return rs.getString(3);
    }
}
