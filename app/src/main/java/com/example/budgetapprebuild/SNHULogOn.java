package com.example.budgetapprebuild;


import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class SNHULogOn extends AsyncTask<String, String, String> {
    SNHULogOn(){};
    public List<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
    public static SNHULogOn dataScrape = new SNHULogOn();
    public String currBalance = "its not working dumbass";
    public String email;
    public String password;
    public String dateLastAccessed;

    public String doInBackground(String... urls) {
        try {
            System.out.println("In SNHULogOn");
            //Document dc = Jsoup.connect("https://get.cbord.com/snhu/full/login.php").timeout(6000).get();
            //System.out.println(dc.title());
            Connection.Response loginFormResponse = Jsoup.connect("https://get.cbord.com/snhu/full/login.php")
                    .method(Connection.Method.GET)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                    .execute();


            FormElement loginForm = (FormElement)loginFormResponse.parse()
                    .select("login_form").first();
            Element loginField = loginForm.select("#login_username_text").first();
            loginField.val("catherine.gallaher@snhu.edu");
            loginField = loginForm.select("#login_password_text.password").first();
            loginField.val("3Mog,3Or,3Mb44");

            System.out.println("Exit SNHULogOn");

            return "True";
        } catch (Exception e) {
            System.out.println("In SNHULogOn catch");
            System.out.println(e);
            return "False";
        } finally {
            return "False";
        }
    }
    /*private HtmlPage transactionPage;
    public List<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
    public static SNHULogOn dataScrape = new SNHULogOn();
    public String currBalance = "its not working dumbass";
    public String email;
    public String password;
    public String dateLastAccessed;

    public boolean logOn(String email, String password)
    {
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setRedirectEnabled(true);
        try {
            HtmlPage page = (HtmlPage) webClient.getPage("https://get.cbord.com/snhu/full/login.php");

            HtmlForm form = page.getFormByName("login_form");

            form.getInputByName("username").setValueAttribute(email);
            HtmlInput passWordInput = form.getInputByName("password");
            //passWordInput.removeAttribute("disabled");
            //System.out.println(PasswordDecryption.decryptAES(password));
            passWordInput.setValueAttribute(password);//PasswordDecryption.decryptAES(password));


            page = form.getInputByValue("Login").dblClick();

            //System.out.println(page.asText());
            String[] thePage = page.asText().split(" ");
            if(thePage[9].compareTo("failed,") != 0)
            {
                webClient.waitForBackgroundJavaScript(10000);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");// HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                dateLastAccessed = dtf.format(now);



                HtmlPage currentPage = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
                HtmlForm selectionForm = currentPage.getHtmlElementById("child_selection_form");
                HtmlDivision div = currentPage.getHtmlElementById("my_recent_transactions");
                List<HtmlTable> listTables = currentPage.getByXPath("//table");
                if (listTables == null) {
                    System.out.println("no tables found");
                }


                for (HtmlTable table : listTables)
                {
                    if (table == listTables.listIterator(0))
                        continue;
                    for (HtmlTableRow row : table.getRows())
                    {

                        String rowText = row.asText();
                        String temp = rowText.replaceAll("\t", " ");

                        //System.out.println(temp);

                        String parsedText[] = temp.split(" ", 11);
                        if ((temp.length() < 21 || parsedText[1].compareTo("Name") == 0) && parsedText[1].compareTo("Cash") ==0)
                        {
                            currBalance = parseBalance(row.asText());
                            break;

                        }
                    }
                    break;

                }

                transactionPage = currentPage.getAnchorByHref("history.php").dblClick();

                currentPage = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();


                listTables = currentPage.getByXPath("//table");
                if (listTables == null)
                    System.out.println("no tables found");

                List<ArrayList<String>> tableExtras = new ArrayList<ArrayList<String>>();
                int infoIndex = 1;
                String[] split1;
                String[] split2;

                for(HtmlTable table : listTables)
                {
                    if(table == listTables.listIterator(0))
                        continue;

                    for(int i = 1; i < table.getRowCount(); i++)
                    {
                        tableExtras.add(new ArrayList<String>());
                        tableExtras.get(i-1).add(table.getCellAt(i, 0).asText());
                        tableExtras.get(i-1).add(table.getCellAt(i, 2).asText());
                        split1 = tableExtras.get(i-1).get(0).split(" ");
                        split2 = tableExtras.get(i-1).get(1).split(" ");
                        System.out.println("Split 1[0] " + (split1[0].compareTo("VISA") == 0) + "\tsplit 2 length " + split2.length + " " + (split2.length >= 3));
                        if(split1[0].compareTo("VISA") != 0 && split2.length < 3)
                        {
                            System.out.println(tableExtras.get(i-1).get(1));
                            //if(Double.parseDouble(parseAmount(table.getCellAt(infoIndex, 3).asText()).substring(1)) <= 00.15)
                            info.add(new ArrayList<String>());
                            info.get(infoIndex-1).add(parseDay(table.getCellAt(infoIndex, 1).asText()));
                            info.get(infoIndex-1).add(parseTime(table.getCellAt(infoIndex, 1).asText()));
                            info.get(infoIndex-1).add(parseAmount(table.getCellAt(infoIndex, 3).asText()));
                            System.out.println(info.get(infoIndex-1).get(2));
                            infoIndex++;
                        }
                    }
                }



                for(int k = 0; k < info.size(); k++)
                {
                    for(int j = 0; j < info.get(k).size(); j++)
                    {
                        System.out.print(info.get(k).get(j) + " ");
                    }
                    System.out.println();
                }

                //System.out.println(currBalance);

                System.out.println("Finished parsing data from scrape!\n-----------------------------------------------\n\n");

                form.remove();
                selectionForm.remove();
                page.cleanUp();
                page.remove();
                currentPage.cleanUp();
                currentPage.remove();
                transactionPage.cleanUp();
                transactionPage.remove();
                webClient.close();

                //SQLConnect.setSNHULogOnObj(this);
                return true;
            }

            System.out.println("Exiting log on");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webClient.close();
        }
        return false;
    }*/

    private String parseBalance(String bal)
    {
        return bal.substring(bal.length() - 7);
    }

    private String parseDay(String date)
    {
        date = date.replaceAll("\t", " ");
        String parsedDate[] = date.split(" ", 11);

        //System.out.println(parsedDate.length + " " + parsedDate[3]);
        //String completeDate = parsedDate[3] + " " + parsedDate[4] + " " + parsedDate[5];
        //String parsedDate2[] = parsedDate[0].split(",", 1);

        String completeDate = parsedDate[0] + " " + parsedDate[1] + " " + parsedDate[2].substring(0, 5);

        //return Integer.parseInt(parsedDate2[0]);
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
        return parsedAmount[1];//.substring(1);//9];//parsedAmount.length];//10 on main page

    }

    /*
    public List<ArrayList<String>> getInfo()
    {
        return info;
    }

    public String getBalance()
    {
        return currBalance;
    }*/
}

