package br.com.tdc.java.concurrency.service;

import br.com.tdc.java.concurrency.repository.AddressRepository;
import br.com.tdc.java.concurrency.data.ImageAddress;
import br.com.tdc.java.concurrency.data.ImageData;

/**
 * Image resize services
 */
public class ResizeService {

    private ImageService imageService = new ImageService();
    private AddressRepository addressRepository = new AddressRepository();

    public ImageAddress resize(ImageData input) {
        return null;

    }


}
