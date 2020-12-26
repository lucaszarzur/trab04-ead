package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.dao.CollectionDao;
import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.populators.CollectionPopulator;
import br.com.utfpr.libraryfive.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private CollectionPopulator collectionPopulator;

    @Override
    public void createCollection(CollectionModel collection) {

        CollectionModel existingCollection = findByTitle(collection.getTitle());
        if (existingCollection.getId() == null) {
            collectionDao.createCollection(collection);
        } else {
            // return error
        }
    }

    @Override
    public void editCollection(CollectionModel collection) {
        collectionDao.editCollection(collection);
    }

    @Override
    public void deleteCollection(CollectionModel collection) {
        collectionDao.deleteCollection(collection);
    }

    @Override
    public List<CollectionModel> listAllCollections() {
        return collectionDao.listAllCollections();
    }

    @Override
    public List<CollectionModel> findAllAvailableCollection() {
        return collectionDao.findAllAvailableCollection();
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
        return collectionDao.isAvailable(collectionId, quantity);
    }

    @Override
    public CollectionModel getCollectionByRegisterForm(HttpServletRequest request, Boolean isNewCollection) {
        return collectionPopulator.populate(request, isNewCollection);
    }
}
