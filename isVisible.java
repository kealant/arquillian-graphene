
package org.jboss.arquillian.graphene.assertions;

import org.openqa.selenium.WebElement;

public class isVisible {

    public WebElement isVisible(WebElement element) {
            assert element.isDisplayed() : true;
            return element;
    }
}
