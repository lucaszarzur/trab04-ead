package br.com.utfpr.libraryfive.dao;

import br.com.utfpr.libraryfive.model.UserModel;

import java.util.List;

public interface UserDao {

    void createUser(UserModel user);

    void editUser(UserModel user);

    void deleteUser(UserModel user);

    List<UserModel> findAllUsers();

    UserModel findById(Integer id);

    UserModel findByEmail(String email);

    UserModel doLogin(String email, String password);
}
