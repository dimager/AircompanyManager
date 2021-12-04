package com.epam.jwd.controller.command;

import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.entity.Role;

import java.util.List;
import java.util.Objects;

public interface Command {
    static Command of(String name) {
        return Commands.getCommand(name);
    }

    static boolean executePermission(String name, Role role) {
        List<Role> roles = Commands.getPermitRoles(name);
        return Objects.nonNull(roles) && (roles.isEmpty() || roles.contains(role));
    }
    ResponseContext execute(RequestContext requestContext);

}
