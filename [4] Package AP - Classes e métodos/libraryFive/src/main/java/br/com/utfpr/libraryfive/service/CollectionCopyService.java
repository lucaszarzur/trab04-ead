package br.com.utfpr.libraryfive.service;


import br.com.utfpr.libraryfive.model.CollectionCopyModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CollectionCopyService {

    void createCollectionCopy(CollectionCopyModel collectionCopy);

    void editCollectionCopy(CollectionCopyModel collectionCopy);

    void deleteCollectionCopy(CollectionCopyModel collectionCopy);

    List<CollectionCopyModel> listAllCollectionCopy();

    CollectionCopyModel findById(Integer id);

    CollectionCopyModel getCollectionCopyByRegisterForm(HttpServletRequest request, Boolean isNewCollectionCopy);

    void editCollectionCopySituation(CollectionCopyModel collectionCopy, String situation);

    CollectionCopyModel.CollectionCopySituation getCollectionCopySituation(String parameter);
}
