package br.com.utfpr.libraryfive.populators;

import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.service.CollectionCopyService;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.util.DateUtils;
import br.com.utfpr.libraryfive.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CollectionCopyPopulator {

    @Autowired
    private FormatUtils formatUtils;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionCopyService collectionCopyService;


    public CollectionCopyModel populate(HttpServletRequest request, Boolean isNewCollectionCopy) {
        CollectionCopyModel collectionCopyModel = new CollectionCopyModel();

        if (isNewCollectionCopy) {
            CollectionModel collection = collectionService.findById(formatUtils.getIntegerValue(request.getParameter("collectionId")));

            collectionCopyModel.setCollection(collection);
            collectionCopyModel.setAcquisitionDate(dateUtils.convertDate(request.getParameter("acquisitionDate")));
            collectionCopyModel.setCollectionCopySituation(collectionCopyService.getCollectionCopySituation(request.getParameter("collectionCopySituation")));
        } else {
            collectionCopyModel = collectionCopyService.findById(formatUtils.getIntegerValue(request.getParameter("collectionCopyToEditId")));

            collectionCopyModel.setAcquisitionDate(dateUtils.convertDate(request.getParameter("acquisitionDate")));
            collectionCopyModel.setCollectionCopySituation(collectionCopyService.getCollectionCopySituation(request.getParameter("collectionCopySituation")));
        }
        return collectionCopyModel;
    }
}
