package com.jw.spring4.demo1.g_aspectsjaoppointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyPointcut {
    @Pointcut(value = "execution(* com.jw.spring4.demo1.g_aspectsjaoppointcut.UserService7.*(..))")
    public void userService7Pointcut(){    //方法名就是切入点名称
    }
}
