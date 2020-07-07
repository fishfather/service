package com.jw.test.repo;

import com.jw.test.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

//不需要加这个注解
//@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByName(String name);

    @Query(value = "select * from user",nativeQuery = true)
    Page<User> findPageUsers(Pageable pageable);

    @Query(value = "select * from user",nativeQuery = true)
    List<User> findAllUsers();


    @Transactional
    @Modifying
    @Query("update User set name = ?1 where id = ?2")   //JPQL
    int modifyById(String  userName, Integer id);
}
