package com.example.budgetapprebuild;


import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;


import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class SNHULogOn extends AsyncTask<String, String, String> {
    public SNHULogOn(){};
    public List<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
    public static SNHULogOn dataScrape = new SNHULogOn();
    //public String currBalance = "its not working";
    public double currBalance = 378.64;
    public String email;
    public String password;
    public String dateLastAccessed;
    public boolean successfulLogOn = false;
    public boolean waiting = true;

    public String doInBackground(String... urls) {
        try {
            Connection.Response loginFormResponse = Jsoup.connect("https://get.cbord.com/snhu/full/login.php").userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").method(Connection.Method.GET).execute(); //Connects to login page

            FormElement loginForm = (FormElement)loginFormResponse.parse().select("#login-form").first();

            //sets username and password
            Element loginField = loginForm.select("#login_username_text").first();
            loginField.val(email);
            Element passwordField = loginForm.select("#login_password_text").first();
            passwordField.val(password);


            Connection.Response loginActionResponse = loginForm.submit().cookies(loginFormResponse.cookies()).timeout(6000).execute(); //logs in

            //changes pages to overview page, using cookies from login to stay logged in
            Connection.Response overviewPage = Jsoup.connect("https://get.cbord.com/snhu/full/funds_home.php").userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").cookies(loginFormResponse.cookies()).method(Connection.Method.GET).timeout(6000).execute();
            Document doc = overviewPage.parse();

            System.out.println("Changed pages");

            //FormElement transactionForm = (FormElement)loginActionResponse.parse().select("#child-selection form")
            Document balancePage = loginActionResponse.parse();
            //System.out.println(balancePage.text());
            String[] thePage = balancePage.text().split(" ");
            if(thePage[10].equals("failed,") == false) //if successful log in
            {
                successfulLogOn = true;
                // TODO: figure out how to scrape balance using Jsoup
                //TODO: figure out how to scrape transaction history using Jsoup
            }
            waiting = false;

            return "False";
        } catch (Exception e) {
            System.out.println(e);
            return "False";
        } finally {
            return "False";
        }
    }
    //Used while scraping transaction history.  DO NOT DELETE.
    private String parseBalance(String bal)
    {
        return bal.substring(bal.length() - 7);
    }

    private String parseDay(String date)
    {
        date = date.replaceAll("\t", " ");
        String parsedDate[] = date.split(" ", 11);

        String completeDate = parsedDate[0] + " " + parsedDate[1] + " " + parsedDate[2].substring(0, 5);

        return completeDate.substring(0, completeDate.length()-1);
    }
    private String parseTime(String time)
    {
        String temp = time.replaceAll("\t", " ");
        String parsedTime[] = temp.split(" ", 11);
        return parsedTime[3];//7 on main page
    }

    private String parseAmount(String amount)
    {
        String temp = amount.replaceAll("\t", " ");
        String parsedAmount[] = temp.split(" ", 20);
        return parsedAmount[1];

    }

}

