package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.dao.AuthorDao;
import br.com.utfpr.libraryfive.model.AuthorCollectionModel;
import br.com.utfpr.libraryfive.model.AuthorModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.populators.AuthorCollectionPopulator;
import br.com.utfpr.libraryfive.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("authorService")
@Transactional
public class AuthorServiceImpl implements AuthorService {

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
    }
}
