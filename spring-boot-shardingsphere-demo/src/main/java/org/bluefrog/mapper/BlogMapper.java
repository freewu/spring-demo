package org.bluefrog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bluefrog.domain.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    boolean insert(Blog blog);

    boolean update(Blog blog);

    List<Blog> find(@Param("currentIndex") Integer currentIndex, @Param("limit") Integer limit);

    int count();

    Blog findById(@Param("id") Long id);

    List<Blog> findByUserId(@Param("userId") Long userId);

    boolean deleteById(@Param("id") Long id);
}
