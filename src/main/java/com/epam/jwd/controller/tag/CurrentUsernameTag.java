package com.epam.jwd.controller.tag;

import com.epam.jwd.service.dto.UserDTO;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class CurrentUsernameTag extends SimpleTagSupport {
    private static final Logger logger = LogManager.getLogger(CurrentUsernameTag.class);
    private UserDTO userDTO;

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public void doTag() throws IOException {
        logger.debug("doTag method");
        JspWriter jspWriter = getJspContext().getOut();
        jspWriter.println(userDTO.getUsername());
    }
}
