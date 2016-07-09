package br.com.tdc.java.concurrency.repository;

import br.com.tdc.java.concurrency.data.ImageAddress;
import br.com.tdc.java.concurrency.util.Simulation;

/**
 * Manage address data
 */
public class AddressRepository {



    public ImageAddress save(ImageAddress address) {
        Simulation.simulate("Image "+address+" saved");
        return address;
    }
}
