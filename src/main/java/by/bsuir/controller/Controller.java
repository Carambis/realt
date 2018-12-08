package by.bsuir.controller;

import by.bsuir.entity.AccessType;
import by.bsuir.entity.CommandName;
import by.bsuir.entity.User;
import by.bsuir.entity.realty.District;
import by.bsuir.entity.realty.Realty;
import by.bsuir.entity.realty.YearBuild;
import by.bsuir.service.RealtyService;
import by.bsuir.service.ServiceFactory;
import by.bsuir.service.UserService;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {
    private static UserService userService = ServiceFactory.getInstance().getUserService();
    private static RealtyService realtyService = ServiceFactory.getInstance().getRealtyService();

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Server server = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            System.err.println(e);
        }
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("Start Connection:" + socket.getLocalAddress() + " " + socket.getLocalPort());
                server = new Server(socket);
                workServer(server);
            } catch (Exception e) {
                System.err.println(e);
            }  finally {
                try {
                    System.out.println("End Connection");
                    socket.close();
                    server.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }

    private static void workServer(Server server) {
        User user;
        while (true) {
            CommandName.MainMenuTag mainMenuTag = server.recvRequest();
            System.out.println("Command: " + mainMenuTag.toString());
            switch (mainMenuTag) {
                case CANCEL:
                    return;
                case REGISTRATION:
                    user = server.recvRequest();
                    boolean arg = userService.registration(user);
                    server.sendRequest(arg);
                    break;
                case AUTHORISATION:
                    user = server.recvRequest();
                    user = userService.authorisation(user);
                    server.sendRequest(user);
                    if (user.getAccessType() == null) {
                        break;
                    }
                    if (user.getAccessType().equals(AccessType.ADMIN)) {
                        adminMenu(server);
                    } else {
                        userMenu(server);
                    }
                    break;
            }
        }
    }

    private static void adminMenu(Server server) {
        while (true) {
            CommandName.MenuAdmin tag = server.recvRequest();
            System.out.println("Command: " + tag.toString());
            switch (tag) {
                case MANAGER_USER:
                    managerUser(server);
                    break;
                case MANAGER_REALT:
                    managerRealty(server);
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private static void managerUser(Server server) {
        User user = null;
        server.sendRequest(userService.selectUser());
        while (true) {
            CommandName.MenuManagerUser managerUser = server.recvRequest();
            System.out.println("Command: " + managerUser.toString());
            switch (managerUser) {
                case ADD:
                    user = server.recvRequest();
                    boolean arg = userService.addUser(user);
                    server.sendRequest(arg);
                    if (arg == true) {
                        server.sendRequest(userService.selectUser());
                    }
                    break;
                case EDIT:
                    user = server.recvRequest();
                    arg = userService.editUser(user);
                    server.sendRequest(arg);
                    if (arg == true) {
                        server.sendRequest(userService.selectUser());
                    }
                    break;
                case REMOVE:
                    user = server.recvRequest();
                    arg = userService.removeUser(user);
                    server.sendRequest(arg);
                    if (arg == true) {
                        server.sendRequest(userService.selectUser());
                    }
                    break;
                case REFRESH_DATA:
                    server.sendRequest(userService.selectUser());
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private static void managerRealty(Server server) {
        boolean arg;
        District district;
        YearBuild yearBuild;
        server.sendRequest(realtyService.selectDistrict());
        server.sendRequest(realtyService.selectYearBuild());
        while (true) {
            CommandName.MenuManagerRealty menuManagerRealty = server.recvRequest();
            System.out.println("Command: " + menuManagerRealty.toString());
            switch (menuManagerRealty) {
                case ADD_DISTRICT:
                    district = server.recvRequest();
                    arg = realtyService.addDistrict(district);
                    server.sendRequest(arg);
                    if (arg == true) {
                        server.sendRequest(realtyService.selectDistrict());
                    }
                    break;
                case EDIT_DISTRICT:
                    district = server.recvRequest();
                    arg = realtyService.editDistrict(district);
                    server.sendRequest(arg);
                    if (arg == true) {
                        server.sendRequest(realtyService.selectDistrict());
                    }
                    break;
                case DELETE_DISTRICT:
                    district = server.recvRequest();
                    arg = realtyService.deleteDistrict(district);
                    server.sendRequest(arg);
                    if (arg == true) {
                        server.sendRequest(realtyService.selectDistrict());
                    }
                    break;
                case ADD_YEAR_BUILD:
                    yearBuild = server.recvRequest();
                    arg = realtyService.yearBuildAdd(yearBuild);
                    server.sendRequest(arg);
                    if (arg == true) {
                        server.sendRequest(realtyService.selectYearBuild());
                    }
                    break;
                case EDIT_YEAR_BUILD:
                    yearBuild = server.recvRequest();
                    arg = realtyService.yearBuildEdit(yearBuild);
                    server.sendRequest(arg);
                    if (arg == true) {
                        server.sendRequest(realtyService.selectYearBuild());
                    }
                    break;
                case DELETE_YEAR_BUILD:
                    yearBuild = server.recvRequest();
                    arg = realtyService.yearBuildDelete(yearBuild);
                    server.sendRequest(arg);
                    if (arg == true) {
                        server.sendRequest(realtyService.selectYearBuild());
                    }
                    break;
                case REFRESH_DATA_DISTRICT:
                    server.sendRequest(realtyService.selectDistrict());
                    break;
                case REFRESH_DATA_YEAR:
                    server.sendRequest(realtyService.selectYearBuild());
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private static void userMenu(Server server) {
        Realty realty = null;
        server.sendRequest(realtyService.selectRealty());
        server.sendRequest(realtyService.selectNameDistrict());
        server.sendRequest(realtyService.selectNameYearBuild());
        while (true) {
            CommandName.UserMenu userMenu = server.recvRequest();
            System.out.println("Command: " + userMenu.toString());
            switch (userMenu) {
                case EXIT:
                    return;
                case CALCULATE:
                    realty = server.recvRequest();
                    server.sendRequest(realtyService.selectPrice(realty));
                    server.sendRequest(realtyService.selectCof(realty));
                    CommandName.UserMenu timeCommand = server.recvRequest();
                    if (timeCommand.equals(CommandName.UserMenu.STOP)) {
                        break;
                    }
                    realtyService.addRealty(realty);
                    server.sendRequest(realtyService.selectRealty());
                    break;
                case BUILD_DIAGRAM:
                    realty = server.recvRequest();
                    server.sendRequest(realtyService.selectPrice(realty));
                    server.sendRequest(realtyService.selectCof(realty));
                    break;
                case REFRESH_DATA:
                    server.sendRequest(realtyService.selectRealty());
                    break;
            }
        }
    }
}
