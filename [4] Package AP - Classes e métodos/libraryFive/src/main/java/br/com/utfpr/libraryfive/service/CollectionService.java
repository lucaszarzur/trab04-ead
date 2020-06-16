package br.com.utfpr.libraryfive.service;

import br.com.utfpr.libraryfive.model.CollectionModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CollectionService {

    void createCollection(CollectionModel collection);

    void editCollection(CollectionModel collection);

    void deleteCollection(CollectionModel collection);

    List<CollectionModel> listAllCollections();

    List<CollectionModel> findAllAvailableCollection();

    CollectionModel findById(Integer id);

    CollectionModel findByTitle(String title);

    boolean isAvailable(Integer collectionId, Integer quantity);

    CollectionModel getCollectionByRegisterForm(HttpServletRequest request, Boolean isNewCollection);
}
