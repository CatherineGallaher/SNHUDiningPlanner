import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class PullFromSite {
	public void pullTest()
	{
		WebClient webClient = new WebClient();
	    try {
	        HtmlPage page = (HtmlPage) webClient.getPage("https://get.cbord.com/snhu/full/login.php");
	        HtmlForm form = page.getFormByName("login_form");
	        form.getInputByName("username").setValueAttribute("catherine.gallaher@snhu.edu"); 
	        HtmlInput passWordInput = form.getInputByName("Yohoho");
	        //passWordInput.removeAttribute("disabled");
	        passWordInput.setValueAttribute("myPassword"); 

	        page = form.getInputByValue("Login").dblClick();

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
}
