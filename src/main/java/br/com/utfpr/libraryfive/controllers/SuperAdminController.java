package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.service.UserService;
import br.com.utfpr.libraryfive.util.FormatUtils;
import br.com.utfpr.libraryfive.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/superAdmin")
public class SuperAdminController {

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    UserService userService;

    @Autowired
    private FormatUtils formatUtils;

    @RequestMapping(value = "/manageUser", method = RequestMethod.GET)
    public ModelAndView showSuperAdminManageUser(@ModelAttribute("user") UserModel userModel, ModelAndView modelAndView, HttpServletRequest request) {

        // Get list of users to manage
        List<UserModel> users = userService.listAllUsers();

        modelAndView.setViewName("superAdmin/superAdminManageUser");
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public ModelAndView superAdminNewUser(@ModelAttribute("user") UserModel userModel, ModelAndView modelAndView, HttpServletRequest request) {

        // Save new user
        userService.save(userModel);

        // Get list of users to manage
        List<UserModel> users = userService.listAllUsers();

        modelAndView.setViewName("superAdmin/superAdminManageUser");
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ModelAndView superAdminEditUser(@ModelAttribute("user") UserModel userModel, ModelAndView modelAndView, HttpServletRequest request) {

        UserModel user = userService.findById(formatUtils.getIntegerValue(request.getParameter("userToEditId")));

        if (user != null) {

            user.setName(userModel.getName());
            user.setEmail(userModel.getEmail());
            user.setUserType(userModel.getUserType());
            user.setAdmin(userModel.getAdmin());
            user.setUserStatus(userModel.getUserStatus());

            // Save new user
            userService.save(user);
        }

        // Get list of users to manage
        List<UserModel> users = userService.listAllUsers();

        modelAndView.setViewName("superAdmin/superAdminManageUser");
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("id") final int id, ModelAndView modelAndView, HttpServletRequest request) {

        UserModel user = userService.findById(id);
        userService.deleteUser(user);

        // Get list of users to manage
        List<UserModel> users = userService.listAllUsers();

        modelAndView.setViewName("superAdmin/superAdminManageUser");
        modelAndView.addObject("users", users);

        return modelAndView;
    }
}
