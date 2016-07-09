package br.com.tdc.java.concurrency.dsl;

import br.com.tdc.java.concurrency.data.ImageAddress;
import br.com.tdc.java.concurrency.data.ImageSize;
import br.com.tdc.java.concurrency.util.Simulation;

import java.util.concurrent.CompletableFuture;

/**
 * DSL to handle with address
 */
public class AddressDSL {

    private CompletableFuture<ImageAddress> address;

    public AddressDSL(CompletableFuture<ImageAddress> address) {
        this.address = address;
    }

    public AddressDSL save() {
        return new AddressDSL(address.thenApplyAsync(addr -> {
            Simulation.simulate("saving... ");
            return addr;
        }));
    }

    public CompletableFuture<ImageAddress> toCompletableFuture() {
        return this.address;
    }

    public ImageAddress join() {
        return this.address.join();
    }

    public AddressDSL returnDefaultOnError() {
        return new AddressDSL(address.exceptionally(ex -> new ImageAddress("error.jpg", ImageSize.ORIGINAL)));
    }
}
