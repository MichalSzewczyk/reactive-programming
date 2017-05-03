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
        Flowable<String> flowable = Flowable.just(TEST);
        flowable.subscribe(string -> receivedTest = string.equals(TEST));
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
        Single<String> single = Single.just(TEST);
        single.subscribe(string -> receivedTest = string.equals(TEST));
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
        Maybe<String> maybe = Maybe.just(TEST);
        maybe.subscribe(string -> receivedTest = string.equals(TEST));
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
        Completable compatible = Completable.fromRunnable(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        compatible.subscribe(() -> receivedTest = true);
        Assert.assertTrue(receivedTest);
    }

    private Boolean value;

    @Test
    public void sampleMaybeUsage() {
        //Prepared MaybeOnSubscribe
        MaybeOnSubscribe<String> maybeOnSubscribe = emitter -> {
            if (value == null) {
                //throw throwable
                emitter.onError(new IllegalStateException());
            } else if (value) {
                //return value
                emitter.onSuccess(TEST);
            } else {
                //return null
                emitter.onComplete();
            }

        };

        //onError
        boolean success = false;
        Maybe<String> maybe = null;
        try {
            maybe = Maybe.create(maybeOnSubscribe);
            maybe.blockingGet();
        } catch (Throwable throwable) {
            success = true;
        } finally {
            Assert.assertTrue(success);
        }

        //onSuccess
        value = true;
        maybe = Maybe.create(maybeOnSubscribe);
        Assert.assertEquals(maybe.blockingGet(), TEST);

        //onError
        value = false;
        maybe = Maybe.create(maybeOnSubscribe);
        Assert.assertNull(maybe.blockingGet());
    }
}
