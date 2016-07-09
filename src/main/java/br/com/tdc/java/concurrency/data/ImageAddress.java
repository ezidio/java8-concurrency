package br.com.tdc.java.concurrency.data;

/**
 * Created by Everton Tavares on 29/06/2016.
 */
public class ImageAddress {

    private String url;
    private ImageSize size;

    public ImageAddress(String url, ImageSize size) {
        this.url = url;
        this.size = size;
    }

    public String toString() {
        return this.size +" => "+this.url;
    }
}
