package com.upsmart.part2;

import org.junit.Test;

/**
 * Created by pxh on 2017/10/26.
 */
@ClassAnno
public class Target {


    private int num;

    /**
     * execution pointcut 织入代码
     * withincode 织入代码，织入代码被执行了两次
     */
    @Test
    public void sayHello() {
        printHello();
    }

    public void printHello() {
        System.out.println("hello world!");
    }

    /**
     * get pointcut织入代码
     * @return
     */
    public int getNum() {
        return num;
    }

    /**
     * set pointcut织入代码
     * @param num
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * call pointcut织入代码
     *
     */
    @Test
    public void call() {
        sayHello();
        getNum();
        add(10);
        setNum(10);
    }

    /**
     * args pointcut 织入代码，发现在织入代码对i的改变，并未影响到main方法中的i值
     * @param i
     */
    public int add(int i) {
        System.out.println("add i =" + i);
        return i+1;
    }

    @Test
    public void call1() {
        System.out.println(add(2));
    }

    public static void main(String[] args) {

    }
}
