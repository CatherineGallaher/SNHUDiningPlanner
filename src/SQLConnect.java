
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

public class SQLConnect {
    public SQLConnect(){}

    public static final String IPADDRESS = "localhost";

    HttpURLConnection conn;
    URL url = null;

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    protected String doInBackground(String... params) {

        String operation = "";
        String[] colVals = new String[6];

        try {
            switch(params[0]) {
                case "getall":
                    url = new URL("http://" + IPADDRESS + "/db-api/API.php?apicall=" + params[0]);
                    System.out.println("in getall");
                    break;
                case "inputVal":
                    url = new URL("http://" + IPADDRESS + "/db-api/API.php?apicall=" + params[0] + "&tableName=" + params[1] + "&colOne=" + params[2] + "&colTwo=" + params[3] + "&colThree" + params[4] + "&colFour" + params[5] + "&colFive=" + params[6]);
                    break;
                case "getTableInfo":
                    url = new URL("http://" + IPADDRESS + "/db-api/API.php?apicall=" + params[0] + "&tableName=" + params[1]);
                    break;
                case "createTITable":
                    url = new URL("http://" + IPADDRESS + "/db-api/API.php?apicall=" + params[0] + "&tableName=" + params[1] + "&pass=" + params[2]);
                    break;
                case "dropTITable":
                    if (params[2] != "nerdalert42!")
                        System.out.println("Wrong password provided!");
                    url = new URL("http://" + IPADDRESS + "/db-api/API.php?apicall=" + params[0] + "&tableName=" + params[1] + "&pass=" + params[2]);
                    break;
            }
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

                //parses the json

                String resultString = result.toString();

                //resultString = result.substring(1, result.length() - 1 ).replace("{", "");

                //resultString = result.substring(1, result.length() - 1 ).replace("}", "");

                return (resultString);

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

}

