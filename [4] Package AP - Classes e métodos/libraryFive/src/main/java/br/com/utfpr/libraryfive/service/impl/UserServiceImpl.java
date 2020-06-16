package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.dao.UserDao;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.populators.UserPopulator;
import br.com.utfpr.libraryfive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPopulator userPopulator;

    @Override
    public void createUser(UserModel user) {
        userDao.createUser(user);
    }

    @Override
    public void editUser(UserModel user) {
        userDao.editUser(user);
    }

    @Override
    public void deleteUser(UserModel user) {
        userDao.deleteUser(user);
    }

    @Override
    public List<UserModel> listAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public UserModel findById(Integer id) {
        return userDao.findById(id);
    }

    public UserModel findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public UserModel doLogin(String email, String password) {
        return userDao.doLogin(email, password);
    }

    @Override
    public UserModel getUserByRegisterForm(HttpServletRequest request, Boolean isNewUser) {
        return userPopulator.populate(request, isNewUser);
    }
}
