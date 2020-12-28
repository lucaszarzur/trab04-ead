package br.com.utfpr.libraryfive.dao.impl;

import br.com.utfpr.libraryfive.dao.AuthorDao;
import br.com.utfpr.libraryfive.model.AuthorCollectionModel;
import br.com.utfpr.libraryfive.model.AuthorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Arrays;
import java.util.List;

@Repository("authorDao")
@Transactional
public class AuthorDaoImpl implements AuthorDao {

    static final Logger LOG = LoggerFactory.getLogger(AuthorDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<AuthorModel> listAllAuthors() {
        LOG.info("listAllAuthors started!");
        List<AuthorModel> users;

        try {
            users = entityManager.createQuery("select a from AuthorModel a", AuthorModel.class).getResultList();
            LOG.info("Authors found in database!");
        } catch (NoResultException e) {
            users = Arrays.asList();
        }
        return users;
    }

    @Override
    public AuthorModel findById(Integer id) {
        LOG.info("findById started!");

        List<AuthorModel> authors = entityManager.createQuery("select a from AuthorModel a where a.id = :id", AuthorModel.class)
                .setParameter("id", id)
                .getResultList();

        if (authors.isEmpty()) {
            LOG.info("The author " + id + " doesn't exist!");
            return new AuthorModel();
        }

        LOG.info("Success! Author with name " + authors.stream().findFirst().get().getName() + " found in database!");
        return authors.stream().findFirst().orElse(null);
    }

    @Override
    public AuthorModel findByName(String name) {
        LOG.info("findByName started!");

        List<AuthorModel> authors = entityManager.createQuery("select a from AuthorModel a where a.name = :name", AuthorModel.class)
                .setParameter("name", name)
                .getResultList();

        if (authors.isEmpty()) {
            LOG.info("The author " + name + " doesn't exist!");

            return new AuthorModel();
        }

        LOG.info("Success! Author with name " + authors.stream().findFirst().get().getName() + " found in database!");
        return authors.stream().findFirst().orElse(null);
    }

    @Override
    public AuthorModel findAuthorNameByCollectionTitle(String title) {
        LOG.info("findAuthorNameByCollectionTitle started!");

        List<AuthorModel> authors = entityManager.createQuery("select a from AuthorModel a " +
                                                                      " INNER JOIN a.authorCollectionList ac " +
                                                                      " where ac.collection.title = :title")
                                                                      .setParameter("title", title)
                                                                      .getResultList();

        if (authors.isEmpty()) {
            LOG.info("The author for this title doesn't exist!");

            return new AuthorModel();
        }

        LOG.info("Success! Author with name " + authors.stream().findFirst().get().getName() + " found in database!");
        return authors.stream().findFirst().orElse(null);
    }

    @Override
    public void createAuthor(AuthorModel author) {
        entityManager.persist(author);
    }

    @Override
    public void editAuthor(AuthorModel author) {
        entityManager.merge(author);
    }

    @Override
    public void deleteAuthor(AuthorModel author) {
        LOG.info("deleteAuthor started!");
        try {
            entityManager.remove(author);
            LOG.info("Author successfully deleted in database!");
        } catch (NoResultException e) {
            LOG.info("Author delete fail, because " + e.getMessage());
        }
    }

    @Override
    public void createAuthorCollection(AuthorCollectionModel authorCollection) {
        entityManager.persist(authorCollection);
    }
}
