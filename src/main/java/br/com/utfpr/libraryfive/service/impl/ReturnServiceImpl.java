package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.dao.ReturnDao;
import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.model.ReturnModel;
import br.com.utfpr.libraryfive.populators.ReturnPopulator;
import br.com.utfpr.libraryfive.service.CollectionCopyService;
import br.com.utfpr.libraryfive.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("returnService")
@Transactional
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnDao returnDao;

    @Autowired
    private CollectionCopyService collectionCopyService;

    @Autowired
    private ReturnPopulator returnPopulator;

    @Override
    public void makeReturn(LoanModel loan) {
        ReturnModel returnModel = returnPopulator.populate(loan);

        returnDao.makeReturn(returnModel);
        collectionCopyService.editCollectionCopySituation(loan.getCollectionCopy(), CollectionCopyModel.CollectionCopySituation.Disponível.toString()); // TODO - Testar
    }

    @Override
    public List<ReturnModel> findAllReturnedLoansByEmail(String userEmail) {
        return returnDao.findAllReturnedLoansByEmail(userEmail);
    }
}
