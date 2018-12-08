package by.bsuir.entity;

import java.io.Serializable;

public final class CommandName implements Serializable{
    public enum MainMenuTag {
        CANCEL, AUTHORISATION, REGISTRATION
    }

    public enum MenuAdmin {
        MANAGER_USER, MANAGER_REALT, EXIT
    }

    public enum MenuManagerUser {
        ADD, REMOVE, EDIT, REFRESH_DATA, EXIT
    }

    public enum MenuManagerRealty {
        ADD_DISTRICT, DELETE_DISTRICT, EDIT_DISTRICT, ADD_YEAR_BUILD, DELETE_YEAR_BUILD, EDIT_YEAR_BUILD,
        REFRESH_DATA_DISTRICT,  EXIT , REFRESH_DATA_YEAR
    }

    public enum UserMenu {
        CALCULATE, EXIT,BUILD_DIAGRAM,REFRESH_DATA, STOP,CONTINUE
    }
}
