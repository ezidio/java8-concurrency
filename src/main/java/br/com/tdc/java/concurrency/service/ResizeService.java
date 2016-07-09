package br.com.tdc.java.concurrency.service;

import br.com.tdc.java.concurrency.data.ImageFilter;
import br.com.tdc.java.concurrency.data.ImageSize;
import br.com.tdc.java.concurrency.dsl.ImageDSL;
import br.com.tdc.java.concurrency.repository.AddressRepository;
import br.com.tdc.java.concurrency.data.ImageAddress;
import br.com.tdc.java.concurrency.data.ImageData;
import br.com.tdc.java.concurrency.util.CompletableFutureUtils;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Image resize services
 */
public class ResizeService {

    private ImageService imageService = new ImageService();
    private AddressRepository addressRepository = new AddressRepository();

    public List<ImageAddress> resize(ImageData input) {

        CompletableFuture.completedFuture(new ImageAddress("default.jpg", ImageSize.ORIGINAL));
        CompletableFuture<ImageAddress> thumbnail = resizeAndSave(input, ImageSize.THUMBNAIL);
        CompletableFuture<ImageAddress> large = resizeAndSave(input, ImageSize.LARGE);
        CompletableFuture<ImageAddress> medium = resizeAndSave(input, ImageSize.MEDIUM);
        CompletableFuture<ImageAddress> original = upload(input);

        return CompletableFutureUtils.allOf(thumbnail, large, medium, original).join();

        /*
        Retorno usando Join

        List<ImageAddress> result = new ArrayList<>();
        result.add(thumbnail.join());
        result.add(large.join());
        result.add(medium.join());
        result.add(original.join());

        return result;
        */
    }

    private CompletableFuture<ImageAddress> upload(ImageData input) {
        return CompletableFuture.supplyAsync(() -> {
            ImageAddress uploadedAddress = imageService.uploadToCloud(input);
            return addressRepository.save(uploadedAddress);
        });
    }

    private CompletableFuture<ImageAddress> resizeAndSave(ImageData input, ImageSize size) {

        // Usando uma DSL
        return ImageDSL.of(input)
                .resizeTo(size)
                //.applyFilter(ImageFilter.GRAYSCALE) // Caso necessite
                .uploadToCloud()
                .save()
                .returnDefaultOnError()
                .toCompletableFuture();
        /*
        Assincrona, usando os metodos de tratamento de Erro
        return CompletableFuture.supplyAsync(() -> imageService.resizeTo(input, size))
                .exceptionally(ex -> new ImageData("tdc_error.jpg", size))
                .thenApplyAsync(img -> imageService.uploadToCloud(img))
                .thenApplyAsync(address -> addressRepository.save(address))
                .handleAsync((address, ex) -> {
                    if (ex != null) {
                        System.out.println("deu erro");
                        return new ImageAddress("erro.jpg", ImageSize.ORIGINAL);
                    }
                    return address;
                });
               */

        /*
        sincrona
        return CompletableFuture.supplyAsync(() -> {
            ImageData resized = imageService.resizeTo(input, size);
            ImageAddress address = imageService.uploadToCloud(resized);
            addressRepository.save(address);
            return address;
        });*/
    }


}
