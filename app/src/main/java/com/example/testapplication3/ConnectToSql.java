package com.example.testapplication3;

import android.os.AsyncTask;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.*;
import java.util.Collection;

public class ConnectToSql extends AsyncTask<String, Void, String> {

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    public String doInBackground(String... params) {
        String useless = params[0];
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();



            String instanceConnectionName = "budgetappsqlservertry2:us-central1:budgetapp2";
            String databaseName = "BudgetApp";

            String IP_of_instance = "35.226.216.86:3306";
            String username = "Septri";
            String password = "septri258";

            String jdbcUrl = String.format("jdbc:mysql://%s/%s?cloudSqlInstance=%s" + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
                    IP_of_instance, databaseName, instanceConnectionName);

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
