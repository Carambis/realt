package by.bsuir.service;

import by.bsuir.dao.DAOFactory;
import by.bsuir.service.impl.RealtyServiceImpl;
import by.bsuir.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();

    private final RealtyService realtyService = new RealtyServiceImpl();

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public RealtyService getRealtyService() {
        return realtyService;
    }
}
