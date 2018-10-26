package com.example.springdatajpa.repository.dto;

import com.example.springdatajpa.domain.dto.ArticleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhou
 */
public interface ArticleDTORepository extends JpaRepository<ArticleDTO, Long>, JpaSpecificationExecutor<ArticleDTO> {
}
