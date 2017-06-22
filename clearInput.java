import org.openqa.selenium.WebElement;

public class clearInput {
    public SELF cleared(SELF){
        assert this.getText() == "";
    }
}
