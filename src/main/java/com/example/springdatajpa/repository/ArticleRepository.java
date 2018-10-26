package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zhou
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>,JpaSpecificationExecutor<Article> {

}
