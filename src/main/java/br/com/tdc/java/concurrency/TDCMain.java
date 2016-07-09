package br.com.tdc.java.concurrency;

import br.com.tdc.java.concurrency.data.ImageAddress;
import br.com.tdc.java.concurrency.data.ImageData;
import br.com.tdc.java.concurrency.data.ImageSize;
import br.com.tdc.java.concurrency.service.ResizeService;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Main Application
 */
public class TDCMain {


    public static void main(String[] args) {

        ResizeService resizeService = new ResizeService();

        long init = System.currentTimeMillis();

        List<ImageAddress> result = resizeService.resize(new ImageData("tdc.jpg", ImageSize.ORIGINAL));

        System.out.println(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - init));
        System.out.println("Imagem processada com sucesso!");
        System.out.println(result);
    }

}
