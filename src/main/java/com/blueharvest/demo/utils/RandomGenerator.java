package com.blueharvest.demo.utils;

public class RandomGenerator {

    public static Long randomId(){
        long leftLimit = 1L;
        long rightLimit = 10000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
