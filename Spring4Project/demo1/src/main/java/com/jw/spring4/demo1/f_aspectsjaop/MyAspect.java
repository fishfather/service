package com.jw.spring4.demo1.f_aspectsjaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect  //申明为切面类
public class MyAspect {

    @Before("execution(* com.jw.spring4.demo1.f_aspectsjaop.UserService6.*(..))")
    public void before(){
        System.out.println("前置通知-------------");
    }

    @AfterReturning(value = "execution(* com.jw.spring4.demo1.f_aspectsjaop.UserService6.get(..))", returning = "returnValue")
    public void afterReturning(Object returnValue){      //这里returnValue必须和标签里一样
        System.out.println("后置通知------------- 返回值："+returnValue);
    }

    @Around("execution(* com.jw.spring4.demo1.f_aspectsjaop.UserService6.*(..))")
    public Object around(ProceedingJoinPoint pj) throws Throwable {      //这里returnValue必须和标签里一样
        System.out.println("环绕通知-------------开始");

        Object retObj = pj.proceed();   //执行目标对象的方法

        System.out.println("环绕通知-------------结束 --- 返回值："+retObj);

        return retObj;
    }

    @AfterThrowing(value="execution(* com.jw.spring4.demo1.f_aspectsjaop.UserService6.throwMethod(..))", throwing = "selfE")
    public void afterThrowing(Throwable selfE){
        System.out.println("异常捕获--------------"+selfE.getMessage());
    }

    //最终通知,不管方法有没错都会执行，类似finally
    @After("execution(* com.jw.spring4.demo1.f_aspectsjaop.UserService6.*(..))")
    public void finallyMethod(){
        System.out.println("最终通知--------------");
    }



}
