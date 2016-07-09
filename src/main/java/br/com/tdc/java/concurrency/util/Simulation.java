package br.com.tdc.java.concurrency.util;

import java.util.concurrent.TimeUnit;

/**
 * Created by Everton Tavares on 29/06/2016.
 */
public class Simulation {

    public static void simulate(String text) {
        try {
            System.out.println(text);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
