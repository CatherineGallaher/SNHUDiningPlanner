import com.gargoylesoftware.htmlunit.BrowserVersion;

import java.util.List;

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
        webClient.getOptions().setJavaScriptEnabled(false);
        try {
            HtmlPage page = (HtmlPage) webClient.getPage("https://get.cbord.com/snhu/full/login.php");
            
            HtmlForm form = page.getFormByName("login_form");
            
            form.getInputByName("username").setValueAttribute("john.canducci@snhu.edu");
            HtmlInput passWordInput = form.getInputByName("password");
            //passWordInput.removeAttribute("disabled");
            passWordInput.setValueAttribute("L+pg:GFiuu^{Y+h@4r/`");

            page = form.getInputByValue("Login").dblClick();

            System.out.println(page.asText());
            
            //transactionPage = page.getElementByName("History").click();
            //transactionPage = page.getAnchorByHref("https://get.cbord.com/snhu/full/history.php").click();
            //transactionPage = page.getAnchorByHref("tabletop_left").click();
            //HtmlPage newPage = (HtmlPage) webClient.getPage("https://get.cbord.com/snhu/full/funds_home.php");
            HtmlPage currentPage = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
            HtmlForm selectionForm = currentPage.getHtmlElementById("child_selection_form");
            //String f = currentPage.getElementByName("activity_details").asText();
            HtmlDivision div = currentPage.getHtmlElementById("my_recent_transactions");
            //HtmlAnchor htmlAnchor = currentPage.getAnchorByHref("history.php");
            
            //HtmlPage transactionPage = htmlAnchor.click();
            
            //HtmlDivision div = currentPage.getHtmlElementById("my_recent_transactions");
            //Iterable<HtmlElement> subDiv = div.getHtmlElementDescendants();
            
            //String xpath = "(//table[@class='table table-striped table-bordered'])";
            //HtmlTable transTable = (HtmlTable) div.getByXPath("//table[@class=table table-striped table-bordered]").get(1);
            
            //List<HtmlTable> tables = (HtmlTableColumn) div.getByXPath("//table");
            
            List<HtmlTable> listTables = currentPage.getByXPath("//table[@id=]");
            
            if (listTables == null)
            	System.out.println("no tables found");
            
            System.out.println(listTables.toArray().length);
            //Iterable<HtmlTable> newTable = (Iterable<HtmlTable>) table;
            //final List<HtmlTableColumn> listCol;
            for (HtmlTable table : listTables)
            {
	            for (HtmlTableRow row : table.getRows())
	            {
	            	for (HtmlTableCell cell : row.getCells()) 
	            	{
	            		System.out.println("found:" + cell.asText());
	            	}
	            }
            }
            System.out.println("finished!");
            
            System.out.println(transactionPage.asText());
            //System.out.println(trans1.asText());
            
            form.remove();
            selectionForm.remove();
            page.cleanUp();
            page.remove();
            currentPage.cleanUp();
            currentPage.remove();
            transactionPage.cleanUp();
            transactionPage.remove();
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
}
