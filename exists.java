import java.util.NoSuchElementException;

class Exists {
    public SELF Exists(SELF self){
        try{
        assert findElement(this) != null;
        return self;
        }
        catch (NoSuchElementException nosuchelenentexception){
            return false; //throw an exception?
        }
    }
}

// unsure about this way of writing assertions

// .getWebElement() instead?