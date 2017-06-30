import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ParentElement {
    boolean hasParent(WebElement elem){
        try {
            WebElement parent = elem.findElement(By.xpath(".."));
            return true;
        }
        catch (NoSuchElementException nosuchelementexception){
            return false;
        }
    }
}
