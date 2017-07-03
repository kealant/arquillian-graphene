import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.NoSuchElementException;

class Exists{
   public WebElement Exists(String element, String locator, WebDriver driver){
       String [] posLocators = {"xpath", "className", "cssSelector", "id", "linkText", "name", "partialLinkText", "tagName"};
       if (!Arrays.asList(posLocators).contains(locator)){
           System.out.println("Invalid locator string, must supply one of the By locator options");
       }
       try{
            WebElement elem = driver.findElement(By.locator(element));
        return elem;
       }

   }
}
