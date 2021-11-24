package com.epam.jwd.controller.api;

public interface JSPParameters {
     String JSP_USERNAME_INPUT_FILED_NAME = "username";
     String JSP_FIRSTNAME_INPUT_FILED_NAME = "firstname";
     String JSP_LASTNAME_INPUT_FILED_NAME = "lastname";
     String JSP_EMAIL_INPUT_FILED_NAME = "email";
     String JSP_PASSWORD_INPUT_FILED_NAME = "password";
     String JSP_PASSWORD_REPEAT_INPUT_FILED_NAME = "password-repeat";

     String JSP_USERNAME_IS_USED_ATTRIBUTE_NAME = "usernameInUse";
     String JSP_PASSWORD_MISMATCH_ATTRIBUTE_NAME = "passwordState";
     String SESSION_SUCCESSFULLY_LOGIN_STATE_ATTRIBUTE_NAME = "loginState";
     String JSP_INCORRECT_LOGIN_DATA_ATTRIBUTE_NAME = "loginInputIsIncorrect";
     String JSP_EMAIL_IS_USED_STATE_ATTRIBUTE_NAME = "emailInUse";
     String USER_REGISTER_STATE_ATTRIBUTE_NAME = "userIsRegistered";
     String SESSION_USERNAME_ATTRIBUTE = "sessionUsername";
     String SESSION_USERID_ATTRIBUTE = "sessionUserId";
     String SESSION_USER_ROLE_ATTRIBUTE = "sessionUserRole";
     String SESSION_USER_LOGIN_STATE_ATTRIBUTE_NAME = "userIsLoggedIn";
}
