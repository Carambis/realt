package by.bsuir.dao;

import by.bsuir.entity.User;

import java.util.List;

public interface DAOUser {
    User authorisation(User user);
    List<User> selectUser();
    boolean addUser(User user);
    boolean editUser(User user);
    boolean removeUser(User user);
}
