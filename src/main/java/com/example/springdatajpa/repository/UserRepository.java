package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author zhou
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 根据姓名查找用户
     * 
     * @param        name 姓名
     * @return       用户
     * @date         2018/10/25 11:19
     * @author       zhou
     */
    User findByName(String name);

    /**
     * 根据姓名和年龄查找用户
     * @param name 姓名
     * @param age 年龄
     * @return 用户
     * @author zhou
     */
    User findByNameAndAge(String name, Integer age);

    /**
     * 根据用户姓名查找用户完整信息
     *
     * @param name 姓名
     * @return user
     * @author zhou
     */
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

}
