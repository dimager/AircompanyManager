package com.epam.jwd.controller.command.authentication;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.impl.SignupService;
import com.epam.jwd.service.impl.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SignupCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignupCommand.class);
    public static final String SIGNUP_JSP = "/controller?command=SHOW_SIGNUP_PAGE";
    private static final int RESULT_MESSAGE_CODE =105;
    private static final Command INSTANCE = new SignupCommand();


    private static final ResponseContext SIGNUP_COMMAND_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return SIGNUP_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        UserDTO userDTO = new UserDTO();
        List<Integer> errors = new ArrayList<>();
        ValidationService validationService = new ValidationService();
        SignupService signupService = new SignupService();

        String password = requestContext.getParamFromJSP(Attributes.JSP_PASSWORD_INPUT_FILED_NAME).trim();
        String passwordRepeat = requestContext.getParamFromJSP(Attributes.JSP_PASSWORD_REPEAT_INPUT_FILED_NAME).trim();
        userDTO.setUsername(requestContext.getParamFromJSP(Attributes.JSP_USERNAME_INPUT_FILED_NAME));
        userDTO.setFirstName(requestContext.getParamFromJSP(Attributes.JSP_FIRSTNAME_INPUT_FILED_NAME));
        userDTO.setLastName(requestContext.getParamFromJSP(Attributes.JSP_LASTNAME_INPUT_FILED_NAME));
        userDTO.setEmail(requestContext.getParamFromJSP(Attributes.JSP_EMAIL_INPUT_FILED_NAME));
        userDTO.setPassword(password);
        try {
            if (validationService.validateNewUser(userDTO,passwordRepeat,errors)) {
                signupService.saveNewUser(userDTO);
                requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE);

            }
            else {
                requestContext.addAttributeToJSP(Attributes.COMMAND_ERRORS_ATTRIBUTE, errors);
            }
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }
        return SIGNUP_COMMAND_CONTEXT;
    }

}
