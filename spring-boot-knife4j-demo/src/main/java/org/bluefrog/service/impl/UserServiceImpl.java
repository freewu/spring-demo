package org.bluefrog.service.impl;

import org.bluefrog.domain.User;
import org.bluefrog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private Map<Integer,User> list = new HashMap<>();

    @PostConstruct
    public void init() {
        System.out.println("UserServiceImpl 初始化");
        list.put(1,new User(1, "aaa", "password1", LocalDateTime.now()));
        list.put(2,new User(2, "bbb", "password2", LocalDateTime.now()));
    }

    @PreDestroy
    public void destory() {
        System.out.println("UserServiceImpl 销毁啦");
    }

    @Override
    public User getOne(int id) {
        return list.get(id);
    }

    @Override
    public List<User> getList() {
        return (List<User>) list.values();
    }
}
