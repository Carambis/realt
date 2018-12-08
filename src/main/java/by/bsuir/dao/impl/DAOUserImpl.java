package by.bsuir.dao.impl;

import by.bsuir.entity.AccessType;
import by.bsuir.entity.User;
import by.bsuir.dao.DAOUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUserImpl implements DAOUser {
    private Connection connection;
    private final static String SELECT_STRING = "SELECT access_type FROM user WHERE login = ? and password = ?";
    private final static String SELECT_ALL_USER = "SELECT * FROM user";
    private final static String COLUMN_USER_TYPE = "access_type";
    private final static String INSERT_USER = "INSERT INTO user (login,password,access_type) VALUES (?,?,?)";
    private final static String EDIT_USER = "UPDATE user SET password = ?, access_type = ? WHERE login = ?";
    private final static String REMOVE_USER = "DELETE FROM user WHERE login = ?";

    public DAOUserImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User authorisation(User user) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_STRING);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setAccessType(AccessType.valueOf(resultSet.getString(COLUMN_USER_TYPE)));
            }
        } catch (SQLException e) {
            user.setAccessType(null);
            System.err.println(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        }
        return user;
    }

    @Override
    public List<User> selectUser() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        List<User> userList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setAccessType(AccessType.valueOf(resultSet.getString(COLUMN_USER_TYPE)));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return userList;
    }

    @Override
    public boolean addUser(User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            if (user.getAccessType() != null) {
                preparedStatement.setString(3, user.getAccessType().toString());
            } else {
                preparedStatement.setString(3, AccessType.USER.toString());
            }
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    @Override
    public boolean editUser(User user) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(EDIT_USER);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getAccessType().toString());
            preparedStatement.setString(3, user.getLogin());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    @Override
    public boolean removeUser(User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(REMOVE_USER);
            preparedStatement.setString(1, user.getLogin());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }
}
