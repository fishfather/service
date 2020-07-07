package com.jw.test.controller;

import com.jw.test.cache.UserCache;
import com.jw.test.vo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class TestController {

    @Autowired
    private UserCache cache;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("getuser")
    public String test1(int uid){
        return ""+cache.getUserDetailsByUid(uid);
    }

    @GetMapping("updateuser")
    public String test2(int uid){
        return ""+cache.updateUserInfo(new UserDetails(uid, ""));
    }

    @GetMapping("deleteuser")
    public String test3(int uid){
        return ""+cache.delUserInfoById(uid);
    }

    @GetMapping("cache")
    public String test4(){
        ConcurrentMapCacheManager cg = (ConcurrentMapCacheManager) cacheManager;
        System.out.println("Allow null:"+cg.isAllowNullValues());
        Collection<String> cacheNames = cacheManager.getCacheNames();
        System.out.println("cache list:"+cacheNames);
        Cache userdetails = cacheManager.getCache("user_details");
        ConcurrentMapCache cCache = (ConcurrentMapCache) userdetails;
        System.out.println("cache detail:"+cCache);
        return "null";
    }
}
