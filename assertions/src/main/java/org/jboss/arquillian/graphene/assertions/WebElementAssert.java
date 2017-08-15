package org.jboss.arquillian.graphene.assertions;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.xpath.operations.Bool;
import org.assertj.core.api.AbstractAssert;
import org.jboss.arquillian.graphene.findby.ByJQuery;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.assertj.core.api.Assertions.assertThat;

public class WebElementAssert extends AbstractAssert<WebElementAssert, WebElement> {

    private boolean negated;

    public WebElementAssert(WebElement actual) {
        super(actual, WebElementAssert.class);
    }

    public WebElementAssert hasText(String expectedText) {
        if(this.negated){
            assertThat(this.actual.getText()).as("checking strings are not equal").overridingErrorMessage("contains expected string").isNotEqualTo(expectedText);
            this.negated = false;
            return this;
        }
        else {
            assertThat(this.actual.getText()).as("checking equality").overridingErrorMessage("contains other string").isEqualTo(expectedText);
            return this;
        }
    }

    public WebElementAssert hasChild(){
            if (this.negated) {
                assertThat(this.actual.findElements(ByJQuery.selector("*"))).asList().size().isEqualTo(0);
                this.negated = false;
                return this;
            } else {
                assertThat(this.actual.findElement(ByJQuery.selector("*")));
                return this;
            }
    }

    //work in progress!
    public WebElementAssert hasParent(){
        assertThat(this.actual.findElement(ByJQuery.selector("")));
        return this;
    }


    public WebElementAssert isDisplayed(){
        if(this.negated){
            assertThat(Boolean.valueOf(this.actual.isDisplayed())).as("checking element is not displayed").overridingErrorMessage("element is displayed").isFalse();
            this.negated = false;
            return this;
        }
        else {
            assertThat(Boolean.valueOf(this.actual.isDisplayed())).as("checking element is displayed").overridingErrorMessage("element is not displayed").isTrue();
            return this;
        }
    }

    //doesn't work for dropboxes
    public WebElementAssert isSelected(){
        if(this.negated){
            assertThat(Boolean.valueOf(this.actual.isSelected())).as("checking element is not selected").overridingErrorMessage("element is selected").isFalse();
            this.negated = false;
            return this;
        }
        else {
            assertThat(Boolean.valueOf(this.actual.isSelected())).as("checking element is selected").overridingErrorMessage("element is not selected").isTrue();
            return this;
        }
    }

    //support's dropboxes, supply dropbox and text of chosen option to assert it's chosen
    public WebElementAssert isChosenD(Select dropdown){
        if(this.negated){
            assertThat(dropdown.getFirstSelectedOption()).as("checking supplied choice is not chosen").overridingErrorMessage("supplied choice is chosen").isNotEqualTo(this.actual);
            this.negated = false;
            return this;
        }
        else {
            assertThat(dropdown.getFirstSelectedOption()).as("checking supplied choice is chosen").overridingErrorMessage("supplied choice is not chosen").isEqualTo(this.actual);
            return this;
        }
    }


    public WebElementAssert containsText(String expectedText){
        String text = this.actual.getAttribute("value");
        if(this.negated){
            assertThat(text).as("checking text value is not the same as the supplied string").overridingErrorMessage("contains the supplied string").isNotEqualTo(expectedText);
            this.negated = false;
            return this;
        }
        else {
            assertThat(text).as("checking text value is the same as the supplied string").overridingErrorMessage("contains other string").isEqualTo(expectedText);
            return this;
        }
    }

    public WebElementAssert isEmpty(){
        String content = this.actual.getAttribute("value");
        if(this.negated){
            assertThat(content).as("checking text input is not empty").overridingErrorMessage("is actually empty").isNotEqualTo("");
            this.negated = false;
            return this;
        }
        else {
            assertThat(content).as("checking text input is empty").overridingErrorMessage("is not empty").isEqualTo("");
            return this;
        }
    }

    public WebElementAssert isTypeOf(String expectedType){
        if(this.negated){
            assertThat(this.actual.getAttribute("type")).as("checking not supplied type").overridingErrorMessage("contains supplied type").isNotEqualTo(expectedType);
            this.negated = false;
            return this;
        }
        else {
            assertThat(this.actual.getAttribute("type")).as("checking is supplied type").overridingErrorMessage("contains other type").isEqualTo(expectedType);
            return this;
        }
    }

    public WebElementAssert hasCssClass(String expectedClass){
        if(this.negated){
            assertThat(this.actual.getAttribute("class")).as("checking css class does not match supplied css class").overridingErrorMessage("is supplied css class").isNotEqualTo(expectedClass);
            this.negated = false;
            return this;
        }
        else {
            assertThat(this.actual.getAttribute("class")).as("checking css class matches supplied class").overridingErrorMessage("is not supplied css class").isEqualTo(expectedClass);
            return this;
        }
    }

    public WebElementAssert isFocused(WebDriver browser){
        if(this.negated){
            assertThat(this.actual).as("checking element is not in focus").overridingErrorMessage("element is in focus").isNotEqualTo(browser.switchTo().activeElement());
            this.negated = false;
            return this;
        }
        else {
            assertThat(this.actual).as("checking element is in focus").overridingErrorMessage("element is not in focus").isEqualTo(browser.switchTo().activeElement());
            return this;
        }
    }

    public WebElementAssert isEnabled(){
        if(this.negated){
            assertThat(Boolean.valueOf(this.actual.isEnabled())).as("checking element is not enabled").overridingErrorMessage("element is enabled").isFalse();
            this.negated = false;
            return this;
        }
        else {
            assertThat(Boolean.valueOf(this.actual.isEnabled())).as("checking element is enabled").overridingErrorMessage("element is not enabled").isTrue();
            return this;
        }
    }

    public WebElementAssert textMatchesRegex(String regex){
        if(this.negated){
            assertThat(Boolean.valueOf(this.actual.getText().matches(regex))).as("checking regex does not match text").overridingErrorMessage("regex matches text").isFalse();
            this.negated = false;
            return this;
        }
        else {
            assertThat(Boolean.valueOf(this.actual.getText().matches(regex))).as("checking regex matches text").overridingErrorMessage("regex does not match text").isTrue();
            return this;
        }
    }

    public WebElementAssert isNot(){
        this.negated = true;
        return this;
    }

    public WebElementAssert find(String locator, WebDriver browser){
        WebElement possible = browser.findElement(By.id(locator));
        assertThat(possible);
        return (WebElementAssert) possible;
    }

}
