package br.com.tdc.java.concurrency.dsl;

import br.com.tdc.java.concurrency.data.ImageAddress;
import br.com.tdc.java.concurrency.data.ImageData;
import br.com.tdc.java.concurrency.data.ImageFilter;
import br.com.tdc.java.concurrency.data.ImageSize;
import br.com.tdc.java.concurrency.service.ImageService;
import br.com.tdc.java.concurrency.util.Simulation;

import java.util.concurrent.CompletableFuture;

/**
 * DSL to handle with image
 */
public class ImageDSL {

    private CompletableFuture<ImageData> image;

    public ImageDSL(CompletableFuture<ImageData> image) {
        this.image = image;
    }

    public static ImageDSL read(String image) {
        return new ImageDSL(CompletableFuture.supplyAsync(() -> {
            Simulation.simulate("reading " + image);
            return new ImageData(image, ImageSize.ORIGINAL);
        }));
    }

    public ImageDSL resizeTo(ImageSize newSize) {
        return new ImageDSL(image.thenApplyAsync(img -> {
            Simulation.simulate("Resizing to " + newSize);
            return new ImageData(img.getData(), newSize);
        }));
    }

    public ImageDSL applyFilter(ImageFilter filter) {
        return new ImageDSL(image.thenApplyAsync(img -> {
            Simulation.simulate("applying filter: " + filter);
            return new ImageData(filter.name().toLowerCase() + "_" + img.getData(), img.getSize());
        }));
    }

    public ImageDSL returnDefaultOnError() {
        return new ImageDSL(image.exceptionally(ex -> new ImageData("error.jpg", ImageSize.ORIGINAL)));
    }

    public AddressDSL uploadToCloud() {
        return new AddressDSL(image.thenApplyAsync(img -> {
            Simulation.simulate("uploading to cloud");
            return new ImageAddress("http://my.cloud.server/images/" + img.getData(), img.getSize());
        }));
    }

    public CompletableFuture<ImageData> toCompletableFuture() {
        return this.image;
    }

    public ImageData join() {
        return this.image.join();
    }

    public static ImageDSL of(ImageData input) {
        return new ImageDSL(CompletableFuture.completedFuture(input));
    }
}
