package com.epam.jwd.controller.command;

import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;

public interface Command {

    ResponseContext execute(RequestContext requestContext);

    static Command of(String name) {
        return Commands.getCommand(name);
    }
}
