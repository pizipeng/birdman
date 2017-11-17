package com.upsmart.part2;

import org.junit.Test;

/**
 * Created by pxh on 2017/10/27.
 */
@ClassAnno
public class AnnotitionTarget {

    @FieldAnnotation
    public String name = "add";

    @Test
    public void annotionTest() {
        System.out.println("aaaaddd");
        System.out.println(name);
    }
}
