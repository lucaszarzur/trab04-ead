package br.com.utfpr.libraryfive.dao;

import br.com.utfpr.libraryfive.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface UserDao extends JpaRepository<UserModel, Long> {

    void delete(UserModel user);

    List<UserModel> findAll();

    UserModel findById(Integer id);

    UserModel findByEmail(String email);
}
