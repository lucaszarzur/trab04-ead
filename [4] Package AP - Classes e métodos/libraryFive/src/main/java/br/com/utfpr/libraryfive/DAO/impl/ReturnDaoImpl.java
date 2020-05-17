package br.com.utfpr.libraryfive.DAO.impl;

import br.com.utfpr.libraryfive.DAO.ReturnDao;
import br.com.utfpr.libraryfive.model.ReturnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("returnDao")
@Transactional
public class ReturnDaoImpl implements ReturnDao {

    static final Logger LOG = LoggerFactory.getLogger(ReturnDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void makeReturn(ReturnModel returnModel) {
        entityManager.persist(returnModel);
    }

    @Override
    public List<ReturnModel> findAllReturnedLoansByEmail(String userEmail) {
        LOG.info("findAll returned loans ByEmail started!");
        List<ReturnModel> returnedLoans;

        try {
            returnedLoans = entityManager. createQuery("select l from ReturnModel l" +
                    " INNER JOIN l.loan.user u" +
                    " WHERE u.email = :email").
                    setParameter("email", userEmail).
                    getResultList();
            LOG.info("Returned loans found!");
        } catch (NoResultException e) {
            returnedLoans = null;
        }
        return returnedLoans;
    }
}
