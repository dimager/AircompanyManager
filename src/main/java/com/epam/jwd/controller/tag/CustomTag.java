package com.epam.jwd.controller.tag;
import com.epam.jwd.service.dto.UserDTO;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

public class CustomTag extends SimpleTagSupport {
    private UserDTO userDTO;

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter jspWriter = getJspContext().getOut();
        jspWriter.println(userDTO.getUsername());
    }
}
