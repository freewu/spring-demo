package org.bluefrog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.bluefrog.domain.Config;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ConfigDAO {

    boolean insert(Config config);
}
