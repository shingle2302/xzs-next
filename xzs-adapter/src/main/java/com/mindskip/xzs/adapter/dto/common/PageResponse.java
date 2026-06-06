package com.mindskip.xzs.adapter.dto.common;

import java.util.List;

public class PageResponse<T> {
    private List<T> list;
    private Long total;
    private Integer pageIndex;
    private Integer pageSize;

    public static <T> PageResponse<T> of(List<T> list, Long total, Integer pageIndex, Integer pageSize) {
        PageResponse<T> r = new PageResponse<>();
        r.list = list; r.total = total; r.pageIndex = pageIndex; r.pageSize = pageSize;
        return r;
    }

    public List<T> getList() { return list; }
    public void setList(List<T> list) { this.list = list; }
    public Long getTotal() { return total; }
    public void setTotal(Long total) { this.total = total; }
    public Integer getPageIndex() { return pageIndex; }
    public void setPageIndex(Integer pageIndex) { this.pageIndex = pageIndex; }
    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
}
