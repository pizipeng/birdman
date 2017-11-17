package com.upsmart.part2;

/**
 * Created by pxh on 2017/10/27.
 */
public aspect AroundAspect {
    pointcut Arg(int i): execution(* com.upsmart.part2.Target.add(int)) && args(i);

    int around(int x): Arg(x) {
        System.out.println("Entering: " + thisJoinPoint.getSourceLocation());
        int newValue = proceed(x*3);
        return newValue;
    }
}
