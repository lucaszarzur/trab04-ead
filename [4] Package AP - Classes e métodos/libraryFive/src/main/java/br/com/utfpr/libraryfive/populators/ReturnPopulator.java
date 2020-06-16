package br.com.utfpr.libraryfive.populators;

import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.model.ReturnModel;
import br.com.utfpr.libraryfive.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReturnPopulator {

    @Autowired
    private DateUtils dateUtils;

    public ReturnModel populate(LoanModel loan) {
        ReturnModel returnModel = new ReturnModel();
        returnModel.setLoan(loan);
        returnModel.setReturnDate(dateUtils.convertLocalDateTimeToDate(dateUtils.getActualDate()));

        return returnModel;
    }
}
