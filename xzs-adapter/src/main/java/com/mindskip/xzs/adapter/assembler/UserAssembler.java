package com.mindskip.xzs.adapter.assembler;

import com.mindskip.xzs.adapter.dto.admin.user.UserResponse;
import com.mindskip.xzs.domain.aggregate.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserAssembler {
    UserAssembler INSTANCE = Mappers.getMapper(UserAssembler.class);

    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "userLevel", ignore = true)
    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);
}
