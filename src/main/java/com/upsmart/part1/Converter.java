package com.upsmart.part1;

/**
 * Created by pxh on 2017/10/20.
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F f);
}