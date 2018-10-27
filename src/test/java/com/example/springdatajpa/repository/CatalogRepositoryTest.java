package com.example.springdatajpa.repository;

import com.example.springdatajpa.BaseTest;
import com.example.springdatajpa.domain.Catalog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CatalogRepositoryTest extends BaseTest {

    @Autowired
    private CatalogRepository catalogRepository;
    @Test
    public void insertTest() {
        catalogRepository.save(new Catalog("一级分类1"));
        catalogRepository.save(new Catalog("一级分类2"));
        catalogRepository.save(new Catalog("一级分类3"));
        catalogRepository.save(new Catalog("1子分类"));
        catalogRepository.save(new Catalog("2子分类"));
        catalogRepository.save(new Catalog("2子分类"));
        catalogRepository.save(new Catalog("3子分类"));
        catalogRepository.save(new Catalog("3子分类"));
        catalogRepository.save(new Catalog("3子分类"));
    }
}