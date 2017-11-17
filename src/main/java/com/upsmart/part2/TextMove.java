package com.upsmart.part2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pxh on 2017/10/27.
 */
public class TextMove {

    public void move(List<Animal> list) {
        for (Animal a: list) {
            a.move();
        }
    }

    @Test
    public void testAA() {
        List<Animal> list = new ArrayList<>();
        list.add(new Bird());
        list.add(new Snake());
        move(list);
    }
}
