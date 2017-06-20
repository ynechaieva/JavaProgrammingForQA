package pl.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.pft.addressbook.model.ContactData;


public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd){
    super(wd);
  }

  public void fillContactForm(ContactData contact){
    type(By.name("firstname"), contact.getFirstName());
    type(By.name("lastname"), contact.getLastName());
    type(By.name("email"), contact.getEmail());
  }

  public void submitContactCreation(){
    click(By.name("submit"));
  }

  public void selectContact(){
    click(By.name("selected[]"));
  }

  public void deleteSelectedContact(){
    click(By.xpath("//input[contains(@value, 'DELETE')]"));
    allertAccept();
  }
}
