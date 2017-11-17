package com.upsmart.part2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.lang.annotation.Annotation;

/**
 * Created by pxh on 2017/10/27.
 * 有三种级别的Annotation
 * 1、类级别的Annotation
 * 2、Field 级别的Annotation
 * 3、作用于Method级别的Annotation
 */
@Aspect
public class AnnotationTest {


    @Before("@target(ClassAnno)")
    public void classAspect(JoinPoint joinPoint) {
        System.out.println("Enter: " + joinPoint.getSourceLocation());
    }

    @After("@annotation(FieldAnnotation)")
    public void fieldAspect(JoinPoint joinPoint){
        System.out.println("after: " + joinPoint);
    }
}
