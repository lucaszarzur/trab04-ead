package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.service.UserService;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.populator.UserPopulator;
import br.com.utfpr.libraryfive.util.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/user")
public class UserController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    Session session;

    @Autowired
    UserService userService;

    @Autowired
    UserPopulator userPopulator;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newUser(final HttpServletRequest request) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            UserModel user = userService.getUserByRegisterForm(request, true);

            if (user != null) {
                userService.createUser(user);

                return REDIRECT_TO_ADMIN_VIEW_USERS;
            }
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(final HttpServletRequest request) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            UserModel user = userService.getUserByRegisterForm(request, false);

            userService.editUser(user);

            return REDIRECT_TO_ADMIN_VIEW_USERS;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") final int id) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            UserModel user = userService.findById(id);
            userService.deleteUser(user);

            return REDIRECT_TO_ADMIN_VIEW_USERS;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/listAllUsers", method = RequestMethod.GET)
    public ModelAndView listAllUsers(@ModelAttribute("actualUserEmail") final String actualUserEmail,
                                     HttpServletRequest request, HttpServletResponse response,
                                     BindingResult result) {

        UserModel actualUser = userService.findByEmail(actualUserEmail);
        if (actualUser.getAdmin()) {

            List<UserModel> users = userService.listAllUsers();

            ModelAndView modelAndView = new ModelAndView("homeAdmin");
            modelAndView.addObject("users", users);
            modelAndView.addObject("user", actualUser);
            LOG.info("Users success retrieved!");

            return modelAndView;
        }
            return null;
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    public UserModel findById(final Integer id) {
        return userService.findById(id);
    }

    private ModelAndView newPage(String page, String object) {
        ModelAndView modelAndView = null;

        modelAndView = new ModelAndView(page);
        modelAndView.addObject(object, new UserModel());

        return modelAndView;
    }
}
