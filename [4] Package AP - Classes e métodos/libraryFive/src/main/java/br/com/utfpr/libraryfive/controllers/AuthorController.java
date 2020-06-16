package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.model.AuthorModel;
import br.com.utfpr.libraryfive.populators.AuthorPopulator;
import br.com.utfpr.libraryfive.service.AuthorService;
import br.com.utfpr.libraryfive.util.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Transactional
@RequestMapping("/author")
public class AuthorController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private Session session;

    @Autowired
    private AuthorPopulator authorPopulator;

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newUser(final HttpServletRequest request) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            AuthorModel author = authorPopulator.populate(request);

            if (author != null) {
                authorService.createAuthor(author);

                LOG.info("Author has been created!");

                return REDIRECT_TO_ADMIN_VIEW_AUTHORS;
            }
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editAuthor(final HttpServletRequest request) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            AuthorModel author = authorService.findById(Integer.valueOf(request.getParameter("authorToEditId")));
            author.setName(request.getParameter("name"));

            authorService.editAuthor(author);

            LOG.info("Author has been edited!");

            return REDIRECT_TO_ADMIN_VIEW_AUTHORS;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteAuthor(@RequestParam("id") final int id) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            AuthorModel author = authorService.findById(id);
            authorService.deleteAuthor(author);

            LOG.info("Author has been deleted in database!");

            return REDIRECT_TO_ADMIN_VIEW_AUTHORS;
        }
        // retorna erro
        return null;
    }
}
