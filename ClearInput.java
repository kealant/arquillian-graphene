import org.openqa.selenium.WebElement;

public class ClearInput {
    public WebElement cleared(WebElement element){
        assert element.getText().equals("") == true : "The input area is not clear." ;
        return element;
    }
}
