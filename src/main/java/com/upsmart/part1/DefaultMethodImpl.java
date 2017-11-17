package com.upsmart.part1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pxh on 2017/10/20.
 */
public class DefaultMethodImpl implements IntefaceDefaultMethod {
    @Override
    public double calculate(int a) {
        return sqrt(a);
    }


    public static void main(String[] args) {
        DefaultMethodImpl method = new DefaultMethodImpl();
        System.out.println(method.calculate(10));
        Map<String, Integer> testMap = new HashMap<>();
        testMap.putIfAbsent("aa", null);
        testMap.putIfAbsent(null, 1);
        testMap.putIfAbsent("aa", 2);
        testMap.putIfAbsent("aa", 3);
        System.out.println(testMap.get("aa"));
    }
}
