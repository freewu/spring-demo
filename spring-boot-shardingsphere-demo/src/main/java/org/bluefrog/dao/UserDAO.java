package org.bluefrog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bluefrog.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDAO {

    boolean insert(User user);

    int count();

    List<User> find(@Param("currentIndex") Integer currentIndex, @Param("limit") Integer limit);
}
