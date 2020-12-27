package br.com.utfpr.libraryfive.util;

import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class SessionUtils {

    @Autowired
    UserService userService;

    public UserModel getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        return userService.findByEmail(currentUsername);
    }

    public String getBaseUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    public void createSessionErrorMessage(HttpServletRequest request, String attribute, String errorMessage) {
        request.getSession().setAttribute(attribute, errorMessage);
    }

    public void removeSessionAttribute(HttpSession httpSession, String attribute) {
        httpSession.removeAttribute(attribute);
    }
}
