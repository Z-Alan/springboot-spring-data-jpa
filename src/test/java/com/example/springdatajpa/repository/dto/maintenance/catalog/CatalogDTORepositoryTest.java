package com.example.springdatajpa.repository.dto.maintenance.catalog;

import com.example.springdatajpa.BaseTest;
import com.example.springdatajpa.domain.Article;
import com.example.springdatajpa.domain.Catalog;
import com.example.springdatajpa.domain.dto.maintenance.CatalogDTO;
import com.example.springdatajpa.repository.CatalogRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.rmi.runtime.Log;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
public class CatalogDTORepositoryTest extends BaseTest {

    @Autowired
    private CatalogDTORepository catalogDTORepository;
    @Autowired
    private CatalogRepository catalogRepository;


    @Test
    public void findAllTest() {
        List<CatalogDTO> catalogDTOS =  catalogDTORepository.findAll();
//        List<Catalog> catalogs = catalogRepository.findAll();
//        assertThat(catalogs.size(),is(3));
//        for (CatalogDTO c : catalogDTOS) {
//            log.info("数据 ----- [---- {} ----]", c.toString());
//        }
        System.out.println(catalogDTOS);
        assertThat(catalogDTOS.size(),is(9));
    }
}