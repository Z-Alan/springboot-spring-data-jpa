package com.example.springdatajpa.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 分类 一级分类，二级分类
 *
 * @author zhouqiang
 */
@Entity
@Data
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "catalog_name", nullable = false)
    private String catalogName;

    @Column(name = "p_id")
    private Long pId;

    public Catalog() {
    }

    public Catalog(String catalogName, Long pId) {
        this.catalogName = catalogName;
        this.pId = pId;
    }

    public Catalog(String catalogName) {
        this.catalogName = catalogName;
    }
}

