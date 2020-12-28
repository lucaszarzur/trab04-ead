package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.dao.AuthorDao;
import br.com.utfpr.libraryfive.model.AuthorCollectionModel;
import br.com.utfpr.libraryfive.model.AuthorModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.populators.AuthorCollectionPopulator;
import br.com.utfpr.libraryfive.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

    static final Logger LOG = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private AuthorCollectionPopulator authorCollectionPopulator;

    @Override
    public List<AuthorModel> listAllAuthors() {
        return authorDao.listAllAuthors();
    }

    @Override
    public AuthorModel findById(Integer id) {
        return authorDao.findById(id);
    }

    @Override
    public AuthorModel findByName(String name) {
        return authorDao.findByName(name);
    }

    @Override
    public AuthorModel findAuthorNameByCollectionTitle(String title) {
        return authorDao.findAuthorNameByCollectionTitle(title);
    }

    @Override
    public void createAuthor(AuthorModel author) {
        authorDao.createAuthor(author);
    }

    @Override
    public void editAuthor(AuthorModel author) {
        authorDao.editAuthor(author);
    }

    @Override
    public void deleteAuthor(AuthorModel author) {
        authorDao.deleteAuthor(author);
    }

    @Override
    public void createAuthorCollection(CollectionModel collection) {
        AuthorCollectionModel authorCollection = authorCollectionPopulator.populate(collection);

        authorDao.createAuthorCollection(authorCollection);

        LOG.info("Collection " + collection.getTitle() + " was related with Author " +
                collection.getAuthorCollectionList().iterator().next().getAuthor().getName() +
                " and successfully created!");
    }
}
