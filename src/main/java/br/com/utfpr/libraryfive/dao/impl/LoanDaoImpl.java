package br.com.utfpr.libraryfive.dao.impl;

import br.com.utfpr.libraryfive.dao.LoanDao;
import br.com.utfpr.libraryfive.model.LoanModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@Repository("loanDao")
public class LoanDaoImpl implements LoanDao {

    static final Logger LOG = LoggerFactory.getLogger(LoanDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void makeLoan(LoanModel loan) {
        entityManager.persist(loan);
    }

    @Override
    public LoanModel findById(Integer id) {
        LOG.info("findById started!");

        List<LoanModel> loans = entityManager.createQuery("select a from LoanModel a where a.id = :id", LoanModel.class)
                .setParameter("id", id)
                .getResultList();

        if (loans.isEmpty()) {
            LOG.info("The loan " + id + " doesn't exist!");

            return new LoanModel();
        }

        LOG.info("Success! Loan with id " + loans.stream().findFirst().get().getId() + " found in database!");
        return loans.stream().findFirst().orElse(null);
    }

    @Override
    public List<LoanModel> listAll() {
        LOG.info("listAll loans started!");
        List<LoanModel> loans;

        try {
            loans = entityManager. createQuery("select l from LoanModel l", LoanModel.class).getResultList();
            LOG.info("Loans found in database!");
        } catch (NoResultException e) {
            loans = Arrays.asList();
        }
        return loans;
    }

    @Override
    public List<LoanModel> listAllByEmail(String userEmail) {
        LOG.info("listAll loans ByEmail started!");
        List<LoanModel> loans;

        try {
            loans = entityManager. createQuery("select l from LoanModel l" +
                                                       " INNER JOIN l.user u" +
                                                       " WHERE u.email = :email AND l.returnModel.size = :size").
                                                       setParameter("email", userEmail).
                                                       setParameter("size", 0).
                                                       getResultList();
            LOG.info("Loans found in database!");
        } catch (NoResultException e) {
            loans = Arrays.asList();
        }
        return loans;
    }

    @Override
    public void renewLoan(LoanModel loanModel) {
        entityManager.merge(loanModel);
    }
}
