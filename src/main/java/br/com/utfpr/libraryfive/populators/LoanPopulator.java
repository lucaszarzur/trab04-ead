package br.com.utfpr.libraryfive.populators;

import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LoanPopulator {

    @Autowired
    private DateUtils dateUtils;

    public LoanModel populate(UserModel user, CollectionCopyModel collectionCopy) {
        LocalDateTime actualDate = dateUtils.getActualDate();
        LocalDateTime dateToReturn = dateUtils.calculateDateToReturn(7); // By default, the customer can keep the book for only 7 days

        LoanModel loanModel = new LoanModel();
        loanModel.setUser(user);
        loanModel.setCollectionCopy(collectionCopy);
        loanModel.setLoanDate(dateUtils.convertLocalDateTimeToDate(actualDate));
        loanModel.setExpectedReturnDate(dateUtils.convertLocalDateTimeToDate(dateToReturn));

        return loanModel;
    }
}
