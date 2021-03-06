package pl.pft.mantis.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.id("username"), username);
    type(By.id("email-field"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String user, String password) throws InterruptedException {
    wd.get(confirmationLink);
    type(By.id("realname"), user);
    type(By.id("password"), password);
    type(By.id("password-confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }

  public void changePassword(String confirmationLink, String user, String password) throws InterruptedException {
    wd.get(confirmationLink);
    type(By.id("password"), password);
    type(By.id("password-confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }

  public void loginThroughWeb(String user, String password) {
    WebDriverWait wait = new WebDriverWait(wd, 5);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form[id='login-form']")));
    type(By.id("username"), user);
    click(By.cssSelector("input[value='Login']"));
    type(By.id("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public boolean isLoggedOnWebAs(String username) {
    String hrefText = wd.findElement(By.xpath(String.format("//a[contains(@href,'/account_page.php')][text()='%s ( %s ) ']", username, username))).getText();
    if(hrefText.contains(username))
      return true;
    else return false;
  }

  public void resetUserPassword(String username) {
    WebDriverWait wait = new WebDriverWait(wd, 5);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Manage')]")));
    click(By.xpath("//span[contains(text(),'Manage')]"));
    click(By.xpath("//a[contains(text(),'Manage Users')]"));
    click(By.linkText(username));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void logout(String username) {
    click(By.xpath(String.format("//span[contains(text(), '%s')]", username)));
    click(By.xpath("//a[contains(@href, 'logout_page.php')]"));
  }

}