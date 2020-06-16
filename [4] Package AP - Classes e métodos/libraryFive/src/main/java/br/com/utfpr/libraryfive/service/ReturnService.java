package br.com.utfpr.libraryfive.service;

import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.model.ReturnModel;

import java.util.List;

public interface ReturnService {

    void makeReturn(LoanModel loan);

    List<ReturnModel> findAllReturnedLoansByEmail(String userEmail);
}
