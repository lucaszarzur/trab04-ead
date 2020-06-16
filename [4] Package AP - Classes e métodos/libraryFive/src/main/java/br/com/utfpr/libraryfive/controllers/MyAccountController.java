package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.model.ReturnModel;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.service.LoanService;
import br.com.utfpr.libraryfive.service.ReturnService;
import br.com.utfpr.libraryfive.util.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/myAccount")
public class MyAccountController {

    @Autowired
    private Session session;

    @Autowired
    private LoanService loanService;

    @Autowired
    private ReturnService returnService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showMyAccountDetails(ModelAndView modelAndView) {

        UserModel user = session.getCurrentUser();

        List<ReturnModel> returnedLoans = returnService.findAllReturnedLoansByEmail(user.getEmail());
        List<LoanModel> activeLoans = loanService.listAllByEmail(user.getEmail());
        List<LoanModel> overdueLoans = new ArrayList<>();

        for (LoanModel loanModel : activeLoans) {
            if(loanService.isLoanLate(loanModel)) {
                overdueLoans.add(loanModel);
            }
        }

        modelAndView.setViewName("user/myAccount");
        modelAndView.addObject("user", user);
        modelAndView.addObject("allLoans", returnedLoans.size() + activeLoans.size());
        modelAndView.addObject("activeLoans", activeLoans.size());
        modelAndView.addObject("overdueLoans", overdueLoans.size());

        return modelAndView;
    }
}
