package com.mindskip.xzs.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageUtil {

    public static <T> Page<T> toPage(Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) pageIndex = 1;
        if (pageSize == null) pageSize = 10;
        return new Page<>(pageIndex, pageSize);
    }

    public static <T, R> IPage<R> convert(IPage<T> page, Function<T, R> converter) {
        IPage<R> result = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<R> records = page.getRecords().stream().map(converter).collect(Collectors.toList());
        result.setRecords(records);
        return result;
    }
}
