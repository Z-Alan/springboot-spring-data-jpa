package com.example.springdatajpa.service.article.condition;

import com.example.springdatajpa.domain.Article;
import com.example.springdatajpa.domain.dto.ArticleDTO;
import com.example.springdatajpa.domain.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

/**
 * 文章条件查询组装类
 *
 * @author zhou
 */
@Slf4j
public class ArticleConditionSpec {

    /**
     * 字段名
     */
    private static final String ID = "id";
    private static final String USER_ID = "userId";
    private static final String TITLE = "title";
    private static final String NAME = "name";

    /**
     * 表名
     */
    private static final String USER = "user";

    /**
     * specification.
     * id, userId 条件组装
     *
     * @return Specification
     * @author zhou
     */
    public static Specification<Article> getArticleSpec(Article condition) {
        return (Specification<Article>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate.getExpressions().add(
                    criteriaBuilder.equal(root.get(ID).as(Long.class), condition.getId())
            );
            /*
            查询条件 userId in (?)
             */
            if (!StringUtils.isEmpty(condition.getUserId())) {
                predicate.getExpressions().add(
                        criteriaBuilder.and(root.<String>get(USER_ID).in(condition.getCreateTime()))
                );
            }
            return predicate;
        };
    }

    /**
     * specification.
     * id,userId,title(精确查找) 条件组装
     *
     * @param condition 查询条件
     * @return specification
     */
    public static Specification<ArticleDTO> getArticleDTOSpec(ArticleDTO condition) {
        return (Specification<ArticleDTO>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate.getExpressions().add(
                    criteriaBuilder.equal(root.get(ID).as(Long.class), condition.getId())
            );
            /*
            查询条件 userId in (?)
             */
            if (!StringUtils.isEmpty(condition.getUserId())) {
                predicate.getExpressions().add(
                        criteriaBuilder.and(root.<String>get(USER_ID).in(condition.getCreateTime()))
                );
            }
            /*
            查询条件 title in (?)
             */
            if (!StringUtils.isEmpty(condition.getTitle())) {
                predicate.getExpressions().add(
                        criteriaBuilder.and(root.<String>get(TITLE).in(condition.getTitle()))
                );
            }
            return predicate;
        };
    }

    /**
     * specification.
     * id,userId,title(尾部模糊) 条件组装
     *
     * @param condition 查询条件
     * @return specification
     */
    public static Specification<ArticleDTO> getArticleDTOLikeSpec(ArticleDTO condition) {
        return (Specification<ArticleDTO>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate.getExpressions().add(
                    criteriaBuilder.equal(root.get(ID).as(Long.class), condition.getId())
            );
            /*
            查询条件 userId in (?)
             */
            if (!StringUtils.isEmpty(condition.getUserId())) {
                predicate.getExpressions().add(
                        criteriaBuilder.and(root.<String>get(USER_ID).in(condition.getCreateTime()))
                );
            }
            /*
            模糊查询 title like ?
             */
            if (!StringUtils.isEmpty(condition.getTitle())) {
                predicate.getExpressions().add(
                        // like：模糊匹配，跟SQL是一样的
                        criteriaBuilder.like(
                                // article表里面有个String类型的title
                                root.get(TITLE).as(String.class),
                                // 映射规则
                                condition.getTitle() + "%")
                );
            }
            return predicate;
        };
    }

    /**
     * specification.
     * 内连接关联查询 固定条件
     *
     * @param condition userId
     * @return specification
     */
    public static Specification<ArticleDTO> getArticleDTOInnerJoinUserDTOSpec(ArticleDTO condition) {
        return (Specification<ArticleDTO>) (root, criteriaQuery, criteriaBuilder) -> {
            // 声明并创建ArticleDTO的CriteriaQuery对象
            CriteriaQuery<ArticleDTO> query = criteriaBuilder.createQuery(ArticleDTO.class);
            // 连接的时候，要以声明的根查询对象（这里是root，也可以自己创建）进行join
            // Join<Z,X>;是Join生成的对象，这里的Z是被连接的对象，X是目标对象，
            // 连接的属性字段是被连接的对象在目标对象的属性，这里是我们在ArticleDTO内声明的user
            // join的第二个参数是可选的，默认是JoinType.INNER(内连接 inner join)，也可以是JoinType.LEFT（左外连接 left join）
            Join<UserDTO, ArticleDTO> articleDTOJoin = root.join(USER, JoinType.INNER);
            // 用CriteriaQuery对象拼接查询条件，这里只增加了一个查询条件，userId = 1
            query.select(articleDTOJoin).where(criteriaBuilder.equal(root.get(USER_ID), condition.getUserId()));
            Predicate p1;
            // 通过getRestriction获得Predicate对象
            p1 = query.getRestriction();
            return p1;
        };
    }

    /**
     * specification.
     * 查询文章 ArticleDTO
     * 关联用户表 userDTO
     * 动态条件查询 id , userId, title
     *
     * @param condition 查询条件
     */
    public static Specification<ArticleDTO> getArticleDTOInnerJoinUserDTODynamicSpec(ArticleDTO condition) {
        return (Specification<ArticleDTO>) (root, criteriaQuery, criteriaBuilder) -> {
            // Root<X> root 根查询，默认与声明相同
            // join 表
            Join<UserDTO, ArticleDTO> articleDTOJoin = root.join(USER, JoinType.INNER);
            /*
            构建 where 子句
             */
            Predicate predicate = criteriaBuilder.conjunction();
            predicate.getExpressions().add(
                    criteriaBuilder.equal(articleDTOJoin.get(ID).as(Long.class), condition.getId())
            );
            /*
            查询条件 userId in (?)
             */
            if (!StringUtils.isEmpty(condition.getUserId())) {
                predicate.getExpressions().add(
                        criteriaBuilder.and(articleDTOJoin.<String>get(USER_ID).in(condition.getCreateTime()))
                );
            }
            /*
            查询条件 title in (?)
             */
            if (!StringUtils.isEmpty(condition.getTitle())) {
                predicate.getExpressions().add(
                        criteriaBuilder.and(articleDTOJoin.<String>get(TITLE).in(condition.getTitle()))
                );
            }
            /*
            查询关联表条件 name = ?
             */
            if (!StringUtils.isEmpty(condition.getUser()) && !StringUtils.isEmpty(condition.getUser().getName())) {
                predicate.getExpressions()
                        .add(criteriaBuilder
                                .equal(articleDTOJoin.<String>get(NAME).as(String.class),condition.getUser().getName())
                );
            }
            return predicate;
        };
    }
}
