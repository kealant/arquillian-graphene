import org.openqa.selenium.WebElement;

public class Enabled {
    public WebElement enabled(WebElement element){
        assert element.isEnabled();
        return element;
    }

}
