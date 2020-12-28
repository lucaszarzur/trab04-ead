package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.enums.ErrorMessagesTypeEnum;
import br.com.utfpr.libraryfive.exceptions.CollectionAlreadyExists;
import br.com.utfpr.libraryfive.model.*;
import br.com.utfpr.libraryfive.populators.CollectionModifiedPopulator;
import br.com.utfpr.libraryfive.service.AuthorService;
import br.com.utfpr.libraryfive.service.CollectionCopyService;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.util.ModifiedCollection;
import br.com.utfpr.libraryfive.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private SessionUtils sessionUtils;

    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showCollection(HttpServletRequest request, ModelAndView modelAndView, HttpSession httpSession) {

        List<CollectionModel> availableCollection = collectionService.findAllAvailableCollection();

        List<ModifiedCollection> collections = collectionModifiedPopulator.populate(availableCollection, false);

        if (sessionUtils.hasErrorSessionByAttribute(request, ErrorMessagesTypeEnum.ERROR_MESSAGE_LOAN_CREATE.toString())) {
            String errorInLoanCreate = sessionUtils.getErrorSession(request, ErrorMessagesTypeEnum.ERROR_MESSAGE_LOAN_CREATE.toString());
            modelAndView.addObject("errorInLoanCreate", errorInLoanCreate);

            sessionUtils.removeSessionAttribute(httpSession, ErrorMessagesTypeEnum.ERROR_MESSAGE_LOAN_CREATE.toString());
        }

        modelAndView.setViewName("collection/collectionList");
        modelAndView.addObject("collections", collections);
        modelAndView.addObject("user", sessionUtils.getCurrentUser());

        LOG.info("Collections successfully retrieved!");

        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createCollection(@ModelAttribute("collection") CollectionModel collectionModel, final HttpServletRequest request) {

        Boolean isAdmin = sessionUtils.getCurrentUser().getAdmin();

        try {
            if (isAdmin) {
                collectionService.createCollection(request, collectionModel);

                // create in DB and connect collection with author (AuthorCollection)
                authorService.createAuthorCollection(collectionModel);
            }
        } catch (CollectionAlreadyExists e) {

            LOG.error("Error in create collection, because: " + e.getMessage());

        } catch (Exception e) {

            LOG.error("Error in create collection: " + e.getMessage());
        }

        return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editCollection(@ModelAttribute("collection") CollectionModel collectionModel, final HttpServletRequest request) {

        Boolean isAdmin = sessionUtils.getCurrentUser().getAdmin();

        if (isAdmin) {
            collectionService.editCollection(collectionModel, request);

            LOG.info("Collection has been edited!");

            return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCollection(@RequestParam("id") final int id){

        Boolean isAdmin = sessionUtils.getCurrentUser().getAdmin();

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
    public String createCollectionCopy(@ModelAttribute("collectionCopy") CollectionCopyModel collectionCopyModel) {

        Boolean isAdmin = sessionUtils.getCurrentUser().getAdmin();

        if (isAdmin) {
            collectionCopyService.createCollectionCopy(collectionCopyModel);

            LOG.info("Collection copy has been created!");

            return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/copy/edit", method = RequestMethod.POST)
    public String editCollectionCopy(@ModelAttribute("collectionCopy") CollectionCopyModel collectionCopyModel, final HttpServletRequest request) {

        Boolean isAdmin = sessionUtils.getCurrentUser().getAdmin();

        if (isAdmin) {
            collectionCopyService.editCollectionCopy(collectionCopyModel);

            LOG.info("Collection copy has been edited!");

            return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/copy/delete", method = RequestMethod.GET)
    public String deleteCollectionCopy(@RequestParam("id") final int id){

        Boolean isAdmin = sessionUtils.getCurrentUser().getAdmin();

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
