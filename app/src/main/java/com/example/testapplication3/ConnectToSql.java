package com.example.testapplication3;

public class ConnectToSql{

}

/*
import android.os.AsyncTask;

import java.sql.*;

import java.util.Properties;

public class ConnectToSql extends AsyncTask<String, Void, String> {

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    public String doInBackground(String... params) {
        String useless = params[0];
        Connection conn3 = null;
        Statement stat = null;
        ResultSet res = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            String instanceConnectionName = "budgetappsqlservertry2:us-central1:budgetapp2";
            String databaseName = "BudgetApp";

            String IP_of_instance = "127.0.0.1:3307";
            String username = "root";
            String password = "nerdalert42!";

            String jdbcUrl = String.format("jdbc:mysql://%s/%s?cloudSqlInstance=%s" + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&autoReconnect=true&&maxReconnects=3&useSSL=FALSE&user=%s&password=%s",
                    IP_of_instance, databaseName, instanceConnectionName, username, password);

            //String jdbcUrl = "jdbc:mysql://127.0.0.1:3307/";

            Properties info = new Properties();
            info.put("proxy_host", "127.0.0.1");
            info.put("proxy_port", "3307");
            info.put("user", "root");
            info.put("password", "nerdalert42!");

            conn3 = DriverManager.getConnection(jdbcUrl, info);
            //Connection connection = DriverManager.getConnection(jdbcUrl);

            System.out.println("Test");
            stat = conn3.createStatement();

            res = stat.executeQuery("Select * from users;");
            String result = "";
            while (res.next()) {
               result += (res.getInt(1) + " " + res.getString(2) + " " + res.getString(3));
            }

            return result;
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
        finally {
            System.out.println("In finally");
            if (conn3 != null) {
                try {
                    conn3.close();
                    System.out.println("Closing Connection");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (stat != null)
            {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (res != null)
            {
                try {
                    res.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    protected void  onPostExecute(String result)
    {
        super.onPostExecute(result);
    }
} */
