package com.example.springdatajpa.domain.dto.maintenance;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 分类以及子分类维护
 * @author zhou
 */
@Entity(name = "catalog")
@Data
public class CatalogDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "catalog_name", nullable = false)
    private String catalogName;

    @Column(name = "p_id")
    private Long pId;

    /**
     * 子分类
     */
    @OneToMany
    @JoinColumn(name = "p_id")
    private List<CatalogDTO> subCatalogDTOs;

    @Override
    public String toString() {
        return "CatalogDTO{" +
                "id=" + id +
                ", catalogName='" + catalogName + '\'' +
                ", pId=" + pId +
                ", subCatalogDTOs=" + subCatalogDTOs +
                '}';
    }
}
