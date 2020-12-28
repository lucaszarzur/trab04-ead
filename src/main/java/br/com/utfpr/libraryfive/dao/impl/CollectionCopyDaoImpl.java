package br.com.utfpr.libraryfive.dao.impl;

import br.com.utfpr.libraryfive.dao.CollectionCopyDao;
import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Arrays;
import java.util.List;

@Repository("collectionCopyDao")
@Transactional
public class CollectionCopyDaoImpl implements CollectionCopyDao {

    static final Logger LOG = LoggerFactory.getLogger(CollectionCopyDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createCollectionCopy(CollectionCopyModel collectionCopy) {
        entityManager.persist(collectionCopy);
    }

    @Override
    public void editCollectionCopy(CollectionCopyModel collectionCopy) {
        entityManager.merge(collectionCopy);
    }

    @Override
    public void deleteCollectionCopy(CollectionCopyModel collectionCopy) {
        LOG.info("deleteCollectionCopy started!");
        try {
            entityManager.remove(collectionCopy);
            LOG.info("Collection copy successfully deleted in database!");
        } catch (NoResultException e) {
            LOG.info("Collection copy delete fail, because " + e.getMessage());
        }
    }

    @Override
    public List<CollectionCopyModel> listAllCollectionCopy() {
        LOG.info("listAllCollectionCopy started!");
        List<CollectionCopyModel> collectionCopyList;

        try {
            collectionCopyList = entityManager. createQuery("select c from CollectionCopyModel c", CollectionCopyModel.class).
                    getResultList();
            LOG.info("Collection copies found in database!");
        } catch (NoResultException e) {
            collectionCopyList = Arrays.asList();
        }
        return collectionCopyList;
    }

    @Override
    public CollectionCopyModel findById(Integer id) {
        LOG.info("findById started!");

        List<CollectionCopyModel> collections = entityManager.createQuery("select cc from CollectionCopyModel cc where cc.id = :id", CollectionCopyModel.class)
                .setParameter("id", id)
                .getResultList();

        if (collections.isEmpty()) {
            LOG.info("The collection copy " + id + " doesn't exist!");

            return new CollectionCopyModel();
        }

        LOG.info("Success! Collection with ID " + collections.stream().findFirst().get().getId() + " found in database!");
        return collections.stream().findFirst().orElse(null);
    }
}
