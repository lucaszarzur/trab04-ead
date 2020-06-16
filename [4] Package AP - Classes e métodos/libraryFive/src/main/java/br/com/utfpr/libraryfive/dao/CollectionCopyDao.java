package br.com.utfpr.libraryfive.dao;

import br.com.utfpr.libraryfive.model.CollectionCopyModel;

import java.util.List;

public interface CollectionCopyDao {

    void createCollectionCopy(CollectionCopyModel collectionCopy);

    void editCollectionCopy(CollectionCopyModel collectionCopy);

    void deleteCollectionCopy(CollectionCopyModel collectionCopy);

    List<CollectionCopyModel> listAllCollectionCopy();

    CollectionCopyModel findById(Integer id);
}
