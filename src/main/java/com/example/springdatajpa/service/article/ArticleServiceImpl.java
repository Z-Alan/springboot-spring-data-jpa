package com.example.springdatajpa.service.article;

import com.example.springdatajpa.domain.Article;
import com.example.springdatajpa.domain.dto.ArticleDTO;
import com.example.springdatajpa.repository.ArticleRepository;
import com.example.springdatajpa.repository.dto.ArticleDTORepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author zhou
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDTORepository articleDTORepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void innerJoinExample(ArticleDTO condition) {
        // 根据查询结果，声明返回值对象，这里要查询用户的文章列表，所以声明返回对象为ArticleDTO
        Specification<ArticleDTO> spec = ArticleConditionSpec.getArticleDTOInnerJoinUserDTOSpec(condition);
        articleDTOResultPrint(spec);
    }

    @Override
    public void innerJoinDynamicConditionExample(ArticleDTO condition) {
        Specification<ArticleDTO> spec = ArticleConditionSpec.getArticleDTOInnerJoinUserDTODynamicSpec(condition);
        articleDTOResultPrint(spec);
    }

    @Override
    public void singleDynamicConditionExample(Article condition) {
        Specification<Article> spec = ArticleConditionSpec.getArticleSpec(condition);
        articleResultPrint(spec);
    }

    @Override
    public void singleDynamicConditionExample(ArticleDTO condition) {
        Specification<ArticleDTO> spec = ArticleConditionSpec.getArticleDTOSpec(condition);
        articleDTOResultPrint(spec);
    }

    @Override
    public void singleDynamicConditionLikeExample(ArticleDTO condition) {
        Specification<ArticleDTO> spec = ArticleConditionSpec.getArticleDTOLikeSpec(condition);
        articleDTOResultPrint(spec);
    }

    /**
     * 输出分页信息 测试用
     *
     * @param spec 复杂条件关联查询对象
     */
    private void articleDTOResultPrint(Specification<ArticleDTO> spec) {
        //分页查询
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        //查询的分页结果
        Page<ArticleDTO> page = articleDTORepository.findAll(spec, pageable);
        log.info("分页结果 ----- [---- {} ----]", page);
        log.info("分页结果统计 ----- [---- {} ----]", page.getTotalElements());
        log.info("分页结果页数 ----- [---- {} ----]", page.getTotalPages());
        for (ArticleDTO c : page.getContent()) {
            log.info("分页内容 ----- [---- {} ----]", c.toString());
        }
    }

    /**
     * 输出分页信息 测试用
     *
     * @param spec 复杂条件关联查询对象
     */
    private void articleResultPrint(Specification<Article> spec) {
        /* 注意
        Pageable pageable = new Pageable(page, size, sort)已经过时，
        可以使用Pageable pageable = PageRequest.of(page, size, sort)代替
        注意参数的类型，其中size要大于等于1，page则是从0开始算起。
         */
        //分页查询
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        //查询的分页结果
        Page<Article> page;
        page = articleRepository.findAll(spec, pageable);
        log.info("分页结果 ----- [---- {} ----]", page);
        log.info("分页结果统计 ----- [---- {} ----]", page.getTotalElements());
        log.info("分页结果页数 ----- [---- {} ----]", page.getTotalPages());
        for (Article c : page.getContent()) {
            log.info("分页内容 ----- [---- {} ----]", c.toString());
        }
    }
}
