package com.upsmart.part1;

/**
 * Created by pxh on 2017/10/20.
 */
@FunctionalInterface
public interface IntefaceDefaultMethod {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

}
