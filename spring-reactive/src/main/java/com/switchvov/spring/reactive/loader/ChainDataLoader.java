package com.switchvov.spring.reactive.loader;

import java.util.concurrent.CompletableFuture;

/**
 * @author switch
 * @since 2020/2/12
 */
public class ChainDataLoader extends DataLoader {

    @Override
    protected void doLoad() {
        CompletableFuture.runAsync(this::loadConfigurations)
                .thenRun(this::loadUsers)
                .thenRun(this::loadOrders)
                .whenComplete((result, throwable) -> {
                    // 完成时回调
                    loadMock("加载完成", 0);
                })
                .join();
    }

    public static void main(String[] args) {
        new ChainDataLoader().load();
    }
}
