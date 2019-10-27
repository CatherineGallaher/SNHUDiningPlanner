package com.example.budgetapprebuild;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class SQLConnect {
    public SQLConnect()
    {

    }
    public String connect()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://35.226.216.86:budgetappsqlservertry2:us-central1:budgetapp2?user=Septri");
            //DriverManager.getConnection("jdbc:mysql://IP:Instance_name?user=user_name");
			/*
			Statement statement= con.createStatement();
			ResultSet resultSet=statement.executeQuery("select * from users");
			while(resultSet.next())
			{
				System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
				con.close();
			}
			*/

            String instanceConnectionName = "budgetappsqlservertry2:us-central1:budgetapp2";
            String databaseName = "BudgetApp";

            String IP_of_instance = "35.226.216.86";
            String username = "root";
            String password = "nerdalert42!";

            //String testURL = "jdbc:mysql://35.226.216.86:budgetapp2?user=Septri";
            //jdbc:mysql://google/%s?cloudSqlInstance=<INSTANCE_CONNECTION_NAME>&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=<MYSQL_USER_NAME>&password=<MYSQL_USER_PASSWORD
            String jdbcUrl = String.format("jdbc:mysql://%s/%s?cloudSqlInstance=%s" + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
                    IP_of_instance, databaseName , instanceConnectionName);

            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            Statement stat = connection.createStatement();
            ResultSet res = stat.executeQuery("Select * from users;");
            String result = "";
            while(res.next())
            {
                result += res.getInt(1) + " " + res.getString(2) + " " + res.getString(3);
            }

            connection.close();
            return result;
        }catch(Exception e) {System.out.println(e); return "error";}
    }
}

