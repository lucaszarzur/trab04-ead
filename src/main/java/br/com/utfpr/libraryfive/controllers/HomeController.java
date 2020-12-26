package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.util.Session;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController extends AbstractController {

    static final Logger LOG = Logger.getLogger(HomeController.class);

    @Autowired
    Session session;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {

        modelAndView.setViewName("home/home");
        modelAndView.addObject("user", session.getCurrentUser());

        LOG.info("The user " + session.getCurrentUser().getName() + " has logged in and is being redirected to homepage!");

        return modelAndView;
    }
}
