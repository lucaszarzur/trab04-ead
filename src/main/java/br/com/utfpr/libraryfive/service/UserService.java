package br.com.utfpr.libraryfive.service;

import br.com.utfpr.libraryfive.model.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserModel save(UserModel userModel);

    void deleteUser(UserModel user);

    List<UserModel> listAllUsers();

    UserModel findById(Integer id);

    UserModel findByEmail(String email);
}
