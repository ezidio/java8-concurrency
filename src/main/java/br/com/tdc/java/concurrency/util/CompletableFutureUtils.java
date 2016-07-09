package br.com.tdc.java.concurrency.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * CompletableFuture utilities
 */
public class CompletableFutureUtils {

    private CompletableFutureUtils() {
        // Cannot instantiate
    }

    public static <U> CompletableFuture<List<U>> allOf(List<CompletableFuture<U>> futures) {
        CompletableFuture[] futureArray = futures.stream().toArray(CompletableFuture[]::new);
        return allOf(futureArray);
    }

    public static <U> CompletableFuture<List<U>> allOf(CompletableFuture<U> ... futures) {
        return CompletableFuture.allOf(futures).thenApply(it -> {
            List<U> result = new ArrayList<U>();
            for (CompletableFuture<U> future : futures) {
                result.add(future.join());
            }
            return result;
        });
    }

}
