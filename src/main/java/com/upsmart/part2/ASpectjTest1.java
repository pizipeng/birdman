package com.upsmart.part2;

import org.junit.Test;

/**
 * Created by pxh on 2017/10/25.
 * 本例主要是结合代码来熟悉aspectj的使用
 */
public class ASpectjTest1 {

    public void foo() {
        System.out.println("foo.........");
    }

    public void bar() {
        foo();
        System.out.println("bar.........");
    }

    /**
     * cflow pointcut 织入代码
     */
    @Test
    public void testMethod() {
        bar();
        foo();
    }



    public static void main(String[] args) {

    }
}
