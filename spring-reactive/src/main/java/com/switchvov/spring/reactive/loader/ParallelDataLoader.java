package com.switchvov.spring.reactive.loader;

import java.util.concurrent.CompletableFuture;

/**
 * @author switch
 * @since 2020/2/12
 */
public class ParallelDataLoader extends DataLoader {

    @Override
    protected void doLoad() {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(this::loadConfigurations);
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(this::loadUsers);
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(this::loadOrders);
        CompletableFuture.allOf(future1, future2, future3).join();
    }

    public static void main(String[] args) {
        new ParallelDataLoader().load();
    }
}
