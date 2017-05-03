import io.reactivex.Flowable;
import org.junit.Assert;
import org.junit.Test;
import io.reactivex.Observable;

/**
 * Created by Michał Szewczyk on 2017-05-03.
 */

public class Examples {
    private final static String TEST = "test";
    private boolean receivedTest;

    /**
     * First, basic usage
     */
    @Test
    public void checkIfSubscriberReceivedTestValue(){
        receivedTest = false;
        Observable<String> observable = Observable.just(TEST);
        observable.subscribe(string -> receivedTest = string.equals(TEST));
        Assert.assertTrue(receivedTest);
    }

    /**
     * Flowable<T> example
     * Flowable - emits 0 or n items and terminates with complete or an error.
     * Supports backpressure, which allows to control how fast a source emits items.
     */
    @Test
    public void checkIfSubscriberReceivedTestValueFromFlowable(){
        receivedTest = false;
        Flowable<String> observable = Flowable.just(TEST);
        observable.subscribe(string -> receivedTest = string.equals(TEST));
        Assert.assertTrue(receivedTest);
    }
}
