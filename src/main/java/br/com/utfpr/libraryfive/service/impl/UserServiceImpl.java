package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.dao.UserDao;
import br.com.utfpr.libraryfive.model.RoleModel;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserModel save(UserModel userModel) {

        return userDao.save(userModel);
    }

    @Override
    public void deleteUser(UserModel user) {
        userDao.delete(user);
    }

    @Override
    public List<UserModel> listAllUsers() {
        return userDao.findAll();
    }

    @Override
    public UserModel findById(Integer id) {
        return userDao.findById(id);
    }

    public UserModel findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userDao.findByEmail(username);
        if (userModel == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(userModel.getEmail(), userModel.getPassword(), mapRolesToAuthorities(userModel.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleModel> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
