package com.mindskip.xzs.domain.gateway;

import com.mindskip.xzs.domain.aggregate.message.TextContent;

public interface TextContentGateway {
    TextContent findById(Integer id);
    void save(TextContent textContent);
    void update(TextContent textContent);
}
