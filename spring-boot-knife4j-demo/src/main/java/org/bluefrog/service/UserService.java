package org.bluefrog.service;

import org.bluefrog.domain.User;

import java.util.List;

public interface UserService {
    public User getOne(int id);
    public List<User> getList();
}
