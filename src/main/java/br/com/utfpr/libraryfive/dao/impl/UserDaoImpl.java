package br.com.utfpr.libraryfive.dao.impl;

import br.com.utfpr.libraryfive.dao.UserDao;
import br.com.utfpr.libraryfive.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(UserModel user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(UserModel user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(UserModel user) {
        LOG.info("deleteUser started!");
        try {
            entityManager.remove(user);
            LOG.info("User successfully deleted in database!");
        } catch (NoResultException e) {
            LOG.info("User delete fail, because " + e.getMessage());
        }
    }

    @Override
    public List<UserModel> findAllUsers() {
        LOG.info("findAllUsers started!");
        List<UserModel> users;

        try {
            users = entityManager.createQuery("select u from UserModel u", UserModel.class).getResultList();
            LOG.info("Users found in database!");
        } catch (NoResultException e) {
            users = Arrays.asList();
        }
        return users;
    }

    @Override
    public UserModel findById(Integer id) {
        LOG.info("findById started!");

        List<UserModel> users = entityManager.createQuery("select u from UserModel u where u.id = :id", UserModel.class)
                                                                  .setParameter("id", id)
                                                                  .getResultList();

        if (users.isEmpty()) {
            LOG.info("The user " + id + " doesn't exist!");

            return new UserModel();
        }

        LOG.info("Success! User with name " + users.stream().findFirst().get().getName() + " found in database!");
        return users.stream().findFirst().orElse(null);
    }

    @Override
    public UserModel findByEmail(String email) {
        LOG.info("findByName started!");

        List<UserModel> users = entityManager.createQuery("select u from UserModel u where u.email = :email", UserModel.class)
                                                                  .setParameter("email", email)
                                                                  .getResultList();

        if (users.isEmpty()) {
            LOG.info("The user with " + email + " doesn't exist!");

            return new UserModel();
        }

        LOG.info("Success! User with name " + users.stream().findFirst().get().getName() + " found in database!");
        return users.stream().findFirst().orElse(null);
    }

    @Override
    public UserModel doLogin(String email, String password) {
        LOG.info("doLogin started!");

        List<UserModel> users = entityManager.createQuery("select u from UserModel u where u.email = :email and u.password = :password", UserModel.class)
                                                                  .setParameter("email", email)
                                                                  .setParameter("password", password)
                                                                  .getResultList();

        if (users.isEmpty()) {
            LOG.info("The user with " + email + " doesn't exist!");

            return new UserModel();
        }

        LOG.info("Success! User with name " + users.stream().findFirst().get().getName() + " found in database!");
        return users.stream().findFirst().orElse(null);
    }
}
