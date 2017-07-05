import org.openqa.selenium.WebElement;

public class Disabled {
    public WebElement disabled(WebElement element){
        assert element.isEnabled() == false : "Element is actually enabled";
        return element;
    }
}
