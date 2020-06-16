package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.service.UserService;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.util.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Transactional
public class LoginController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    Session session;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView showLogin() {

        ModelAndView modelAndView = new ModelAndView("login/login");
        modelAndView.addObject("user", new UserModel());

        return modelAndView;
    }

    // TODO - Refactor this with Spring Security
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("user") UserModel user,  ModelAndView modelAndView) {

        if (userService.doLogin(user.getEmail(), user.getPassword()) != null) {
            LOG.info("User " + user.getEmail() + " exists, redirecting to homepage...");

            UserModel userModel = userService.findByEmail(user.getEmail());

            // Without Spring Security, this is the best way I found to deal with current user
            session.setCurrentUser(userModel);

            modelAndView.addObject("userName", userModel.getName());
            modelAndView.addObject("email", userModel.getEmail());

            return REDIRECT_TO_HOMEPAGE;
        } else {
            LOG.info("User " + user.getEmail() + "doesn't exists, redirecting to login page...");

            return REDIRECT_TO_LOGIN;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {

        return REDIRECT_TO_LOGIN;
    }
}
