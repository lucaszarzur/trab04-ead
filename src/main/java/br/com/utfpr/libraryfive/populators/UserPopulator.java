package br.com.utfpr.libraryfive.populators;

import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.service.UserService;
import br.com.utfpr.libraryfive.util.DateUtils;
import br.com.utfpr.libraryfive.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserPopulator {

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private FormatUtils formatUtils;

    @Autowired
    private UserService userService;

    public UserModel populate(HttpServletRequest request, Boolean isNewUser) {
        UserModel user = new UserModel();

        if (isNewUser) {
            user.setId(formatUtils.getIntegerValue(request.getParameter("id")));
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(new BCryptPasswordEncoder().encode(request.getParameter("password")));
            user.setUserType(request.getParameter("userType").equals("ALUNO") ? UserModel.UserType.ALUNO : UserModel.UserType.SERVIDOR);
            user.setStreet(request.getParameter("street"));
            user.setStreetNumber(formatUtils.getIntegerValue(request.getParameter("streetNumber")));
            user.setAdditionalAddress(request.getParameter("additionalAddress"));
            user.setNeighborhood(request.getParameter("neighborhood"));
            user.setCity(request.getParameter("city"));
            user.setState(request.getParameter("state"));
            user.setBirthDate(dateUtils.convertDate(request.getParameter("birthDate")));
            user.setAdmin(request.getParameter("admin").equals("SIM") ? true : false);
            user.setUserStatus(request.getParameter("userStatus").equals("ATIVO") ? UserModel.UserStatus.ATIVO : UserModel.UserStatus.INATIVO);

        } else {
            user = userService.findById(formatUtils.getIntegerValue(request.getParameter("userToEditId")));

            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setUserType(request.getParameter("userType").equals("ALUNO") ? UserModel.UserType.ALUNO : UserModel.UserType.SERVIDOR);
            user.setAdmin(request.getParameter("admin").equals("SIM") ? true : false);
            user.setUserStatus(request.getParameter("userStatus").equals("ATIVO") ? UserModel.UserStatus.ATIVO : UserModel.UserStatus.INATIVO);
        }
        return user;
    }
}
