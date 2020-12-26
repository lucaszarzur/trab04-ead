package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.dao.CollectionCopyDao;
import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import br.com.utfpr.libraryfive.populators.CollectionCopyPopulator;
import br.com.utfpr.libraryfive.service.CollectionCopyService;
import br.com.utfpr.libraryfive.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("collectionCopyService")
public class CollectionCopyServiceImpl implements CollectionCopyService {

    @Autowired
    DateUtils dateUtils;

    @Autowired
    private CollectionCopyDao collectionCopyDao;

    @Autowired
    CollectionCopyPopulator collectionCopyPopulator;

    @Override
    public void createCollectionCopy(CollectionCopyModel collectionCopy) {
        collectionCopyDao.createCollectionCopy(collectionCopy);
    }

    @Override
    public void editCollectionCopy(CollectionCopyModel collectionCopy) {
        collectionCopyDao.editCollectionCopy(collectionCopy);
    }

    @Override
    public void deleteCollectionCopy(CollectionCopyModel collectionCopy) {
        collectionCopyDao.deleteCollectionCopy(collectionCopy);
    }

    @Override
    public List<CollectionCopyModel> listAllCollectionCopy() {
        return collectionCopyDao.listAllCollectionCopy();
    }

    @Override
    public CollectionCopyModel findById(Integer id) {
        return collectionCopyDao.findById(id);
    }

    @Override
    public CollectionCopyModel getCollectionCopyByRegisterForm(HttpServletRequest request, Boolean isNewCollectionCopy) {
        return collectionCopyPopulator.populate(request, isNewCollectionCopy);
    }

    @Override
    public void editCollectionCopySituation(CollectionCopyModel collectionCopy, String situation) {
        collectionCopy.setCollectionCopySituation(getCollectionCopySituation(situation));
        editCollectionCopy(collectionCopy);
    }

    @Override
    public CollectionCopyModel.CollectionCopySituation getCollectionCopySituation(String parameter) {

        if (parameter.equals(CollectionCopyModel.CollectionCopySituation.Disponível.toString())) {
            return CollectionCopyModel.CollectionCopySituation.Disponível;
        } else if (parameter.equals(CollectionCopyModel.CollectionCopySituation.Devolvido.toString())) {
            return CollectionCopyModel.CollectionCopySituation.Devolvido;
        } else if (parameter.equals(CollectionCopyModel.CollectionCopySituation.Emprestado.toString())) {
            return CollectionCopyModel.CollectionCopySituation.Emprestado;
        } else if (parameter.equals(CollectionCopyModel.CollectionCopySituation.Extraviado.toString())) {
            return CollectionCopyModel.CollectionCopySituation.Extraviado;
        } else if (parameter.equals(CollectionCopyModel.CollectionCopySituation.Reservado.toString())) {
            return CollectionCopyModel.CollectionCopySituation.Reservado;
        }
        return null;
    }
}
