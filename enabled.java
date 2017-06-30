import org.openqa.selenium.WebElement;

public class enabled {
    public WebElement enabled(WebElement element){
        assert element.isEnabled();
        return element;
    }

}
