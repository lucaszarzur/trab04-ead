package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.model.ReturnModel;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.service.LoanService;
import br.com.utfpr.libraryfive.service.ReturnService;
import br.com.utfpr.libraryfive.util.FormatUtils;
import br.com.utfpr.libraryfive.util.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


@Controller
@RequestMapping("/loan")
public class LoanController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private Session session;

    @Autowired
    private FormatUtils formatUtils;

    @Autowired
    private LoanService loanService;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private ReturnService returnService;

    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String newLoan(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Load properties file from class path
        ResourceBundle rb = ResourceBundle.getBundle("libraryFive", new Locale("pt"));
        String propertyValue = rb.getString("errorMessageLoan");

        Integer collectionId = formatUtils.getIntegerValue(request.getParameter("collectionId"));
        Integer collectionQty = formatUtils.getIntegerValue(request.getParameter("quantity"));

        if (collectionId != null && collectionService.isAvailable(collectionId, collectionQty)) {
            loanService.makeLoan(collectionId, collectionQty);
        } else {
            // if not OK (make loan), redirect to collections list with an error message
            request.getSession().setAttribute("errorMessageLoan", propertyValue);

            return REDIRECT_TO_COLLECTIONS_LIST;
        }
        LOG.info("Loan has been created!");

        // if OK (make loan), redirect to my loans
        return REDIRECT_TO_MY_LOANS;
    }

    @RequestMapping(value = "/myloans", method = RequestMethod.GET)
    public ModelAndView showMyLoans(HttpServletRequest request, ModelAndView modelAndView) {

        List<LoanModel> loans = loanService.listAllByEmail(session.getCurrentUser().getEmail());

        modelAndView.setViewName("loan/userLoan");
        modelAndView.addObject("loans", loans);
        modelAndView.addObject("userName", session.getCurrentUser().getName());
        modelAndView.addObject("baseUrl", session.getBaseUrl(request));

        LOG.info("My loans successfully retrieved!");

        return modelAndView;
    }

    @RequestMapping(value = {"/return"}, method = RequestMethod.GET)
    public String returnLoan(@RequestParam("id") final int id) {

        LoanModel loan = loanService.findById(id);
        returnService.makeReturn(loan);

        LOG.info("Return has been created!");

        return REDIRECT_TO_MY_LOANS;
    }

    @RequestMapping(value = "/myhistory", method = RequestMethod.GET)
    public ModelAndView myHistoryLoans(ModelAndView modelAndView) {

        UserModel currentUser = session.getCurrentUser();

        List<ReturnModel> returnedLoans = returnService.findAllReturnedLoansByEmail(currentUser.getEmail());

        modelAndView.setViewName("loan/loanHistory");
        modelAndView.addObject("returnedLoans", returnedLoans);
        modelAndView.addObject("userName", currentUser.getName());

        LOG.info("My history (loans) successfully retrieved!");

        return modelAndView;
    }

    @RequestMapping(value = "/renew", method = RequestMethod.GET)
    public String renewLoan(@RequestParam("id") final int id) {

        LoanModel loan = loanService.findById(id);
        if (loan != null) {
            loanService.renewLoan(loan);

            LOG.info("Loan renewal successfully created!");

            return REDIRECT_TO_MY_LOANS;
        }
        // error
        return null;
    }
}
