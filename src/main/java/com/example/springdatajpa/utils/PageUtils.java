package com.example.springdatajpa.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 分页工具
 *
 * @author zhouqiang
 */
public class PageUtils {
    /**
     * 将MySQL的limit的offset、limit参数转化为Pageable的page、size,同时加入排序字段properties
     *
     * @param limit      个数
     * @param offset     偏移量
     * @param properties 单字段排序
     * @return 分页和排序规则对象
     */
    public static Pageable getMySQLPageable(Integer limit, Integer offset,
                                 String properties) {
        // 第几页
        int page = offset / limit;
        int size = limit;
        Sort sort = new Sort(Sort.Direction.DESC, properties);
        return PageRequest.of(page, size, sort);
    }
}
