package web.service;

import web.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getUsersList();
    public void addUser(User user);
    public void removeUser(Long id);
    public User findUserById(Long id);
    public void updateUser(User user);
}
