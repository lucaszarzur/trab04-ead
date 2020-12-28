package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.dao.CollectionCustomDao;
import br.com.utfpr.libraryfive.dao.CollectionDao;
import br.com.utfpr.libraryfive.enums.ErrorMessagesTypeEnum;
import br.com.utfpr.libraryfive.exceptions.CollectionAlreadyExists;
import br.com.utfpr.libraryfive.model.AuthorCollectionModel;
import br.com.utfpr.libraryfive.model.AuthorModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.service.AuthorService;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.util.FormatUtils;
import br.com.utfpr.libraryfive.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {

    static final Logger LOG = LoggerFactory.getLogger(CollectionServiceImpl.class);

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private CollectionCustomDao collectionCustomDao;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private FormatUtils formatUtils;

    @Override
    public void createCollection(HttpServletRequest request, CollectionModel collectionModel) throws CollectionAlreadyExists {

        CollectionModel existingCollection = findByTitle(collectionModel.getTitle());
        if (existingCollection == null) {

            // set author by author name chosen by admin
            AuthorModel author = authorService.findByName(request.getParameter("author"));
            collectionModel.setAuthorCollectionList(getAuthor(author));

            collectionDao.save(collectionModel);

            LOG.info("Collection has been created!");

        } else {
            // Load properties file from class path
            ResourceBundle rb = ResourceBundle.getBundle("libraryFive", new Locale("pt"));
            String propertyValue = rb.getString(ErrorMessagesTypeEnum.ERROR_MESSAGE_COLLECTION_CREATE.toString());

            sessionUtils.createSessionErrorMessage(request, ErrorMessagesTypeEnum.ERROR_MESSAGE_COLLECTION_CREATE.toString(), propertyValue);

            LOG.error("Não foi possível criar obra, obra já existente!");

            throw new CollectionAlreadyExists("Não foi possível criar obra, obra já existente!");
        }
    }

    @Override
    public void editCollection(CollectionModel collectionModel, HttpServletRequest request) {
        // get collection to edit
        CollectionModel collection = collectionService.findById(formatUtils.getIntegerValue(request.getParameter("collectionToEditId")));

        // set author by author name chosen by admin
        AuthorModel author = authorService.findByName(request.getParameter("authorName"));
        collection.getAuthorCollectionList().iterator().next().setAuthor(author);

        collection.setTitle(collectionModel.getTitle());
        collection.setPublicationYear(collectionModel.getPublicationYear());
        collection.setCollectionType(collectionModel.getCollectionType());

        collectionDao.save(collection);
    }

    @Override
    public void deleteCollection(CollectionModel collection) {
        collectionDao.delete(collection);
    }

    @Override
    public List<CollectionModel> listAllCollections() {
        return collectionDao.findAll();
    }

    @Override
    public List<CollectionModel> findAllAvailableCollection() {
        return collectionCustomDao.findAllAvailableCollection();
    }

    @Override
    public CollectionModel findById(Integer id) {
        return collectionDao.findById(id);
    }

    @Override
    public CollectionModel findByTitle(String title) {
        return collectionDao.findByTitle(title);
    }

    @Override
    public boolean isAvailable(Integer collectionId, Integer quantity) {
        return collectionCustomDao.isAvailable(collectionId, quantity);
    }

    @Override
    public List<CollectionModel.CollectionType> listAllCollectionTypes() {
        return Arrays.asList(CollectionModel.CollectionType.Literatura, CollectionModel.CollectionType.Tese, CollectionModel.CollectionType.Outros);
    }

    private List<AuthorCollectionModel> getAuthor(AuthorModel author) {
        AuthorCollectionModel authorCollection = new AuthorCollectionModel();
        authorCollection.setAuthor(author);

        return Arrays.asList(authorCollection);
    }

    private CollectionModel.CollectionType getCollectionType(String parameter) {

        if (parameter.equals(CollectionModel.CollectionType.Literatura.toString())) {
            return CollectionModel.CollectionType.Literatura;
        } else if (parameter.equals(CollectionModel.CollectionType.Tese.toString())) {
            return CollectionModel.CollectionType.Tese;
        } else if (parameter.equals(CollectionModel.CollectionType.Outros.toString())) {
            return CollectionModel.CollectionType.Outros;
        }
        return null;
    }
}
