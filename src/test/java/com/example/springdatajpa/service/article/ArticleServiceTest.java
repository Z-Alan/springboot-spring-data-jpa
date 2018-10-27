package com.example.springdatajpa.service.article;

import com.example.springdatajpa.BaseTest;
import com.example.springdatajpa.domain.Article;
import com.example.springdatajpa.domain.User;
import com.example.springdatajpa.domain.dto.ArticleDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleServiceTest extends BaseTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void innerJoinExampleTest() {
        ArticleDTO condition = new ArticleDTO();
        condition.setUserId(1L);
        articleService.innerJoinExample(condition);
    }

    @Test
    public void singleDynamicConditionExampleForArticleTest() {
        Article condition  = new Article();
        condition.setId(11L);
//        condition.setTitle("第1篇文章");
        articleService.singleDynamicConditionExample(condition);
    }

    @Test
    public void singleDynamicConditionLikeExampleTest() {
        ArticleDTO condition = new ArticleDTO();
        condition.setId(11L);
        condition.setTitle("第");
//        condition.setTitle("5");
        articleService.singleDynamicConditionLikeExample(condition);
    }

    @Test
    public void singleDynamicConditionExampleForArticleDTOTest() {
        ArticleDTO condition  = new ArticleDTO();
        condition.setId(11L);
//        condition.setTitle("第1篇文章");
        articleService.singleDynamicConditionExample(condition);
    }

    @Test
    public void innerJoinDynamicConditionExampleTest() {
        ArticleDTO condition = new ArticleDTO();
        condition.setId(1L);
        User user = new User();
        user.setName("AA");
//        condition.setUser(user);
        articleService.innerJoinDynamicConditionExample(condition);
    }
}