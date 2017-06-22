
import org.openqa.selenium.WebElement;

public class selected {

    protected final SELF self;
    
    SELF selected(SELF self){
        assert this.isSelected();
        return this;
    }
}
