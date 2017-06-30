import org.openqa.selenium.WebElement;

public class containsValue{
    public WebElement containsValue(WebElement element, String expected){
        assert element.getText().equals(expected);
        return element;
    }
}
