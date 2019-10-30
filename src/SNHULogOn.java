import com.gargoylesoftware.htmlunit.BrowserVersion;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

public class SNHULogOn {
    private HtmlPage transactionPage;
    public void logOn()
    {
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setRedirectEnabled(true);
        try {
            HtmlPage page = (HtmlPage) webClient.getPage("https://get.cbord.com/snhu/full/login.php");
            
            HtmlForm form = page.getFormByName("login_form");
            
            form.getInputByName("username").setValueAttribute("catherine.gallaher@snhu.edu");
            HtmlInput passWordInput = form.getInputByName("password");
            //passWordInput.removeAttribute("disabled");
            passWordInput.setValueAttribute("3Mog,3Or,3Mb44");

            page = form.getInputByValue("Login").dblClick();

            //System.out.println(page.asText());

            webClient.waitForBackgroundJavaScript(10000);
            
            //transactionPage = page.getAnchorByHref("https://get.cbord.com/snhu/full/history.php").click();
            //transactionPage = page.getAnchorByHref("tabletop_left").click();
            //HtmlPage newPage = (HtmlPage) webClient.getPage("https://get.cbord.com/snhu/full/funds_home.php");
            HtmlPage currentPage = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
            HtmlForm selectionForm = currentPage.getHtmlElementById("child_selection_form");
            //String f = currentPage.getElementByName("activity_details").asText();
            HtmlDivision div = currentPage.getHtmlElementById("my_recent_transactions");
            List<HtmlTable> listTables = currentPage.getByXPath("//table");
            if (listTables == null)
            	System.out.println("no tables found");
            
            String currBalance = "its not working dumbass";
            int i = 0;
            for (HtmlTable table : listTables)
            {
            	if (table == listTables.listIterator(0))
            		continue;
	            for (HtmlTableRow row : table.getRows())
	            {
	            	
	            	String rowText = row.asText();
	            	String temp = rowText.replaceAll("\t", " ");
	            	
	            	System.out.println(temp);
	            	
	        		String parsedText[] = temp.split(" ", 11);
	        		if (temp.length() < 21 || parsedText[1].compareTo("Name") == 0)
	        		{
	        			if(parsedText[1].compareTo("Cash") ==0)
	        			{
	        				currBalance = parseBalance(row.asText());
	        			}
	        			break;
	        		}
	        			
	        		i++;
	            }
	            
            }
            
            transactionPage = currentPage.getAnchorByHref("history.php").dblClick();
            
            currentPage = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
            //HtmlAnchor htmlAnchor = currentPage.getAnchorByHref("history.php");
            //transactionPage = currentPage.getAnchorByHref("history.php").click();
            //HtmlPage transactionPage = htmlAnchor.click();
            /*
            List<String> searchResults = new ArrayList<>();
            List<HtmlAnchor> l = page.getByXPath("//a[@class='https://get.cbord.com/snhu/full/history.php']");
            for(HtmlAnchor a: l) {
                a.click();
            }
            */
            /*
            Iterable<DomNode> node = (Iterable<DomNode>) currentPage.getDescendants();
            System.out.println("0");
            for (DomNode a : node)
            {
            	System.out.println(a.asText());
            }
            currentPage = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
            */
            //HtmlDivision div = currentPage.getHtmlElementById("my_recent_transactions");
            //Iterable<HtmlElement> subDiv = div.getHtmlElementDescendants();
            
            //String xpath = "(//table[@class='table table-striped table-bordered'])";
            //HtmlTable transTable = (HtmlTable) div.getByXPath("//table[@class=table table-striped table-bordered]").get(1);
            
            //List<HtmlTable> tables = (HtmlTableColumn) div.getByXPath("//table");
            
            listTables = currentPage.getByXPath("//table");
            if (listTables == null)
            	System.out.println("no tables found");
            
            //System.out.println(listTables.toArray().length);
            //Iterable<HtmlTable> newTable = (Iterable<HtmlTable>) table;
            //final List<HtmlTableColumn> listCol;
            
            List<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
            String parsedText[] = {"","","","","","","","","","",""};
            i = 0;
            for (HtmlTable table : listTables)
            {
            	if (table == listTables.listIterator(0))
            		continue;
	            for (HtmlTableRow row : table.getRows())
	            {
	            	info.add(new ArrayList<String>());
	            	//System.out.println(row.asText());
	            	
	            	String rowText = row.asText();
	            	String temp = rowText.replaceAll("\t", " ");
	            	
	            	System.out.println(temp);
	            	
	        		parsedText = temp.split(" ", 11);
	        		
	        		if (temp.length() < 21 || parsedText[1].compareTo("Cash") != 0)
	        		{
	        			continue;
	        		}
	        			
	        		
	        		info.get(i).add(parseDay(temp)); 
	            	info.get(i).add(parseTime(temp));
	            	info.get(i).add(parseAmount(temp));
	        		i++;
	            }

            }
            

            
            
            for(int k = 0; k < info.size(); k++)
            {
            	for(int j = 0; j < info.get(k).size(); j++)
            	{
            		System.out.print(info.get(k).get(j) + "| ");
            	}
            	System.out.println();
            }
            
            System.out.println(currBalance);
            System.out.println("Finished parsing data from scrape!\n-----------------------------------------------\n\n");
            
            //System.out.println(currentPage.asText());
            //System.out.println(trans1.asText());
            
            form.remove();
            selectionForm.remove();
            page.cleanUp();
            page.remove();
            currentPage.cleanUp();
            currentPage.remove();
            //transactionPage.cleanUp();
            //transactionPage.remove();
            webClient.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webClient.close();
        }
    }

    public HtmlPage getTransactionPage()
    {
        return transactionPage;
    }

    public void closeTransactionPage()
    {
        System.out.println(transactionPage.asText());
        transactionPage.remove();
    }
    
    private String parseBalance(String bal)
    {
    	return bal.substring(bal.length() - 6);
    }
    
    private String parseDay(String date)
	{
    	date = date.replaceAll("\t", " ");
		String parsedDate[] = date.split(" ", 11);
		
		//System.out.println(parsedDate.length + " " + parsedDate[3]);
		String completeDate = parsedDate[3] + " " + parsedDate[4] + " " + parsedDate[5];
		//String parsedDate2[] = parsedDate[0].split(",", 1);
		
		
		
		
		//return Integer.parseInt(parsedDate2[0]);
		return completeDate.substring(0, completeDate.length()-1);
	}
    private String parseTime(String time)
	{
    	String temp = time.replaceAll("\t", " ");
		String parsedTime[] = temp.split(" ", 11);
		return parsedTime[6];//7 on main page
	}
    
    private String parseAmount(String amount)
    {
    	String temp = amount.replaceAll("\t", " ");
    	String parsedAmount[] = temp.split(" ", 20);
    	return parsedAmount[parsedAmount.length];//10 on main page
    }
}
