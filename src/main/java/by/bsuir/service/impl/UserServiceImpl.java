package by.bsuir.service.impl;

import by.bsuir.dao.DAOFactory;
import by.bsuir.dao.DAOUser;
import by.bsuir.entity.User;
import by.bsuir.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    DAOUser daoUser = DAOFactory.getInstance().getDaoUser();

    @Override
    public User authorisation(User user) {
        return daoUser.authorisation(user);
    }

    @Override
    public boolean registration(User user) {
        return daoUser.addUser(user);
    }

    @Override
    public List<User> selectUser() {
        return daoUser.selectUser();
    }

    @Override
    public boolean addUser(User user) {
        return daoUser.addUser(user);
    }

    @Override
    public boolean editUser(User user) {
        return daoUser.editUser(user);
    }

    @Override
    public boolean removeUser(User user) {
        return daoUser.removeUser(user);
    }
}
