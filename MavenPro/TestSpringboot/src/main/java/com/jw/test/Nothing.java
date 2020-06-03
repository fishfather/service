package com.jw.test;

import com.jw.test.service.PrototypeService;
import com.jw.test.util.ApplicationContextUtil;

/**
 * Just to test if non-ioc class can access spring ioc beans
 * from ApplicationContextUtil
 */
public class Nothing {
    public PrototypeService get(){
        return ApplicationContextUtil.getBean(PrototypeService.class);
    }
}
