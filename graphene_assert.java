/**
 * Created by Kealan on 30/05/2017.
 */
abstract class GrapheneAssert<SELF extends GrapheneAssert<SELF, ACTUAL>, ACTUAL> {

    protected final ACTUAL actual;

    @SuppressWarnings("unchecked")
    protected final SELF self(){
        return (SELF) this;
    }

    protected GrapheneAssert(ACTUAL actual){
        this.actual = actual;
    }



}
