package br.com.tdc.java.concurrency.exception;

/**
 * Created by Everton Tavares on 29/06/2016.
 */
public class ImageException extends Exception {
    public ImageException(String msg, Throwable e) {
        super(msg, e);
    }
}
