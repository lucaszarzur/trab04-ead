package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.util.SessionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController extends AbstractController {

    static final Logger LOG = Logger.getLogger(HomeController.class);

    @Autowired
    SessionUtils sessionUtils;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {

        modelAndView.setViewName("home/home");
        modelAndView.addObject("user", sessionUtils.getCurrentUser());

        LOG.info("The user " + sessionUtils.getCurrentUser().getName() + " has logged in and is being redirected to homepage!");

        return modelAndView;
    }
}
