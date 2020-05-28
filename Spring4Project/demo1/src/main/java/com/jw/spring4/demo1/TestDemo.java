package com.jw.spring4.demo1;

import com.jw.spring4.demo1.d_javaconfig.UserService4;
import com.jw.spring4.demo1.e_beanscanbyjavaconfig.UserService5;
import com.jw.spring4.demo1.f_aspectsjaop.UserService6;
import com.jw.spring4.demo1.g_aspectsjaoppointcut.UserService7;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemo {
    public static void main(String[] args) {
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
//        1. use bean id to get userService
//        UserService us =  (UserService) appContext.getBean("userService");
//        2 user class to get userService
//        UserService us =  (UserService) appContext.getBean(UserService.class);
//        us.get();
//
//        UserService2 us2 =  (UserService2) appContext.getBean(UserService2.class);
//        us2.get();

//        OrderService3 os3 = appContext.getBean(OrderService3.class);
//        os3.get();

        UserService4 us4 =  appContext.getBean(UserService4.class);
        us4.get();
        System.out.println(us4 == appContext.getBean(UserService4.class));

//        UserService5 us5 =  appContext.getBean(UserService5.class);
//        us5.get();
//        System.out.println(us5 == appContext.getBean(UserService5.class));

//        UserService6 us6 =  appContext.getBean(UserService6.class);
//        us6.get();
//        us6.update();
//        us6.throwMethod();

//        UserService7 us7 =  appContext.getBean(UserService7.class);
//        us7.get();
//        us7.update();
    }
}
