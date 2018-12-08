package by.bsuir.service;

import by.bsuir.entity.User;

import java.util.List;

public interface UserService {
    User authorisation(User user);
    boolean registration(User user);
    List<User> selectUser();
    boolean addUser(User user);
    boolean editUser(User user);
    boolean removeUser(User user);

}
