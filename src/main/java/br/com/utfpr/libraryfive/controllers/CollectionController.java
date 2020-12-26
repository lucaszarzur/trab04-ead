package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.enums.ErrorMessagesTypeEnum;
import br.com.utfpr.libraryfive.model.*;
import br.com.utfpr.libraryfive.populators.CollectionModifiedPopulator;
import br.com.utfpr.libraryfive.service.AuthorService;
import br.com.utfpr.libraryfive.service.CollectionCopyService;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.util.ModifiedCollection;
import br.com.utfpr.libraryfive.util.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/collection")
public class CollectionController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(CollectionController.class);

    @Autowired
    private CollectionService collectionService;

    @Autowired
    CollectionModifiedPopulator collectionModifiedPopulator;

    @Autowired
    private CollectionCopyService collectionCopyService;

    @Autowired
    private Session session;

    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showCollection(HttpServletRequest request, ModelAndView modelAndView, HttpSession httpSession) {

        List<CollectionModel> availableCollection = collectionService.findAllAvailableCollection();

        List<ModifiedCollection> collections = collectionModifiedPopulator.populate(availableCollection, false);

        String errorInLoan = request.getSession().getAttribute("errorMessageLoan") != null ? request.getSession().getAttribute("errorMessageLoan").toString() : "";

        modelAndView.setViewName("collection/collectionList");
        modelAndView.addObject("collections", collections);
        modelAndView.addObject("user", session.getCurrentUser());
        modelAndView.addObject("errorInLoan", errorInLoan);

        session.removeSessionAttribute(httpSession, ErrorMessagesTypeEnum.ERROR_MESSAGE_LOAN.toString());

        LOG.info("Collections successfully retrieved!");

        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createCollection(final HttpServletRequest request) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            CollectionModel collection = collectionService.getCollectionByRegisterForm(request, true);

            if (collection != null) {
                collectionService.createCollection(collection);
                LOG.info("Collection has been created!");

                // create in DB and connect collection with author (AuthorCollection)
                authorService.createAuthorCollection(collection);
                LOG.info("Collection " + collection.getTitle() + " was related with Author " +
                        collection.getAuthorCollectionList().iterator().next().getAuthor().getName() +
                        " and successfully created!");

                return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
            }
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editCollection(final HttpServletRequest request) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            CollectionModel collection =  collectionService.getCollectionByRegisterForm(request, false);

            collectionService.editCollection(collection);

            LOG.info("Collection has been edited!");

            return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCollection(@RequestParam("id") final int id){

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            CollectionModel collection =  collectionService.findById(id);
            collectionService.deleteCollection(collection);

            LOG.info("Collection has been deleted in database!");

            return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/copy/new", method = RequestMethod.POST)
    public String createCollectionCopy(final HttpServletRequest request) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            CollectionCopyModel collectionCopy = collectionCopyService.getCollectionCopyByRegisterForm(request, true);

            if (collectionCopy != null) {
                collectionCopyService.createCollectionCopy(collectionCopy);

                LOG.info("Collection copy has been created!");

                return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
            }
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/copy/edit", method = RequestMethod.POST)
    public String editCollectionCopy(final HttpServletRequest request) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            CollectionCopyModel collectionCopy = collectionCopyService.getCollectionCopyByRegisterForm(request, false);

            collectionCopyService.editCollectionCopy(collectionCopy);

            LOG.info("Collection copy has been edited!");

            return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/copy/delete", method = RequestMethod.GET)
    public String deleteCollectionCopy(@RequestParam("id") final int id){

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            CollectionCopyModel collectionCopy = collectionCopyService.findById(id);
            collectionCopyService.deleteCollectionCopy(collectionCopy);

            LOG.info("Collection copy has been deleted in database!");

            return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
        }
        // retorna erro
        return null;
    }
}
