import org.openqa.selenium.WebElement;

public class containsValue{
    public WebElement containsValue(WebElement element, String expected){
        assert element.getText().equals(expected) : "The value contained in this element does not match the expected string." ;
        return element;
    }
}
