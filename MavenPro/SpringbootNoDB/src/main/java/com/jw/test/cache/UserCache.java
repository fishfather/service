package com.jw.test.cache;

import com.jw.test.vo.UserDetails;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserCache {

    @Cacheable(value = "user_details", key = "#uid", unless="#result == null")
    public UserDetails getUserDetailsByUid(int uid){
        System.out.println(" Cacheable enter..."+uid);
        UserDetails userDetails = new UserDetails(uid, "User"+uid);
        if(uid == 5)
            return null;
        return userDetails;
    }

    @CachePut(value = "user_details", key = "#user.id")
    public UserDetails updateUserInfo(UserDetails user){
        System.out.println(" CachePut enter..."+user.getId());
        UserDetails userDetails = new UserDetails(user.getId(), "User New "+user.getId());
        if(user.getId() == 3)
            return null;
        return userDetails;
    }

    @CacheEvict(value = "user_details", key = "#uid")
    public int delUserInfoById(int uid){
        System.out.println(" CacheEvict enter..."+uid);
        return 1;
    }
}
