package com.mindskip.xzs.adapter.dto.common;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class PageRequest {
    @Min(1)
    private Integer pageIndex = 1;

    @Min(1)
    @Max(500)
    private Integer pageSize = 10;

    public Integer getPageIndex() { return pageIndex; }
    public void setPageIndex(Integer pageIndex) { this.pageIndex = pageIndex; }
    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
}
