
package org.jboss.arquillian.graphene.assertions;

import org.openqa.selenium.WebElement;

public class isVisible {

    public SELF isVisible(SELF self) {
            assert this.isDisplayed() : true;
    }
}
//just focused on mechanism of identifing whether the condition is satisfied or not
//still unsure whether basing on self type and returning the object is the best decision