package org.bluefrog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bluefrog.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    boolean insert(User user);

    int count();

    List<User> find(@Param("currentIndex") Integer currentIndex, @Param("limit") Integer limit);
}
