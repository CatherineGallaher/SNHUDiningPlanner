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
            
            form.getInputByName("username").setValueAttribute("john.canducci@snhu.edu");
            HtmlInput passWordInput = form.getInputByName("password");
            //passWordInput.removeAttribute("disabled");
            passWordInput.setValueAttribute("");

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
            
            List<HtmlTable> listTables = currentPage.getByXPath("//table");
            if (listTables == null)
            	System.out.println("no tables found");
            
            System.out.println(listTables.toArray().length);
            //Iterable<HtmlTable> newTable = (Iterable<HtmlTable>) table;
            //final List<HtmlTableColumn> listCol;
            
            for (HtmlTable table : listTables)
            {
            	if (table == listTables.listIterator(0))
            		continue;
	            for (HtmlTableRow row : table.getRows())
	            {
	            	String[] info = new String[3];
	            	System.out.println(row.asText());
	            	info[0] = parseDay(row.asText());
	            	info[1] = parseTime(row.asText());
	            	info[2] = parseAmount(row.asText());
	            	for (int i = 0; i < info.length; i++)
	            		System.out.println(info[i]);
	            }
            }
            System.out.println("finished!");
            
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
    	return bal.substring(3);
    }
    
    private String parseDay(String date)
	{
    	date = date.replaceAll("\t", " ");
		String parsedDate[] = date.split(" ", 11);
		for(int i = 0; i < parsedDate.length; i++)
		{
			System.out.println(i + " " + parsedDate[i] + "-");
		}
		if (parsedDate.length < 6)
			return " ";
		//System.out.println(parsedDate.length + " " + parsedDate[3]);
		String completeDate = parsedDate[3] + " " + parsedDate[4] + " " + parsedDate[5];
		//String parsedDate2[] = parsedDate[0].split(",", 1);
		
		
		
		
		//return Integer.parseInt(parsedDate2[0]);
		return completeDate;
	}
    private String parseTime(String time)
	{
    	String temp = time.replaceAll("\t", " ");
		String parsedTime[] = temp.split(" ", 11);
		if (parsedTime.length < 11)
			return " ";
		return parsedTime[7];//7
	}
    
    private String parseAmount(String amount)
    {
    	String temp = amount.replaceAll("\t", " ");
    	String parsedAmount[] = temp.split(" ", 11);
    	if (parsedAmount.length < 11)
    		return " ";
    	return parsedAmount[10];//10
    }
}
