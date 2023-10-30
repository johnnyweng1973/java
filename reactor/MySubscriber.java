import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class MySubscriber<T> extends BaseSubscriber<T> {
    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        // Custom logic when the subscription is established
        System.out.println("Subscription established");
        request(1); // Request initial elements
    }

    @Override
    protected void hookOnNext(T value) {
        // Custom logic for processing each emitted element
        System.out.println("Received: " + value);
        request(1); // Request the next element
    }

    @Override
    protected void hookOnComplete() {
        // Custom logic when the sequence completes
        System.out.println("Sequence completed");
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        // Custom error handling logic
        System.out.println("Error occurred: " + throwable.getMessage());
    }

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(1, 5);
        MySubscriber<Integer> subscriber = new MySubscriber<>();
        flux.subscribe(subscriber);
    }
}
