import com.gargoylesoftware.htmlunit.BrowserVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.xml.crypto.Data;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class SNHULogOn {
    private HtmlPage transactionPage;
    private List<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
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
            
            form.getInputByName("username").setValueAttribute("catherine.gallaher@snhu.edu");
            HtmlInput passWordInput = form.getInputByName("password");
            //passWordInput.removeAttribute("disabled");
            passWordInput.setValueAttribute(PasswordDecryption.decryptAES(password));
            

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
                
                for(HtmlTable table : listTables)
                {
    				if(table == listTables.listIterator(0))
                		continue;

                	for(int i = 1; i < table.getRowCount(); i++)
                	{
                		info.add(new ArrayList<String>());
                		info.get(i-1).add(parseDay(table.getCellAt(i, 1).asText()));
                		info.get(i-1).add(parseTime(table.getCellAt(i, 1).asText()));
                		info.get(i-1).add(parseAmount(table.getCellAt(i, 3).asText()));
                	}
                }

                
                
               /* for(int k = 0; k < info.size(); k++)
                {
                	for(int j = 0; j < info.get(k).size(); j++)
                	{
                		System.out.print(info.get(k).get(j) + " ");
                	}
                	System.out.println();
                }*/
                
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webClient.close();
        }
        return false;
    }
    
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
    	return parsedAmount[1];//9];//parsedAmount.length];//10 on main page
    }
    
    public List<ArrayList<String>> getInfo()
    {
    	return info;
    }
    
    public String getBalance()
    {
    	return currBalance;
    }
}
