package com.switchvov.spring.web.servlet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author switch
 * @since 2020/2/10
 */
@RestController
public class HelloWorldAsyncController {

    private BlockingQueue<DeferredResult<String>> queue = new ArrayBlockingQueue<>(5);

    private final Random random = new Random();

    @PostConstruct
    public void process() {
        CompletableFuture.runAsync(() -> {
            DeferredResult<String> result;
            while (true) {
                try {
                    result = queue.take();
                    // 随机超时时间
                    long timeout = random.nextInt(2000);
                    // 模拟等待时间
                    Thread.sleep(timeout);
                    // 计算结果
                    result.setResult("Hello World");
                    println("执行计算结果，消耗：" + timeout + " ms.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @GetMapping("/hello-world")
    public DeferredResult<String> helloWorld() {
        DeferredResult<String> result = new DeferredResult<>(500L);
//        result.setResult("Hello World");
        // 入队操作
        queue.offer(result);
        println("Hello World");
        result.onCompletion(() -> println("执行结束"));
        result.onTimeout(() -> println("执行超时"));
        return result;
    }

    @GetMapping("/hello-world-2")
    public Callable<String> helloWorld2() {
        long startTime = System.currentTimeMillis();
        println("Hello World");
        return () -> {
            long costTime = System.currentTimeMillis() - startTime;
            println("执行计算结果，消耗：" + costTime + " ms.");
            return "Hello World";
        };
    }

    @GetMapping("/hello-world-3")
    public CompletionStage<String> helloWorld3() {
        long startTime = System.currentTimeMillis();
        println("Hello World");

        return CompletableFuture.supplyAsync(() -> {
            long costTime = System.currentTimeMillis() - startTime;
            println("执行计算结果，消耗：" + costTime + " ms.");
            return "Hello World";
        });
    }

    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("HelloWorldAsyncController[" + threadName + "]：" + object);
    }
}
