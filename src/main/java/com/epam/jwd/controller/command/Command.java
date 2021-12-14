package com.epam.jwd.controller.command;

import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.entity.Role;

import java.util.List;
import java.util.Objects;

public interface Command {
    /**
     * Allows getting command by name.
     * @param name command  name in string.
     * @return if command was founded by name return command, otherwise default command
     */
    static Command of(String name) {
        return Commands.getCommand(name);
    }

    /**
     * Allows getting command name.
     * @param name command  name in string.
     * @return if command was founded by name return command, otherwise default null
     */
    static Commands name(String name) {
        return Commands.getCommandName(name);
    }

    /**
     * Allows checking execute permission for command in order to user role
     * @param name command name
     * @param role user role
     * @return true - if user has execution permission, otherwise false
     */
    static boolean executePermission(String name, Role role) {
        List<Role> roles = Commands.getPermitRoles(name);
        if (Objects.isNull(roles)) {
            return true;
        } else {
            return  (roles.isEmpty() || roles.contains(role));
        }
    }

    /**
     * Method which is determined in each command of command pattern
     * @param requestContext request from the client
     * @return Response - processed by method body request
     */
    ResponseContext execute(RequestContext requestContext);

}
