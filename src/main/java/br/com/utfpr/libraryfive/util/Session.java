package br.com.utfpr.libraryfive.util;

import br.com.utfpr.libraryfive.model.UserModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class Session {

    UserModel currentUser = new UserModel();

    public void setCurrentUser(UserModel user) {
        this.currentUser = user;
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public String getBaseUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
