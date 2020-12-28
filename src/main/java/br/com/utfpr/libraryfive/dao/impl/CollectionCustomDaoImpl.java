package br.com.utfpr.libraryfive.dao.impl;

import br.com.utfpr.libraryfive.dao.CollectionCustomDao;
import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Arrays;
import java.util.List;

@Repository("collectionCustomDao")
@Transactional
public class CollectionCustomDaoImpl implements CollectionCustomDao {

    static final Logger LOG = LoggerFactory.getLogger(CollectionCustomDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

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
