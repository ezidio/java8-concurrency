package br.com.tdc.java.concurrency.service;

import br.com.tdc.java.concurrency.data.ImageAddress;
import br.com.tdc.java.concurrency.data.ImageData;
import br.com.tdc.java.concurrency.data.ImageFilter;
import br.com.tdc.java.concurrency.data.ImageSize;
import br.com.tdc.java.concurrency.util.Simulation;

import java.util.concurrent.CompletableFuture;

/**
 * Handle operation on image
 */
public class ImageService {

    public ImageData readImage(String address) {
        Simulation.simulate("Reading " + address);
        return new ImageData(address, ImageSize.ORIGINAL);
    }

    public CompletableFuture<ImageData> applyFilterAsync(ImageData original, ImageFilter filter) {
        return CompletableFuture.supplyAsync(() -> this.applyFilter(original, filter));
    }

    public ImageData applyFilter(ImageData original, ImageFilter filter) {
        Simulation.simulate("Applying filter " + filter + " to " + original);
        return new ImageData(filter.name().toLowerCase() + "_" + original.getData(), original.getSize());
    }


    public CompletableFuture<ImageAddress> uploadToCloudAsync(ImageData image) {
        return CompletableFuture.supplyAsync(() -> uploadToCloud(image));
    }

    public ImageAddress uploadToCloud(ImageData image) {
        Simulation.simulate("Uploading " + image + " to cloud");
        String address = "http://some.cloud.service.com/image/" + image.getSize().name().toLowerCase() + "_" + image.getData();
        return new ImageAddress(address, image.getSize());
    }


    public CompletableFuture<ImageData> resizeToAsync(ImageData image, ImageSize size) {
        return CompletableFuture.supplyAsync(() -> resizeTo(image, size));
    }

    public ImageData resizeTo(ImageData image, ImageSize size) {
        Simulation.simulate("resizing " + image + " to " + size);
        return new ImageData(image.getData(), size);
    }


}
