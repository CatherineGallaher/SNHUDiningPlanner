import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SNHULogOn {
    private HtmlPage transactionPage;
    public void logOn()
    {
        WebClient webClient = new WebClient();
        try {
            HtmlPage page = (HtmlPage) webClient.getPage("https://get.cbord.com/snhu/full/login.php");
            HtmlForm form = page.getFormByName("login_form");
            form.getInputByName("username").setValueAttribute("catherine.gallaher@snhu.edu");
            HtmlInput passWordInput = form.getInputByName("password");
            //passWordInput.removeAttribute("disabled");
            passWordInput.setValueAttribute("");

            page = form.getInputByValue("Login").dblClick();

            //transactionPage = page.getElementByName("History").click();
            //transactionPage = page.getAnchorByHref("https://get.cbord.com/snhu/full/history.php").click();
            transactionPage = page.getAnchorByHref("tabletop_left").click();

            form.remove();
            page.cleanUp();
            page.remove();
            webClient.close();
            System.out.println(page.asText());
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
