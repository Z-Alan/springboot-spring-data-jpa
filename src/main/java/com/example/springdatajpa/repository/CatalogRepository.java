package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhou
 */
public interface CatalogRepository extends JpaRepository<Catalog,Long> {
}
