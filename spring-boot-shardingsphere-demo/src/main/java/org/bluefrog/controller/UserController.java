package org.bluefrog.controller;

import org.bluefrog.mapper.UserMapper;
import org.bluefrog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserMapper orderDAO;

    @PostMapping("")
    public Object save(@RequestBody User user) {
        System.out.println(user);
        if (user == null || !StringUtils.hasText(user.getName())) {
            return "name 不能为空";
        } else if (!StringUtils.hasText(user.getPassword())) {
            return "password 不能为空";
        }
        user.setCreateTime(LocalDateTime.now());
        return orderDAO.insert(user);
    }

    @GetMapping(value = "list")
    public Object list(Integer currentIndex, Integer limit) {
        if (currentIndex == null || currentIndex < 0 || limit == null || limit <= 0) {
            currentIndex = 0;
            limit = 10;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("count", orderDAO.count());
        map.put("list", orderDAO.find(currentIndex, limit));
        return map;
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Integer id) {
        return "id: " + id;
    }
}
