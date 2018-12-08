package by.bsuir.dao;

import by.bsuir.dao.impl.DAORealtyImpl;
import by.bsuir.dao.impl.DAOUserImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    private final static String PATH_TO_DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/object_realt";
    private final static String CONNECTION_LOGIN = "root";
    private final static String CONNECTION_PASSWORD = "root";
    private final static DAOFactory instance = new DAOFactory();
    private static Connection connection;
    private static DAOUser daoUser = new DAOUserImpl(connection);
    private static DAORealty daoRealty = new DAORealtyImpl(connection);

    private DAOFactory() {
        try {
            Class.forName(PATH_TO_DRIVER);
            connection = DriverManager.getConnection(URL, CONNECTION_LOGIN, CONNECTION_PASSWORD);
            System.out.println("Start Connection With Database");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public DAOUser getDaoUser() {
        return daoUser;
    }

    public static DAORealty getDaoRealty() {
        return daoRealty;
    }
}
