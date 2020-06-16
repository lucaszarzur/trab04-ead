package br.com.utfpr.libraryfive.dao.impl;

import br.com.utfpr.libraryfive.dao.CollectionDao;
import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@Repository("collectionDao")
@Transactional
public class CollectionDaoImpl implements CollectionDao {

    static final Logger LOG = LoggerFactory.getLogger(CollectionDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createCollection(CollectionModel collection) {
        entityManager.persist(collection);
    }

    @Override
    public void editCollection(CollectionModel collection) {
        entityManager.merge(collection);
    }

    @Override
    public void updateStatus(CollectionModel collection, String bookStatus) {

    }

    @Override
    public void deleteCollection(CollectionModel collection) {
        LOG.info("deleteCollection started!");
        try {
            entityManager.remove(collection);
            LOG.info("Collection successfully deleted in database!");
        } catch (NoResultException e) {
            LOG.info("Collection delete fail, because " + e.getMessage());
        }
    }

    @Override
    public void setActive(CollectionModel collection, Boolean active) {

    }

    @Override
    public List<CollectionModel> listAllCollections() {
        LOG.info("listAllCollections started!");
        List<CollectionModel> collections;

        try {
            collections = entityManager. createQuery("select c from CollectionModel c", CollectionModel.class).
                    getResultList();
            LOG.info("Collections found in database!");
        } catch (NoResultException e) {
            collections = Arrays.asList();
        }
        return collections;
    }

    @Override
    public List<CollectionModel> findAllAvailableCollection() {
        LOG.info("findAllAvailableCollection started!");
        List<CollectionModel> collections;

        try {
            collections = entityManager. createQuery("select c from CollectionModel c" +
                                                             " INNER JOIN c.collectionCopyList cp" +
                                                             " WHERE cp.collectionCopySituation = :collectionCopySituation").
                                                             setParameter("collectionCopySituation", CollectionCopyModel.CollectionCopySituation.Disponível).
                                                             getResultList();
            LOG.info("Collections found in database!");
        } catch (NoResultException e) {
            collections = Arrays.asList();
        }
        return collections;
    }

    @Override
    public CollectionModel findById(Integer id) {
        LOG.info("findById started!");

        List<CollectionModel> collections = entityManager.createQuery("select c from CollectionModel c where c.id = :id", CollectionModel.class)
                .setParameter("id", id)
                .getResultList();

        if (collections.isEmpty()) {
            LOG.info("The author " + id + " doesn't exist!");

            return new CollectionModel();
        }

        LOG.info("Success! Collection with ID " + collections.stream().findFirst().get().getId() + " found in database!");
        return collections.stream().findFirst().orElse(null);
    }

    @Override
    public CollectionModel findByTitle(String title) {
        LOG.info("findById started!");

        List<CollectionModel> collections = entityManager.createQuery("select c from CollectionModel c where c.title = :title", CollectionModel.class)
                .setParameter("title", title)
                .getResultList();

        if (collections.isEmpty()) {
            LOG.info("The collection " + title + " doesn't exist!");

            return new CollectionModel();
        }

        LOG.info("Success! Collection with title " + collections.stream().findFirst().get().getTitle() + " found in database!");
        return collections.stream().findFirst().orElse(null);
    }

    @Override
    public CollectionModel findByType(String type) {
        return new CollectionModel();
    }

    @Override
    public List<CollectionModel> showCollectionInfo() {
        return Arrays.asList(new CollectionModel());
    }

    @Override
    public boolean isAvailable(Integer collectionId, Integer quantity) {
        LOG.info("collection isAvailable started!");
        List<CollectionModel> collections;
        Boolean result = false;

        try {
            collections = entityManager. createQuery("select c from CollectionModel c" +
                                                             " INNER JOIN c.collectionCopyList cp" +
                                                             " WHERE cp.collectionCopySituation = :collectionCopySituation " +
                                                             " AND c.id = :collectionId").
                                                             setParameter("collectionCopySituation", CollectionCopyModel.CollectionCopySituation.Disponível).
                                                             setParameter("collectionId", collectionId).
                                                             getResultList();
            LOG.info("Collections found in database!");

            if (collections.size() >= quantity)
                result = true;

        } catch (NoResultException e) {
            LOG.info("Collection haven't been retrieved, because " + e.getMessage());
        }
        return result;
    }
}
