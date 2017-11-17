package com.upsmart.part2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by pxh on 2017/10/27.
 * 用来比较target within this的区别
 *  第一张我们介绍过，说apsectj是动态、静态植入结合的。 那么Target()  this()就是属于他动态植入的方式，
 *  within是静态植入的。故target(),this()需要在运行时才能确定那些被拦截。
 *  比如刚才的例子，我们在给Animal加多一个实现类，用target() 他仍然可以被拦截。
 *  所以target()和this()会用继承关系作用,也就是说：如果你的signature是一个基类，
 *  那么这个pointcut同时也会对他的子类也起作用。
 * 另外target 和 this 可以获取他们对应的实例。  但是within没法作到。
 * target()是指：我们pointcut 所选取的Join point 的所有者，直白点说就是：
 * 指明拦截的方法属于那个类。
 * this()是指： 我们pointcut 所选取的Join point 的调用的所有者，就是说：
 * 方法是在那个类中被调用的。

 */
@Aspect
public class MoveAspect {

    //@Before("call(* move(..))") 拦截到所有move方法
    // @Before("call(* move(..)) && target(Animal)") 拦截到所有 Animal move方法
//    @Before("call(* move(..)) && this(Animal)") 未拦截到move方法
//    @Before("call(* move(..)) && this(TextMove)") 拦截到所有move方法
//    @Before("execution(* move(..)) && this(Animal)")  所有在Animal及子类
//    中执行的move()的点都被拦截了
//    @Before("execution(* move(..)) && within(Animal)") 未拦截到move方法
//    @Before("execution(* move(..)) && within(Snake)") 只拦截到了Snake的move方法

    public void moveAspect(JoinPoint joinPoint) {
        System.out.println("Entering "+ joinPoint);
    }
}
