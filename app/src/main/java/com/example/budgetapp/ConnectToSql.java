package com.example.budgetapp;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectToSql extends AsyncTask<String, Void, String> {

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    public String doInBackground(String... params) {
        String useless = params[0];
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String instanceConnectionName = "budgetappsqlservertry2:us-central1:budgetapp2";
            String databaseName = "BudgetApp";

            String IP_of_instance = "127.0.0.1:3307";
            String username = "Septri";
            String password = "septri258";

            //String jdbcUrl = String.format("jdbc:mysql://%s/%s?cloudSqlInstance=%s" + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&autoReconnect=true&&maxReconnects=5&useSSL=FALSE",
            //        IP_of_instance, databaseName, instanceConnectionName);

            String jdbcUrl = "jdbc:mysql://127.0.0.1:3307/BudgetApp?cloudSqlInstance=budgetappsqlservertry2:us-central1:budgetapp2&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false";

            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            Statement stat = connection.createStatement();
            ResultSet res = stat.executeQuery("Select * from users;");
            String result = "";
            while (res.next()) {
               result += (res.getInt(1) + " " + res.getString(2) + " " + res.getString(3));
            }

            connection.close();
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
    }

    protected void  onPostExecute(String result)
    {
        super.onPostExecute(result);
    }
}
