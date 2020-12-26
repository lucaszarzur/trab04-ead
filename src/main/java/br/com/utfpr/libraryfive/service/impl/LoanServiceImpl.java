package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.dao.LoanDao;
import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.populators.LoanPopulator;
import br.com.utfpr.libraryfive.service.CollectionCopyService;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.service.LoanService;
import br.com.utfpr.libraryfive.service.UserService;
import br.com.utfpr.libraryfive.util.DateUtils;
import br.com.utfpr.libraryfive.util.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service("loanService")
public class LoanServiceImpl implements LoanService {

    @Autowired
    private Session session;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private UserService userService;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private LoanPopulator loanPopulator;

    @Autowired
    private CollectionCopyService collectionCopyService;

    @Override
    public void makeLoan(Integer collectionId, Integer quantity) {

        // ID_USUARIO
        UserModel user = userService.findById(session.getCurrentUser().getId());

        // ID_EXEMPLAR
        CollectionCopyModel collectionCopy = collectionService.findById(collectionId).getCollectionCopyList().stream().filter(i -> i.getCollectionCopySituation().equals(CollectionCopyModel.CollectionCopySituation.Disponível)).findFirst().get();
        if (collectionCopy != null) {

            LoanModel loan = loanPopulator.populate(user, collectionCopy);

            loanDao.makeLoan(loan);
            collectionCopyService.editCollectionCopySituation(collectionCopy, CollectionCopyModel.CollectionCopySituation.Emprestado.toString()); // TODO - TESTAR
        }
        // retorna erro
    }

    @Override
    public LoanModel findById(Integer id) {
        return loanDao.findById(id);
    }

    @Override
    public List<LoanModel> listAll() {
        return loanDao.listAll();
    }

    @Override
    public List<LoanModel> listAllByEmail(String userEmail) {
        return loanDao.listAllByEmail(userEmail);
    }

    @Override
    public void renewLoan(LoanModel loanModel) {

        /* TODO:
        1 - Check if has reservations for the collection requested */

        if (!isLoanLate(loanModel)) {
            LocalDateTime newDateToReturn = dateUtils.calculateDateToReturn(7);

            loanModel.setExpectedReturnDate(dateUtils.convertLocalDateTimeToDate(newDateToReturn));

            loanDao.renewLoan(loanModel);
        } else {
            // error
        }
    }

    @Override
    public Boolean isLoanLate(LoanModel loanModel) {
        return dateUtils.getActualDate().isAfter(dateUtils.convertDate(loanModel.getExpectedReturnDate().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }
}
