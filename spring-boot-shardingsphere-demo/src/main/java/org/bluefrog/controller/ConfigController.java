package org.bluefrog.controller;

import org.bluefrog.dao.ConfigDAO;
import org.bluefrog.domain.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;


@RestController
@RequestMapping("config")
public class ConfigController {

    @Autowired
    private ConfigDAO configDAO;

    @PostMapping("")
    public Object save(Config config) {
        if (config == null || StringUtils.hasText(config.getRemark())) {
            return "remark 不能为空";
        }

        config.setCreateTime(LocalDateTime.now());
        config.setLastModifyTime(LocalDateTime.now());
        return configDAO.insert(config);
    }
}
