import io.reactivex.*;
import org.junit.Assert;
import org.junit.Test;

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

    /**
     * Single<T> example
     * Single - Emits either a single item or an error.
     * The reactive version of a method call.
     * You subscribe to a Single and you get either a return value or an error.
     */
    @Test
    public void checkIfSubscriberReceivedTestValueFromSingle() {
        receivedTest = false;
        Single<String> observable = Single.just(TEST);
        observable.subscribe(string -> receivedTest = string.equals(TEST));
        Assert.assertTrue(receivedTest);
    }


    /**
     * Maybe<T> example
     * Maybe - Succeeds with an item, or no item, or errors.
     * The reactive version of an Optional.
     */
    @Test
    public void checkIfSubscriberReceivedTestValueFromMaybe() {
        receivedTest = false;
        Maybe<String> observable = Maybe.just(TEST);
        observable.subscribe(string -> receivedTest = string.equals(TEST));
        Assert.assertTrue(receivedTest);
    }

    /**
     * Completable example
     * Completable - Either completes or returns an error.
     * It never return items.
     * The reactive version of a Runnable..
     */
    @Test
    public void checkIfSubscriberReceivedTestValueFromCompletable() {
        receivedTest = false;
        Completable observable = Completable.fromRunnable(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        observable.subscribe(() -> receivedTest = true);
        Assert.assertTrue(receivedTest);
    }
}
