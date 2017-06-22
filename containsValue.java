/**
 * Created by Kealan on 19/06/2017.
 */
public class containsValue extends GrapheneAssert<SELF, actual> {
    public SELF containsValue(SELF expected){
        assert this.getText() : expected;
    }
}

//