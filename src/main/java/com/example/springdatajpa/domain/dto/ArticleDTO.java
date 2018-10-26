package com.example.springdatajpa.domain.dto;

import com.example.springdatajpa.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 文章实体类
 *
 * @author zhou
 */
@Entity(name = "article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name="create_time",nullable = false)
    private String createTime;

    @Column(name="user_id",nullable = false)
    private Long userId;

    /*
    实体映射重复列必须设置：insertable = false,updatable = false
     */
    /**
     *
     */
    @OneToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private User user;

}
