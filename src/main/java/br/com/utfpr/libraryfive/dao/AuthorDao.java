package br.com.utfpr.libraryfive.dao;

import br.com.utfpr.libraryfive.model.AuthorCollectionModel;
import br.com.utfpr.libraryfive.model.AuthorModel;

import java.util.List;

public interface AuthorDao {

    List<AuthorModel> listAllAuthors();

    AuthorModel findById(Integer id);

    AuthorModel findByName(String name);

    AuthorModel findAuthorNameByCollectionTitle(String title);

    void createAuthor(AuthorModel author);

    void editAuthor(AuthorModel author);

    void deleteAuthor(AuthorModel author);

    void createAuthorCollection(AuthorCollectionModel authorCollection);
}
