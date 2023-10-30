package web.service;

import web.model.User;
import java.util.List;


public interface UserService {
    List<User> getUsersList();
    List<User> getUsersList(int count);
    User getUserById(long id);
    void addUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
}
