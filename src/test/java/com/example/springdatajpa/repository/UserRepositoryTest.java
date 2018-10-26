package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void insertTestData(){
        /*
        插入操作，保存数据
         */
        User user = new User();
        user.setName("AAA");
        user.setAge( 10);
        userRepository.save(user);
    }

    /**
     * 测试数据回滚 加上 @Transactional事务注解 @Rollback 注解默认为true，数据回滚，false操作不回滚
     */
    @Test
    @Transactional
    @Rollback()
    public void updateUserWithNameById(){
        /*
        主键存在数据，更新数据，主键不存在数据插入
         */
        User newUser = new User();
        newUser.setAge(400);
        newUser.setName("JJJ");
        newUser.setId(10L);
        userRepository.save(newUser);
    }

    @Test
    public void findByNameTest() {
        User user = userRepository.findByName("JJJ");
        assertThat(user.getAge(),is(100));
    }

    @Test
    public void findByNameAndAgeTest() {
        User user = userRepository.findByNameAndAge("JJJ", 100);
        assertThat(user.getAge(),is(100));
    }

    @Test
    public void findUserTest() {
        User user = userRepository.findUser("HHH");
        assertThat(user.getAge(),is(90));
    }
}