
import org.openqa.selenium.WebElement;

public class Selected {
    
    public WebElement selected(WebElement element){
        assert element.isSelected();
        return element;
    }
}
