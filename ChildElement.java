import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ChildElement {
    boolean hasChild(WebElement elem){
        try{
            WebElement child = elem.findElement(By.xpath(".//input"));
            return true;
        }
        catch(NoSuchElementException ex){
            return false;
        }
    }
}
