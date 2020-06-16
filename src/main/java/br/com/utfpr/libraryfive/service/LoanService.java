package br.com.utfpr.libraryfive.service;

import br.com.utfpr.libraryfive.model.LoanModel;

import java.util.List;

public interface LoanService {

    void makeLoan(Integer collectionId, Integer quantity);

    LoanModel findById(Integer id);

    List<LoanModel> listAll();

    List<LoanModel> listAllByEmail(String userEmail);

    void renewLoan(LoanModel loanModel);

    Boolean isLoanLate(LoanModel loanModel);
}
