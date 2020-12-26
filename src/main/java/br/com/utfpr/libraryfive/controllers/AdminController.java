package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.model.*;
import br.com.utfpr.libraryfive.populators.CollectionModifiedPopulator;
import br.com.utfpr.libraryfive.service.*;
import br.com.utfpr.libraryfive.util.ModifiedCollection;
import br.com.utfpr.libraryfive.util.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private Session session;

    @Autowired
    AuthorService authorService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    CollectionModifiedPopulator collectionModifiedPopulator;

    @Autowired
    CollectionCopyService collectionCopyService;

    @Autowired
    LoanService loanService;

    @Autowired
    UserService userService;


    @RequestMapping(value = {"/manage/authors"}, method = RequestMethod.GET)
    public ModelAndView manageAuthors(ModelAndView modelAndView, HttpServletRequest request) {

        List<AuthorModel> authors = authorService.listAllAuthors();

        modelAndView.setViewName("author/adminAuthor");
        modelAndView.addObject("authors", authors);
        modelAndView.addObject("userName", session.getCurrentUser().getName());
        modelAndView.addObject("baseUrl", session.getBaseUrl(request));

        LOG.info("Authors successfully retrieved!");

        return modelAndView;
    }

    @RequestMapping(value = {"/manage/collections"}, method = RequestMethod.GET)
    public ModelAndView manageCollections(ModelAndView modelAndView, HttpServletRequest request) {

        List<CollectionModel> allCollections = collectionService.listAllCollections();

        List<ModifiedCollection> collections = collectionModifiedPopulator.populate(allCollections, true);

        // Add a list of collection copy to display in second table in the same page
        modelAndView.addObject("collectionCopyList", collectionCopyService.listAllCollectionCopy());

        modelAndView.setViewName("collection/collectionListAdmin");
        modelAndView.addObject("collections", collections);
        modelAndView.addObject("authors", authorService.listAllAuthors());
        modelAndView.addObject("userName", session.getCurrentUser().getName());
        modelAndView.addObject("baseUrl", session.getBaseUrl(request));

        LOG.info("Collections successfully retrieved!");

        return modelAndView;
    }

    @RequestMapping(value = {"/manage/loans"}, method = RequestMethod.GET)
    public ModelAndView manageLoans(ModelAndView modelAndView) {

        List<LoanModel> allLoans = loanService.listAll();

        modelAndView.setViewName("loan/adminLoan");
        modelAndView.addObject("loans", allLoans);
        modelAndView.addObject("userName", session.getCurrentUser().getName());

        LOG.info("Loans successfully retrieved!");

        return modelAndView;
    }

    @RequestMapping(value = {"/manage/users"}, method = RequestMethod.GET)
    public ModelAndView manageUsers(ModelAndView modelAndView, HttpServletRequest request) {

        List<UserModel> users = userService.listAllUsers();

        modelAndView.setViewName("user/adminView");
        modelAndView.addObject("users", users);
        modelAndView.addObject("userName", session.getCurrentUser().getName());
        modelAndView.addObject("baseUrl", session.getBaseUrl(request));

        LOG.info("Users successfully retrieved!");

        return modelAndView;
    }
}
