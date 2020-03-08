package com.jw.spring4.demo1.g_aspectsjaoppointcut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect  //申明为切面类
public class MyAspectPointCut {

    //可以多个pointcut结合使用
    @Before("MyAspectPointCut.userService7PointcutForGet()||MyAspectPointCut.userService7PointcutForUpdate()")
    public void before(){
        System.out.println("前置通知-------------");
    }

    //最终通知,不管方法有没错都会执行，类似finally
    @After("com.jw.spring4.demo1.g_aspectsjaoppointcut.MyPointcut.userService7Pointcut()")
    public void finallyMethod(){
        System.out.println("最终通知--------------");
    }





    @Pointcut(value = "execution(* com.jw.spring4.demo1.g_aspectsjaoppointcut.UserService7.get(..))")
    private void userService7PointcutForGet(){    //方法名就是切入点名称
    }

    @Pointcut(value = "execution(* com.jw.spring4.demo1.g_aspectsjaoppointcut.UserService7.update(..))")
    private void userService7PointcutForUpdate(){    //方法名就是切入点名称
    }

}
