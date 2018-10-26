package com.example.springdatajpa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 用户DTO
 *
 * @author zhou
 */
@Entity(name = "user" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    /**
     * 一对多， 一个用户对应多个文章，关联的字段是文章表里的userId
     */
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<ArticleDTO> articles;

}
