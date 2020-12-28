package br.com.utfpr.libraryfive.dao;

import br.com.utfpr.libraryfive.model.CollectionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionDao extends JpaRepository<CollectionModel, Long> {

    void delete(CollectionModel collection);

    CollectionModel findById(Integer id);

    CollectionModel findByTitle(String title);
}
