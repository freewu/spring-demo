package org.bluefrog.controller;

import org.bluefrog.mapper.BlogMapper;
import org.bluefrog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogMapper blogDAO;

    @PostMapping("")
    public Object save(Blog blog) {
        if (blog == null || StringUtils.hasText(blog.getTitle())) {
            return "标题不能为空";
        } else if (StringUtils.hasText(blog.getContent())) {
            return "正文不能为空";
        } else if (blog.getUserId() == null) {
            return "请设置 userId";
        }

        blog.setUpdateTime(LocalDateTime.now());
        if (blog.getId() == null) {
            return blogDAO.insert(blog);
        } else {
            return blogDAO.update(blog);
        }
    }

    @GetMapping("list")
    public Object list(Integer currentIndex, Integer limit) {
        if (currentIndex == null || currentIndex < 0 || limit == null || limit <= 0) {
            currentIndex = 0;
            limit = 10;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("count", blogDAO.count());
        map.put("list", blogDAO.find(currentIndex, limit));
        return map;
    }

    @GetMapping(value = "{id}")
    public Object findById(@PathVariable Long id) {
        return blogDAO.findById(id);
    }

    @GetMapping(value = "userId/{userId}")
    public Object findByUserId(@PathVariable Long userId) {
        return blogDAO.findByUserId(userId);
    }
}
