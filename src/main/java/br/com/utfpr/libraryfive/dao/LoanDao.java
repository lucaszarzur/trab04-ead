package br.com.utfpr.libraryfive.dao;

import br.com.utfpr.libraryfive.model.LoanModel;

import java.util.List;

public interface LoanDao {

    void makeLoan(LoanModel loan);

    LoanModel findById(Integer id);

    List<LoanModel> listAll();

    List<LoanModel> listAllByEmail(String userEmail);

    void renewLoan(LoanModel loanModel);
}
