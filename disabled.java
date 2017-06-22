import org.openqa.selenium.WebElement;

public class disabled {
    public SELF disabled(SELF){
        assert this.isEnabled() == false;
        return this;
    }
}
