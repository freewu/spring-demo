package org.bluefrog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bluefrog.domain.Config;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ConfigMapper {

    boolean insert(Config config);

    List<Config> find(@Param("currentIndex") Integer currentIndex, @Param("limit") Integer limit);

    int count();

    boolean update(Config config);
}
