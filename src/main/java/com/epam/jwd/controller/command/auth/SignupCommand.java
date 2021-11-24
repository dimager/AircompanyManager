package com.epam.jwd.controller.command.auth;

import com.epam.jwd.controller.api.JSPParameters;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.impl.AuthenticationService;
import com.epam.jwd.service.impl.RegistrationService;
import com.epam.jwd.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignupCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignupCommand.class);
    public static final String SIGNUP_JSP = "/WEB-INF/jsp/signup.jsp";
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
        UserDTO userDTO = new UserDTO();
        UserValidator userValidator = new UserValidator();
        RegistrationService registrationService = new RegistrationService();
        String password = requestContext.getParamFromJSP(JSPParameters.JSP_PASSWORD_INPUT_FILED_NAME).trim();
        String passwordRepeat = requestContext.getParamFromJSP(JSPParameters.JSP_PASSWORD_REPEAT_INPUT_FILED_NAME).trim();
        userDTO.setUsername(requestContext.getParamFromJSP(JSPParameters.JSP_USERNAME_INPUT_FILED_NAME));
        userDTO.setFirstName(requestContext.getParamFromJSP(JSPParameters.JSP_FIRSTNAME_INPUT_FILED_NAME));
        userDTO.setLastName(requestContext.getParamFromJSP(JSPParameters.JSP_LASTNAME_INPUT_FILED_NAME));
        userDTO.setEmail(requestContext.getParamFromJSP(JSPParameters.JSP_EMAIL_INPUT_FILED_NAME));
        userDTO.setPassword(password);
        boolean errors = false;

        try {
            if (AuthenticationService.usernameInUse(userDTO)) {
                errors = true;
                requestContext.addAttributeToJSP(JSPParameters.JSP_USERNAME_IS_USED_ATTRIBUTE_NAME, true);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }


        try {
            if (AuthenticationService.emailInUse(userDTO)) {
                errors = true;
                requestContext.addAttributeToJSP(JSPParameters.JSP_EMAIL_IS_USED_STATE_ATTRIBUTE_NAME, true);

            }
        } catch (DAOException e) {
            e.printStackTrace();
        }


        try {
            if (!UserValidator.newUserPasswordValidate(password, passwordRepeat)) {
                requestContext.addAttributeToJSP(JSPParameters.JSP_PASSWORD_MISMATCH_ATTRIBUTE_NAME, true);
                errors = true;
            }
        } catch (ValidatorException e) {
            e.printStackTrace();
        }

        try {
            if (!errors && registrationService.registerNewUser(userDTO)) {
                requestContext.addAttributeToJSP(JSPParameters.USER_REGISTER_STATE_ATTRIBUTE_NAME, true);
            }
        } catch (DAOException e) {
            fillForm(userDTO, requestContext);
            e.printStackTrace();
        }

        return SIGNUP_COMMAND_CONTEXT;
    }

    private void fillForm(UserDTO userDTO, RequestContext requestContext) {
        requestContext.addAttributeToJSP(JSPParameters.JSP_USERNAME_INPUT_FILED_NAME, userDTO.getUsername());
        requestContext.addAttributeToJSP(JSPParameters.JSP_FIRSTNAME_INPUT_FILED_NAME, userDTO.getFirstName());
        requestContext.addAttributeToJSP(JSPParameters.JSP_LASTNAME_INPUT_FILED_NAME, userDTO.getLastName());
        requestContext.addAttributeToJSP(JSPParameters.JSP_EMAIL_INPUT_FILED_NAME, userDTO.getEmail());
    }
}
