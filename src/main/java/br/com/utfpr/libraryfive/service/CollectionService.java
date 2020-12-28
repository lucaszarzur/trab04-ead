package br.com.utfpr.libraryfive.service;

import br.com.utfpr.libraryfive.exceptions.CollectionAlreadyExists;
import br.com.utfpr.libraryfive.model.CollectionModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CollectionService {

    void createCollection(HttpServletRequest request, CollectionModel collectionModel) throws CollectionAlreadyExists;

    void editCollection(CollectionModel collectionModel, HttpServletRequest request);

    void deleteCollection(CollectionModel collection);

    List<CollectionModel> listAllCollections();

    List<CollectionModel> findAllAvailableCollection();

    CollectionModel findById(Integer id);

    CollectionModel findByTitle(String title);

    boolean isAvailable(Integer collectionId, Integer quantity);

    List<CollectionModel.CollectionType> listAllCollectionTypes();
}
