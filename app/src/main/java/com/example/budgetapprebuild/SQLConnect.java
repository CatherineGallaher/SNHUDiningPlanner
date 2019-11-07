package com.example.budgetapprebuild;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class SQLConnect extends AsyncTask<String, String, String> {
    public SQLConnect(){}

    HttpURLConnection conn;
    URL url = null;

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;


    /*
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
    */

    protected String doInBackground(String... params) {
        try {
            // Enter URL address where your php file resides
            url = new URL("http://10.0.82.131/db-api/API.php?apicall=getall");

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.toString();
        }
        try {

            // Setup HttpURLConnection class to send and receive data from php
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("GET");

            // setDoOutput to true as we recieve data from json file
            conn.setDoOutput(true);

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return e1.toString();
        }

        try {

            int response_code = conn.getResponseCode();

            // Check if successful connection made
            if (response_code == HttpURLConnection.HTTP_OK) {

                // Read data sent from server
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Pass data to onPostExecute method
                return (result.toString());

            } else {

                return ("unsuccessful");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            conn.disconnect();
        }
    }

    /*public void setInfo()
    {
        boolean isLoggedIn = SNHULogOn.dataScrape.logOn("catherine.gallaher@snhu.edu", PasswordEncryption.encryptionAES("x")); //Should really be called from LoginActivity or something
        if(isLoggedIn)
        {
            this.balance = SNHULogOn.dataScrape.currBalance; //Current balance
            this.info = SNHULogOn.dataScrape.getInfo(); //List<ArrayList<String>> of transactions
            this.theDate = SNHULogOn.dataScrape.dateLastAccessed; //Last date accessed
            System.out.println(theDate);
            System.out.println(balance);

            for(int k = 0; k < info.size(); k++)
            {
                for(int j = 0; j < info.get(k).size(); j++)
                {
                    System.out.print(info.get(k).get(j) + " ");
                }
                System.out.println();
            }
        }
    }*/

}

