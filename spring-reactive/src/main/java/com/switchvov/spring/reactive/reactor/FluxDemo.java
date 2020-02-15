package com.switchvov.spring.reactive.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author switch
 * @since 2020/2/12
 */
public class FluxDemo {
    public static void main(String[] args) {
        println("运行...");
        Flux.just("A", "B", "C")
                .publishOn(Schedulers.elastic())  // 线程池切换
                .map(value -> "+" + value)
                .subscribe(
                        FluxDemo::println, // 消费处理 = onNext(T)
                        FluxDemo::println, // 异常处理 = onError(Throwable)
                        () -> println("完成操作!"), // 完成回调 = onComplete()
                        subscription -> {
                            // 背压操作 = onSubscribe(Subscription)

                            // n 请求元素数量
//                            subscription.request(3);
                            // 取消上游传输数据到下游
                            subscription.cancel();
                        }
                );
    }

    public static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[线程：" + threadName + "]：" + object);
    }
}
