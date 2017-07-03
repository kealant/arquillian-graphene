import org.openqa.selenium.WebElement;

public class disabled {
    public WebElement disabled(WebElement element){
        assert element.isEnabled() : false;
        return element;
    }
}
