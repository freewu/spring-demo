package org.bluefrog.controller;

import org.bluefrog.mapper.ConfigMapper;
import org.bluefrog.domain.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("config")
public class ConfigController {

    @Autowired
    private ConfigMapper configDAO;

    @PostMapping("")
    public Object save(@RequestBody Config config) {
        if (config == null || !StringUtils.hasText(config.getRemark())) {
            return "remark 不能为空";
        }
        config.setLastModifyTime(LocalDateTime.now());
        if (config.getId() == null) {
            config.setCreateTime(LocalDateTime.now());
            return configDAO.insert(config);
        } else {
            return configDAO.update(config);
        }
    }

    @GetMapping(value = "list")
    public Object list(Integer currentIndex, Integer limit) {
        if (currentIndex == null || currentIndex < 0 || limit == null || limit <= 0) {
            currentIndex = 0;
            limit = 10;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("count", configDAO.count());
        map.put("list", configDAO.find(currentIndex, limit));
        return map;
    }
}
