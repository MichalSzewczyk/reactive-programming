import org.junit.Assert;
import org.junit.Test;
import io.reactivex.Observable;

/**
 * Created by Michał Szewczyk on 2017-05-03.
 */

public class Examples {
    private final static String TEST = "test";
    private boolean receivedTest;
    @Test
    public void checkIfSubscriberReceivedTestValue(){
        Observable<String> observable = Observable.just(TEST);
        observable.subscribe(string -> receivedTest = string.equals(TEST));
        Assert.assertTrue(receivedTest);
    }
}
