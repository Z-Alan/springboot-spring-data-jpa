package com.example.springdatajpa.service.article;

import com.example.springdatajpa.domain.Article;
import com.example.springdatajpa.domain.dto.ArticleDTO;

/**
 * 动态sql条件查询，分页，以及表关联查询 示例
 *
 * @author zhou
 */

public interface ArticleService {

    /**
     * example.
     * 仅查询单表数据
     * 单表 动态条件查询
     * @param condition  查询条件 id, userId
     */
    void singleDynamicConditionExample(Article condition);

    /**
     * example.
     * 查询文章和用户，查询实体类中关联的数据
     * 动态条件查询
     *
     * @param condition 查询条件 id,userId,title(精确)
     */
    void singleDynamicConditionExample(ArticleDTO condition);

    /**
     * example.
     *
     * 查询文章和用户
     * 动态条件 有模糊条件
     * @param condition 查询条件 id,userId, title(模糊)
     */
    void singleDynamicConditionLikeExample(ArticleDTO condition);
    /**
     * example.
     * 关联查询 内连接(inner join ) 固定条件查询
     * @param condition  userId
     * @author zhou
     */
    void innerJoinExample(ArticleDTO condition);

    /**
     * example.
     * 内连接关联查询 动态条件查询
     * @param condition 条件
     */
    void innerJoinDynamicConditionExample(ArticleDTO condition);


}
