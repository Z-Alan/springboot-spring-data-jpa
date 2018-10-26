package com.example.springdatajpa.repository;

import com.example.springdatajpa.BaseTest;
import com.example.springdatajpa.domain.Article;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class ArticleRepositoryTest extends BaseTest {


    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void insertTestData() {
        /*
        插入操作，保存数据
         */
        Long number = 1L ;
        Long number2 = 2L ;
        Long number3 = 3L ;
        Long number4 = 4L ;
        Article article = new Article();
        article.setTitle("第"+number+"篇文章");
        article.setContent("第"+number+"篇文章内容");
        article.setCreateTime("2018-10-22 16:42:32");
        article.setUserId(number);
        articleRepository.save(article);
    }
}