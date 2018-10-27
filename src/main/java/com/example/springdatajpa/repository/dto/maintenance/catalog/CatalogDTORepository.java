package com.example.springdatajpa.repository.dto.maintenance.catalog;

import com.example.springdatajpa.domain.dto.maintenance.CatalogDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 分类以及子分类维护(maintenance)接口
 * @author  zhou
 */
public interface CatalogDTORepository extends JpaRepository<CatalogDTO,Long>,JpaSpecificationExecutor<CatalogDTO> {
}
