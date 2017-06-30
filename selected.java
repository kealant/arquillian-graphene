
import org.openqa.selenium.WebElement;

public class selected {
    
    public WebElement selected(WebElement element){
        assert element.isSelected();
        return element;
    }
}
