import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/*
create a publisher:
    1. two publisher, Flux and Mono
    2. create a Flux, use its static factory method range() for Integer, or just()
           Flux<Integer> flux = Flux.range(1,10);
           Flux<String> flux = Flux.just("data1", "data2", "data3");
    3. subscribe to publisher by call subscribe. publisher will create a subscriber out of function, or you can
       input a customized subscriber, as we can see in the following examples:
          1. pass function directly. System.out::println    out is a static member of System. use:: to reference this
              method
          2.  pass lambda which create an instance of a customized class which extends BaseSubscriber
          3.  pass a lambda function
          4.  in c++, passing a function of a class makes no sense because we need object to invoke a method, but in java
              using instance::method will implicitly pass reference of instance, if method is statical one, you need to pass
              classname::method

    4.  publisher will call onSubscribe of subscriber with subscription object as argument to notify subscriber.
    5.  subscriber will keep the subscription.
    6.  subscriber will implement multiple interface, CoreSubscriber, Subscription, Disposole.
    7.  once onsubscribe is called, subscriber will call request which is method of subscription interface to request data
    8.  onNext is called to receive data, hookOnNext will be called if you have your own subscriber class

Flux<Integer> has method subscribe.

 */


public class ReactorSample {
    public void printHashCode(){
        System.out.println(this.hashCode());
    }
    public void printNameInstance(int num) {
        System.out.println("Hello, " + num);
    }
    public static void printNameInstanceStatic(int num) {
        System.out.println("Hello, " + num);
    }

    public static void main(String[] args) {
        ReactorSample sample = new ReactorSample();
        sample.printHashCode();

        // Create a Flux emitting a sequence of integers from 1 to 5
        Flux<Integer> flux = Flux.range(1, 5).log();

        // Subscribe to the Flux and print each emitted value
        flux.subscribe(System.out::println);

        // Second subscriber: Multiply each emitted value by 10 and print the result
        flux.map(num -> num * 10)
                .subscribe(System.out::println);

        flux.subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                subscription.request(2); // Request two elements
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println(value);
            }
        });

        flux.subscribe(sample::printNameInstance);
        flux.subscribe(ReactorSample::printNameInstanceStatic);
        flux.subscribe(data->{System.out.println("hello"); System.out.println(data);});
    }
}

