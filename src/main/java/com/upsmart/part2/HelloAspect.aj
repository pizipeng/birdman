package com.upsmart.part2;

import org.aspectj.runtime.CFlow;

/**
 * Created by pxh on 2017/10/26.
 * aspectj基础语法
 * 1、定义一个切面，关键字aspect。这定义java类的语法类似
 * 2、定义pointcut： [修饰符(public, protected...)] pointcut pointcut名字(): 表达式
 * 3、定义advice：  通知类型():pointcut名字(){........逻辑}
 *
 *  一个最基本的aspect，就是这样组成的。值得一提的是：aspectj支持很多类型的pointcut,最基本的就是
 *  method call pointcut（方法级别），而spring的aop仅支持method call pointcut。
 *  而至于advice， aspectj也一样，就是5种类型。
 *
 */
public aspect HelloAspect {

    /**
     * pointcut 基于正则的语法， 那么肯定也支持通配符，含义如下：
     *
     * *表示任何数量的字符，除了(.)
     * .. 表示任何数量的字符包括任何数量的（.）
     * + 描述指定类型的任何子类或者子接口
     * 同java一样，提供了一元和二元的条件表达操作符
     * 一元操作符： ！
     * 二元操作符： || 和 &&
     * 优先权同java
     * 语法结构：
     * execution([修饰符] 返回值类型 方法名(参数) [异常模式])
     * 例子：
     *  execution(public * *.*(..)) 所有的public方法
     *  execution(* hello(..)) 所有的hello方法
     *  execution(String hello(..)) 所有返回值为String的hello方法
     *  execution(* hello(String)) 所有参数为String类型的hello方法
     *  execution(* hello(String..)) 至少有一个参数，且第一个参数类型为String的hello方法
     *  execution(* com.aspect..*(..)) 所有com.aspect包，以及子孙包下的所有方法
     *  execution(* com..*.*Dao.find*(..)) com包下所有的以Dao结尾的类的以find
     *  开头的方法
     */
    //方法和构造函数执行点
    pointcut HelloWorldPointCut(): execution(* sayHello(..));

    //方法和构造函数调用点
    pointcut CallPoint(): call(* com.upsmart.part2.Target.sayHello(..));

    //属性的读操作
    pointcut GetPoint(): get(* com.upsmart.part2.Target.num);

    //属性写操作
    pointcut SetPoint(): set(* com.upsmart.part2.Target.num);

//    //异常处理
//    pointcut ExceptionHandler(): handler();
//
//    //advice执行
//    pointcut AdviceExecution(): adviceexecution();
//
//    //类初始化
//    pointcut ClassInitial(): staticinitialization();
//
//    //对象初始化
//    pointcut ObjectInitial(): initialization();
//
//    //对象预先初始化
//    pointcut ObjectPreInitial(): preinitialization();

    //捕获在指定类或者增强中的程序体中的所有连接点，包括内部类
    pointcut WithIn(): execution(* main(..)) && !within(ASpectjTest1);
//
    //用于捕获在构造器或者方法中的所有连接点，包括在其中的本地类
    pointcut WithInCode(): withincode(* sayHello(..));

//    //所有Type or id 的实例的执行点，匹配所有的连接点，如方法调用，属性设置，当前的执行对象为Account
//    pointcut This(): this();

//    //匹配所有的连接点，目标对象为Type或id
//    pointcut Target(): target();

    //参数类型为Type
    pointcut Arg(int i): execution(* com.upsmart.part2.Target.add(int)) && args(i);

    pointcut barPoint(): execution(* bar(..));

    pointcut fooPoint(): execution(* foo(..));

    //获取的是一个控制流程，包括织入后的对象，在使用时一定记得用!with()剔除aspect本身
    pointcut CFlow(): cflow(barPoint()) && !within(HelloAspect);

    pointcut fooInBar(): CFlow() && fooPoint();

    //同上，不包括连接点本身本身
    pointcut CFlowBelow(): cflowbelow(barPoint()) && !within(HelloAspect);

    before(): HelloWorldPointCut() {
        System.out.println("hello aspectj");
        System.out.println("Entering: " + thisJoinPoint.getSourceLocation());
    }

    /**
     * 智力用到了一组内置对象：thisJoinPoint， 他表示当前jionPoint，跟我们在
     * java中的this其实是差不多的，getSourceLocation表示源代码的位置
     */
    before(): CallPoint() {
        System.out.println("before call");
        System.out.println("Entering: " + thisJoinPoint.getSourceLocation());
    }

    before(): GetPoint() {
        System.out.println("before get");
    }

    before(): SetPoint() {
        System.out.println("before set");
    }

//    before(int x): Arg(x) {
//        x+= 5;
//        System.out.println("in the aspect i= " + x);
//    }

    before(): WithIn() {
        System.out.println("Entering: " + thisJoinPoint.getSourceLocation());
    }

    /**
     * 在匹配方法处被调用了两次,
     * 调用system.out.print 方法会有get(PrintStream java.lang.System.out)
     * 和call(void java.io.PrintStream.println(String))
     * 两个切点
     */
    before(): WithInCode() {
        System.out.println("withincode pointcut");
        System.out.println("Entering: " + thisJoinPoint);
    }

    /**
     * aspectj有5种类型的advice
     * before( Formals)
     * after( Formals) returning [ (Formal) ]
     * after( Formals) throwing [ ( Formal) ]
     * after( Formals )
     * Type around( Formals )
     *
     */
    before(): CFlow() {
        System.out.println("Enter: " + thisJoinPoint);
    }


    after() returning(Object o): CFlowBelow() {
        System.out.println("Returned normally with " + o);
        System.out.println("CFlowBelow Enter: " + thisJoinPoint);
    }

    /**
     * 不能定义在同一个aspect文件中，报以下异常：
     * ’Error:(146, 0) ajc: circular advice precedence:
     * can't determine precedence between two or more pieces of
     * advice that apply to the same join point:
     * method-execution(int com.upsmart.part2.Target.add(int))‘
     * 不能确定运用在同一个切点上织入代码的顺序
     */
//    int around(int x): Arg(x) {
//        System.out.println("Entering: " + thisJoinPoint.getSourceLocation());
//        int newValue = proceed(x*3);
//        return newValue;
//    }



}
