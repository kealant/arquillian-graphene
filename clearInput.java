import org.openqa.selenium.WebElement;

public class clearInput {
    public WebElement cleared(WebElement element){
        assert element.getText().equals("");
        return element;
    }
}
